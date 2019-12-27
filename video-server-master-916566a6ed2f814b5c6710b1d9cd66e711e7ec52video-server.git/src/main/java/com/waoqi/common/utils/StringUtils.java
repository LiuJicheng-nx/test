package com.waoqi.common.utils;

import java.util.Random;

/**
 * @author waoqi
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    private static String HIDD = "***********************************************";

    /**
     * 获取随机字符串
     *
     * @param len
     * @return
     */
    public String getRandomString(int len) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        return this.getRandomValue(len, str);
    }

    public String getRandomInteger(int len) {
        String str = "0123456789";
        return this.getRandomValue(len, str);
    }


    private String getRandomValue(int len, String str) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


    public static String keepTail(String str, int keepTail) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        if (str.length() < keepTail) {
            return str;
        }
        String pre = HIDD.substring(0, str.length() - keepTail);
        return pre + str.substring(str.length() - keepTail);
    }
}
