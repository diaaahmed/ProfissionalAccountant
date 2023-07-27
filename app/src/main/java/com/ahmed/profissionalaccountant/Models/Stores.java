
package com.ahmed.profissionalaccountant.Models;

import com.google.gson.annotations.SerializedName;


public class Stores {

    @SerializedName("BranchID")
    private Integer mBranchID;
    @SerializedName("ID")
    private Integer mID;
    @SerializedName("Name")
    private String mName;

    @Override
    public String toString() {
        return mName ;
    }

    public Integer getBranchID() {
        return mBranchID;
    }

    public void setBranchID(Integer branchID) {
        mBranchID = branchID;
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

}
