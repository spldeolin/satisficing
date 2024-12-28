package com.spldeolin.satisficing.identity.app.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.spldeolin.satisficing.api.RequestResult;
import com.spldeolin.satisficing.identity.app.dto.req.CreateRoleReqDto;
import com.spldeolin.satisficing.identity.app.dto.req.DeleteRoleReqDto;
import com.spldeolin.satisficing.identity.app.dto.req.GrantRoleToUserReqDto;
import com.spldeolin.satisficing.identity.app.dto.req.ListRolesReqDto;
import com.spldeolin.satisficing.identity.app.dto.req.RevokeRoleToUserReqDto;
import com.spldeolin.satisficing.identity.app.dto.req.UpdateRoleReqDto;
import com.spldeolin.satisficing.identity.app.dto.resp.ListRolesRespDto;
import com.spldeolin.satisficing.identity.app.service.CreateRoleService;
import com.spldeolin.satisficing.identity.app.service.DeleteRoleService;
import com.spldeolin.satisficing.identity.app.service.GrantRoleToUserService;
import com.spldeolin.satisficing.identity.app.service.ListRolesService;
import com.spldeolin.satisficing.identity.app.service.RevokeRoleToUserService;
import com.spldeolin.satisficing.identity.app.service.UpdateRoleService;

/**
 * 角色管理
 *
 * @author Deolin 2024-06-19
 */
@RestController
public class RoleController {

    @Autowired
    private ListRolesService listRolesService;

    @Autowired
    private CreateRoleService createRoleService;

    @Autowired
    private UpdateRoleService updateRoleService;

    @Autowired
    private DeleteRoleService deleteRoleService;

    @Autowired
    private GrantRoleToUserService grantRoleToUserService;

    @Autowired
    private RevokeRoleToUserService revokeRoleToUserService;

    /**
     * 角色列表
     */
    @PostMapping("listRoles")
    public RequestResult<PageInfo<ListRolesRespDto>> listRoles(@RequestBody @Valid ListRolesReqDto req) {
        return RequestResult.success(listRolesService.listRoles(req));
    }

    /**
     * 创建角色
     */
    @PostMapping("createRole")
    public RequestResult<Void> createRole(@RequestBody @Valid CreateRoleReqDto req) {
        createRoleService.createRole(req);
        return RequestResult.success();
    }

    /**
     * 更新角色
     */
    @PostMapping("updateRole")
    public RequestResult<Void> updateRole(@RequestBody @Valid UpdateRoleReqDto req) {
        updateRoleService.updateRole(req);
        return RequestResult.success();
    }

    /**
     * 删除角色
     */
    @PostMapping("deleteRole")
    public RequestResult<Void> deleteRole(@RequestBody @Valid DeleteRoleReqDto req) {
        deleteRoleService.deleteRole(req);
        return RequestResult.success();
    }

    /**
     * 将角色授予用户
     */
    @PostMapping("grantRoleToUser")
    public RequestResult<Void> grantRoleToUser(@RequestBody @Valid GrantRoleToUserReqDto req) {
        grantRoleToUserService.grantRoleToUser(req);
        return RequestResult.success();
    }

    /**
     * 将角色取消授予用户
     */
    @PostMapping("revokeRoleToUser")
    public RequestResult<Void> revokeRoleToUser(@RequestBody @Valid RevokeRoleToUserReqDto req) {
        revokeRoleToUserService.revokeRoleToUser(req);
        return RequestResult.success();
    }

}
