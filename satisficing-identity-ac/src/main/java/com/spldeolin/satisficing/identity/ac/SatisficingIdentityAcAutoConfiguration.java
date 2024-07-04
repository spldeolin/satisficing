package com.spldeolin.satisficing.identity.ac;

import javax.annotation.PostConstruct;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Deolin 2024-06-02
 */
@Configuration
@ComponentScan("com.spldeolin.satisficing.identity.ac")
@EnableFeignClients("com.spldeolin.satisficing.identity.ac")
@Slf4j
public class SatisficingIdentityAcAutoConfiguration {

    @PostConstruct
    public void postConstruct() {
        log.info("satisficing-identity-ac is auto configured");
    }

}
