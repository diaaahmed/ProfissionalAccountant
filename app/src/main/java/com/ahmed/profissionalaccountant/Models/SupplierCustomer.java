
package com.ahmed.profissionalaccountant.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class SupplierCustomer implements Serializable {

    @SerializedName("Address")
    private String mAddress;
    @SerializedName("BranchID")
    private int mBranchID;
    @SerializedName("Center_id")
    private Integer mCenterId;
    @SerializedName("Credit")
    private Double mCredit;
    @SerializedName("D_First")
    private Double mDFirst;
    @SerializedName("ID")
    private int mID;
    @SerializedName("IsActive")
    private Boolean mIsActive;
    @SerializedName("IsCustVendor")
    private Boolean mIsCustVendor;
    @SerializedName("M_First")
    private Double mMFirst;
    @SerializedName("Mail")
    private String mMail;
    @SerializedName("Name")
    private String mName;
    @SerializedName("Note")
    private String mNote;
    @SerializedName("Phone")
    private String mPhone;
    @SerializedName("SalesRep_id")
    private Integer mSalesRepId;
    @SerializedName("TaxNumber")
    private String mTaxNumber;
    @SerializedName("Type")
    private Boolean mType;
    @SerializedName("SegalNumber")
    private String mSegalNumber;

    public String getmSegalNumber() {
        return mSegalNumber;
    }

    public void setmSegalNumber(String mSegalNumber) {
        this.mSegalNumber = mSegalNumber;
    }

    public SupplierCustomer(String mAddress, Integer mBranchID, Integer mCenterId, Double mCredit, Double mDFirst, Boolean mIsActive, Double mMFirst, String mMail, String mName,
                            String mNote, String mPhone, Integer mSalesRepId, String mTaxNumber, Boolean mType , String mSegalNumber) {
        this.mAddress = mAddress;
        this.mBranchID = mBranchID;
        this.mCenterId = mCenterId;
        this.mCredit = mCredit;
        this.mDFirst = mDFirst;
        this.mIsActive = mIsActive;
        this.mMFirst = mMFirst;
        this.mMail = mMail;
        this.mName = mName;
        this.mIsCustVendor=false;
        this.mNote = mNote;
        this.mPhone = mPhone;
        this.mSalesRepId = mSalesRepId;
        this.mTaxNumber = mTaxNumber;
        this.mType = mType;
        this.mSegalNumber=mSegalNumber;

    }


    public SupplierCustomer(Integer mID, String mAddress, Integer mBranchID, Integer mCenterId, Double mCredit, Double mDFirst, Boolean mIsActive, Double mMFirst, String mMail, String mName,
                            String mNote, String mPhone, Integer mSalesRepId, String mTaxNumber, Boolean mType , String mSegalNumber) {
       this.mID= mID;
        this.mAddress = mAddress;
        this.mBranchID = mBranchID;
        this.mCenterId = mCenterId;
        this.mCredit = mCredit;
        this.mDFirst = mDFirst;
        this.mIsActive = mIsActive;
        this.mMFirst = mMFirst;
        this.mMail = mMail;
        this.mName = mName;
        this.mIsCustVendor=false;
        this.mNote = mNote;
        this.mPhone = mPhone;
        this.mSalesRepId = mSalesRepId;
        this.mTaxNumber = mTaxNumber;
        this.mType = mType;
        this.mSegalNumber=mSegalNumber;

    }


    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public int getBranchID() {
        return mBranchID;
    }

    public void setBranchID(int  branchID) {
        mBranchID = branchID;
    }

    public Integer getCenterId() {
        return mCenterId;
    }

    public void setCenterId(Integer centerId) {
        mCenterId = centerId;
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

    public Boolean getIsCustVendor() {
        return mIsCustVendor;
    }

    public void setIsCustVendor(Boolean isCustVendor) {
        mIsCustVendor = isCustVendor;
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

    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public Integer getSalesRepId() {
        return mSalesRepId;
    }

    public void setSalesRepId(Integer    salesRepId) {
        mSalesRepId = salesRepId;
    }

    public String getTaxNumber() {
        return mTaxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        mTaxNumber = taxNumber;
    }

    public Boolean getType() {
        return mType;
    }

    public void setType(Boolean type) {
        mType = type;
    }

}
