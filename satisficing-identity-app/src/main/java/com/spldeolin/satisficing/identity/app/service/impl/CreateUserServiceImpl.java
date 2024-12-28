package com.spldeolin.satisficing.identity.app.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spldeolin.satisficing.app.exception.BizException;
import com.spldeolin.satisficing.app.id.SnowFlake;
import com.spldeolin.satisficing.identity.ac.dto.LoginSession;
import com.spldeolin.satisficing.identity.app.dto.req.CreateUserReqDto;
import com.spldeolin.satisficing.identity.app.entity.UserEntity;
import com.spldeolin.satisficing.identity.app.mapper.UserMapper;
import com.spldeolin.satisficing.identity.app.service.CreateUserService;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Allison 1875 Lot No: HT1001S-D3209C95
 *
 * @author Deolin
 */
@Service
@Slf4j
public class CreateUserServiceImpl implements CreateUserService {

    public static final StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

    private static final String defaultPassword = "123456";

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public void createUser(CreateUserReqDto req) {
        UserEntity duplication = userMapper.queryUserEx2(null, req.getUsername(), req.getMobile());
        if (duplication != null) {
            if (duplication.getUsername().equals(req.getUsername())) {
                throw new BizException("用户名已存在");
            }
            if (duplication.getMobile().equals(req.getMobile())) {
                throw new BizException("手机号已存在");
            }
        }

        UserEntity user = new UserEntity();
        user.setId(snowFlake.nextId());
        user.setUserUuid(UUID.randomUUID().toString());
        user.setUsername(req.getUsername());
        user.setMobile(req.getMobile());
        user.setPassword(passwordEncryptor.encryptPassword(defaultPassword));
        user.setNickName(req.getNickName());
        user.setCreateUserUuid(LoginSession.getCurrent().getLoginUserUuid());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateUserUuid(LoginSession.getCurrent().getLoginUserUuid());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

}
