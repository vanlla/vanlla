package com.github.vanlla.shiro.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 加密使用
 *
 * @author Vanlla
 * @since 1.0
 */
public class Sha256Hash extends SimpleHash {

    public static final String ALGORITHM_NAME = "SHA-256";

    public Sha256Hash() {
        super(ALGORITHM_NAME);
    }

    public Sha256Hash(Object source) {
        super(ALGORITHM_NAME, source);
    }

    public Sha256Hash(Object source, Object salt) {
        super(ALGORITHM_NAME, source, salt);
    }

    public Sha256Hash(Object source, Object salt, int hashIterations) {
        super(ALGORITHM_NAME, source, salt, hashIterations);
    }

    public static Sha256Hash fromHexString(String hex) {
        Sha256Hash hash = new Sha256Hash();
        hash.setBytes(Hex.decode(hex));
        return hash;
    }

    public static Sha256Hash fromBase64String(String base64) {
        Sha256Hash hash = new Sha256Hash();
        hash.setBytes(Base64.decode(base64));
        return hash;
    }
}