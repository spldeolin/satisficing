package com.spldeolin.satisficing.app.util;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import com.spldeolin.satisficing.api.SysException;
import com.spldeolin.satisficing.app.util.RsaUtils.KeyPair;
import lombok.extern.slf4j.Slf4j;

/**
 * AES加解密工具类
 * <p>
 * 采用256位密钥，AES/GCM/NoPadding模式，提供认证加密
 *
 * @author Deolin 2025-01-XX
 */
@Slf4j
public class AesUtils {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int KEY_SIZE = 256;
    private static final int GCM_TAG_LENGTH = 128;
    private static final int GCM_IV_LENGTH = 12;

    private static final SecureRandom sr = new SecureRandom();

    private AesUtils() {
        throw new UnsupportedOperationException("Never instantiate me.");
    }

    /**
     * 生成AES密钥
     *
     * @return 密钥（Base64编码）
     */
    public static String generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(KEY_SIZE, sr);
            SecretKey secretKey = keyGenerator.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (Exception e) {
            log.error("生成AES密钥失败", e);
            throw new SysException("生成AES密钥失败", e);
        }
    }

    /**
     * 加密
     *
     * @param plaintext 明文
     * @param key 密钥（Base64编码）
     * @return 密文（Base64编码，包含IV）
     */
    public static String encrypt(String plaintext, String key) {
        try {
            SecretKey secretKey = getSecretKey(key);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);

            // 生成随机IV
            byte[] iv = new byte[GCM_IV_LENGTH];
            sr.nextBytes(iv);
            GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);
            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

            // 将IV和密文组合：IV(12字节) + 密文
            byte[] combined = new byte[iv.length + encryptedBytes.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encryptedBytes, 0, combined, iv.length, encryptedBytes.length);

            return Base64.getEncoder().encodeToString(combined);
        } catch (Exception e) {
            log.error("AES加密失败", e);
            throw new SysException("AES加密失败", e);
        }
    }

    /**
     * 解密
     *
     * @param ciphertext 密文（Base64编码，包含IV）
     * @param key 密钥（Base64编码）
     * @return 明文
     */
    public static String decrypt(String ciphertext, String key) {
        try {
            SecretKey secretKey = getSecretKey(key);
            byte[] combined = Base64.getDecoder().decode(ciphertext);

            // 提取IV和密文
            byte[] iv = new byte[GCM_IV_LENGTH];
            byte[] encryptedBytes = new byte[combined.length - GCM_IV_LENGTH];
            System.arraycopy(combined, 0, iv, 0, iv.length);
            System.arraycopy(combined, iv.length, encryptedBytes, 0, encryptedBytes.length);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);

            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("AES解密失败", e);
            throw new SysException("AES解密失败", e);
        }
    }

    private static SecretKey getSecretKey(String keyBase64) {
        byte[] keyBytes = Base64.getDecoder().decode(keyBase64);
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    public static void main(String[] args) {
        String secretKey = IdKeyUtils.secretKey32();
        System.out.println(secretKey);

        String ciphertext = encrypt("你好 aa ff11_", secretKey);
        String plaintext = decrypt(ciphertext, secretKey);
        System.out.println(ciphertext);
        System.out.println(plaintext);
    }

}
