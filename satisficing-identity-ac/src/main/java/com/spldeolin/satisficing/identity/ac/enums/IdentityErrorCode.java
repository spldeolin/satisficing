package com.spldeolin.satisficing.identity.ac.enums;

import com.spldeolin.satisficing.api.ErrorCode;
import lombok.AllArgsConstructor;

/**
 * @author Deolin 2024-06-08
 */
@AllArgsConstructor
public enum IdentityErrorCode implements ErrorCode {

    NO_AUTHC("403", null),

    ;

    private final String code;

    private final String defaultMsg;

    @Override
    public String code() {
        return code;
    }

    @Override
    public String defaultMsg() {
        return defaultMsg;
    }
}
