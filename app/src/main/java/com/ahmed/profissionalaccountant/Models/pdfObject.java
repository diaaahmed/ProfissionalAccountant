package com.ahmed.profissionalaccountant.Models;

import java.io.Serializable;

public class pdfObject implements Serializable {
    private String Name;
    private String Date;
    private Integer billNo;
    private String sellType;
    private String PhoneNo;
    private String TaxNo;
    private  String SegelNo;
    private Double TotalbeforeTax;
    private Double discountBeforeTax;
    private Double TaxAmount;
    private Double NetIncludeTax;
    private Double Paid;
    private Double residual;
    private String Qr;


    public pdfObject() {

    }

    public String getSegelNo() {
        return SegelNo;
    }

    public void setSegelNo(String segelNo) {
        SegelNo = segelNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Integer getBillNo() {
        return billNo;
    }

    public void setBillNo(Integer billNo) {
        this.billNo = billNo;
    }

    public String getSellType() {
        return sellType;
    }

    public void setSellType(String sellType) {
        this.sellType = sellType;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getTaxNo() {
        return TaxNo;
    }

    public void setTaxNo(String taxNo) {
        TaxNo = taxNo;
    }

    public Double getTotalbeforeTax() {
        return TotalbeforeTax;
    }

    public void setTotalbeforeTax(Double totalbeforeTax) {
        TotalbeforeTax = totalbeforeTax;
    }

    public Double getDiscountBeforeTax() {
        return discountBeforeTax;
    }

    public void setDiscountBeforeTax(Double discountBeforeTax) {
        this.discountBeforeTax = discountBeforeTax;
    }

    public Double getTaxAmount() {
        return TaxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        TaxAmount = taxAmount;
    }

    public Double getNetIncludeTax() {
        return NetIncludeTax;
    }

    public void setNetIncludeTax(Double netIncludeTax) {
        NetIncludeTax = netIncludeTax;
    }

    public Double getPaid() {
        return Paid;
    }

    public void setPaid(Double paid) {
        Paid = paid;
    }

    public Double getResidual() {
        return residual;
    }

    public void setResidual(Double residual) {
        this.residual = residual;
    }

    public String getQr() {
        return Qr;
    }

    public void setQr(String qr) {
        Qr = qr;
    }
}
