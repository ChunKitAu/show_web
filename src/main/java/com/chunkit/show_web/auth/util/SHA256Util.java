package com.chunkit.show_web.auth.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * SHA256Util加密工具
 * @auther ChunKitAu
 * @create 2020-01-15 15
 */
public class SHA256Util {
    private SHA256Util(){};

    //加密算法S
    public final static String HASH_ALGORITHM_NAME = "SHA-256";
    //加密次数
    public final static int HASH_ITERATIONS = 15;
    /**  执行加密-采用SHA256和盐值加密 **/
    public static String sha256(String password, String salt) {
        return new SimpleHash(HASH_ALGORITHM_NAME, password, salt, HASH_ITERATIONS).toString();
    }

}
