package com.spldeolin.satisficing.identity.app.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.spldeolin.satisficing.api.RequestResult;
import com.spldeolin.satisficing.identity.app.dto.req.CreateUserReqDto;
import com.spldeolin.satisficing.identity.app.dto.req.ListUsersReqDto;
import com.spldeolin.satisficing.identity.app.dto.req.UpdateUserReqDto;
import com.spldeolin.satisficing.identity.app.dto.resp.ListUsersRespDto;
import com.spldeolin.satisficing.identity.app.service.CreateUserService;
import com.spldeolin.satisficing.identity.app.service.ListUsersService;
import com.spldeolin.satisficing.identity.app.service.UpdateUserService;

/**
 * 用户管理
 *
 * @author Deolin 2024-06-03
 */
@RestController
public class UserController {

    @Autowired
    private ListUsersService listUsersService;

    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private UpdateUserService updateUserService;

    /**
     * 用户列表
     */
    @PostMapping("listUsers")
    public RequestResult<PageInfo<ListUsersRespDto>> listUsers(@RequestBody @Valid ListUsersReqDto req) {
        return RequestResult.success(listUsersService.listUsers(req));
    }

    /**
     * 创建用户
     */
    @PostMapping("createUser")
    public RequestResult<Void> createUser(@RequestBody @Valid CreateUserReqDto req) {
        createUserService.createUser(req);
        return RequestResult.success();
    }

    /**
     * 更新用户
     */
    @PostMapping("updateUser")
    public RequestResult<Void> updateUser(@RequestBody @Valid UpdateUserReqDto req) {
        updateUserService.updateUser(req);
        return RequestResult.success();
    }

}
