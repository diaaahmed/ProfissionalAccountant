package com.ahmed.profissionalaccountant.Ui.SupplierCustomerFragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ahmed.profissionalaccountant.Models.BigCenters;
import com.ahmed.profissionalaccountant.Models.SalesRep;
import com.ahmed.profissionalaccountant.Models.StatusCodeResult;
import com.ahmed.profissionalaccountant.Models.SupplierCustomer;
import com.ahmed.profissionalaccountant.Network.RetrofitClient;
import com.ahmed.profissionalaccountant.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupplierCustomerViewModel extends AndroidViewModel  {

    public MutableLiveData<ArrayList<SalesRep>> SalesMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<BigCenters>> BigCentersLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<BigCenters>> SmallCentersData = new MutableLiveData<>();
    MutableLiveData Addresponse= new MutableLiveData();

    public SupplierCustomerViewModel(@NonNull Application application) {
        super(application);
    }

    public void getSalseRep(){
        RetrofitClient.getINSTANCE().getSalesReps().enqueue(new Callback<ArrayList<SalesRep>>() {
            @Override
            public void onResponse(Call<ArrayList<SalesRep>> call, Response<ArrayList<SalesRep>> response) {
                ArrayList<SalesRep> salesReps= new ArrayList<>();

                salesReps.add(new SalesRep(0, getApplication().getString(R.string.choose)));salesReps.addAll((ArrayList<SalesRep>) response.body());
                SalesMutableLiveData.setValue(salesReps);

            }

            @Override
            public void onFailure(Call<ArrayList<SalesRep>> call, Throwable t) {

            }
        });
    }

    public void getCenters (){
        RetrofitClient.getINSTANCE().getCenters().enqueue(new Callback<ArrayList<BigCenters>>() {
            @Override
            public void onResponse(Call<ArrayList<BigCenters>> call, Response<ArrayList<BigCenters>> response) {
                ArrayList<BigCenters> responceList = new ArrayList<>();
                responceList.add(new BigCenters(getApplication().getString(R.string.choose)));
                responceList.addAll(response.body());
                BigCentersLiveData.setValue(responceList);
            }

            @Override
            public void onFailure(Call<ArrayList<BigCenters>> call, Throwable t) {

            }
        });
    }

    public void addSupplierCustomer(SupplierCustomer supplierCustomer)
    {
        RetrofitClient.getINSTANCE().addSupplierCustomer(supplierCustomer).enqueue(new Callback<StatusCodeResult>() {
            @Override
            public void onResponse(Call<StatusCodeResult> call, Response<StatusCodeResult> response) {

                Addresponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<StatusCodeResult> call, Throwable t) {

                        t.getStackTrace();
            }
        });
    }

    public void getSmallCenter(int bigcenterId)
    {
            if (bigcenterId!= 0)
            {        RetrofitClient.getINSTANCE().getsmallCenters(bigcenterId).enqueue(new Callback<ArrayList<BigCenters>>() {
                @Override
                public void onResponse(Call<ArrayList<BigCenters>> call, Response<ArrayList<BigCenters>> response) {
                    ArrayList<BigCenters> responceList = new ArrayList<>();
                    responceList.add(new BigCenters(getApplication().getString(R.string.choose)));
                    responceList.addAll(response.body());
                    SmallCentersData.setValue(responceList);
                }

                @Override
                public void onFailure(Call<ArrayList<BigCenters>> call, Throwable t) {
                        int x =0;
                }
            });


            }
            else  {

                ArrayList<BigCenters> responceList = new ArrayList<>();
                responceList.add(new BigCenters(getApplication().getString(R.string.choose)));
                SmallCentersData.setValue(responceList);
            }


    }

}
