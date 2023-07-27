package com.ahmed.profissionalaccountant.Network;

import com.ahmed.profissionalaccountant.Models.BigCenters;
import com.ahmed.profissionalaccountant.Models.Bill;
import com.ahmed.profissionalaccountant.Models.BillPaymentApi;
import com.ahmed.profissionalaccountant.Models.CheckStoreClass;
import com.ahmed.profissionalaccountant.Models.CompnyData;
import com.ahmed.profissionalaccountant.Models.InvoiceClass;
import com.ahmed.profissionalaccountant.Models.ItemUnits;
import com.ahmed.profissionalaccountant.Models.Items;
import com.ahmed.profissionalaccountant.Models.SalesRep;
import com.ahmed.profissionalaccountant.Models.StatusCodeResult;
import com.ahmed.profissionalaccountant.Models.StoreItemsInStore;
import com.ahmed.profissionalaccountant.Models.Stores;
import com.ahmed.profissionalaccountant.Models.SupplierCustomer;
import com.ahmed.profissionalaccountant.Models.TreeMain;
import com.ahmed.profissionalaccountant.Models.Units;
import com.ahmed.profissionalaccountant.Models.VwDefaults;
import com.ahmed.profissionalaccountant.Models.searchItems;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static String baseUrl="http://65.109.97.12:8080/api/CustomerSupplier/";
    private RetrofitService retrofitService;
    private static  RetrofitClient INSTANCE;

    public RetrofitClient() {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitService=  retrofit.create(RetrofitService.class);

    }

    public static RetrofitClient getINSTANCE() {
        if (null== INSTANCE)
        {
             INSTANCE= new RetrofitClient();
        }
        return INSTANCE;
    }

    public Call<CompnyData> getCompanyName (){return retrofitService.getCompanyName();}
    public Call<ArrayList<SalesRep>> getSalesReps()
    {
        return  retrofitService.GetSalesRep();
    }

    public Call<ArrayList<BigCenters>> getCenters()
    {
        return retrofitService.getCenters();
    }

    public Call<StatusCodeResult> addSupplierCustomer(SupplierCustomer supplierCustomer){
        return retrofitService.addSupplierCustomer(supplierCustomer);
    }

    public Call<ArrayList<BigCenters>> getsmallCenters(int CenterID)
    {
        return retrofitService.getsmallCenters(CenterID);
    }

    public Call<ArrayList<SupplierCustomer>> getSearchResult (searchItems searchItems){
        return retrofitService.searchCustomers( searchItems);
    }

    public Call<SupplierCustomer>getCustomer(Integer customerID)
    {
        return retrofitService.getCUstomer(customerID);
    }

    public  Call<BigCenters> getParent(Integer smallCenter)
    {
        return retrofitService.getParentCenter(smallCenter);
    }

    public Call<StatusCodeResult> EditSupplierCustomer(SupplierCustomer supplierCustomer)
    {
        return retrofitService.EditSupplierCustomer(supplierCustomer);
    }
    public Call<ArrayList<Items>> getAllItems ()
    {
        return retrofitService.getAllItems();
    }

    public Call<ArrayList<ItemUnits>> getItemUnits(Integer iteemId) {return  retrofitService.getItemUnits(iteemId);}

    public Call<ArrayList<Units>> getUnits (){return  retrofitService.getUnits();}
    public Call<ArrayList<Units>> getSpecifiedUnits (Integer itemId){return  retrofitService.getSpecifiedUnits( itemId);}

    public Call<ItemUnits> getUnitsWithUnitID (Integer itemId, Integer unitID)
    {
        return retrofitService.getSpecifiedUnitsWithUnitID(itemId, unitID);
    }

    public Call<ArrayList<Stores>> getStores (){return retrofitService.getStores();}

    public Call<Float> GetMinPeace (Integer itemid, Integer unitid){return  retrofitService.GetMinPeace(itemid, unitid);}

    public Call<ArrayList<StoreItemsInStore>> CheckStore (ArrayList<CheckStoreClass> checkStore)
    {
        return retrofitService.CheckStore(checkStore);
    }
    public  Call<ArrayList<TreeMain>> GetFundsAndBanks (boolean choose)
    {
        return retrofitService.GetFundsAndBanks(choose);
    }
    public Call<VwDefaults> GetDefaults (Integer branchId, Integer Sn)
    {
        return  retrofitService.GetDefaults(branchId, Sn);
    }

    public Call<StatusCodeResult> AddCashInvoice (InvoiceClass invoiceClass)
    {
        return  retrofitService.AddCashInvoice(invoiceClass);
    }


}
