package com.spldeolin.satisficing.identity.app.service.impl;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spldeolin.satisficing.app.exception.BizException;
import com.spldeolin.satisficing.identity.ac.javabean.LoginSession;
import com.spldeolin.satisficing.identity.app.entity.RoleEntity;
import com.spldeolin.satisficing.identity.app.javabean.req.UpdateRoleReqDto;
import com.spldeolin.satisficing.identity.app.mapper.RoleMapper;
import com.spldeolin.satisficing.identity.app.service.UpdateRoleService;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Allison 1875 Lot No: HT1001S-F03AE4A1
 *
 * @author Allison 1875
 */
@Slf4j
@Service
public class UpdateRoleServiceImpl implements UpdateRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Transactional
    @Override
    public void updateRole(UpdateRoleReqDto req) {
        RoleEntity duplication = roleMapper.queryRoleEx(req.getRoleName(), req.getRoleId());
        if (duplication != null) {
            throw new BizException("无法更新：角色名称已存在");
        }

        RoleEntity role = roleMapper.queryById(req.getRoleId());
        role.setRoleName(req.getRoleName());
        role.setDescription(req.getDescription());
        role.setUpdateUserUuid(LoginSession.getCurrent().getLoginUserUuid());
        role.setUpdateTime(LocalDateTime.now());
        try {
            roleMapper.updateByIdEvenNull(role);
        } catch (Exception e) {
            log.warn("unique key is still duplicate, role={}", role, e);
            throw new BizException("无法更新：角色名称已存在");
        }

    }

}
