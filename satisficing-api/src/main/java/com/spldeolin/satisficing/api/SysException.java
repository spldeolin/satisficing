package com.spldeolin.satisficing.api;

/**
 * 框架、第三方、中间间等抛出的系统异常
 *
 * @author Deolin 2025-01-27
 */
public class SysException extends BaseException {

    private static final long serialVersionUID = -7065133734158273038L;

    public SysException(String message) {
        super(message);
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysException(Throwable cause) {
        super(cause);
    }

}
