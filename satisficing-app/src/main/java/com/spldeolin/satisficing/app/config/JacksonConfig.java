package com.spldeolin.satisficing.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spldeolin.satisficing.app.util.JsonUtils;

/**
 * @author Deolin 2024-04-03
 */
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return JsonUtils.createObjectMapper();
    }

}