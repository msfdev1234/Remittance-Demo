package com.madmobiledevs.tayseer_tech.Model;

public class SendingCountry {

    int countryId, mobileMinLength, mobileMaxLength;

    String countryName,countryCode,sendingCountryCallingCode, flagUrl;

    public SendingCountry(int countryId, int mobileMinLength, int mobileMaxLength, String countryName, String countryCode, String sendingCountryCallingCode, String flagUrl) {
        this.countryId = countryId;
        this.mobileMinLength = mobileMinLength;
        this.mobileMaxLength = mobileMaxLength;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.sendingCountryCallingCode = sendingCountryCallingCode;
        this.flagUrl = flagUrl;
    }

    public SendingCountry() {
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getMobileMinLength() {
        return mobileMinLength;
    }

    public void setMobileMinLength(int mobileMinLength) {
        this.mobileMinLength = mobileMinLength;
    }

    public int getMobileMaxLength() {
        return mobileMaxLength;
    }

    public void setMobileMaxLength(int mobileMaxLength) {
        this.mobileMaxLength = mobileMaxLength;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getSendingCountryCallingCode() {
        return sendingCountryCallingCode;
    }

    public void setSendingCountryCallingCode(String sendingCountryCallingCode) {
        this.sendingCountryCallingCode = sendingCountryCallingCode;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }
}
