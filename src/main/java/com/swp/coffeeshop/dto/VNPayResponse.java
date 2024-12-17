package com.swp.coffeeshop.dto;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

public class VNPayResponse {
    private String vnp_Amount;
    private String vnp_BankCode;
    private String vnp_BankTranNo;
    private String vnp_CardType;
    private String vnp_OrderInfo;
    private String vnp_PayDate;

    public VNPayResponse() {
    }

    public VNPayResponse(String vnp_Amount, String vnp_BankCode, String vnp_BankTranNo, String vnp_CardType, String vnp_OrderInfo, String vnp_PayDate) {
        this.vnp_Amount = vnp_Amount;
        this.vnp_BankCode = vnp_BankCode;
        this.vnp_BankTranNo = vnp_BankTranNo;
        this.vnp_CardType = vnp_CardType;
        this.vnp_OrderInfo = vnp_OrderInfo;
        this.vnp_PayDate = vnp_PayDate;
    }

    public VNPayResponse(Map<String, String> params) {
        this.vnp_Amount = params.get("vnp_Amount");
        this.vnp_BankCode = params.get("vnp_BankCode");
        this.vnp_BankTranNo = params.get("vnp_BankTranNo");
        this.vnp_CardType = params.get("vnp_CardType");
        this.vnp_OrderInfo = params.get("vnp_OrderInfo");
        this.vnp_PayDate = params.get("vnp_PayDate");
    }

    public String getVnp_Amount() {
        long amount = Long.parseLong(vnp_Amount) / 100;
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
    }

    public void setVnp_Amount(String vnp_Amount) {
        this.vnp_Amount = vnp_Amount;
    }

    public String getVnp_BankCode() {
        return vnp_BankCode;
    }

    public void setVnp_BankCode(String vnp_BankCode) {
        this.vnp_BankCode = vnp_BankCode;
    }

    public String getVnp_BankTranNo() {
        return vnp_BankTranNo;
    }

    public void setVnp_BankTranNo(String vnp_BankTranNo) {
        this.vnp_BankTranNo = vnp_BankTranNo;
    }

    public String getVnp_CardType() {
        return vnp_CardType;
    }

    public void setVnp_CardType(String vnp_CardType) {
        this.vnp_CardType = vnp_CardType;
    }

    public String getVnp_OrderInfo() {
        return vnp_OrderInfo;
    }

    public void setVnp_OrderInfo(String vnp_OrderInfo) {
        this.vnp_OrderInfo = vnp_OrderInfo;
    }

    public String getVnp_PayDate() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime localDateTime = LocalDateTime.parse(vnp_PayDate, inputFormatter);
        return localDateTime.format(outputFormatter);
    }

    public void setVnp_PayDate(String vnp_PayDate) {
        this.vnp_PayDate = vnp_PayDate;
    }

}
