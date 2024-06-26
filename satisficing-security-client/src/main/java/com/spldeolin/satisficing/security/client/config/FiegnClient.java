package com.spldeolin.satisficing.security.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import com.spldeolin.satisficing.security.client.javabean.LoginSession;
import feign.RequestInterceptor;

/**
 * @author Deolin 2024-06-05
 */
@Configuration
public class FiegnClient {

    @Bean
    public RequestInterceptor transmitTokenRequestInterceptor() {
        return template -> {
            if (LoginSession.getCurrent() != null) {
                template.header(HttpHeaders.AUTHORIZATION, LoginSession.getCurrent().getToken());
            }
        };
    }

}
