package com.example.demo.Util;

import java.util.Base64;

public class ImageUtils {

    public static String encodeImage(byte[] imageData) {
        return Base64.getEncoder().encodeToString(imageData);
    }
}