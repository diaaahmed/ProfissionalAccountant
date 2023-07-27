package com.ahmed.profissionalaccountant.Ui.EditCustomer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ahmed.profissionalaccountant.Models.BigCenters;
import com.ahmed.profissionalaccountant.Models.StatusCodeResult;
import com.ahmed.profissionalaccountant.Models.SupplierCustomer;
import com.ahmed.profissionalaccountant.Network.RetrofitClient;
import com.ahmed.profissionalaccountant.Ui.SupplierCustomerFragment.SupplierCustomerViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class editCustomer_ViewModel extends AndroidViewModel {
    MutableLiveData<SupplierCustomer> CustomerData = new MutableLiveData<>();
    MutableLiveData <BigCenters> ParentCenter= new MutableLiveData<>();
    MutableLiveData<StatusCodeResult> result= new MutableLiveData<>();
    SupplierCustomerViewModel supplierCustomerViewModel;
    public editCustomer_ViewModel(@NonNull Application application) {
        super(application);
    }


    public void getCustomer (Integer customerId)
    {
        RetrofitClient.getINSTANCE().getCustomer(customerId).enqueue(new Callback<SupplierCustomer>() {
            @Override
            public void onResponse(Call<SupplierCustomer> call, Response<SupplierCustomer> response) {
                CustomerData.setValue(response.body());
                getParent(response.body().getCenterId());
            }

            @Override
            public void onFailure(Call<SupplierCustomer> call, Throwable t) {

            }
        });

    }


    public void getParent (Integer SmallCenterID)
    {
        RetrofitClient.getINSTANCE().getParent(SmallCenterID).enqueue(new Callback<BigCenters>() {
            @Override
            public void onResponse(Call<BigCenters> call, Response<BigCenters> response) {
                ParentCenter.setValue(response.body());
            }

            @Override
            public void onFailure(Call<BigCenters> call, Throwable t) {

            }
        });
    }

    public void Edit (SupplierCustomer supplierCustomer)
    {
        RetrofitClient.getINSTANCE().EditSupplierCustomer(supplierCustomer).enqueue(new Callback<StatusCodeResult>() {
            @Override
            public void onResponse(Call<StatusCodeResult> call, Response<StatusCodeResult> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<StatusCodeResult> call, Throwable t) {

            }
        });

    }


}
