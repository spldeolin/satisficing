#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api;

import javax.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ${package}.api.dto.req.SampleReqDto;
import ${package}.api.dto.resp.SampleRespDto;
import com.spldeolin.satisficing.api.RequestResult;

/**
 * @author ${author} ${today}
 */
@FeignClient(value = "${parentArtifactId}")
public interface SampleClient {

    @PostMapping("/sampleMethod")
    RequestResult<SampleRespDto> sampleMethod(@RequestBody @Valid SampleReqDto req);

}
