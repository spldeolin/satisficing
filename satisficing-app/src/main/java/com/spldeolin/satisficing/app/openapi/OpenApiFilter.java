package com.spldeolin.satisficing.app.openapi;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Deolin 2025-08-02
 */
@WebFilter(filterName = "openApiFilter", urlPatterns = "/" + OpenApiConstant.OPEN_API_URL_PREFIX + "/*")
@Component
@Slf4j
public class OpenApiFilter extends OncePerRequestFilter {

    @Autowired
    private OpenApiCredentialService openApiCredentialService;

    @Value("${openapi.maxOffsetSeconds}")
    private Long maxOffsetSeconds;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 获取AK
        String ak = request.getHeader(OpenApiConstant.ACCESS_KEY_HEADER_NAME);
        if (StringUtils.isBlank(ak)) {
            log.warn("ak is absent");
            throw new OpenApiAuthcException("Fail to authenticate open api");
        }

        // 获取时间戳，校验时间有效窗口
        long clientTimestamp = Integer.parseInt(request.getHeader(OpenApiConstant.TIMESTAMP_HEADER_NAME));
        long serverTimestamp = Instant.now().getEpochSecond();
        if (Math.abs(serverTimestamp - clientTimestamp) > maxOffsetSeconds) {
            log.warn("timestamp is invalid, clientTimestamp={}, serverTimestamp={} maxOffsetSeconds={}",
                    clientTimestamp, serverTimestamp, maxOffsetSeconds);
            throw new OpenApiAuthcException("Fail to authenticate open api");
        }

        // 获取nonce，TODO 防重放校验
        String nonce = request.getHeader(OpenApiConstant.NONCE_HEADER_NAME);
        if (StringUtils.isBlank(nonce)) {
            log.warn("nonce is absent");
            throw new OpenApiAuthcException("Fail to authenticate open api");
        }

        // 根据ak获取sk
        String sk = openApiCredentialService.getSecretKeyByAccessKey(ak);
        if (StringUtils.isBlank(sk)) {
            log.warn("cannot find sk by ak, ak={}", ak);
            throw new OpenApiAuthcException("Fail to authenticate open api");
        }

        // 获取客户端签名
        String clientSignture = request.getHeader(OpenApiConstant.SIGNATURE_HEADER_NAME);
        if (StringUtils.isBlank(clientSignture)) {
            log.warn("signature is absent");
            throw new OpenApiAuthcException("Fail to authenticate open api");
        }

        // 计算服务端签名
        String dataForVerification = String.format("%s-%s-%s-%s-%s", OpenApiConstant.VERIFICATION_MAGIC_NUMBER, ak,
                clientTimestamp, nonce, sk);
        HashCode hashCode = Hashing.hmacSha256(sk.getBytes()).hashString(dataForVerification, StandardCharsets.UTF_8);
        String serverSignture = Base64.getEncoder().encodeToString(hashCode.asBytes());

        // AK上下文

        if (!serverSignture.equals(clientSignture)) {
            log.warn("signature is invalid, dataForVerification={} clientSignture={}, serverSignture={}",
                    dataForVerification, clientSignture, serverSignture);
            throw new OpenApiAuthcException("Fail to authenticate open api");
        }

        filterChain.doFilter(request, response);
    }

}
