package com.spldeolin.satisficing.app.exception;

import com.spldeolin.satisficing.api.BaseException;

/**
 * 这是用于对IOException等Checked-Excpetion在进行wrap处理的异常，
 * 使后者能够作为RuntimeException抛出，简化调用方的catch处理
 *
 * @author Deolin 2023-04-12
 */
public final class UncheckedException extends BaseException {

    private static final long serialVersionUID = 2808466052164900942L;

    public UncheckedException(Throwable cause) {
        super(cause);
    }

}