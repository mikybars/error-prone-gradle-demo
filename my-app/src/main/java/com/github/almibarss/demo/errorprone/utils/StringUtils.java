package com.github.almibarss.demo.errorprone.utils;

public class StringUtils {

    private StringUtils() {
    }

    public static String toUpperCase(String s) {
        return s.substring(0).toUpperCase();
    }
}
