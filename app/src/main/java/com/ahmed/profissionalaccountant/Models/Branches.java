
package com.ahmed.profissionalaccountant.Models;

import com.google.gson.annotations.SerializedName;


public class Branches {

    @SerializedName("ID")
    private int mID;
    @SerializedName("Name")
    private String mName;

    @Override
    public String toString() {
        return  mName ;
    }

    public int getID() {
        return mID;
    }

    public void setID(int iD) {
        mID = iD;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
