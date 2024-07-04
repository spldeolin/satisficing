package com.spldeolin.satisficing.identity.ac;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Deolin 2024-06-20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessControl {

    /**
     * 是否完全跳过认证和访问控制。这种场景获取不到登录态
     */
    boolean skipAuthc() default false;

    /**
     * 是否需要登录才能访问
     */
    boolean isLoginRequired() default true;

    /**
     * 需要拥有这个权限才能访问
     */
    String hasPermission();

    /**
     * 需要拥有所有这些权限才能访问
     */
    String[] hasAllPermssion();

    /**
     * 需要拥有任一这些权限才能访问
     */
    String[] hasAnyPermssion();

    /**
     * 需要拥有这个角色才能访问
     */
    String hasRole();

    /**
     * 需要拥有所有这些角色才能访问
     */
    String[] hasAllRole();

    /**
     * 需要拥有任一这些角色才能访问
     */
    String[] hasAnyRole();

}
