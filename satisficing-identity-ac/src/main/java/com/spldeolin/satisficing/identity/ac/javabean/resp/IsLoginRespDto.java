package com.spldeolin.satisficing.identity.ac.javabean.resp;

import com.spldeolin.satisficing.identity.ac.javabean.LoginSession;
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
public class IsLoginRespDto {

    /**
     * 是否已登录
     */
    Boolean isLogin;

    /**
     * 登录态
     */
    LoginSession loginSession;

    /**
     * 若指定需要生成code，则返回用于换取token的code
     */
    String code;

}