
package com.ahmed.profissionalaccountant.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@SuppressWarnings("unused")
public class ItemUnits implements Serializable {

    @SerializedName("Count")
    private Double mCount;
    @SerializedName("ID")
    private Integer mID;
    @SerializedName("IsDefault")
    private Boolean mIsDefault;
    @SerializedName("ItemID")
    private Long mItemID;
    @SerializedName("Location")
    private String mLocation;
    @SerializedName("MaxCount")
    private Double mMaxCount;
    @SerializedName("OrderCount")
    private Double mOrderCount;
    @SerializedName("PriceCost")
    private Double mPriceCost;
    @SerializedName("PriceSale1")
    private Double mPriceSale1;
    @SerializedName("PriceSale2")
    private Double mPriceSale2;
    @SerializedName("PriceSale3")
    private Double mPriceSale3;
    @SerializedName("UnitID")
    private Integer mUnitID;
    @SerializedName("UnitID2")
    private Long mUnitID2;
    @SerializedName("unitName")
    private String mUnitName;
    @SerializedName("UnitRow")
    private Long mUnitRow;

    @Override
    public String toString() {
        return  mUnitName ;

    }

    public Double getCount() {
        return mCount;
    }

    public void setCount(Double count) {
        mCount = count;
    }

    public Integer getID() {
        return mID;
    }

    public void setID(Integer iD) {
        mID = iD;
    }

    public Boolean getIsDefault() {
        return mIsDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        mIsDefault = isDefault;
    }

    public Long getItemID() {
        return mItemID;
    }

    public void setItemID(Long itemID) {
        mItemID = itemID;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public Double getMaxCount() {
        return mMaxCount;
    }

    public void setMaxCount(Double maxCount) {
        mMaxCount = maxCount;
    }

    public Double getOrderCount() {
        return mOrderCount;
    }

    public void setOrderCount(Double orderCount) {
        mOrderCount = orderCount;
    }

    public Double getPriceCost() {
        return mPriceCost;
    }

    public void setPriceCost(Double priceCost) {
        mPriceCost = priceCost;
    }

    public Double getPriceSale1() {
        return mPriceSale1;
    }

    public void setPriceSale1(Double priceSale1) {
        mPriceSale1 = priceSale1;
    }

    public Double getPriceSale2() {
        return mPriceSale2;
    }

    public void setPriceSale2(Double priceSale2) {
        mPriceSale2 = priceSale2;
    }

    public Double getPriceSale3() {
        return mPriceSale3;
    }

    public void setPriceSale3(Double priceSale3) {
        mPriceSale3 = priceSale3;
    }

    public Integer getUnitID() {
        return mUnitID;
    }

    public void setUnitID(Integer unitID) {
        mUnitID = unitID;
    }

    public Long getUnitID2() {
        return mUnitID2;
    }

    public void setUnitID2(Long unitID2) {
        mUnitID2 = unitID2;
    }

    public String getUnitName() {
        return mUnitName;
    }

    public void setUnitName(String unitName) {
        mUnitName = unitName;
    }

    public Long getUnitRow() {
        return mUnitRow;
    }

    public void setUnitRow(Long unitRow) {
        mUnitRow = unitRow;
    }

}
