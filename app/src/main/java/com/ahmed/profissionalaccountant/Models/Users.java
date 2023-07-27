
package com.ahmed.profissionalaccountant.Models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Users {

    @SerializedName("ALLAcountiongs")
    private List<Object> mALLAcountiongs;
    @SerializedName("Address")
    private Object mAddress;
    @SerializedName("Bills")
    private List<Object> mBills;
    @SerializedName("formsBtns")
    private List<Object> mFormsBtns;
    @SerializedName("ID")
    private Long mID;
    @SerializedName("IsActive")
    private Boolean mIsActive;
    @SerializedName("IsManger")
    private Boolean mIsManger;
    @SerializedName("MadeSanaads")
    private List<Object> mMadeSanaads;
    @SerializedName("Name")
    private String mName;
    @SerializedName("Offers")
    private List<Object> mOffers;
    @SerializedName("Password")
    private String mPassword;
    @SerializedName("Phone")
    private Object mPhone;
    @SerializedName("ProjectCenters")
    private List<Object> mProjectCenters;
    @SerializedName("Taswyas")
    private List<Object> mTaswyas;
    @SerializedName("TransferStoreDetails")
    private List<Object> mTransferStoreDetails;
    @SerializedName("Transfers")
    private List<Object> mTransfers;
    @SerializedName("UserForms")
    private List<Object> mUserForms;
    @SerializedName("UserName")
    private String mUserName;

    public Users(String mPassword, String mUserName) {
        this.mPassword = mPassword;
        this.mUserName = mUserName;
    }

    public List<Object> getALLAcountiongs() {
        return mALLAcountiongs;
    }

    public void setALLAcountiongs(List<Object> aLLAcountiongs) {
        mALLAcountiongs = aLLAcountiongs;
    }

    public Object getAddress() {
        return mAddress;
    }

    public void setAddress(Object address) {
        mAddress = address;
    }

    public List<Object> getBills() {
        return mBills;
    }

    public void setBills(List<Object> bills) {
        mBills = bills;
    }

    public List<Object> getFormsBtns() {
        return mFormsBtns;
    }

    public void setFormsBtns(List<Object> formsBtns) {
        mFormsBtns = formsBtns;
    }

    public Long getID() {
        return mID;
    }

    public void setID(Long iD) {
        mID = iD;
    }

    public Boolean getIsActive() {
        return mIsActive;
    }

    public void setIsActive(Boolean isActive) {
        mIsActive = isActive;
    }

    public Boolean getIsManger() {
        return mIsManger;
    }

    public void setIsManger(Boolean isManger) {
        mIsManger = isManger;
    }

    public List<Object> getMadeSanaads() {
        return mMadeSanaads;
    }

    public void setMadeSanaads(List<Object> madeSanaads) {
        mMadeSanaads = madeSanaads;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Object> getOffers() {
        return mOffers;
    }

    public void setOffers(List<Object> offers) {
        mOffers = offers;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public Object getPhone() {
        return mPhone;
    }

    public void setPhone(Object phone) {
        mPhone = phone;
    }

    public List<Object> getProjectCenters() {
        return mProjectCenters;
    }

    public void setProjectCenters(List<Object> projectCenters) {
        mProjectCenters = projectCenters;
    }

    public List<Object> getTaswyas() {
        return mTaswyas;
    }

    public void setTaswyas(List<Object> taswyas) {
        mTaswyas = taswyas;
    }

    public List<Object> getTransferStoreDetails() {
        return mTransferStoreDetails;
    }

    public void setTransferStoreDetails(List<Object> transferStoreDetails) {
        mTransferStoreDetails = transferStoreDetails;
    }

    public List<Object> getTransfers() {
        return mTransfers;
    }

    public void setTransfers(List<Object> transfers) {
        mTransfers = transfers;
    }

    public List<Object> getUserForms() {
        return mUserForms;
    }

    public void setUserForms(List<Object> userForms) {
        mUserForms = userForms;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

}
