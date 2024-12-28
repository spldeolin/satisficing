package com.spldeolin.satisficing.identity.app.dto.req;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * <p>Allison 1875 Lot No: HT1001S-858BE8C1
 *
 * @author Allison 1875 2024-06-19
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ListRolesReqDto {

    /**
     * 角色名称
     */
    String roleName;

    /**
     * 描述
     */
    String description;

    @NotNull
    Integer pageNum = 1;

    @NotNull
    Integer pageSize = 30;

}
