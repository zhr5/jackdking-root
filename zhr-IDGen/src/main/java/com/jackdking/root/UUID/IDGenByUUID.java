package com.jackdking.root.UUID;

public class IDGenByUUID {
    public static String generateUUID() {
        return java.util.UUID.randomUUID().toString().replaceAll("-", "");
    }
    public static void main(String[] args) {
        System.out.println(generateUUID());
    }
}
