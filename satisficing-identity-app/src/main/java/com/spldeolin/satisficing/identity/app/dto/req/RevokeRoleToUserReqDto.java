package com.spldeolin.satisficing.identity.app.dto.req;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * <p>Allison 1875 Lot No: HT1001S-9B6CC6AB
 *
 * @author Allison 1875 2024-06-19
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class RevokeRoleToUserReqDto {

    /**
     * 角色ID
     */
    @NotNull
    Long roleId;

    /**
     * 用户ID
     */
    @NotNull
    Long userId;

}
