package com.spldeolin.satisficing.identity.app.service;

import com.github.pagehelper.PageInfo;
import com.spldeolin.satisficing.identity.app.dto.req.ListDepartmentsReqDto;
import com.spldeolin.satisficing.identity.app.dto.resp.ListDepartmentsRespDto;

/**
 * @author Allison 1875
 */
public interface ListDepartmentsService {

    PageInfo<ListDepartmentsRespDto> listDepartments(ListDepartmentsReqDto req);

}
