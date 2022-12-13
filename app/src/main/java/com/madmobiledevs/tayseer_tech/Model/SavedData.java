package com.madmobiledevs.tayseer_tech.Model;

public class SavedData {

    String senderName, receiverName, sendingCountry, receivingCountry, purpose, sendingCurrency, receivingCurrency;

    String sendingAmount;

    public SavedData() {
    }

    String receivingAmount;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSendingCountry() {
        return sendingCountry;
    }

    public void setSendingCountry(String sendingCountry) {
        this.sendingCountry = sendingCountry;
    }

    public String getReceivingCountry() {
        return receivingCountry;
    }

    public void setReceivingCountry(String receivingCountry) {
        this.receivingCountry = receivingCountry;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getSendingCurrency() {
        return sendingCurrency;
    }

    public void setSendingCurrency(String sendingCurrency) {
        this.sendingCurrency = sendingCurrency;
    }

    public String getReceivingCurrency() {
        return receivingCurrency;
    }

    public void setReceivingCurrency(String receivingCurrency) {
        this.receivingCurrency = receivingCurrency;
    }

    public String getSendingAmount() {
        return sendingAmount;
    }

    public void setSendingAmount(String sendingAmount) {
        this.sendingAmount = sendingAmount;
    }

    public String getReceivingAmount() {
        return receivingAmount;
    }

    public void setReceivingAmount(String receivingAmount) {
        this.receivingAmount = receivingAmount;
    }

    public SavedData(String senderName, String receiverName, String sendingCountry, String receivingCountry, String purpose, String sendingCurrency, String receivingCurrency, String sendingAmount, String receivingAmount) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.sendingCountry = sendingCountry;
        this.receivingCountry = receivingCountry;
        this.purpose = purpose;
        this.sendingCurrency = sendingCurrency;
        this.receivingCurrency = receivingCurrency;
        this.sendingAmount = sendingAmount;
        this.receivingAmount = receivingAmount;
    }
}
