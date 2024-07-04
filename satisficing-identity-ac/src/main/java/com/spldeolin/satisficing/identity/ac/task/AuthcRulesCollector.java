package com.spldeolin.satisficing.identity.ac.task;

import java.lang.reflect.Method;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.spldeolin.satisficing.identity.ac.annotation.Authc;
import com.spldeolin.satisficing.identity.ac.enums.AuthcRule;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Deolin 2024-05-31
 */
@Slf4j
@Component
public class AuthcRulesCollector implements ApplicationRunner {

    private static final ArrayListMultimap<Method, AuthcRule> rules = ArrayListMultimap.create();

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void run(ApplicationArguments args) {
        requestMappingHandlerMapping.getHandlerMethods().forEach((requestMappingInfo, handlerMethod) -> {
            Method method = handlerMethod.getMethod();
            Authc authc = AnnotationUtils.findAnnotation(method, Authc.class);
            if (authc == null) {
                rules.putAll(method, Lists.newArrayList());
            } else {
                rules.putAll(method, Lists.newArrayList(authc.value()));
            }
            log.info("handlerMethod={}", handlerMethod);
        });
        log.info("authcRuels={}", rules);
    }

    public List<AuthcRule> listAuthcRules(HandlerMethod handlerMethod) {
        return rules.get(handlerMethod.getMethod());
    }

}
