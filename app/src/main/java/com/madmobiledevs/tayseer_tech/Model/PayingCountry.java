package com.madmobiledevs.tayseer_tech.Model;

public class PayingCountry {
    int countryId, mobileMinLength, mobileMaxLength;

    String countryName,countryCode,payingCountryCallingCode, flagUrl;

    public int getCountryId() {
        return countryId;
    }

    public PayingCountry() {
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

    public String getPayingCountryCallingCode() {
        return payingCountryCallingCode;
    }

    public void setPayingCountryCallingCode(String payingCountryCallingCode) {
        this.payingCountryCallingCode = payingCountryCallingCode;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    public PayingCountry(int countryId, int mobileMinLength, int mobileMaxLength, String countryName, String countryCode, String payingCountryCallingCode, String flagUrl) {
        this.countryId = countryId;
        this.mobileMinLength = mobileMinLength;
        this.mobileMaxLength = mobileMaxLength;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.payingCountryCallingCode = payingCountryCallingCode;
        this.flagUrl = flagUrl;
    }
}
