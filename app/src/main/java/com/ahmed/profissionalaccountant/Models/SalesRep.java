
package com.ahmed.profissionalaccountant.Models;


import com.google.gson.annotations.SerializedName;
public class SalesRep {

    @SerializedName("Address")
    private String mAddress;
    @SerializedName("Bouns")
    private Double mBouns;
    @SerializedName("Credit")
    private Double mCredit;
    @SerializedName("D_First")
    private Double mDFirst;
    @SerializedName("ID")
    private int mID;
    @SerializedName("IsActive")
    private Boolean mIsActive;
    @SerializedName("M_First")
    private Double mMFirst;
    @SerializedName("Mail")
    private String mMail;
    @SerializedName("Name")
    private String mName;
    @SerializedName("Phone")
    private String mPhone;

    public SalesRep()
    {

    }

    public SalesRep(String mName) {
        this.mName = mName;
    }
    public SalesRep(Integer mID){
        this.mID= mID;
    }

    public SalesRep(int mID, String mName) {
        this.mID = mID;
        this.mName = mName;
    }

    public SalesRep(String mAddress, Double mBouns, Double mCredit, Double mDFirst, int mID, Boolean mIsActive, Double mMFirst, String mMail, String mName, String mPhone) {
        this.mAddress = mAddress;
        this.mBouns = mBouns;
        this.mCredit = mCredit;
        this.mDFirst = mDFirst;
        this.mID = mID;
        this.mIsActive = mIsActive;
        this.mMFirst = mMFirst;
        this.mMail = mMail;
        this.mName = mName;
        this.mPhone = mPhone;
    }

    @Override
    public String toString() {
        return mName ;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Double getBouns() {
        return mBouns;
    }

    public void setBouns(Double bouns) {
        mBouns = bouns;
    }

    public Double getCredit() {
        return mCredit;
    }

    public void setCredit(Double credit) {
        mCredit = credit;
    }

    public Double getDFirst() {
        return mDFirst;
    }

    public void setDFirst(Double dFirst) {
        mDFirst = dFirst;
    }

    public int getID() {
        return mID;
    }

    public void setID(int iD) {
        mID = iD;
    }

    public Boolean getIsActive() {
        return mIsActive;
    }

    public void setIsActive(Boolean isActive) {
        mIsActive = isActive;
    }

    public Double getMFirst() {
        return mMFirst;
    }

    public void setMFirst(Double mFirst) {
        mMFirst = mFirst;
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

}
