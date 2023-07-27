
package com.ahmed.profissionalaccountant.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class InvoiceClass implements Serializable {

    @SerializedName("bill")
    private Bill Bill;
    @SerializedName("billDetailsApis")
    private ArrayList<BillDetailsApi> BillDetailsApis;
    @SerializedName("billPaymentApis")
    private ArrayList<BillPaymentApi> BillPaymentApis;

    private pdfObject pdfObject;

    public com.ahmed.profissionalaccountant.Models.pdfObject getPdfObject() {
        return pdfObject;
    }

    public void setPdfObject(com.ahmed.profissionalaccountant.Models.pdfObject pdfObject) {
        this.pdfObject = pdfObject;
    }

    public InvoiceClass(Bill mBill, ArrayList<BillDetailsApi> mBillDetailsApis) {
        this.Bill = mBill;
        this.BillDetailsApis = mBillDetailsApis;
    }

    public InvoiceClass(Bill mBill, ArrayList<BillDetailsApi> mBillDetailsApis, ArrayList<BillPaymentApi> mBillPaymentApis) {
        this.Bill = mBill;
        this.BillDetailsApis = mBillDetailsApis;
        this.BillPaymentApis = mBillPaymentApis;
    }

    public Bill getBill() {
        return Bill;
    }

    public void setBill(Bill bill) {
        Bill = bill;
    }

    public ArrayList<BillDetailsApi> getBillDetailsApis() {
        return BillDetailsApis;
    }

    public void setBillDetailsApis(ArrayList<BillDetailsApi> billDetailsApis) {
        BillDetailsApis = billDetailsApis;
    }

    public ArrayList<BillPaymentApi> getBillPaymentApis() {
        return BillPaymentApis;
    }

    public void setBillPaymentApis(ArrayList<BillPaymentApi> billPaymentApis) {
        BillPaymentApis = billPaymentApis;
    }

}
