package com.platform.maker.utils;

import java.util.Random;

public class KeyUtils {
    /** 生成唯一主键 */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        Integer num = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(num);
    }
}
