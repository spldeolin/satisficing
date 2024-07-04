package com.spldeolin.satisficing.identity.app.service;

import com.github.pagehelper.PageInfo;
import com.spldeolin.satisficing.identity.app.javabean.req.ListUsersReqDto;
import com.spldeolin.satisficing.identity.app.javabean.resp.ListUsersRespDto;

/**
 * <p>Allison 1875 Lot No: HT1001S-77216521
 *
 * @author Deolin
 */
public interface ListUsersService {

    PageInfo<ListUsersRespDto> listUsers(ListUsersReqDto req);

}
