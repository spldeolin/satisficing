package com.spldeolin.satisficing.identity.ac.webmvc;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.spldeolin.satisficing.api.RequestResult;
import com.spldeolin.satisficing.identity.ac.enums.IdentityErrorCode;
import com.spldeolin.satisficing.identity.ac.exception.UnauthcRequestException;
import lombok.extern.slf4j.Slf4j;

/**
 * 用于统一异常处理的ControllerAdvice
 *
 * @author Deolin 2023-04-13
 */
@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class IdentityExceptionAdvice {

    /**
     * 未认证的请求
     */
    @ExceptionHandler(UnauthcRequestException.class)
    public RequestResult<?> handler(UnauthcRequestException e) {
        return RequestResult.failure(IdentityErrorCode.NO_AUTHC, e.getMessage());
    }

}