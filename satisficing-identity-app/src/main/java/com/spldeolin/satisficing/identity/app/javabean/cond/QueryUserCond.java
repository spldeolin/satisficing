package com.spldeolin.satisficing.identity.app.javabean.cond;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * QT1001S-1B2F0DC4
 *
 * @author Deolin 2024-06-03
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class QueryUserCond {

    /**
     * 用户名
     */
    String username;

    /**
     * 手机号
     */
    String mobile;

    /**
     * 用户昵称
     */
    String nickName;

    /**
     * 插入数据的时间
     */
    LocalDateTime createTime;

    /**
     * 插入数据的时间
     */
    LocalDateTime createTimeEx;

    Long roleId;

    Long departmentId;

}
