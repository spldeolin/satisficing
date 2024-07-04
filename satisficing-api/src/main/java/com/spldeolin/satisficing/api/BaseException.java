package com.spldeolin.satisficing.api;

/**
 * satisficing中所有异常的基类
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 3064681644544038244L;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
