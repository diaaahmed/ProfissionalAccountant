
package com.ahmed.profissionalaccountant.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class BillPaymentApi implements Serializable {

    @SerializedName("Account_id")
    private Integer mAccountId;
    @SerializedName("Amount")
    private Double mAmount;
    @SerializedName("type")
    private Integer mType;
    @SerializedName("NumberCheck")
    private String mNumberCheck;

    public BillPaymentApi(Double mAmount, String mNumberCheck) {
        this.mAmount = mAmount;
        this.mNumberCheck = mNumberCheck;
    }

    public BillPaymentApi() {

    }

    public String getmNumberCheck() {
        return mNumberCheck;
    }

    public void setmNumberCheck(String mNumberCheck) {
        this.mNumberCheck = mNumberCheck;
    }

    public Integer getAccountId() {
        return mAccountId;
    }

    public void setAccountId(Integer accountId) {
        mAccountId = accountId;
    }

    public Double getAmount() {
        return mAmount;
    }

    public void setAmount(Double amount) {
        mAmount = amount;
    }

    public Integer getType() {
        return mType;
    }

    public void setType(Integer type) {
        mType = type;
    }

}
