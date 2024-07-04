package com.spldeolin.satisficing.identity.app.javabean.resp;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author Deolin 2024-06-02
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginByCodeRespDto {

    /**
     * 登录凭证
     */
    String token;

}
