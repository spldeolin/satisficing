package com.spldeolin.satisficing.app.webmvc;

import java.util.stream.Collectors;
import javax.servlet.ServletException;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.spldeolin.satisficing.api.ErrorCode;
import com.spldeolin.satisficing.api.RequestResult;
import com.spldeolin.satisficing.app.exception.BizException;
import lombok.extern.slf4j.Slf4j;

/**
 * 用于统一异常处理的ControllerAdvice
 *
 * @author Deolin 2023-04-13
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
@Slf4j
public class GlobalExceptionAdvice {

    /**
     * 非法请求：各种ServletException
     *
     * @see NoHandlerFoundException
     * @see HttpRequestMethodNotSupportedException
     * @see HttpMediaTypeNotSupportedException
     */
    @ExceptionHandler(ServletException.class)
    public RequestResult<?> handler(ServletException e) {
        log.warn("ServletException, message={}", e.getMessage());
        return RequestResult.failure(ErrorCode.ILLEGAL_REQUEST, "非法请求");
    }

    /**
     * 非法请求：请求Body格式错误
     * <pre>
     * 以下情况时，会被捕获：
     * alkdjfaldfjlalkajkdklf               可能是因为Body不是合法的JSON格式
     * (空)                                 JSON不存在
     * {"userAge"="notNumberValue"}         JSON中字段类型错误
     * </pre>
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public RequestResult<?> httpMessageNotReadable(HttpMessageNotReadableException e) {
        log.warn("请求Body非法，message={}", e.getMessage());
        return RequestResult.failure(ErrorCode.ILLEGAL_REQUEST, "非法请求");
    }

    /**
     * 非法请求：请求Body内字段没有通过注解校验（通过参数级@Valid 启用的参数校验）
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RequestResult<?> handle(MethodArgumentNotValidException e) {
        log.warn("请求Body参数校验未通过，invalids={}", e.getBindingResult().getFieldErrors().stream()
                .map(fe -> String.format("path=%s reason=%s value=%s", fe.getField(), fe.getDefaultMessage(),
                        fe.getRejectedValue())).collect(Collectors.joining(" | ")));
        return RequestResult.failure(ErrorCode.ILLEGAL_REQUEST, "非法请求");
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BizException.class)
    public RequestResult<?> handle(BizException e) {
        return RequestResult.failure(e.errorCode(), e.getMessage());
    }

    /**
     * 服务端异常
     */
    @ExceptionHandler(Throwable.class)
    public RequestResult<?> handle(Throwable e) {
        log.error("WEB请求在服务端发生异常", e);
        return RequestResult.failure(ErrorCode.SERVER_EXCEPTION,
                String.format("内部错误，请稍后重试（%s）", MDC.get("traceId")));
    }

}