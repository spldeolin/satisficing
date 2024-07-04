package com.spldeolin.satisficing.identity.app;

import javax.annotation.PostConstruct;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Deolin 2024-06-02
 */
@Configuration
@ComponentScan("com.spldeolin.satisficing.identity.app")
@MapperScan("com.spldeolin.satisficing.identity.app.mapper")
@Slf4j
public class SatisficingIdentityAppAutoConfiguration {

    @PostConstruct
    public void postConstruct() {
        log.info("satisficing-identity-app is auto configured");
    }

}
