package com.spldeolin.satisficing.identity.app.dto.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * <p>Allison 1875 Lot No: HT1001S-F03AE4A1
 *
 * @author Allison 1875 2024-06-19
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UpdateRoleReqDto {

    /**
     * 角色ID
     */
    @NotNull
    Long roleId;

    /**
     * 角色名称
     */
    @NotBlank
    @Size(max = 255)
    String roleName;

    /**
     * 角色描述
     */
    @Size(max = 512)
    String description;

}
