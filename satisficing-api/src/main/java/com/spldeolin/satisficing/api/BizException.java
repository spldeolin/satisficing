package com.spldeolin.satisficing.api;

/**
 * 业务异常
 *
 * @author Deolin 2025-01-27
 */
public class BizException extends BaseException {

    private static final long serialVersionUID = 1486467565747939828L;

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

}
