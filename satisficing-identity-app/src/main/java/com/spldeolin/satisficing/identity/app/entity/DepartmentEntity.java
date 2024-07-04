package com.spldeolin.satisficing.identity.app.entity;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * 部门
 * <p>department
 * <p>
 * <p>Any modifications may be overwritten by future code generations.
 *
 * @author Allison 1875 2024-06-23
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class DepartmentEntity {

    /**
     * 主键
     * <p>id
     * <p>不能为null
     */
    Long id;

    /**
     * 部门名称
     * <p>department_name
     * <p>长度：255
     * <p>不能为null
     */
    String departmentName;

    /**
     * 主键路径
     * <p>path
     * <p>长度：255
     * <p>不能为null
     */
    String path;

    /**
     * 上级部门ID
     * <p>parent_id
     * <p>长度：255
     */
    String parentId;

    /**
     * 描述
     * <p>description
     * <p>长度：512
     */
    String description;

    /**
     * 插入数据时登录者的userUuid。若数据并非登录者插入的，值为-1
     * <p>create_user_uuid
     * <p>长度：36
     * <p>不能为null
     * <p>默认：''
     */
    String createUserUuid;

    /**
     * 插入数据的时间
     * <p>create_time
     * <p>不能为null
     * <p>默认：CURRENT_TIMESTAMP
     */
    LocalDateTime createTime;

    /**
     * 最近一次更新数据时登录者的userUuid。若数据从未更新过，值与create_staff_uuid保持一致；若数据并非登录者更新的，值为empty
     * <p>update_user_uuid
     * <p>长度：36
     * <p>不能为null
     * <p>默认：''
     */
    String updateUserUuid;

    /**
     * 最近一次更新数据的时间。若数据从未更新过，与create_time保持一致
     * <p>update_time
     * <p>不能为null
     * <p>默认：CURRENT_TIMESTAMP
     */
    LocalDateTime updateTime;

}
