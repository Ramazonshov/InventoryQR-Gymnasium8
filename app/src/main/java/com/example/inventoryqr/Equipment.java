package com.example.inventoryqr;

public class Equipment {
    private String qrCode;
    private String name;
    private String invNumber;
    private String model;
    private String cabinet;
    private String status;

    public Equipment(String qrCode, String name, String invNumber, String model, String cabinet, String status) {
        this.qrCode = qrCode;
        this.name = name;
        this.invNumber = invNumber;
        this.model = model;
        this.cabinet = cabinet;
        this.status = status;
    }

    // Геттеры
    public String getQrCode() { return qrCode; }
    public String getName() { return name; }
    public String getInvNumber() { return invNumber; }
    public String getModel() { return model; }
    public String getCabinet() { return cabinet; }
    public String getStatus() { return status; }
}