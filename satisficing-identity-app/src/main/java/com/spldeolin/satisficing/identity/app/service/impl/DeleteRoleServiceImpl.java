package com.spldeolin.satisficing.identity.app.service.impl;

import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spldeolin.satisficing.app.exception.BizException;
import com.spldeolin.satisficing.identity.app.dto.req.DeleteRoleReqDto;
import com.spldeolin.satisficing.identity.app.entity.User2roleEntity;
import com.spldeolin.satisficing.identity.app.mapper.RoleMapper;
import com.spldeolin.satisficing.identity.app.mapper.User2roleMapper;
import com.spldeolin.satisficing.identity.app.service.DeleteRoleService;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Allison 1875 Lot No: HT1001S-774D2245
 *
 * @author Allison 1875
 */
@Slf4j
@Service
public class DeleteRoleServiceImpl implements DeleteRoleService {

    @Autowired
    private User2roleMapper user2roleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Transactional
    @Override
    public void deleteRole(DeleteRoleReqDto req) {
        List<User2roleEntity> user2roles = user2roleMapper.queryByRoleId(req.getRoleId());
        if (CollectionUtils.isNotEmpty(user2roles)) {
            throw new BizException("无法删除：角色关联了用户");
        }

        roleMapper.dropRole(req.getRoleId());
    }

}
