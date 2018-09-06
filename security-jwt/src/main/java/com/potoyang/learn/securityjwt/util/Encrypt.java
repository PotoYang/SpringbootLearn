package com.potoyang.learn.securityjwt.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/4 16:49
 * Modified By:
 * Description:
 */

public class Encrypt {
    public static String e(String inputText) {
        return md5(inputText);
    }

    public static String md5AndSha(String inputText) {
        return sha(md5(inputText));
    }

    private static String md5(String inputText) {
        return encrypt(inputText, "md5");
    }

    private static String sha(String inputText) {
        return encrypt(inputText, "sha-1");
    }

    private static String encrypt(String inputText, String algorithmName) {
        if ((inputText == null) || ("".equals(inputText.trim()))) {
            throw new IllegalArgumentException("请输入要加密的内容");
        }
        if ((algorithmName == null) || ("".equals(algorithmName.trim()))) {
            algorithmName = "md5";
        }
        try {
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            m.update(inputText.getBytes("UTF8"));
            byte[] s = m.digest();

            return hex(s);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String hex(byte[] arr) {
        StringBuilder sb = new StringBuilder();
        for (byte anArr : arr) {
            sb.append(Integer.toHexString(anArr & 0xFF | 0x100).substring(1, 3));
        }
        return sb.toString();
    }
}

