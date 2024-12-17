package com.swp.coffeeshop.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class VNPayUtils {
    public static String createPaymentUrl(String baseUrl, Map<String, String> params, String hashSecret) throws Exception {
        SortedMap<String, String> sortedParams = new TreeMap<>(params);
        StringBuilder query = new StringBuilder();
        StringBuilder hashData = new StringBuilder();

        Set<Map.Entry<String, String>> entrySet = sortedParams.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String value = URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8);

            if (!query.isEmpty()) {
                query.append("&");
                hashData.append("&");
            }

            query.append(key).append("=").append(value);
            hashData.append(key).append("=").append(value);
        }
        String secureHash = hmacSHA512(hashData.toString(), hashSecret);
        return baseUrl + "?" + query.toString() + "&vnp_SecureHash=" + secureHash;
    }

    public static String hmacSHA512(String data, String key) throws Exception {
        // Khởi tạo HMAC-SHA512
        Mac hmacSHA512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmacSHA512.init(secretKey);

        // Tạo chữ ký
        byte[] hashBytes = hmacSHA512.doFinal(data.getBytes(StandardCharsets.UTF_8));

        // Chuyển đổi kết quả byte[] thành chuỗi hex
        StringBuilder hash = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hash.append('0'); // Thêm số 0 nếu thiếu
            hash.append(hex);
        }
        return hash.toString();
    }

    public static String HmacSHA256(String data, String secret) throws Exception {
        Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        mac.init(secretKey);
        byte[] hmacBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder hash = new StringBuilder();
        for (byte b : hmacBytes) {
            hash.append(String.format("%02x", b));
        }
        return hash.toString();
    }
}

