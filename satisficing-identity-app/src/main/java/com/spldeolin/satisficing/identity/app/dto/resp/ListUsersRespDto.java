package com.spldeolin.satisficing.identity.app.dto.resp;

import java.time.LocalDateTime;
import java.util.List;
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
public class ListUsersRespDto {

    /**
     * 用户UUID
     */
    String userUuid;

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
     * 用户被授予的角色名称
     */
    List<String> roleNames;

    /**
     * 用户所处的部门名称
     */
    List<String> departmentNames;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    LocalDateTime updateTime;

}
