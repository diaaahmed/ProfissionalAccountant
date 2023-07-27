package com.ahmed.profissionalaccountant.Ui.print;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ahmed.profissionalaccountant.Models.Farsi;
import com.ahmed.profissionalaccountant.Models.InvoiceClass;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.BaseDirection;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;
import com.itextpdf.text.pdf.languages.LanguageProcessor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TestCreatePdf {


    public static final String maj = "res/font/arial.ttf";
    private static final DecimalFormat df = new DecimalFormat("0.00");
    String name,CompanyName , date, SalesRepName, mypayment;
    Double  net;



   // @RequiresApi(api = Build.VERSION_CODES.O)
    public void PublicCreatePdf (Context  context, InvoiceClass invoiceClass) throws IOException
    {
        df.setRoundingMode(RoundingMode.HALF_DOWN);

        int checkPermissin= ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (checkPermissin!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        }else
        {
           /* Drawable d =getDrawable(R.drawable.logo);
            Bitmap bitmap= ((BitmapDrawable) d).getBitmap();
            ByteArrayOutputStream stream= new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 10, stream);
            byte[] bitmapData= stream.toByteArray();
            ImageData imageData= ImageDataFactory.create(bitmapData);
            Image image= new Image(imageData);
            image.scaleAbsolute(150, 150);
            image.setFixedPosition(0,700);*/

            FontProgram fontProgram = FontProgramFactory.createFont(maj);

            LanguageProcessor languageProcessor = new ArabicLigaturizer();

            PdfFont font= PdfFontFactory.createFont(fontProgram, PdfEncodings.IDENTITY_H, true);
            String pdfPath= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            File file = new File(pdfPath, name+date+".pdf" );
            OutputStream outputStream= new FileOutputStream(file);

            PdfWriter writer = new PdfWriter(file);

            PdfDocument pdfDocument= new PdfDocument(writer);

            Document document= new Document(pdfDocument);
            document.setBaseDirection(BaseDirection.RIGHT_TO_LEFT);
            document.add(new Paragraph(languageProcessor.process(CompanyName)).setFont(font).setFontSize(30).setTextAlignment(TextAlignment.CENTER));
            // document.add(image);
            float[] columnWidth= {200,  100};
            Table table = new Table(columnWidth);
            table.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            table.setBaseDirection(BaseDirection.RIGHT_TO_LEFT);





            //  document.add(image);
            table.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(name))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)));
            table.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("اسم العميل:"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)));

            table.addCell(new Cell().add(new Paragraph(date).setTextAlignment(TextAlignment.RIGHT)));
            table.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("التاريخ:"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)));

            table.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(SalesRepName))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)));
            table.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("مندوب البيع:"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)));

            table.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(mypayment))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)));
            table.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("طريقة الدفع:"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)));


            float[] QcolumnWidth= {150,100,  100, 100, 100, 300};
            Table Qtable = new Table(QcolumnWidth);
            Qtable.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            Qtable.setBaseDirection(BaseDirection.RIGHT_TO_LEFT);

            Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("الإجمالي"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
            Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("الخصم"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));

            Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("السعر"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
            Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("الكمية"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
            Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("الوحدة"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
            Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("الصنف"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));


            for (Integer i = 0; i<invoiceClass.getBillDetailsApis().size() ;i++){
                Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(invoiceClass.getBillDetailsApis().get(i).getInvTot().toString()))).setFont(font).setTextAlignment(TextAlignment.CENTER)));
                Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(invoiceClass.getBillDetailsApis().get(i).getDiscount().toString()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
           //     Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(invoiceClass.getBillDetailsApis().get(i).getTempPrice().toString()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
                Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(invoiceClass.getBillDetailsApis().get(i).getQuantity().toString()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
                Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(invoiceClass.getBillDetailsApis().get(i).getUnitname()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
                Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(invoiceClass.getBillDetailsApis().get(i).getItemame()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
            }



            Qtable.addCell(new Cell().add(new Paragraph(invoiceClass.getBill().getDiscount().toString()).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)).setBackgroundColor(ColorConstants.GRAY));
            Qtable.addCell(new Cell(0,5).add(new Paragraph(languageProcessor.process(Farsi.Convert("خصم الفاتورة"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));


            Qtable.addCell(new Cell().add(new Paragraph(df.format(net).toString()).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
            Qtable.addCell(new Cell(0,5).add(new Paragraph(languageProcessor.process(Farsi.Convert("المجموع"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));


            document.add(table);
            document.add(Qtable);
            document.close();


        }



    }
}
