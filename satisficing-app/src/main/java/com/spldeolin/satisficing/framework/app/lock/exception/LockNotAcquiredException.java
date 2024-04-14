package com.spldeolin.satisficing.framework.app.lock.exception;


import org.slf4j.MDC;

/**
 * 没有获取到锁
 *
 * @author Deolin 2021-09-27
 */
public class LockNotAcquiredException extends RuntimeException {

    private static final long serialVersionUID = 2312367844604932835L;

    public LockNotAcquiredException() {
        super(String.format("系统繁忙，请稍后重试（错误代码：%s）", MDC.get("traceId")));
    }

    public LockNotAcquiredException(String message) {
        super(message);
    }

}