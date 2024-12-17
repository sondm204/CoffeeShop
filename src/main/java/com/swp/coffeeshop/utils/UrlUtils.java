package com.swp.coffeeshop.utils;

import org.springframework.web.util.UriComponentsBuilder;

public class UrlUtils {
    public static String buildUrlFromObject(String baseUrl, Object object) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);

        // Sử dụng Reflection để lấy các trường và giá trị từ đối tượng
        for (java.lang.reflect.Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true); // Để truy cập các trường private
            try {
                Object value = field.get(object);
                if (value != null) { // Bỏ qua các trường null
                    builder.queryParam(field.getName(), value.toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return builder.toUriString();
    }
}
