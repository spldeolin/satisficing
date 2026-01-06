package com.spldeolin.satisficing.app.util;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import com.spldeolin.satisficing.api.SysException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * RSA加解密工具类
 * <p>
 * 采用2048位密钥，RSA/ECB/PKCS1Padding模式
 *
 * @author Deolin 2025-01-XX
 */
@Slf4j
public class RsaUtils {

    private static final String ALGORITHM = "RSA";

    private static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";

    private static final int KEY_SIZE = 2048;

    private static final int MAX_ENCRYPT_BLOCK_SIZE = 245; // 2048位密钥最大加密块大小

    private static final int MAX_DECRYPT_BLOCK_SIZE = 256; // 2048位密钥最大解密块大小

    private static final SecureRandom sr = new SecureRandom();

    private RsaUtils() {
        throw new UnsupportedOperationException("Never instantiate me.");
    }

    @AllArgsConstructor
    public static class KeyPair {

        public final String publicKey;

        public final String privateKey;

    }

    /**
     * 生成RSA密钥对
     *
     * @return 字符串数组，[0]为公钥Base64，[1]为私钥Base64
     */
    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGenerator.initialize(KEY_SIZE, sr);
            java.security.KeyPair keyPair = keyPairGenerator.generateKeyPair();

            String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
            String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

            return new KeyPair(publicKey, privateKey);
        } catch (Exception e) {
            log.error("生成RSA密钥对失败", e);
            throw new SysException("生成RSA密钥对失败", e);
        }
    }

    /**
     * 加密
     *
     * @param plaintext 明文
     * @param publicKey 公钥（Base64编码）
     * @return 密文（Base64编码）
     */
    public static String encrypt(String plaintext, String publicKey) {
        try {
            PublicKey key = getPublicKey(publicKey);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] data = plaintext.getBytes(StandardCharsets.UTF_8);
            int inputLen = data.length;
            byte[] encryptedBytes = new byte[0];
            int offSet = 0;
            byte[] cache;

            // 分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK_SIZE) {
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK_SIZE);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                encryptedBytes = mergeBytes(encryptedBytes, cache);
                offSet += MAX_ENCRYPT_BLOCK_SIZE;
            }

            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            log.error("RSA加密失败", e);
            throw new SysException("RSA加密失败", e);
        }
    }

    /**
     * 解密
     *
     * @param ciphertext 密文（Base64编码）
     * @param privateKey 私钥（Base64编码）
     * @return 明文
     */
    public static String decrypt(String ciphertext, String privateKey) {
        try {
            PrivateKey key = getPrivateKey(privateKey);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] data = Base64.getDecoder().decode(ciphertext);
            int inputLen = data.length;
            byte[] decryptedBytes = new byte[0];
            int offSet = 0;
            byte[] cache;

            // 分段解密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK_SIZE) {
                    cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK_SIZE);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                decryptedBytes = mergeBytes(decryptedBytes, cache);
                offSet += MAX_DECRYPT_BLOCK_SIZE;
            }

            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("RSA解密失败", e);
            throw new SysException("RSA解密失败", e);
        }
    }

    private static PublicKey getPublicKey(String publicKeyBase64) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(publicKeyBase64);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            return keyFactory.generatePublic(spec);
        } catch (Exception e) {
            log.error("恢复公钥失败", e);
            throw new SysException("恢复公钥失败", e);
        }
    }

    private static PrivateKey getPrivateKey(String privateKeyBase64) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(privateKeyBase64);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            return keyFactory.generatePrivate(spec);
        } catch (Exception e) {
            log.error("恢复私钥失败", e);
            throw new SysException("恢复私钥失败", e);
        }
    }

    private static byte[] mergeBytes(byte[] array1, byte[] array2) {
        byte[] merged = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, merged, 0, array1.length);
        System.arraycopy(array2, 0, merged, array1.length, array2.length);
        return merged;
    }

    public static void main(String[] args) {
        KeyPair keyPair = generateKeyPair();
        System.out.println(keyPair.publicKey);
        System.out.println(keyPair.privateKey);

        String ciphertext = encrypt("你好 aa ff11_", keyPair.publicKey);
        String plaintext = decrypt(ciphertext, keyPair.privateKey);
        System.out.println(ciphertext);
        System.out.println(plaintext);
    }

}
