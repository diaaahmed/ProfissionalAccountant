package com.ahmed.profissionalaccountant.Ui.AddInvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;

import com.ahmed.profissionalaccountant.Models.InvoiceClass;
import com.ahmed.profissionalaccountant.R;
import com.ahmed.profissionalaccountant.Ui.print.TestCreatePdf;

import java.io.IOException;

public class PrintPostponed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_postponed);

        ImageButton CreatePdf= (ImageButton) findViewById(R.id.createPdf);
        InvoiceClass invoiceClass=(InvoiceClass) getIntent().getSerializableExtra("invoiceClass");


        CreatePdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestCreatePdf t= new TestCreatePdf();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    try {
                        t.PublicCreatePdf(PrintPostponed.this,invoiceClass );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}