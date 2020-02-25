package com.daqiao.kvgh.utils;

public class TextUtil {
    public static String getUUID(String code) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] cArray = code.toCharArray();
        for( char c : cArray ) {
            int i = c - 0;
            stringBuilder.append(String.valueOf(i));
        }
        return stringBuilder.toString();
    }
}
