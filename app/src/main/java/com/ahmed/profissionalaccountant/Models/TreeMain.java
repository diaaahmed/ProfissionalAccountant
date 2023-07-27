
package com.ahmed.profissionalaccountant.Models;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

@SuppressWarnings("unused")
public class TreeMain {

    @SerializedName("AccountEnd")
    private Integer mAccountEnd;
    @SerializedName("D_First")
    private Double mDFirst;
    @SerializedName("DateOpen")
    private String mDateOpen;
    @SerializedName("ID")
    private Integer mID;
    @SerializedName("M_First")
    private Double mMFirst;
    @SerializedName("Main")
    private Integer mMain;
    @SerializedName("Name")
    private String mName;
    @SerializedName("SN")
    private Integer mSN;
    @SerializedName("Stat")
    private Boolean mStat;




    public Integer getAccountEnd() {
        return mAccountEnd;
    }

    public void setAccountEnd(Integer accountEnd) {
        mAccountEnd = accountEnd;
    }

    public Double getDFirst() {
        return mDFirst;
    }

    public void setDFirst(Double dFirst) {
        mDFirst = dFirst;
    }

    public String getDateOpen() {
        return mDateOpen;
    }

    public void setDateOpen(String dateOpen) {
        mDateOpen = dateOpen;
    }

    public Integer getID() {
        return mID;
    }

    public void setID(Integer iD) {
        mID = iD;
    }

    public Double getMFirst() {
        return mMFirst;
    }

    public void setMFirst(Double mFirst) {
        mMFirst = mFirst;
    }

    public Integer getMain() {
        return mMain;
    }

    public void setMain(Integer main) {
        mMain = main;
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

    public Boolean getStat() {
        return mStat;
    }

    public void setStat(Boolean stat) {
        mStat = stat;
    }

}
