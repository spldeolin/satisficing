package com.spldeolin.satisficing.identity.app.dto.cond;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * QT1001S-D3A662D1
 *
 * @author Allison 1875 2024-06-19
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UpdateRoleCond {

    /**
     * 角色名
     */
    String roleName;

    /**
     * 描述
     */
    String description;

    /**
     * 最近一次更新数据时登录者的userUuid。若数据从未更新过，值与create_staff_uuid保持一致；若数据并非登录者更新的，值为empty
     */
    String updateUserUuid;

    /**
     * 最近一次更新数据的时间。若数据从未更新过，与create_time保持一致
     */
    LocalDateTime updateTime;

}
