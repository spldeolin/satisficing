package com.spldeolin.satisficing.identity.ac.filter;

import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.ServletRequestPathUtils;
import com.spldeolin.satisficing.api.RequestResult;
import com.spldeolin.satisficing.identity.ac.client.SsoClient;
import com.spldeolin.satisficing.identity.ac.enums.AuthcRule;
import com.spldeolin.satisficing.identity.ac.exception.UnauthcRequestException;
import com.spldeolin.satisficing.identity.ac.javabean.LoginSession;
import com.spldeolin.satisficing.identity.ac.javabean.req.IsLoginReqDto;
import com.spldeolin.satisficing.identity.ac.javabean.resp.IsLoginRespDto;
import com.spldeolin.satisficing.identity.ac.task.AuthcRulesCollector;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Deolin 2024-05-30
 */
@Slf4j
@Component
public class AuthcFilter extends OncePerRequestFilter {

    @Autowired
    private SsoClient ssoClient;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    private AuthcRulesCollector authcRulesCollector;

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 获取mvcHandler声明的认证规则
            HandlerMethod handlerMethod = getHandlerMethod(request);
            if (handlerMethod == null) {
                log.warn("No handlerMethod found for request, uri={}", request.getRequestURI());
                filterChain.doFilter(request, response);
                return;
            }
            List<AuthcRule> authcRules = authcRulesCollector.listAuthcRules(handlerMethod);

            // 部分系统handler 不解析token，不认证
            if (handlerMethod.getBeanType() == BasicErrorController.class) {
                filterChain.doFilter(request, response);
                return;
            }

            // 规则为none 不解析token，不认证
            if (authcRules.contains(AuthcRule.NONE)) {
                filterChain.doFilter(request, response);
                return;
            }

            // 解析token，存入上下文
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (token != null) {
                RequestResult<IsLoginRespDto> rpcResp = ssoClient.isLogin(new IsLoginReqDto().setToken(token));
                if (rpcResp.getErrorCode() == null) {
                    if (Boolean.TRUE.equals(rpcResp.getData().getIsLogin())) {
                        LoginSession.setCurrent(rpcResp.getData().getLoginSession());
                    }
                } else {
                    log.warn("fail to rpc ssoClient.isLogin");
                }
            } else {
                log.debug("no token found in request");
            }

            // 规则为anonymous 解析token，但不认证
            if (authcRules.contains(AuthcRule.ANONYMOUS)) {
                filterChain.doFilter(request, response);
                return;
            }

            // 规则是其他情况 解析token，进行认证
            if (LoginSession.getCurrent() == null) {
                handlerExceptionResolver.resolveException(request, response, null,
                        new UnauthcRequestException("用户未登录"));
                return;
            }
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
            return;
        }

        // 认证通过，继续执行
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
