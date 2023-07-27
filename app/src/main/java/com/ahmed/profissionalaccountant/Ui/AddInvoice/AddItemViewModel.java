package com.ahmed.profissionalaccountant.Ui.AddInvoice;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ahmed.profissionalaccountant.Models.ItemUnits;
import com.ahmed.profissionalaccountant.Models.Items;
import com.ahmed.profissionalaccountant.Models.Stores;
import com.ahmed.profissionalaccountant.Models.Units;
import com.ahmed.profissionalaccountant.Network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddItemViewModel extends AndroidViewModel {
    MutableLiveData<ArrayList<Items>> items= new MutableLiveData<>();
    MutableLiveData <ArrayList<Units>> units = new MutableLiveData<>();
    MutableLiveData <ItemUnits> unitsWithId = new MutableLiveData<>();
    MutableLiveData <ArrayList<ItemUnits>> ItemUnits= new MutableLiveData<>();
    MutableLiveData <ArrayList<Stores>> stores= new MutableLiveData<>();
    MutableLiveData<Float> GetMinPeace= new MutableLiveData<>();
    public AddItemViewModel(@NonNull Application application) {
        super(application);
    }

    public void getAllItems()
    {
        RetrofitClient.getINSTANCE().getAllItems().enqueue(new Callback<ArrayList<Items>>() {
            @Override
            public void onResponse(Call<ArrayList<Items>> call, Response<ArrayList<Items>> response) {
                items.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Items>> call, Throwable t) {

            }
        });
    }

    public void GetUnitsTypes ()
    {
        RetrofitClient.getINSTANCE().getUnits().enqueue(new Callback<ArrayList<Units>>() {
            @Override
            public void onResponse(Call<ArrayList<Units>> call, Response<ArrayList<Units>> response) {
                units.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Units>> call, Throwable t) {
                Integer i =0;
            }
        });
    }

    public void GetItemUnits (Integer ItemId){
        RetrofitClient.getINSTANCE().getItemUnits(ItemId).enqueue(new Callback<ArrayList<ItemUnits>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemUnits>> call, Response<ArrayList<ItemUnits>> response) {
                ItemUnits.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<ItemUnits>> call, Throwable t) {

            }
        });
    }

    public void GetSpecifiedUnits (Integer ItemId)
    {
        RetrofitClient.getINSTANCE().getSpecifiedUnits(ItemId).enqueue(new Callback<ArrayList<Units>>() {
            @Override
            public void onResponse(Call<ArrayList<Units>> call, Response<ArrayList<Units>> response) {
                units.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Units>> call, Throwable t) {
                        Integer x =0;
            }
        });
    }

    public void GetUnitsWithId (Integer ItemId, Integer UnitID)
    {
        RetrofitClient.getINSTANCE().getUnitsWithUnitID(ItemId, UnitID).enqueue(new Callback<ItemUnits>() {
            @Override
            public void onResponse(Call<ItemUnits> call, Response<ItemUnits> response) {
                unitsWithId.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ItemUnits> call, Throwable t) {

            }
        });
    }
    public void getStores()
    {
        RetrofitClient.getINSTANCE().getStores().enqueue(new Callback<ArrayList<Stores>>() {
            @Override
            public void onResponse(Call<ArrayList<Stores>> call, Response<ArrayList<Stores>> response) {
                    stores.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Stores>> call, Throwable t) {

            }
        });
    }

    public void GetMinPeace (Integer itemid, Integer unitid)
    {
        RetrofitClient.getINSTANCE().GetMinPeace(itemid, unitid).enqueue(new Callback<Float>() {
            @Override
            public void onResponse(Call<Float> call, Response<Float> response) {
                    GetMinPeace.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Float> call, Throwable t) {
            }
        });
    }


}
