package com.github.vanlla.shiro.oauth2;

import com.github.vanlla.core.exception.RestException;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * token生成器
 *
 * @author Vanlla
 * @since 1.0
 */
public class TokenGenerator {
    private static final char[] HEX_CODE = "0123456789abcdef".toCharArray();

    public TokenGenerator() {
    }

    /**
     * 生成Token
     *
     * @param loginType
     * @return
     */
    public static String generateValue(int loginType) {
        return generateValue(UUID.randomUUID().toString(), loginType);
    }

    public static String toHexString(byte[] data) {
        if (data == null) {
            return null;
        } else {
            StringBuilder r = new StringBuilder(data.length * 2);

            for (int i = 0; i < data.length; ++i) {
                byte b = data[i];
                r.append(HEX_CODE[b >> 4 & 15]);
                r.append(HEX_CODE[b & 15]);
            }

            return r.toString();
        }
    }

    /**
     * 生成登录Token
     *
     * @param param
     * @param loginType
     * @return
     */
    private static String generateValue(String param, int loginType) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes());
            byte[] messageDigest = algorithm.digest();
            return toHexString(messageDigest) + loginType;
        } catch (Exception e) {
            throw new RestException("生成Token失败", e);
        }
    }
}