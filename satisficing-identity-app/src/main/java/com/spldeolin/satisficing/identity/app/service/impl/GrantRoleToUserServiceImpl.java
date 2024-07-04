package com.spldeolin.satisficing.identity.app.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spldeolin.satisficing.app.id.SnowFlake;
import com.spldeolin.satisficing.identity.ac.javabean.LoginSession;
import com.spldeolin.satisficing.identity.app.entity.RoleEntity;
import com.spldeolin.satisficing.identity.app.entity.User2roleEntity;
import com.spldeolin.satisficing.identity.app.entity.UserEntity;
import com.spldeolin.satisficing.identity.app.javabean.req.GrantRoleToUserReqDto;
import com.spldeolin.satisficing.identity.app.mapper.RoleMapper;
import com.spldeolin.satisficing.identity.app.mapper.User2roleMapper;
import com.spldeolin.satisficing.identity.app.mapper.UserMapper;
import com.spldeolin.satisficing.identity.app.service.GrantRoleToUserService;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Allison 1875 Lot No: HT1001S-ABAAC57D
 *
 * @author Allison 1875
 */
@Slf4j
@Service
public class GrantRoleToUserServiceImpl implements GrantRoleToUserService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private User2roleMapper user2roleMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Transactional
    @Override
    public void grantRoleToUser(GrantRoleToUserReqDto req) {
        List<RoleEntity> roles = roleMapper.queryByIds(req.getRoleIds());
        List<UserEntity> users = userMapper.queryByIds(req.getUserIds());
        if (CollectionUtils.isEmpty(roles) || CollectionUtils.isEmpty(users)) {
            log.warn("无法获取角色或用户");
            return;
        }

        for (RoleEntity role : roles) {
            for (UserEntity user : users) {
                User2roleEntity user2role = new User2roleEntity();
                user2role.setId(snowFlake.nextId());
                user2role.setUserId(user.getId());
                user2role.setRoleId(role.getId());
                user2role.setCreateUserUuid(LoginSession.getCurrent().getLoginUserUuid());
                user2role.setCreateTime(LocalDateTime.now());
                user2role.setUpdateUserUuid(LoginSession.getCurrent().getLoginUserUuid());
                user2role.setUpdateTime(LocalDateTime.now());
                try {
                    user2roleMapper.insert(user2role);
                } catch (DuplicateKeyException e) {
                    log.warn("用户与角色关联关系已存在 user2role={}", user2role, e);
                }
            }
        }
    }

}
