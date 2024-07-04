package com.spldeolin.satisficing.identity.app.javabean.req;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * <p>Allison 1875 Lot No: HT1001S-77216521
 *
 * @author Deolin 2024-06-03
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ListUsersReqDto {

    /**
     * 用户名
     */
    String username;

    /**
     * 手机
     */
    String mobile;

    /**
     * 昵称
     */
    String nickName;

    /**
     * 创建时间的左区间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    LocalDateTime createTimeLeft;

    /**
     * 创建时间的右区间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    LocalDateTime createTimeRight;

    /**
     * 角色ID对应角色下的用户
     */
    Long roleId;

    /**
     * 部门ID对应部门下的用户
     */
    Long departmentId;

    @NotNull
    Integer pageNum = 1;

    @NotNull
    Integer pageSize = 30;

}
