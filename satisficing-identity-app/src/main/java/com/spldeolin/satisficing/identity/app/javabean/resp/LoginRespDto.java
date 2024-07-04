package com.spldeolin.satisficing.identity.app.javabean.resp;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author Deolin 2024-05-31
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRespDto {

    /**
     * 登录凭证
     */
    String token;

    /**
     * 用于换取token的临时code
     */
    String code;

}