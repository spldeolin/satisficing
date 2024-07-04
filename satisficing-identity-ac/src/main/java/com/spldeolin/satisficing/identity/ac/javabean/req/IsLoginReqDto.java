package com.spldeolin.satisficing.identity.ac.javabean.req;

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
public class IsLoginReqDto {

    /**
     * token
     */
    String token;

    /**
     * token有效时，是否需要生成一个用于换取token的临时code
     */
    Boolean needCode;

}