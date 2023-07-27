package com.ahmed.profissionalaccountant.PublicFunc;

import android.icu.math.BigDecimal;
import android.icu.math.MathContext;
import android.os.Build;

import androidx.lifecycle.ViewModel;

import com.ahmed.profissionalaccountant.Models.InvoiceClass;
import com.ahmed.profissionalaccountant.Models.pdfObject;
import com.google.android.gms.common.api.Api;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class PublicViewModel extends ViewModel {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";

    public boolean CheckDouble (String mystring)
    {
        try
        {
            Double.parseDouble(mystring);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


    public boolean CheckInt (String mystring)
    {
        try
        {
            Integer.parseInt(mystring);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public Double DoubleHandling (Double myval)
    {
       try {
           return Double.valueOf(df.format(myval)) ;
       }
       catch (Exception e)
       {
            return 0.0;
       }
    }

    public static pdfObject ProcessInvoice (InvoiceClass invoiceClass)
    {   df.setRoundingMode(RoundingMode.HALF_DOWN);
        pdfObject  obj= new pdfObject();
        obj.setName(ConvertNull( invoiceClass.getPdfObject().getName()));
        obj.setBillNo(Integer.parseInt(invoiceClass.getPdfObject().getBillNo().toString()));
        obj.setQr(ConvertNull(invoiceClass.getPdfObject().getQr()) );
        obj.setSegelNo(ConvertNull(invoiceClass.getPdfObject().getSegelNo()));
        obj.setTaxNo(ConvertNull(invoiceClass.getPdfObject().getTaxNo()));


        Double NetVal=0.0, TotalIncludeTax=0.0, TotalBeforeTax=0.0, ItemsDiscount=0.0,
          TaxItems=0.0,      DiscountBeforeTax=0.0, taxAmount=0.0, PostPonedPaidAmount=0.0, residual=0.0;
        for (int i =0; i<invoiceClass.getBillDetailsApis().size(); i++)
        {
            // اجمالي سعر الوحدات قبل الضريبة
            Double tempbeforTax= Double.valueOf(PublicViewModel.TryArabicNumber(df.format((invoiceClass.getBillDetailsApis().get(i).getPrice()
                    *invoiceClass.getBillDetailsApis().get(i).getQuantity()))) );
            TotalBeforeTax += tempbeforTax;

            // اجمالي سعر الوحدات بعد الضريبة
            Double tempIncludeTax= Double.valueOf(PublicViewModel.TryArabicNumber( df.format ((invoiceClass.getBillDetailsApis().get(i).getPrice()*1.15)*
                    invoiceClass.getBillDetailsApis().get(i).getQuantity()-
                    invoiceClass.getBillDetailsApis().get(i).getDiscount())));
            TotalIncludeTax+= tempIncludeTax;
            // الضريبة المدفوعة لكل وحدات
            Double tempTaxPerItem=  Double.valueOf(PublicViewModel.TryArabicNumber( df.format (invoiceClass.getBillDetailsApis().get(i).getTaxItem()*
                    invoiceClass.getBillDetailsApis().get(i).getQuantity()*
                    invoiceClass.getBillDetailsApis().get(i).getPrice())));
            TaxItems+=tempTaxPerItem;

            Double tempItemDiscount= Double.valueOf(PublicViewModel.TryArabicNumber( df.format(invoiceClass.getBillDetailsApis().get(i).getDiscount())));
            ItemsDiscount+=tempItemDiscount;
        }
        //  قيمة الخصم قبل اضافة الضريبة
        DiscountBeforeTax= Double.valueOf(PublicViewModel.TryArabicNumber( df.format(invoiceClass.getBill().getDiscount()/1.15)));
        Double val= invoiceClass.getBill().getDiscount()-DiscountBeforeTax;
        taxAmount=Double.valueOf(PublicViewModel.TryArabicNumber( df.format(TaxItems-(val+ItemsDiscount))));
        TotalBeforeTax= Double.valueOf(PublicViewModel.TryArabicNumber( df.format(TotalBeforeTax)));
        NetVal= Double.valueOf(PublicViewModel.TryArabicNumber( df.format (TotalIncludeTax-invoiceClass.getBill().getDiscount())));
        PostPonedPaidAmount= invoiceClass.getBillPaymentApis().get(0).getAmount();
        obj.setTotalbeforeTax(TotalBeforeTax);
        obj.setTaxAmount(taxAmount);
        obj.setNetIncludeTax(NetVal);
        obj.setPaid(PostPonedPaidAmount);
        obj.setDiscountBeforeTax(DiscountBeforeTax);
        obj.setResidual(Double.valueOf(PublicViewModel.TryArabicNumber( df.format(NetVal-PostPonedPaidAmount))));
        return obj;
    }

        public static String ConvertNull (String value)
        {
            if (value== null || value=="null" || value.length()==0)
            {
                return  "";
            }
            else {return  value;}
        }



    public static String arabicToDecimal(String number) {
        char[] chars = new char[number.length()];
        for(int i=0;i<number.length();i++) {
            char ch = number.charAt(i);
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            chars[i] = ch;
        }
        return new String(chars);
    }

    public  static String TryArabicNumber(String number )
    {

        Double newNumber=0.0;
        String xs = number.replace("٠", "0")
                .replace("١", "1").replace("٢", "2").replace("٣", "3")
                .replace("٤", "4").replace("٥", "5").replace("٦", "6")
                .replace("٧", "7").replace("٨", "8").replace("٩", "9")
                .replace("٫", ".");

        return  xs;
    }

}
