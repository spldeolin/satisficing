package com.spldeolin.satisficing.identity.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spldeolin.satisficing.app.exception.BizException;
import com.spldeolin.satisficing.identity.app.entity.UserEntity;
import com.spldeolin.satisficing.identity.app.javabean.req.UpdateUserReqDto;
import com.spldeolin.satisficing.identity.app.mapper.UserMapper;
import com.spldeolin.satisficing.identity.app.service.UpdateUserService;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Allison 1875 Lot No: HT1001S-DF7438EF
 *
 * @author Deolin
 */
@Slf4j
@Service
public class UpdateUserServiceImpl implements UpdateUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void updateUser(UpdateUserReqDto req) {
        UserEntity duplication = userMapper.queryUserEx2(null, req.getUsername(), req.getMobile());
        if (duplication != null) {
            if (duplication.getUsername().equals(req.getUsername())) {
                throw new BizException("用户名已存在");
            }
            if (duplication.getMobile().equals(req.getMobile())) {
                throw new BizException("手机号已存在");
            }
        }

        UserEntity user = userMapper.queryUser(req.getUserUuid());
        if (user == null) {
            log.warn("user absent，userUuid={}", req.getUserUuid());
            return;
        }

        user.setUsername(req.getUsername());
        user.setNickName(req.getNickName());
        user.setMobile(req.getMobile());
        userMapper.updateById(user);
    }

}
