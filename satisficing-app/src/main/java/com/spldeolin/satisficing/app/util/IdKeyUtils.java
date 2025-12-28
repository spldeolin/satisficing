package com.spldeolin.satisficing.app.util;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

/**
 * 生成ID、密钥的工具类
 *
 * @author Deolin 2025-11-25
 */
public class IdKeyUtils {

    private static final SecureRandom sr = new SecureRandom();

    private IdKeyUtils() {
        throw new UnsupportedOperationException("Never instantiate me.");
    }

    /**
     * 生成去除-、转小写的简化UUID（shortUuid）
     */
    public static String shortUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }

    /**
     * 生成URL安全的Base64随机密钥（16位22个字符）
     */
    public static String secretKey16() {
        return secretKey(16);
    }

    /**
     * 生成URL安全的Base64随机密钥（32位43个字符）
     */
    public static String secretKey32() {
        return secretKey(32);
    }

    /**
     * 生成URL安全的Base64随机密钥（64位86个字符）
     */
    public static String secretKey64() {
        return secretKey(64);
    }

    /**
     * 生成URL安全的Base64随机密钥（指定位数）
     */
    public static String secretKey(int bit) {
        byte[] apiKeyBytes = new byte[bit];
        sr.nextBytes(apiKeyBytes);
        String retval = Base64.getUrlEncoder().withoutPadding().encodeToString(apiKeyBytes);
        return retval;
    }

}
