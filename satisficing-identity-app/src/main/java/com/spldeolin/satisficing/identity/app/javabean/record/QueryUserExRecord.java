package com.spldeolin.satisficing.identity.app.javabean.record;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author Deolin 2024-06-21
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QueryUserExRecord {

    Long userId;

    String userUuid;

    String username;

    String mobile;

    String nickName;

    String createUserUuid;

    LocalDateTime createTime;

    String updateUserUuid;

    LocalDateTime updateTime;

    String roleNames;

    String departmentNames;

}