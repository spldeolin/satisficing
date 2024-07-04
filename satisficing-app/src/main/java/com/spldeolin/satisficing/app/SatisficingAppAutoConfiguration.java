package com.spldeolin.satisficing.app;

import javax.annotation.PostConstruct;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Deolin 2024-06-02
 */
@Configuration
@ComponentScan("com.spldeolin.satisficing.app")
@Slf4j
public class SatisficingAppAutoConfiguration {

    @PostConstruct
    public void postConstruct() {
        log.info("satisficing-app is auto configured");
    }

}
