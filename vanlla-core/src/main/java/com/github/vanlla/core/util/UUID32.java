package com.github.vanlla.core.util;

import java.util.UUID;

/**
 * 生产不带-的UUID
 *
 * @author Vanlla
 * @since 1.0
 */
public class UUID32 {

    public static String getID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
