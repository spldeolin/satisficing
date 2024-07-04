package com.spldeolin.satisficing.identity.app.service.impl;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spldeolin.satisficing.app.exception.BizException;
import com.spldeolin.satisficing.app.id.SnowFlake;
import com.spldeolin.satisficing.identity.ac.javabean.LoginSession;
import com.spldeolin.satisficing.identity.app.entity.RoleEntity;
import com.spldeolin.satisficing.identity.app.javabean.req.CreateRoleReqDto;
import com.spldeolin.satisficing.identity.app.mapper.RoleMapper;
import com.spldeolin.satisficing.identity.app.service.CreateRoleService;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Allison 1875 Lot No: HT1001S-79B68D13
 *
 * @author Allison 1875
 */
@Slf4j
@Service
public class CreateRoleServiceImpl implements CreateRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Transactional
    @Override
    public void createRole(CreateRoleReqDto req) {
        RoleEntity duplication = roleMapper.queryRoleEx(req.getRoleName(), null);
        if (duplication != null) {
            throw new BizException("无法创建：角色名称已存在");
        }

        RoleEntity role = new RoleEntity();
        role.setId(snowFlake.nextId());
        role.setRoleName(req.getRoleName());
        role.setDescription(req.getDescription());
        role.setCreateUserUuid(LoginSession.getCurrent().getLoginUserUuid());
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateUserUuid(LoginSession.getCurrent().getLoginUserUuid());
        role.setUpdateTime(LocalDateTime.now());
        try {
            roleMapper.insert(role);
        } catch (DuplicateKeyException e) {
            log.warn("unique key is still duplicate, role={}", role, e);
            throw new BizException("无法创建：角色名称已存在");
        }
    }

}
