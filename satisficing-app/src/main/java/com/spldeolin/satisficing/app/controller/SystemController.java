package com.spldeolin.satisficing.app.controller;

import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;
import com.spldeolin.satisficing.api.RequestResult;
import com.spldeolin.satisficing.app.enumcache.EnumCollector;
import com.spldeolin.satisficing.app.id.SnowFlake;

/**
 * 系统API
 *
 * @author Deolin 2020-09-23
 */
@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private EnumCollector enumCollector;

    @Autowired
    private SnowFlake snowFlake;

    /**
     * 根据枚举名，获取多个枚举的每个枚举项
     */
    @PostMapping({"/listEnums"})
    public RequestResult<Map<String, Map<String, String>>> listEnums(@RequestBody Collection<String> enumNames) {
        Map<String, Map<String, String>> map = Maps.newHashMap();
        for (String enumName : enumNames) {
            map.put(enumName, MoreObjects.firstNonNull(enumCollector.listEnumContants(enumName), Maps.newHashMap()));
        }
        return RequestResult.success(map);
    }

    /**
     * 获取本服务节点的Snow Flake worker ID
     */
    @PostMapping("/getWorkerId")
    public RequestResult<Long> getWorkerId() {
        return RequestResult.success(snowFlake.getWorkerId());
    }

}
