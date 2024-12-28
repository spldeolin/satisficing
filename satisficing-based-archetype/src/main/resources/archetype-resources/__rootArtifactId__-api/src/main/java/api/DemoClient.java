#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api;

import javax.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ${package}.api.dto.req.DemoReqDto;
import ${package}.api.dto.resp.DemoRespDto;
import com.spldeolin.satisficing.api.RequestResult;

/**
 * @author Deolin 2023-04-09
 */
@FeignClient(value = "${parentArtifactId}-app")
public interface DemoClient {

    @PostMapping("/demoMethod")
    RequestResult<DemoRespDto> demoMethod(@RequestBody @Valid DemoReqDto req);

}
