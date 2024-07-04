package com.spldeolin.satisficing.identity.app.service;

import com.spldeolin.satisficing.identity.ac.javabean.req.IsLoginReqDto;
import com.spldeolin.satisficing.identity.ac.javabean.resp.IsLoginRespDto;
import com.spldeolin.satisficing.identity.app.javabean.req.LoginByCodeReqDto;
import com.spldeolin.satisficing.identity.app.javabean.req.LoginReqDto;
import com.spldeolin.satisficing.identity.app.javabean.resp.LoginByCodeRespDto;
import com.spldeolin.satisficing.identity.app.javabean.resp.LoginRespDto;

/**
 * @author Deolin 2024-05-31
 */
public interface SsoService {

    LoginRespDto login(LoginReqDto req);

    LoginByCodeRespDto loginByCode(LoginByCodeReqDto req);

    IsLoginRespDto isLogin(IsLoginReqDto req);

    void logout();

}
