package com.ahmed.profissionalaccountant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ahmed.profissionalaccountant.Ui.SearchCustomerFragment.SearchCustomerFragment;
import com.ahmed.profissionalaccountant.Ui.MainFragment.Main_Fragment;
import com.ahmed.profissionalaccountant.Ui.SupplierCustomerFragment.Add_new_customer_supplier_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    RelativeLayout containerLayout;
    BottomNavigationView bottomNavigationView;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



       String myLanguage= getIntent().getStringExtra("language");
        Locale myLocale = new Locale(myLanguage);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

        conf.setLayoutDirection(new Locale(myLanguage));

        setContentView(R.layout.activity_main);
        Integer branchId= getIntent().getIntExtra("branchId", 0);
        containerLayout=findViewById(R.id.container);


        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(MainActivity.this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.NewCustomerSupplier:
                Add_new_customer_supplier_fragment fragment= new Add_new_customer_supplier_fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, "New").commit();
                break;
            case R.id.home:
                Main_Fragment NewHome= new Main_Fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, NewHome, "MNew").commit();
                break;
            case R.id.CustomerSupplier:
                SearchCustomerFragment searchCustomerFragment= new SearchCustomerFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, searchCustomerFragment, "SNew").commit();

            case R.id.more:
                int x= 0;
                //Side_Fragment side_fragment= new Side_Fragment();
               // getSupportFragmentManager().beginTransaction().replace(R.id.mylay, side_fragment, "A").commit();
                //drawerLayout.openDrawer(GravityCompat.START);
                break;


        }


        return true;
    }

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce){
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce=true;
        Toast.makeText(this, "Double click to Exit", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 1500);

    }
}