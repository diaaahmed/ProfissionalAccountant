package com.ahmed.profissionalaccountant.Ui.cashPayment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ahmed.profissionalaccountant.Models.CompnyData;
import com.ahmed.profissionalaccountant.Models.InvoiceClass;
import com.ahmed.profissionalaccountant.Models.StatusCodeResult;
import com.ahmed.profissionalaccountant.Models.TreeMain;
import com.ahmed.profissionalaccountant.Models.VwDefaults;
import com.ahmed.profissionalaccountant.Network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class cashPaymentViewModel extends AndroidViewModel {
        MutableLiveData<ArrayList<TreeMain>> Cash= new MutableLiveData<ArrayList<TreeMain>>();
        MutableLiveData<ArrayList<TreeMain>> Banks = new MutableLiveData<ArrayList<TreeMain>>();MutableLiveData<VwDefaults> cashDefault= new MutableLiveData<>();
    MutableLiveData<VwDefaults> networkDefault= new MutableLiveData<>();
    MutableLiveData<VwDefaults> HewalaDefault= new MutableLiveData<>();
    MutableLiveData<VwDefaults> checkDefault= new MutableLiveData<>();
    MutableLiveData<StatusCodeResult> addInvoiceResult = new MutableLiveData<>();
    MutableLiveData<CompnyData> getData= new MutableLiveData<>();
    public cashPaymentViewModel(@NonNull Application application) {
        super(application);
    }


    public void GetCahFund(boolean choose)
        {
            RetrofitClient.getINSTANCE().GetFundsAndBanks(choose).enqueue(new Callback<ArrayList<TreeMain>>() {
                @Override
                public void onResponse(Call<ArrayList<TreeMain>> call, Response<ArrayList<TreeMain>> response) {
                    Cash.setValue(response.body());
                }

                @Override
                public void onFailure(Call<ArrayList<TreeMain>> call, Throwable t) {

                }
            });
        }

        public void GetBanks (boolean choose)
        {
            RetrofitClient.getINSTANCE().GetFundsAndBanks(choose).enqueue(new Callback<ArrayList<TreeMain>>() {
                @Override
                public void onResponse(Call<ArrayList<TreeMain>> call, Response<ArrayList<TreeMain>> response) {
                    Banks.setValue(response.body());
                }

                @Override
                public void onFailure(Call<ArrayList<TreeMain>> call, Throwable t) {

                }
            });
        }


        public void cashDefault (Integer branchId, Integer sn)
        {
            RetrofitClient.getINSTANCE().GetDefaults(branchId, sn).enqueue(new Callback<VwDefaults>() {
                @Override
                public void onResponse(Call<VwDefaults> call, Response<VwDefaults> response) {
                        cashDefault.setValue(response.body());
                }

                @Override
                public void onFailure(Call<VwDefaults> call, Throwable t) {

                }
            });
        }
        public void BankDefault (Integer branchId, Integer sn)
        {
        RetrofitClient.getINSTANCE().GetDefaults(branchId, sn).enqueue(new Callback<VwDefaults>() {
            @Override
            public void onResponse(Call<VwDefaults> call, Response<VwDefaults> response) {
                networkDefault.setValue(response.body());
            }

            @Override
            public void onFailure(Call<VwDefaults> call, Throwable t) {

            }
        });
    }

        public void HewalaDefault (Integer branchId, Integer sn)
        {
        RetrofitClient.getINSTANCE().GetDefaults(branchId, sn).enqueue(new Callback<VwDefaults>() {
            @Override
            public void onResponse(Call<VwDefaults> call, Response<VwDefaults> response) {
                HewalaDefault.setValue(response.body());
            }

            @Override
            public void onFailure(Call<VwDefaults> call, Throwable t) {

            }
        });
    }

        public void checkDefault (Integer branchId, Integer sn)
        {
        RetrofitClient.getINSTANCE().GetDefaults(branchId, sn).enqueue(new Callback<VwDefaults>() {
            @Override
            public void onResponse(Call<VwDefaults> call, Response<VwDefaults> response) {
                checkDefault.setValue(response.body());
            }

            @Override
            public void onFailure(Call<VwDefaults> call, Throwable t) {

            }
        });
    }
    public void AddCashInvoice (InvoiceClass invoiceClass)
    {
        RetrofitClient.getINSTANCE().AddCashInvoice(invoiceClass).enqueue(new Callback<StatusCodeResult>() {
            @Override
            public void onResponse(Call<StatusCodeResult> call, Response<StatusCodeResult> response) {
                addInvoiceResult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<StatusCodeResult> call, Throwable t) {
            Integer y =0;
            }
        });
    }

    public void getImage ()
    {
        RetrofitClient.getINSTANCE().getCompanyName().enqueue(new Callback<CompnyData>() {
            @Override
            public void onResponse(Call<CompnyData> call, Response<CompnyData> response) {
                        getData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CompnyData> call, Throwable t) {

            }
        });
    }



}
