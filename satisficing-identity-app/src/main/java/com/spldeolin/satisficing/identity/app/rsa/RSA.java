package com.spldeolin.satisficing.identity.app.rsa;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.spldeolin.satisficing.app.exception.UncheckedException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Deolin 2024-06-18
 */
@Slf4j
@Component
public class RSA {

    private final String publicKey;

    private final String privateKey;

    public RSA(@Value("${rsa.public-key}") String publicKey, @Value("${rsa.private-key}") String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        log.info("RSA publicKey / privateKey inited");
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String encrypt(String text) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] encryptedData = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            throw new UncheckedException(e);
        }
    }

    public String decrypt(String base64EncryptedText) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyFactory.generatePrivate(
                    new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(base64EncryptedText));
            return new String(decryptedData);
        } catch (Exception e) {
            throw new UncheckedException(e);
        }
    }

}
