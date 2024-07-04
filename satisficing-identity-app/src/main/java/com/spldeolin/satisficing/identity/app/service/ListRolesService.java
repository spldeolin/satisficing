package com.spldeolin.satisficing.identity.app.service;

import com.github.pagehelper.PageInfo;
import com.spldeolin.satisficing.identity.app.javabean.req.ListRolesReqDto;
import com.spldeolin.satisficing.identity.app.javabean.resp.ListRolesRespDto;

/**
 * <p>Allison 1875 Lot No: HT1001S-858BE8C1
 *
 * @author Allison 1875
 */
public interface ListRolesService {

    PageInfo<ListRolesRespDto> listRoles(ListRolesReqDto req);

}
