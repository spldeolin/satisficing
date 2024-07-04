package com.spldeolin.satisficing.identity.ac;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.ServletRequestPathUtils;
import com.spldeolin.satisficing.identity.ac.client.SsoClient;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Deolin 2024-07-03
 */
@Slf4j
@Component
public class AccessControlFilter extends OncePerRequestFilter {

    @Autowired
    private SsoClient ssoClient;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    private AccessControlCollector accessControlCollector;

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            HandlerMethod handlerMethod = this.getHandlerMethod(request);
            if (handlerMethod == null) {
                log.warn("No handlerMethod found for request, uri={}", request.getRequestURI());
                filterChain.doFilter(request, response);
                return;
            }

            AccessControl ac = accessControlCollector.getAccessControl(handlerMethod);

        } catch (Exception e) {
            // 交给统一异常处理
            handlerExceptionResolver.resolveException(request, response, null, e);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private HandlerMethod getHandlerMethod(HttpServletRequest request) {
        if (!ServletRequestPathUtils.hasParsedRequestPath(request)) {
            // see: https://github.com/spring-projects/spring-boot/issues/24877
            ServletRequestPathUtils.parseAndCache(request);
        }

        HandlerExecutionChain handlerExecutionChain;
        try {
            handlerExecutionChain = requestMappingHandlerMapping.getHandler(request);
        } catch (Exception e) {
            return null;
        }
        if (handlerExecutionChain == null) {
            return null;
        }
        Object handler = handlerExecutionChain.getHandler();
        if (handler instanceof HandlerMethod) {
            return (HandlerMethod) handler;
        }
        return null;
    }

}
