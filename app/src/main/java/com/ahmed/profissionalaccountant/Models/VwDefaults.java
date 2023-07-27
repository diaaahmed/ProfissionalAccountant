
package com.ahmed.profissionalaccountant.Models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class VwDefaults {

    @SerializedName("BranchID")
    private Integer mBranchID;
    @SerializedName("DefaultID")
    private Integer mDefaultID;
    @SerializedName("DefaultName")
    private String mDefaultName;
    @SerializedName("ID")
    private Integer mID;
    @SerializedName("Name")
    private String mName;
    @SerializedName("SN")
    private Integer mSN;

    public Integer getBranchID() {
        return mBranchID;
    }

    public void setBranchID(Integer branchID) {
        mBranchID = branchID;
    }

    public Integer getDefaultID() {
        return mDefaultID;
    }

    public void setDefaultID(Integer defaultID) {
        mDefaultID = defaultID;
    }

    public String getDefaultName() {
        return mDefaultName;
    }

    public void setDefaultName(String defaultName) {
        mDefaultName = defaultName;
    }

    public Integer getID() {
        return mID;
    }

    public void setID(Integer iD) {
        mID = iD;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Integer getSN() {
        return mSN;
    }

    public void setSN(Integer sN) {
        mSN = sN;
    }

}
