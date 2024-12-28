package com.spldeolin.satisficing.identity.app.service;

import com.spldeolin.satisficing.identity.ac.dto.req.IsLoginReqDto;
import com.spldeolin.satisficing.identity.ac.dto.resp.IsLoginRespDto;
import com.spldeolin.satisficing.identity.app.dto.req.LoginByCodeReqDto;
import com.spldeolin.satisficing.identity.app.dto.req.LoginReqDto;
import com.spldeolin.satisficing.identity.app.dto.resp.LoginByCodeRespDto;
import com.spldeolin.satisficing.identity.app.dto.resp.LoginRespDto;

/**
 * @author Deolin 2024-05-31
 */
public interface SsoService {

    LoginRespDto login(LoginReqDto req);

    LoginByCodeRespDto loginByCode(LoginByCodeReqDto req);

    IsLoginRespDto isLogin(IsLoginReqDto req);

    void logout();

}
