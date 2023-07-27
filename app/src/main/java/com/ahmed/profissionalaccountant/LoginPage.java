package com.ahmed.profissionalaccountant;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmed.profissionalaccountant.Models.Branches;
import com.ahmed.profissionalaccountant.Models.CompnyData;
import com.ahmed.profissionalaccountant.Models.StatusCodeResult;
import com.ahmed.profissionalaccountant.Models.Users;
import com.ahmed.profissionalaccountant.Network.RetrofitClient;
import com.ahmed.profissionalaccountant.Network.RetrofitService;

import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPage extends AppCompatActivity  {
    static final String FILE_NAME= "com.ahmed.profissionalaccountant.MY_FILE";
    TextView userNameTv,passwordTv;
    AutoCompleteTextView spinner;
    ArrayAdapter<Branches> adapter;
    String preferredLanguage;
    ImageView companyLogo;
    Button loginBtn;
    Switch language;
    SharedPreferences sharedPreferences;
    TextView entityName;
    Integer SpinnerSelectedItemId=0;
    ArrayList<Branches> branches= new ArrayList<>();
    StatusCodeResult error= new StatusCodeResult();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences= getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

         preferredLanguage=sharedPreferences.getString("preferredLang", "");
        //Locale.getDefault().getLanguage();
        Locale myLocale = new Locale(preferredLanguage);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        conf.setLayoutDirection(new Locale(preferredLanguage));

        setContentView(R.layout.activity_login_page);
        language=findViewById(R.id.langswitch);

        if (preferredLanguage.equals("ar"))
        {
            language.setChecked(false); language.setText("English");
        }
        else {language.setChecked(true); language.setText("عربي");}
        entityName=findViewById(R.id.EntityTitle);
        userNameTv=findViewById(R.id.inputUsername);
        passwordTv=findViewById(R.id.inputPassword);
        spinner=findViewById(R.id.spinner);
        loginBtn=findViewById(R.id.Login);
        spinner.setInputType(0);
        companyLogo=findViewById(R.id.companyLogo);
        GetBranches ();GetCompanyName();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean check= CheckInputs();
                if (check)
                {
                    Users users= new Users(passwordTv.getText().toString().trim(), userNameTv.getText().toString().trim());
                    CheckLoginData(users);
                }

            }
        });
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SpinnerSelectedItemId=   adapter.getItem(position).getID();
            }
        });
        language.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               onLangSwichChange ();
           }
       });
    }

    private void GetCompanyName ()
    {
        RetrofitClient.getINSTANCE().getCompanyName().enqueue(new Callback<CompnyData>() {
            @Override
            public void onResponse(Call<CompnyData> call, Response<CompnyData> response) {
                if (response.body().getLogo()!= null && response.body().getLogo()!="")
                {
                    Picasso.get().load(response.body().getLogo()).into(companyLogo);
                    sharedPreferences.edit().putString("logo", response.body().getLogo()).commit();

                }
                if (preferredLanguage.equals("ar"))
                {
                    entityName.setText(response.body().getName());

                }
                else {
                    entityName.setText(response.body().getEngName());

                }
            }

            @Override
            public void onFailure(Call<CompnyData> call, Throwable t) {
                int y= 0;
            }
        });
    }

    private void GetBranches() {
        Retrofit GetBranchesRetrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetBranchesRetrofit.create(RetrofitService.class).GetBranches().enqueue(new Callback<ArrayList<Branches>>() {
            @Override
            public void onResponse(Call<ArrayList<Branches>> call, Response<ArrayList<Branches>> response) {
                branches=response.body();
                 adapter =new ArrayAdapter<>(LoginPage.this, R.layout.drop_down_item, branches);
                spinner.setAdapter(adapter);

            }


            @Override
            public void onFailure(Call<ArrayList<Branches>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }


    private Boolean CheckInputs ()
    {
        String userValidity= userNameTv.getText().toString().trim();
        String passwordValidity= passwordTv.getText().toString().trim();
        if(!userValidity.equals("") && !passwordValidity.equals("") && SpinnerSelectedItemId!=0)
        {
            return true;
        }
        else {
            Toast.makeText(this, getString(R.string.errorMsgCompleteData),Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void CheckLoginData (Users users)
    {

        Retrofit CheckUserRetrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CheckUserRetrofit.create(RetrofitService.class).confirmLogin(users).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
               error.setStatusCodeDiscripton(response.body().get("StatusDescription").toString());
              error.setStatusCodeId(Integer.parseInt(response.body().get("StatusCode").toString()));

              String myUser= response.body().get("StatusDescription").toString().replace('"', ' ');
                if (error.getStatusCodeId()==200)
                {
                    error.getStatusCodeDiscripton();
                    SharedPreferences sharedPreferences= getSharedPreferences(FILE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor =sharedPreferences.edit();
                    editor.putInt("BranchID" , SpinnerSelectedItemId);

                    try {
                        editor.putInt("userId", Integer.parseInt(myUser.trim()));
                    }catch (Exception e){}
                    editor.apply();


                    Intent goToApp= new Intent(LoginPage.this, MainActivity.class);
                    goToApp.putExtra("branchId", SpinnerSelectedItemId);
                    goToApp.putExtra("language", preferredLanguage);
                    startActivity(goToApp);
                    finish();
                 /*   FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("ProfissionalAccountant");
                    myRef.push().setValue(userNameTv.getText().toString());

                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                              String value = dataSnapshot.getValue(String.class);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.w(TAG, "Failed to read value.", error.toException());

                        }
                    });*/


                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginPage.this);
                    View view= LayoutInflater.from(LoginPage.this).inflate(R.layout.login_err_layout, null, false);
                    builder.setView(view);

                    TextView ErrTxt= view.findViewById(R.id.errorMsg);
                    Button DismissBtn= view.findViewById(R.id.ok);

                    AlertDialog dialog= builder.create();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.setCancelable(false);
                    dialog.show();

                    ErrTxt.setText(getResources().getString(R.string.loginError));
                    DismissBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });



                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.getStackTrace();
            }
        });




    }
    public void onLangSwichChange ()
    {
        String currentLanguage=preferredLanguage;
        if (currentLanguage=="ar")
        {
            if (language.isChecked()==true)
            {
                language.setText("English");
                preferredLanguage = "en";
                sharedPreferences.edit().putString("preferredLang", "en").commit();
                Intent i = new Intent(LoginPage.this, LoginPage.class);
                startActivity(i);
                finish();

            } else {
                language.setText("عربي");
                preferredLanguage = "ar";
                sharedPreferences.edit().putString("preferredLang", "ar").commit();
                Intent i = new Intent(LoginPage.this, LoginPage.class);
                startActivity(i);
                finish();
            }
        }
        else {
            if (language.isChecked()==false)
            {
                language.setText("عربي");
                preferredLanguage = "ar";
                sharedPreferences.edit().putString("preferredLang", "ar").commit();
                Intent i = new Intent(LoginPage.this, LoginPage.class);
                startActivity(i);
                finish();

            } else {
                language.setText("English");
                preferredLanguage = "en";
                sharedPreferences.edit().putString("preferredLang", "en").commit();
                Intent i = new Intent(LoginPage.this, LoginPage.class);
                startActivity(i);
                finish();

            }
        }
    }



}