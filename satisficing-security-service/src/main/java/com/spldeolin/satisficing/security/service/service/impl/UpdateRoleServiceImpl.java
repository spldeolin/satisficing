package com.spldeolin.satisficing.security.service.service.impl;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spldeolin.satisficing.security.client.javabean.LoginSession;
import com.spldeolin.satisficing.security.service.entity.RoleEntity;
import com.spldeolin.satisficing.security.service.javabean.req.UpdateRoleReqDto;
import com.spldeolin.satisficing.security.service.mapper.RoleMapper;
import com.spldeolin.satisficing.security.service.service.UpdateRoleService;
import com.spldeolin.satisficing.service.exception.BizException;
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
