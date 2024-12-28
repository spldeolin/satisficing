package com.spldeolin.satisficing.identity.app.dto.resp;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author Allison 1875 2024-06-23
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ListDepartmentsRespDto {

    /**
     * 部门ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    Long departmentId;

    /**
     * 部门名称
     */
    String departmentName;

    /**
     * 部门全称
     */
    String departmentFullName;

    /**
     * 描述
     */
    String description;

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
