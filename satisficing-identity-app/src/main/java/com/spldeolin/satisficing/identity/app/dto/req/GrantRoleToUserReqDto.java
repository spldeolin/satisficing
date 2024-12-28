package com.spldeolin.satisficing.identity.app.dto.req;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * <p>Allison 1875 Lot No: HT1001S-ABAAC57D
 *
 * @author Allison 1875 2024-06-19
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class GrantRoleToUserReqDto {

    /**
     * 角色ID
     */
    @NotEmpty
    List<Long> roleIds;

    /**
     * 用户ID
     */
    @NotEmpty
    List<Long> userIds;

}
