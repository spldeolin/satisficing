package com.spldeolin.satisficing.identity.app.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.spldeolin.satisficing.app.exception.BizException;
import com.spldeolin.satisficing.identity.ac.exception.UnauthcRequestException;
import com.spldeolin.satisficing.identity.ac.javabean.LoginSession;
import com.spldeolin.satisficing.identity.ac.javabean.req.IsLoginReqDto;
import com.spldeolin.satisficing.identity.ac.javabean.resp.IsLoginRespDto;
import com.spldeolin.satisficing.identity.app.entity.UserEntity;
import com.spldeolin.satisficing.identity.app.javabean.req.LoginByCodeReqDto;
import com.spldeolin.satisficing.identity.app.javabean.req.LoginReqDto;
import com.spldeolin.satisficing.identity.app.javabean.resp.LoginByCodeRespDto;
import com.spldeolin.satisficing.identity.app.javabean.resp.LoginRespDto;
import com.spldeolin.satisficing.identity.app.mapper.UserMapper;
import com.spldeolin.satisficing.identity.app.rsa.RSA;
import com.spldeolin.satisficing.identity.app.service.SsoService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Deolin 2024-05-31
 */
@Service
@Slf4j
public class SsoServiceImpl implements SsoService {

    private static final String codeForTokenKeyPrefix = "codeForToken:";

    private static final String loginSessionKeyPrefix = "loginSession:";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RSA rsa;

    @Autowired
    private RedissonClient redissonClient;

    @Value("${satisficing-indentity.loginExpirationSeconds:7200}")
    private Long loginExpirationSeconds;

    @Override
    public LoginRespDto login(LoginReqDto req) {
        // 获取用户
        UserEntity user = getUserByPrincipal(req.getPrincipal());
        if (user == null) {
            log.info("用户不存在");
            throw new BizException("登录信息或密码错误");
        }

        // 匹配密码
        if (!matchPassword(req.getPassword(), user)) {
            log.info("密码错误");
            throw new BizException("登录信息或密码错误");
        }

        // 创建loginSession
        String token = UUID.randomUUID().toString();
        LoginSession loginSession = new LoginSession();
        loginSession.setToken(token);
        loginSession.setLoginUserUuid(user.getUserUuid());
        loginSession.setLoginUserName(user.getUsername());

        // 缓存loginSession
        RBucket<LoginSession> bucket = redissonClient.getBucket(loginSessionKeyPrefix + token);
        bucket.set(loginSession, loginExpirationSeconds, TimeUnit.SECONDS);

        // 生成code
        String code = generateCode(req.getNeedCode(), token);

        return new LoginRespDto().setCode(code).setToken(token);
    }

    @Override
    public LoginByCodeRespDto loginByCode(LoginByCodeReqDto req) {
        RBucket<String> codeBucket = redissonClient.getBucket(codeForTokenKeyPrefix + req.getCode());
        String token = codeBucket.getAndDelete();
        if (token == null) {
            log.info("token absent, code={}", req.getCode());
            throw new UnauthcRequestException("登录已失效，请重新登录");
        }
        LoginSession loginSession = this.parseToken(token);
        if (loginSession == null) {
            log.info("loginSession absent, code={}", req.getCode());
            throw new UnauthcRequestException("登录已失效，请重新登录");
        }
        return new LoginByCodeRespDto().setToken(token);
    }

    @Override
    public IsLoginRespDto isLogin(IsLoginReqDto req) {
        LoginSession loginSession = this.parseToken(req.getToken());
        String code = this.generateCode(req.getNeedCode(), req.getToken());
        return new IsLoginRespDto().setIsLogin(loginSession != null).setCode(code).setLoginSession(loginSession);
    }

    @Override
    public void logout() {
        if (LoginSession.getCurrent() != null) {
            String token = LoginSession.getCurrent().getToken();
            redissonClient.getKeys().delete(loginSessionKeyPrefix + token);
        }
    }

    private UserEntity getUserByPrincipal(String principal) {
        return userMapper.getByUsernameOrMobile(principal, principal);
    }

    private boolean matchPassword(String password, UserEntity user) {
        password = rsa.decrypt(password);
        return new BCryptPasswordEncoder().matches(password, user.getPassword());
    }

    private LoginSession parseToken(String token) {
        if (token == null) {
            return null;
        }
        RBucket<LoginSession> bucket = redissonClient.getBucket(loginSessionKeyPrefix + token);
        LoginSession loginSession = bucket.get();
        return loginSession;
    }

    private String generateCode(Boolean needCode, String token) {
        String code = null;
        if (needCode != null && needCode) {
            code = UUID.randomUUID().toString();
            redissonClient.getBucket(codeForTokenKeyPrefix + code).set(token, 10, TimeUnit.MINUTES);
        }
        return code;
    }

}
