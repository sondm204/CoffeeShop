package com.swp.coffeeshop.controller;

import com.swp.coffeeshop.utils.UrlUtils;
import com.swp.coffeeshop.utils.VNPayUtils;
import com.swp.coffeeshop.dto.VNPayResponse;
import com.swp.coffeeshop.services.Cart.CartService;
import com.swp.coffeeshop.services.Order.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

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

    OrderService orderService;
    CartService cartService;


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
    public RedirectView paymentReturn(@RequestParam Map<String, String> allParams, Model model, HttpSession session) throws Exception {
        if (!validateVNPayResponse(allParams, hashSecret)) {
            return new RedirectView("/payment-fail");
        }
        if (allParams.get("vnp_ResponseCode").equals("00")) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/CoffeeShop/checkout/success");
            allParams.forEach(builder::queryParam);
            return new RedirectView(builder.toUriString());
        }
        return new RedirectView("/payment-fail");
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

