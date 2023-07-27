package com.ahmed.profissionalaccountant.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ahmed.profissionalaccountant.LoginPage;
import com.ahmed.profissionalaccountant.R;

public class FirstLogin extends AppCompatActivity {
    Button arabic, english;
    static final String FILE_NAME= "com.ahmed.profissionalaccountant.MY_FILE";
    String preferredLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);
        arabic=findViewById(R.id.arablangbtn);
        english=findViewById(R.id.englangbtn);


        SharedPreferences sharedPreferences= getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        preferredLang=  sharedPreferences.getString("preferredLang", "");

        if (preferredLang!="")
        {
           ContinueLogin();
        }


        arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putString("preferredLang", "ar").commit();
                ContinueLogin();
            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putString("preferredLang", "En").commit();
                ContinueLogin();
            }
        });


    }
    public void ContinueLogin ()
    {
        Intent goTologin = new Intent(FirstLogin.this, LoginPage.class);
        startActivity(goTologin);
        finish();
    }
}