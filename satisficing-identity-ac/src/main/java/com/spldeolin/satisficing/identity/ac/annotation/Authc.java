package com.spldeolin.satisficing.identity.ac.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.spldeolin.satisficing.identity.ac.enums.AuthcRule;

/**
 * 认证 Authentication
 *
 * @author Deolin 2024-05-31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authc {

    AuthcRule value() default AuthcRule.USER_LOGIN;

}
