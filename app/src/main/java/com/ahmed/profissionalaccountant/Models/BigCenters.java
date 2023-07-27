
package com.ahmed.profissionalaccountant.Models;

import com.google.gson.annotations.SerializedName;


public class BigCenters {

    @SerializedName("ID")
    private int mID;
    @SerializedName("Name")
    private String mName;
    @SerializedName("Perant")
    private Integer mPerant;

    public BigCenters(String mName) {
        this.mName = mName;
    }
    public BigCenters (int id, String mName) {
        this.mName= mName; this.mID= id;
    }
    @Override
    public String toString() {
        return  mName ;
    }

    public int getID() {
        return mID;
    }

    public void setID(int  iD) {
        mID = iD;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Integer getPerant() {
        return mPerant;
    }

    public void setPerant(Integer perant) {
        mPerant = perant;
    }

}
