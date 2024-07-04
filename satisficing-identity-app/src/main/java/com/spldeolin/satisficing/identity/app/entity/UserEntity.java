package com.spldeolin.satisficing.identity.app.entity;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * 用户
 * <p>user
 * <p>
 * <p>Any modifications may be overwritten by future code generations.
 * <p>Allison 1875 Lot No: PG1001S-F7A47F26
 *
 * @author Allison 1875 2024-06-21
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserEntity {

    /**
     * 主键
     * <p>id
     * <p>不能为null
     */
    Long id;

    /**
     * 用户的UUID
     * <p>user_uuid
     * <p>长度：36
     * <p>不能为null
     */
    String userUuid;

    /**
     * 用户名
     * <p>username
     * <p>长度：255
     * <p>不能为null
     */
    String username;

    /**
     * 手机号
     * <p>mobile
     * <p>长度：255
     * <p>不能为null
     */
    String mobile;

    /**
     * 密码
     * <p>password
     * <p>长度：255
     * <p>不能为null
     */
    String password;

    /**
     * 用户昵称
     * <p>nick_name
     * <p>长度：255
     * <p>不能为null
     */
    String nickName;

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
