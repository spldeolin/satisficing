package com.spldeolin.satisficing.identity.app.dto.req;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author Allison 1875 2024-06-23
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ListDepartmentsReqDto {

    /**
     * 父部门ID
     */
    Long parentId;

    /**
     * 部门名称
     */
    String departmentName;

    @NotNull
    Integer pageNum = 1;

    @NotNull
    Integer pageSize = 30;

}
