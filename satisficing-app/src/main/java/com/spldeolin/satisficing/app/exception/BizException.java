package com.spldeolin.satisficing.app.exception;

import com.spldeolin.satisficing.api.BaseException;
import com.spldeolin.satisficing.api.ErrorCode;

/**
 * 业务异常
 *
 * @author Deolin 2023-04-13
 */
public class BizException extends BaseException {

    private static final long serialVersionUID = -4104806330438981374L;

    private final ErrorCode errorCode;

    private final String errorMsg;

    public BizException(String message) {
        super(message);
        this.errorCode = ErrorCode.GENERAL_BIZ_EXCEPTION;
        this.errorMsg = message;
    }

    public BizException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public final ErrorCode errorCode() {
        return errorCode;
    }

    public final String getErrorMsg() {
        return errorMsg;
    }

}