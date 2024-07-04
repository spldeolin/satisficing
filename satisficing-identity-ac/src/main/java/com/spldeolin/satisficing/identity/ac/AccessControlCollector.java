package com.spldeolin.satisficing.identity.ac;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Deolin 2024-07-03
 */
@Slf4j
@Component
public class AccessControlCollector implements ApplicationRunner {

    private static final Map<Method, AccessControl> acs = new HashMap<>();

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void run(ApplicationArguments args) {
        requestMappingHandlerMapping.getHandlerMethods().forEach((requestMappingInfo, handlerMethod) -> {
            Method method = handlerMethod.getMethod();
            AccessControl ac = AnnotationUtils.findAnnotation(method, AccessControl.class);
            if (ac != null) {
                acs.put(method, ac);
            }
            log.info("handlerMethod={}", handlerMethod);
        });
    }

    public AccessControl getAccessControl(HandlerMethod handlerMethod) {
        return acs.get(handlerMethod.getMethod());
    }

}
