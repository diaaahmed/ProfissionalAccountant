
package com.ahmed.profissionalaccountant.PublicFunc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ahmed.profissionalaccountant.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class pdf_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        PDFView viewer= findViewById(R.id.pdfViewr);
        Button share_btn= findViewById(R.id.share);
        File file=(File) getIntent().getSerializableExtra("file");
        if (file!= null)
        {
            viewer.fromFile(file).enableSwipe(true).load();
        }
        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("application/pdf");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(file.getPath()));

                startActivity(Intent.createChooser(intent, getString(R.string.share)));
            }
        });
    }
}