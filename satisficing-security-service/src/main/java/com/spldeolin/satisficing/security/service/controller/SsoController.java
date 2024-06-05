package com.spldeolin.satisficing.security.service.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.spldeolin.satisficing.client.javabean.RequestResult;
import com.spldeolin.satisficing.security.client.annotation.Authc;
import com.spldeolin.satisficing.security.client.enums.AuthcRule;
import com.spldeolin.satisficing.security.client.javabean.req.IsLoginReqDto;
import com.spldeolin.satisficing.security.client.javabean.resp.IsLoginRespDto;
import com.spldeolin.satisficing.security.service.javabean.req.LoginByCodeReqDto;
import com.spldeolin.satisficing.security.service.javabean.req.LoginReqDto;
import com.spldeolin.satisficing.security.service.javabean.resp.LoginByCodeRespDto;
import com.spldeolin.satisficing.security.service.javabean.resp.LoginRespDto;
import com.spldeolin.satisficing.security.service.service.SsoService;

/**
 * @author Deolin 2024-05-30
 */
@RestController
public class SsoController {

    @Autowired
    private SsoService loginService;

    /**
     * 判断是否已登录，也可用于检查token是否有效（该API不对header中的token进行验证）
     */
    @PostMapping("/isLogin")
    @Authc(AuthcRule.NONE)
    public RequestResult<IsLoginRespDto> isLogin(@RequestBody @Valid IsLoginReqDto req) {
        return RequestResult.success(loginService.isLogin(req));
    }

    /**
     * 密码登录
     */
    @PostMapping("/login")
    @Authc(AuthcRule.NONE)
    public RequestResult<LoginRespDto> login(@RequestBody @Valid LoginReqDto req) {
        return RequestResult.success(loginService.login(req));
    }

    /**
     * code登录（通过code换取token）
     */
    @PostMapping("/loginByCode")
    @Authc(AuthcRule.NONE)
    public RequestResult<LoginByCodeRespDto> loginByCode(@RequestBody @Valid LoginByCodeReqDto req) {
        return RequestResult.success(loginService.loginByCode(req));
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    @Authc(AuthcRule.ANONYMOUS)
    public RequestResult<Void> logout() {
        loginService.logout();
        return RequestResult.success();
    }

}
