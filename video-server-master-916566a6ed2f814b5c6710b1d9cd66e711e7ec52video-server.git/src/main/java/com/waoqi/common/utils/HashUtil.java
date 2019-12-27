package com.waoqi.common.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * 实现php hash加密算法及解密
 */
public class HashUtil {


    /**
     * 密码加密
     *
     * @param pwd
     * @return
     */
    public static String encodeHash(String pwd) {
        if (pwd == null || pwd == "") {
            return null;
        }
        return BCrypt.withDefaults().hashToString(12, pwd.toCharArray());
    }

    /**
     * 密码解密
     *
     * @param hash
     * @return
     */
    public static boolean decodeHash(String pwd, String hash) {
        BCrypt.Result result = BCrypt.verifyer().verify(pwd.toCharArray(), hash);
        return result.verified;
    }



}
