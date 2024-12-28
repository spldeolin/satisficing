package com.spldeolin.satisficing.identity.app.dto.req;

import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author Deolin 2024-06-02
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginByCodeReqDto {

    /**
     * 用于换取token的临时code
     */
    @NotNull
    String code;

}