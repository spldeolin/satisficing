package com.spldeolin.satisficing.identity.ac.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import com.spldeolin.satisficing.api.RequestResult;
import com.spldeolin.satisficing.identity.ac.dto.req.IsLoginReqDto;
import com.spldeolin.satisficing.identity.ac.dto.resp.IsLoginRespDto;

/**
 * @author Deolin 2024-06-02
 */
@FeignClient(name = "satisficing-identity-app", url = "${rpc-url.satisficing-identity-app}", primary = false)
public interface SsoClient {

    @PostMapping("/isLogin")
    RequestResult<IsLoginRespDto> isLogin(IsLoginReqDto req);

}
