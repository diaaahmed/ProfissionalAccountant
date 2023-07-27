package com.ahmed.profissionalaccountant.Ui.SearchCustomerFragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmed.profissionalaccountant.Models.SupplierCustomer;
import com.ahmed.profissionalaccountant.Models.searchItems;
import com.ahmed.profissionalaccountant.Network.RetrofitClient;
import com.ahmed.profissionalaccountant.Network.RetrofitService;
import com.ahmed.profissionalaccountant.Ui.SupplierCustomerFragment.SupplierCustomerViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchCutomerViewModel extends AndroidViewModel {
    MutableLiveData<ArrayList<SupplierCustomer>> searchData= new MutableLiveData<>();

    public SearchCutomerViewModel(@NonNull Application application) {
        super(application);
    }





}
