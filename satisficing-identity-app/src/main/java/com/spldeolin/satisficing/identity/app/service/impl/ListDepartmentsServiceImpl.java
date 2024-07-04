package com.spldeolin.satisficing.identity.app.service.impl;

import org.springframework.stereotype.Service;
import com.github.pagehelper.PageInfo;
import com.spldeolin.satisficing.identity.app.javabean.req.ListDepartmentsReqDto;
import com.spldeolin.satisficing.identity.app.javabean.resp.ListDepartmentsRespDto;
import com.spldeolin.satisficing.identity.app.service.ListDepartmentsService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Allison 1875
 */
@Slf4j
@Service
public class ListDepartmentsServiceImpl implements ListDepartmentsService {

    @Override
    public PageInfo<ListDepartmentsRespDto> listDepartments(ListDepartmentsReqDto req) {
        return null;
    }

}
