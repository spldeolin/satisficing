package com.spldeolin.satisficing.identity.app.dto.resp;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * <p>Allison 1875 Lot No: HT1001S-8D86E744
 *
 * @author Allison 1875 2024-06-18
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class GetPublicKeyRespDto {

    /**
     * base64公钥
     */
    String publicKey;

}
