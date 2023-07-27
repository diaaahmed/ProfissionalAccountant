
package com.ahmed.profissionalaccountant.Models;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class StatusCodeResult  implements Serializable {


    @SerializedName("customeName")
    private String customeName;
    @SerializedName("activity")
    private String mActivity;
    @SerializedName("AdditionalData")
    private String mAdditionalData;
    @SerializedName("Address")
    private String mAddress;
    @SerializedName("InvoiceSerialNumber")
    private Integer mInvoiceSerialNumber;
    @SerializedName("Phone")
    private String mPhone;
    @SerializedName("QRCode")
    private String mQRCode;
    @SerializedName("SalesRepName")
    private String mSalesRepName;
    @SerializedName("SalesRepPhone")
    private String mSalesRepPhone;
    @SerializedName("SegelNumber")
    private String mSegelNumber;
    @SerializedName("StatusCodeDiscripton")
    private String mStatusCodeDiscripton;
    @SerializedName("StatusCodeId")
    private Integer mStatusCodeId;
    @SerializedName("TaxNumber")
    private String mTaxNumber;


    public void StatusCodeResult() {

    }

    public String getcustomeName() {
        return customeName;
    }

    public void setcustomeName(String mcustomerName) {
        this.customeName = mcustomerName;
    }

    public String getActivity() {
        return mActivity;
    }

    public void setActivity(String activity) {
        mActivity = activity;
    }

    public String getAdditionalData() {
        return mAdditionalData;
    }

    public void setAdditionalData(String additionalData) {
        mAdditionalData = additionalData;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Integer getInvoiceSerialNumber() {
        return mInvoiceSerialNumber;
    }

    public void setInvoiceSerialNumber(Integer invoiceSerialNumber) {
        mInvoiceSerialNumber = invoiceSerialNumber;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getQRCode() {
        return mQRCode;
    }

    public void setQRCode(String qRCode) {
        mQRCode = qRCode;
    }

    public String getSalesRepName() {
        return mSalesRepName;
    }

    public void setSalesRepName(String salesRepName) {
        mSalesRepName = salesRepName;
    }

    public String getSalesRepPhone() {
        return mSalesRepPhone;
    }

    public void setSalesRepPhone(String salesRepPhone) {
        mSalesRepPhone = salesRepPhone;
    }

    public String getSegelNumber() {
        return mSegelNumber;
    }

    public void setSegelNumber(String segelNumber) {
        mSegelNumber = segelNumber;
    }

    public String getStatusCodeDiscripton() {
        return mStatusCodeDiscripton;
    }

    public void setStatusCodeDiscripton(String statusCodeDiscripton) {
        mStatusCodeDiscripton = statusCodeDiscripton;
    }

    public Integer getStatusCodeId() {
        return mStatusCodeId;
    }

    public void setStatusCodeId(Integer statusCodeId) {
        mStatusCodeId = statusCodeId;
    }

    public String getTaxNumber() {
        return mTaxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        mTaxNumber = taxNumber;
    }

}
