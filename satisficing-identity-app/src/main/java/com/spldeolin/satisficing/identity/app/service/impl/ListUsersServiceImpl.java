package com.spldeolin.satisficing.identity.app.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.spldeolin.satisficing.app.util.PageUtils;
import com.spldeolin.satisficing.app.util.TextUtils;
import com.spldeolin.satisficing.app.util.TimeUtils;
import com.spldeolin.satisficing.identity.app.dto.cond.QueryUserCond;
import com.spldeolin.satisficing.identity.app.dto.record.QueryUserExRecord;
import com.spldeolin.satisficing.identity.app.dto.req.ListUsersReqDto;
import com.spldeolin.satisficing.identity.app.dto.resp.ListUsersRespDto;
import com.spldeolin.satisficing.identity.app.mapper.UserMapper;
import com.spldeolin.satisficing.identity.app.service.ListUsersService;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Allison 1875 Lot No: HT1001S-77216521
 *
 * @author Deolin
 */
@Slf4j
@Service
public class ListUsersServiceImpl implements ListUsersService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<ListUsersRespDto> listUsers(ListUsersReqDto req) {
        final QueryUserCond queryUserCond = new QueryUserCond();
        queryUserCond.setUsername(req.getUsername());
        queryUserCond.setMobile(req.getMobile());
        queryUserCond.setNickName(req.getNickName());
        queryUserCond.setCreateTime(TimeUtils.toggleToDayStart(req.getCreateTimeLeft()));
        queryUserCond.setCreateTimeEx(TimeUtils.toggleToDayEnd(req.getCreateTimeRight()));
        queryUserCond.setRoleId(req.getRoleId());
        queryUserCond.setDepartmentId(req.getDepartmentId());
        List<QueryUserExRecord> users = userMapper.queryUserEx(queryUserCond);
        if (users.isEmpty()) {
            return new PageInfo<>(Lists.newArrayList());
        }

        /*
            在RBAC中，Role的存在意义在于让用户无需直接管理大量的权限关系，而是通过角色来集中授予相关权限。
            User通常不需要也不应该关联过多的角色，否则就会降低 RBAC 的效果。
            所以User被授予的Roles无需分页，而Roles所关联的User可能需要分页。
            同理，一个Department可能有很多用户，但一个用户不可能加入很多Department
         */

        List<ListUsersRespDto> dtos = Lists.newArrayList();
        for (QueryUserExRecord user : users) {
            ListUsersRespDto dto = new ListUsersRespDto();
            dto.setUserUuid(user.getUserUuid());
            dto.setUsername(user.getUsername());
            dto.setMobile(user.getMobile());
            dto.setNickName(user.getNickName());
            dto.setRoleNames(TextUtils.splitAndUnescapeComma(user.getRoleNames()));
            dto.setDepartmentNames(TextUtils.splitAndUnescapeComma(user.getDepartmentNames()));
            dto.setCreateTime(user.getCreateTime());
            dto.setUpdateTime(user.getUpdateTime());
            dtos.add(dto);
        }

        return PageUtils.transferType(users, dtos);
    }

}
