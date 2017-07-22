package com.ms.jansing.common.util;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by jansing on 17-7-22.
 */
public class IdGen implements Serializable {
    private static SecureRandom random = new SecureRandom();

    public IdGen() {
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    public static String randomBase62(int length) {
        byte[] randomBytes = new byte[length];
        random.nextBytes(randomBytes);
        return Encodes.encodeBase62(randomBytes);
    }

    public String getNextId() {
        return uuid();
    }
}
