package com.spldeolin.satisficing.identity.app.dto.req;

import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author Deolin 2024-05-30
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginReqDto {

    /**
     * 用户名或手机号
     */
    @NotNull
    String principal;

    /**
     * 手机号
     */
    @NotNull
    String password;

    /**
     * 登录成功后，是否需要返回一个用于其他域换取token的code
     */
    Boolean needCode;

}