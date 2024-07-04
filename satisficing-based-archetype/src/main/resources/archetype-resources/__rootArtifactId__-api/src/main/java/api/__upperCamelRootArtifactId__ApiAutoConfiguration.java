package ${package}.api;

import javax.annotation.PostConstruct;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Deolin 2024-06-07
 */
@Configuration
@ComponentScan("${package}.api")
@EnableFeignClients("${package}.api")
@Slf4j
public class ${upperCamelRootArtifactId}ApiAutoConfiguration {

    @PostConstruct
    public void postConstruct() {
        log.info("${artifactId} is auto configured");
    }

}
