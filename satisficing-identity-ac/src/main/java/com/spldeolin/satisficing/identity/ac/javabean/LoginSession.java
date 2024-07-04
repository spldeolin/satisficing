package com.spldeolin.satisficing.identity.ac.javabean;

import java.io.Serializable;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

/**
 * 登录态
 *
 * @author Deolin 2024-05-30
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class LoginSession implements Serializable {

    private static final long serialVersionUID = -2292448057871637479L;

    private static final ThreadLocal<LoginSession> ctx = new TransmittableThreadLocal<>();

    /**
     * 登录凭证
     */
    String token;

    /**
     * 登录者的用户UUID
     */
    String loginUserUuid;

    /**
     * 登录者的用户名
     */
    String loginUserName;

    public static void setCurrent(LoginSession loginSession) {
        Preconditions.checkNotNull(loginSession);
        ctx.set(loginSession);
    }

    public static LoginSession getCurrent() {
        LoginSession result = ctx.get();
        return result;
    }

}