package com.spldeolin.satisficing.identity.app.javabean.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * <p>Allison 1875 Lot No: HT1001S-79B68D13
 *
 * @author Allison 1875 2024-06-19
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CreateRoleReqDto {

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
