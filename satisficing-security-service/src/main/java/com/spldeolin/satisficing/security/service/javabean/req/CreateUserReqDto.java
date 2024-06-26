package com.spldeolin.satisficing.security.service.javabean.req;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * <p>Allison 1875 Lot No: HT1001S-D3209C95
 *
 * @author Deolin 2024-06-03
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CreateUserReqDto {

    /**
     * 用户名
     */
    @NotNull
    String username;

    /**
     * 手机号
     */
    @NotNull
    String mobile;

    /**
     * 用户昵称
     */
    @NotNull
    String nickName;

}
