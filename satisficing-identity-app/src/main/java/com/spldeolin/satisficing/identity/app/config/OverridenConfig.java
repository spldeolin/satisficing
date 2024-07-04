package com.spldeolin.satisficing.identity.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.spldeolin.satisficing.api.RequestResult;
import com.spldeolin.satisficing.identity.ac.client.SsoClient;
import com.spldeolin.satisficing.identity.ac.javabean.req.IsLoginReqDto;
import com.spldeolin.satisficing.identity.ac.javabean.resp.IsLoginRespDto;
import com.spldeolin.satisficing.identity.app.service.SsoService;

/**
 * @author Deolin 2024-06-02
 */
@Configuration
public class OverridenConfig {

    @Autowired
    private SsoService ssoService;

    @Bean
    @Primary
    public SsoClient ssoClient() {
        return new SsoClient() {
            @Override
            public RequestResult<IsLoginRespDto> isLogin(IsLoginReqDto req) {
                return RequestResult.success(ssoService.isLogin(req));
            }
        };
    }

}
