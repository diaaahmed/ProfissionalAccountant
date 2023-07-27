
package com.ahmed.profissionalaccountant.Models;


import com.google.gson.annotations.SerializedName;


public class CheckStoreClass {

    @SerializedName("itemId")
    private Integer mItemId;
    @SerializedName("requiredQuantity")
    private Double mRequiredQuantity;
    @SerializedName("storeId")
    private Integer mStoreId;
    @SerializedName("unitId")
    private Integer mUnitId;

    public CheckStoreClass(Integer mItemId, Double mRequiredQuantity, Integer mStoreId, Integer mUnitId) {
        this.mItemId = mItemId;
        this.mRequiredQuantity = mRequiredQuantity;
        this.mStoreId = mStoreId;
        this.mUnitId = mUnitId;
    }

    public Integer getItemId() {
        return mItemId;
    }

    public void setItemId(Integer itemId) {
        mItemId = itemId;
    }

    public Double getRequiredQuantity() {
        return mRequiredQuantity;
    }

    public void setRequiredQuantity(Double requiredQuantity) {
        mRequiredQuantity = requiredQuantity;
    }

    public Integer getStoreId() {
        return mStoreId;
    }

    public void setStoreId(Integer storeId) {
        mStoreId = storeId;
    }

    public Integer getUnitId() {
        return mUnitId;
    }

    public void setUnitId(Integer unitId) {
        mUnitId = unitId;
    }

}
