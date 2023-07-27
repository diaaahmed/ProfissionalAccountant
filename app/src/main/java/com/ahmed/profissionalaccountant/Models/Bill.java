
package com.ahmed.profissionalaccountant.Models;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("unused")
public class Bill implements Serializable {

    @SerializedName("BillDate")
    private Date BillDate;
    @SerializedName("BillStatus")
    private Boolean BillStatus;
    @SerializedName("BillType")
    private Integer BillType;
    @SerializedName("BranchID")
    private Integer BranchID;
    @SerializedName("Discount")
    private Double Discount;
    @SerializedName("ID")
    private Integer ID;
    @SerializedName("Note1")
    private String Note1;
    @SerializedName("Note2")
    private String Note2;
    @SerializedName("NumberOwner")
    private String NumberOwner;
    @SerializedName("PriceType")
    private Integer PriceType;
    @SerializedName("ProjectCenterID")
    private Integer ProjectCenterID;
    @SerializedName("ReastType")
    private Integer ReastType;
    @SerializedName("SN")
    private Integer SN;
    @SerializedName("SalesRep_id")
    private Integer SalesRepId;
    @SerializedName("SuplierCustomer_id")
    private Integer SuplierCustomerId;
    @SerializedName("User_id")
    private Integer UserId;

    private String SalesRepName;

    public String getSalesRepName() {
        return SalesRepName;
    }

    public void setSalesRepName(String salesRepName) {
        SalesRepName = salesRepName;
    }

    public Date getBillDate() {
        return BillDate;
    }

    public void setBillDate(Date billDate) {
        BillDate = billDate;
    }

    public Boolean getBillStatus() {
        return BillStatus;
    }

    public void setBillStatus(Boolean billStatus) {
        BillStatus = billStatus;
    }

    public Integer getBillType() {
        return BillType;
    }

    public void setBillType(Integer billType) {
        BillType = billType;
    }

    public Integer getBranchID() {
        return BranchID;
    }

    public void setBranchID(Integer branchID) {
        BranchID = branchID;
    }

    public Double getDiscount() {
        return Discount;
    }

    public void setDiscount(Double discount) {
        Discount = discount;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer iD) {
        ID = iD;
    }

    public String getNote1() {
        return Note1;
    }

    public void setNote1(String note1) {
        Note1 = note1;
    }

    public String getNote2() {
        return Note2;
    }

    public void setNote2(String note2) {
        Note2 = note2;
    }

    public String getNumberOwner() {
        return NumberOwner;
    }

    public void setNumberOwner(String numberOwner) {
        NumberOwner = numberOwner;
    }

    public Integer getPriceType() {
        return PriceType;
    }

    public void setPriceType(Integer priceType) {
        PriceType = priceType;
    }

    public Integer getProjectCenterID() {
        return ProjectCenterID;
    }

    public void setProjectCenterID(Integer projectCenterID) {
        ProjectCenterID = projectCenterID;
    }

    public Integer getReastType() {
        return ReastType;
    }

    public void setReastType(Integer reastType) {
        ReastType = reastType;
    }

    public Integer getSN() {
        return SN;
    }

    public void setSN(Integer sN) {
        SN = sN;
    }

    public Integer getSalesRepId() {
        return SalesRepId;
    }

    public void setSalesRepId(Integer salesRepId) {
        SalesRepId = salesRepId;
    }

    public Integer getSuplierCustomerId() {
        return SuplierCustomerId;
    }

    public void setSuplierCustomerId(Integer suplierCustomerId) {
        SuplierCustomerId = suplierCustomerId;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

}
