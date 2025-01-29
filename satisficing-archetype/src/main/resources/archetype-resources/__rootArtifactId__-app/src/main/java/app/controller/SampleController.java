package com.your.group.app.controller;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.spldeolin.satisficing.api.RequestResult;
import ${package}.api.dto.req.SampleReqDto;
import ${package}.api.dto.resp.SampleRespDto;

/**
 * @author ${author} ${today}
 */
@RestController
@Slf4j
public class SampleController {

    @PostMapping("sampleMethod")
    public RequestResult<SampleRespDto> sampleMethod(@RequestBody @Valid SampleReqDto req) {
        SampleRespDto retval = new SampleRespDto();
        retval.setGreeting("Hello, " + req.getName());
        return RequestResult.success(retval);
    }

}