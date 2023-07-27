
package com.ahmed.profissionalaccountant.Models;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class AllAcounting {

    @SerializedName("Amount")
    private Double mAmount;
    @SerializedName("BigSanaadID")
    private Object mBigSanaadID;
    @SerializedName("BillID")
    private Long mBillID;
    @SerializedName("BranchID")
    private Long mBranchID;
    @SerializedName("Customer_id")
    private Long mCustomerId;
    @SerializedName("DateSanad")
    private String mDateSanad;
    @SerializedName("ID")
    private Long mID;
    @SerializedName("Note")
    private String mNote;
    @SerializedName("NumberOwner")
    private String mNumberOwner;
    @SerializedName("OperatorType")
    private Long mOperatorType;
    @SerializedName("ProjectID")
    private Integer mProjectID;
    @SerializedName("SN")
    private Long mSN;
    @SerializedName("SaleRep_id")
    private Integer mSaleRepId;
    @SerializedName("TreeMAin_id")
    private Long mTreeMAinId;
    @SerializedName("treeMain_id2")
    private Long mTreeMainId2;
    @SerializedName("Type")
    private Boolean mType;
    @SerializedName("User_id")
    private Long mUserId;

    public Double getAmount() {
        return mAmount;
    }

    public void setAmount(Double amount) {
        mAmount = amount;
    }

    public Object getBigSanaadID() {
        return mBigSanaadID;
    }

    public void setBigSanaadID(Object bigSanaadID) {
        mBigSanaadID = bigSanaadID;
    }

    public Long getBillID() {
        return mBillID;
    }

    public void setBillID(Long billID) {
        mBillID = billID;
    }

    public Long getBranchID() {
        return mBranchID;
    }

    public void setBranchID(Long branchID) {
        mBranchID = branchID;
    }

    public Long getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(Long customerId) {
        mCustomerId = customerId;
    }

    public String getDateSanad() {
        return mDateSanad;
    }

    public void setDateSanad(String dateSanad) {
        mDateSanad = dateSanad;
    }

    public Long getID() {
        return mID;
    }

    public void setID(Long iD) {
        mID = iD;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
    }

    public String getNumberOwner() {
        return mNumberOwner;
    }

    public void setNumberOwner(String numberOwner) {
        mNumberOwner = numberOwner;
    }

    public Long getOperatorType() {
        return mOperatorType;
    }

    public void setOperatorType(Long operatorType) {
        mOperatorType = operatorType;
    }

    public Integer getProjectID() {
        return mProjectID;
    }

    public void setProjectID(Integer projectID) {
        mProjectID = projectID;
    }

    public Long getSN() {
        return mSN;
    }

    public void setSN(Long sN) {
        mSN = sN;
    }

    public Integer getSaleRepId() {
        return mSaleRepId;
    }

    public void setSaleRepId(Integer saleRepId) {
        mSaleRepId = saleRepId;
    }

    public Long getTreeMAinId() {
        return mTreeMAinId;
    }

    public void setTreeMAinId(Long treeMAinId) {
        mTreeMAinId = treeMAinId;
    }

    public Long getTreeMainId2() {
        return mTreeMainId2;
    }

    public void setTreeMainId2(Long treeMainId2) {
        mTreeMainId2 = treeMainId2;
    }

    public Boolean getType() {
        return mType;
    }

    public void setType(Boolean type) {
        mType = type;
    }

    public Long getUserId() {
        return mUserId;
    }

    public void setUserId(Long userId) {
        mUserId = userId;
    }

}
