package com.swp.coffeeshop.controller;

import com.swp.coffeeshop.VNPay.VNPayUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/CoffeeShop")
public class VNPayController {
    @Value("${vnpay.tmn_code}")
    private String tmnCode;

    @Value("${vnpay.hash_secret}")
    private String hashSecret;

    @Value("${vnpay.payment_url}")
    private String paymentUrl;

    @Value("${vnpay.return_url}")
    private String returnUrl;

    @GetMapping("/vnpay/pay")
    public void createPayment(@RequestParam("amount") int amount, HttpServletResponse response) throws Exception {
        Map<String, String> params = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        params.put("vnp_Version", "2.1.0");
        params.put("vnp_Command", "pay");
        params.put("vnp_TmnCode", tmnCode);
        params.put("vnp_Amount", String.valueOf(amount * 100));
        params.put("vnp_CreateDate", dateFormat.format(new Date()));
        params.put("vnp_ExpireDate", dateFormat.format(new Date(System.currentTimeMillis() + 15 * 60 * 1000)));
        params.put("vnp_OrderInfo", "Thanh toán đơn hàng 3CRoastery");
        params.put("vnp_OrderType", "other");
        params.put("vnp_CurrCode", "VND");
        params.put("vnp_IpAddr", "127.0.0.1");
        params.put("vnp_Locale", "vn");
        params.put("vnp_ReturnUrl", returnUrl);
        params.put("vnp_TxnRef", String.valueOf(System.currentTimeMillis()));

        response.sendRedirect(VNPayUtils.createPaymentUrl(paymentUrl, params, hashSecret));
    }

    @GetMapping("/vnpay/return")
    @ResponseBody
    public String paymentReturn(@RequestParam Map<String, String> allParams) throws Exception {
        if (!validateVNPayResponse(allParams, hashSecret)) {
            return "Payment failed";
        }
        return "Payment result: " + allParams;
    }

    public boolean validateVNPayResponse(Map<String, String> params, String hashSecret) throws Exception {
        String receivedHash = params.get("vnp_SecureHash");
        params.remove("vnp_SecureHash");
        params.remove("vnp_SecureHashType");

        TreeMap<String, String> sortedParams = new TreeMap<>(params);
        StringBuilder hashData = new StringBuilder();

        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            String key = entry.getKey();
            String value = URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8);
            if (!hashData.isEmpty()) {
                hashData.append("&");
            }
            hashData.append(key).append("=").append(value);
        }

        String calculatedHash = VNPayUtils.hmacSHA512(hashData.toString(), hashSecret);
        return receivedHash.equals(calculatedHash);
    }

}

