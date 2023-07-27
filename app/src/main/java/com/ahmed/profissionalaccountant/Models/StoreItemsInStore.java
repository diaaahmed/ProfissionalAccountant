
package com.ahmed.profissionalaccountant.Models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class StoreItemsInStore {

    @SerializedName("availableQuan")
    private Double mAvailableQuan;
    @SerializedName("ItemId")
    private Integer mItemId;
    @SerializedName("ItemName")
    private String mItemName;
    @SerializedName("storeId")
    private Integer mStoreId;
    @SerializedName("StoreName")
    private String mStoreName;
    @SerializedName("UnintName")
    private String mUnintName;
    @SerializedName("unitId")
    private Integer mUnitId;

    public StoreItemsInStore(Double mAvailableQuan, Integer mItemId, String mItemName, Integer mStoreId, String mStoreName, String mUnintName, Integer mUnitId) {
        this.mAvailableQuan = mAvailableQuan;
        this.mItemId = mItemId;
        this.mItemName = mItemName;
        this.mStoreId = mStoreId;
        this.mStoreName = mStoreName;
        this.mUnintName = mUnintName;
        this.mUnitId = mUnitId;
    }

    public Double getAvailableQuan() {
        return mAvailableQuan;
    }

    public void setAvailableQuan(Double availableQuan) {
        mAvailableQuan = availableQuan;
    }

    public Integer getItemId() {
        return mItemId;
    }

    public void setItemId(Integer itemId) {
        mItemId = itemId;
    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String itemName) {
        mItemName = itemName;
    }

    public Integer getStoreId() {
        return mStoreId;
    }

    public void setStoreId(Integer storeId) {
        mStoreId = storeId;
    }

    public String getStoreName() {
        return mStoreName;
    }

    public void setStoreName(String storeName) {
        mStoreName = storeName;
    }

    public String getUnintName() {
        return mUnintName;
    }

    public void setUnintName(String unintName) {
        mUnintName = unintName;
    }

    public Integer getUnitId() {
        return mUnitId;
    }

    public void setUnitId(Integer unitId) {
        mUnitId = unitId;
    }

}
