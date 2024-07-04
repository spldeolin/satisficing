package com.spldeolin.satisficing.app.trace;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 请求轨迹过滤器
 *
 * @author Deolin 2018/12/06
 */
@Component
@Slf4j
public class HttpBodyReportFilter extends OncePerRequestFilter {

    @Autowired
    private HttpBodyReportFilterExclusion httpBodyReportFilterExclusion;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        // 过滤规则
        if (httpBodyReportFilterExclusion.isExcluded(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 请求到达时的报告
        log.info("requestArrived-{}-{}", request.getRequestURL(), MDC.get("traceId"));
        long start = System.currentTimeMillis();

        // 包装request和response
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            // 请求离开时的报告
            log.info("requestLeft-{} {}-{}-{}-{}\t{}\t{}", request.getMethod(), request.getRequestURL(),
                    MDC.get("traceId"), System.currentTimeMillis() - start,
                    request.getHeader(HttpHeaders.AUTHORIZATION), getRawRequestBody(wrappedRequest),
                    getRawResponseBody(wrappedResponse));
        }
    }

    private String getRawRequestBody(ContentCachingRequestWrapper wrappedRequest) {
        String contentType = wrappedRequest.getHeader(HttpHeaders.CONTENT_TYPE);
        if (contentType == null || !contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
            return "<REQUEST BODY IS NOT A JSON>";
        }
        try {
            // Response Body由客户端提供，所以编码从报文中获取
            String encoding = wrappedRequest.getCharacterEncoding();
            String result = IOUtils.toString(wrappedRequest.getContentAsByteArray(), StandardCharsets.UTF_8.toString());
            if (StringUtils.isEmpty(result)) {
                // 报文有body，但handler没有@RequestBody时，wrappedRequest.getInputStream()会有内容
                result = IOUtils.toString(wrappedRequest.getInputStream(), encoding);
            }
            return result;
        } catch (IOException e) {
            log.error("读取request body失败", e);
            return "";
        }
    }

    private String getRawResponseBody(ContentCachingResponseWrapper wrappedResponse) {

        try {
            wrappedResponse.copyBodyToResponse();
            String contentType = wrappedResponse.getHeader(HttpHeaders.CONTENT_TYPE);
            if (contentType != null && !contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
                return "<RESPONSE BODY NOT A JSON>";
            }
            String result = IOUtils.toString(wrappedResponse.getContentInputStream(), StandardCharsets.UTF_8);
            return result;
        } catch (IOException e) {
            log.error("读取response body失败", e);
            return "";
        }
    }

}
