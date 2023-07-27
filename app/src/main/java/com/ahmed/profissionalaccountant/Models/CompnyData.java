
package com.ahmed.profissionalaccountant.Models;

import com.google.gson.annotations.SerializedName;


public class CompnyData {

    @SerializedName("Address")
    private String mAddress;
    @SerializedName("EnfWorkType")
    private String mEnfWorkType;
    @SerializedName("EngName")
    private String mEngName;
    @SerializedName("ID")
    private Long mID;
    @SerializedName("Logo")
    private String mLogo;
    @SerializedName("Mail")
    private String mMail;
    @SerializedName("Name")
    private String mName;
    @SerializedName("Phone")
    private String mPhone;
    @SerializedName("PrinterFix")
    private String mPrinterFix;
    @SerializedName("QR")
    private String mQR;
    @SerializedName("RecordTax")
    private String mRecordTax;
    @SerializedName("Segal")
    private String mSegal;
    @SerializedName("WorkType")
    private String mWorkType;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getEnfWorkType() {
        return mEnfWorkType;
    }

    public void setEnfWorkType(String enfWorkType) {
        mEnfWorkType = enfWorkType;
    }

    public String getEngName() {
        return mEngName;
    }

    public void setEngName(String engName) {
        mEngName = engName;
    }

    public Long getID() {
        return mID;
    }

    public void setID(Long iD) {
        mID = iD;
    }

    public String getLogo() {
        return mLogo;
    }

    public void setLogo(String logo) {
        mLogo = logo;
    }

    public String getMail() {
        return mMail;
    }

    public void setMail(String mail) {
        mMail = mail;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getPrinterFix() {
        return mPrinterFix;
    }

    public void setPrinterFix(String printerFix) {
        mPrinterFix = printerFix;
    }

    public String getQR() {
        return mQR;
    }

    public void setQR(String qR) {
        mQR = qR;
    }

    public String getRecordTax() {
        return mRecordTax;
    }

    public void setRecordTax(String recordTax) {
        mRecordTax = recordTax;
    }

    public String getSegal() {
        return mSegal;
    }

    public void setSegal(String segal) {
        mSegal = segal;
    }

    public String getWorkType() {
        return mWorkType;
    }

    public void setWorkType(String workType) {
        mWorkType = workType;
    }

}
