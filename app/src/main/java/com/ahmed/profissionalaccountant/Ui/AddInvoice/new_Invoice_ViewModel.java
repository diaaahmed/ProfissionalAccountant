package com.ahmed.profissionalaccountant.Ui.AddInvoice;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ahmed.profissionalaccountant.Models.Bill;
import com.ahmed.profissionalaccountant.Models.BillPaymentApi;
import com.ahmed.profissionalaccountant.Models.CheckStoreClass;
import com.ahmed.profissionalaccountant.Models.InvoiceClass;
import com.ahmed.profissionalaccountant.Models.SalesRep;
import com.ahmed.profissionalaccountant.Models.StatusCodeResult;
import com.ahmed.profissionalaccountant.Models.StoreItemsInStore;
import com.ahmed.profissionalaccountant.Network.RetrofitClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class new_Invoice_ViewModel extends AndroidViewModel {
    MutableLiveData <ArrayList<SalesRep>> salesReps = new MutableLiveData<>();
    MutableLiveData<ArrayList<StoreItemsInStore>> checkStore = new MutableLiveData<>();
    MutableLiveData<StatusCodeResult> addPostPonedResult = new MutableLiveData<>();
    MutableLiveData <StatusCodeResult> test = new MutableLiveData<>();
    public new_Invoice_ViewModel(@NonNull Application application) {
        super(application);
    }

    public void getSalesRep ()
    {
        RetrofitClient.getINSTANCE().getSalesReps().enqueue(new Callback<ArrayList<SalesRep>>() {
            @Override
            public void onResponse(Call<ArrayList<SalesRep>> call, Response<ArrayList<SalesRep>> response) {
                salesReps.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<SalesRep>> call, Throwable t) {

            }
        });
    }

    public void CheckStore(ArrayList<CheckStoreClass> checkStoreClasses)
    {
        RetrofitClient.getINSTANCE().CheckStore(checkStoreClasses).enqueue(new Callback<ArrayList<StoreItemsInStore>>() {
            @Override
            public void onResponse(Call<ArrayList<StoreItemsInStore>> call, Response<ArrayList<StoreItemsInStore>> response) {
                checkStore.setValue( response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<StoreItemsInStore>> call, Throwable t) {

            }
        });
    }

    public void AddPostPaidInvoice (InvoiceClass invoiceClass)
    {
        RetrofitClient.getINSTANCE().AddCashInvoice(invoiceClass).enqueue(new Callback<StatusCodeResult>() {
            @Override
            public void onResponse(Call<StatusCodeResult> call, Response<StatusCodeResult> response) {
                addPostPonedResult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<StatusCodeResult> call, Throwable t) {

            }
        });
    }






}
