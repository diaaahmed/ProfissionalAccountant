
package com.ahmed.profissionalaccountant.Models;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class BillDetailsApi implements Serializable {

    @SerializedName("Bill_id")
    private Integer mBillId;
    @SerializedName("Bouns")
    private Double mBouns;
    @SerializedName("CountUnit")
    private Double mCountUnit;
    @SerializedName("Discount")
    private Double mDiscount;
    @SerializedName("ID")
    private Integer mID;
    @SerializedName("Item_id")
    private Integer mItemId;
    @SerializedName("Price")
    private Double mPrice;
    @SerializedName("Quantity")
    private Double mQuantity;
    @SerializedName("Store_id")
    private Integer mStoreId;
    @SerializedName("TaxItem")
    private Double mTaxItem;
    @SerializedName("Unit_id")
    private Integer mUnitId;
    @SerializedName("Unitname")
    private String Unitname;
    @SerializedName("Itemame")
    private String Itemame ;
    @SerializedName("InvTot")
    private Double InvTot;
    @SerializedName("tempPrice")
   private Double tempPrice ;
    @SerializedName("tempTot")
    private Double tempTot;

    public BillDetailsApi() {

    }

    public Double getTempPrice() {
        return tempPrice;
    }

    public void setTempPrice(Double tempPrice) {
        this.tempPrice = tempPrice;
    }

    public Double getTempTot() {
        return tempTot;
    }

    public void setTempTot(Double tempTot) {
        this.tempTot = tempTot;
    }



    public Double getInvTot() {
        return InvTot;
    }

    public void setInvTot(Double invTot) {
        InvTot = invTot;
    }



    public String getUnitname() {
        return Unitname;
    }

    public void setUnitname(String unitname) {
        Unitname = unitname;
    }

    public String getItemame() {
        return Itemame;
    }

    public void setItemame(String itemame) {
        Itemame = itemame;
    }

    public BillDetailsApi(Integer mStoreId , Double mDiscount, Integer mItemId,
                          String itemame, Double mPrice, Double mQuantity, Double mTaxItem, Integer mUnitId, Double mCountUnit,
                          Double invTot, String munitname
                          ) {
        this.mBouns = mBouns;
        this.mCountUnit = mCountUnit;
        this.mDiscount = mDiscount;
        this.mItemId = mItemId;
        this.mPrice = mPrice;
        this.mQuantity = mQuantity;
        this.mStoreId = mStoreId;
        this.mTaxItem = mTaxItem;
        this.mUnitId = mUnitId;
        this.Unitname= munitname;
        this.Itemame= itemame;
        this.InvTot= invTot;
    }

    public Integer getBillId() {
        return mBillId;
    }

    public void setBillId(Integer billId) {
        mBillId = billId;
    }

    public Double getBouns() {
        return mBouns;
    }

    public void setBouns(Double bouns) {
        mBouns = bouns;
    }

    public Double getCountUnit() {
        return mCountUnit;
    }

    public void setCountUnit(Double countUnit) {
        mCountUnit = countUnit;
    }

    public Double getDiscount() {
        return mDiscount;
    }

    public void setDiscount(Double discount) {
        mDiscount = discount;
    }

    public Integer getID() {
        return mID;
    }

    public void setID(Integer iD) {
        mID = iD;
    }

    public Integer getItemId() {
        return mItemId;
    }

    public void setItemId(Integer itemId) {
        mItemId = itemId;
    }

    public Double getPrice() {
        return mPrice;
    }

    public void setPrice(Double price) {
        mPrice = price;
    }

    public Double getQuantity() {
        return mQuantity;
    }

    public void setQuantity(Double quantity) {
        mQuantity = quantity;
    }

    public Integer getStoreId() {
        return mStoreId;
    }

    public void setStoreId(Integer storeId) {
        mStoreId = storeId;
    }

    public Double getTaxItem() {
        return mTaxItem;
    }

    public void setTaxItem(Double taxItem) {
        mTaxItem = taxItem;
    }

    public Integer getUnitId() {
        return mUnitId;
    }

    public void setUnitId(Integer unitId) {
        mUnitId = unitId;
    }

}
