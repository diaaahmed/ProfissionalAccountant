package com.ahmed.profissionalaccountant.Network;

import com.ahmed.profissionalaccountant.Models.Bill;
import com.ahmed.profissionalaccountant.Models.BillPaymentApi;
import com.ahmed.profissionalaccountant.Models.Branches;
import com.ahmed.profissionalaccountant.Models.CheckStoreClass;
import com.ahmed.profissionalaccountant.Models.CompnyData;
import com.ahmed.profissionalaccountant.Models.InvoiceClass;
import com.ahmed.profissionalaccountant.Models.ItemUnits;
import com.ahmed.profissionalaccountant.Models.Items;
import com.ahmed.profissionalaccountant.Models.StatusCodeResult;
import com.ahmed.profissionalaccountant.Models.StoreItemsInStore;
import com.ahmed.profissionalaccountant.Models.Stores;
import com.ahmed.profissionalaccountant.Models.TreeMain;
import com.ahmed.profissionalaccountant.Models.Units;
import com.ahmed.profissionalaccountant.Models.Users;
import com.ahmed.profissionalaccountant.Models.BigCenters;
import com.ahmed.profissionalaccountant.Models.SalesRep;
import com.ahmed.profissionalaccountant.Models.SupplierCustomer;
import com.ahmed.profissionalaccountant.Models.VwDefaults;
import com.ahmed.profissionalaccountant.Models.searchItems;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET ("GetAllBranches")
    Call<ArrayList<Branches>> GetBranches ();



    @POST("ConfirmUsers")
    Call<JsonObject> confirmLogin (@Body Users users);
    @GET("GetCompanyName")
    Call<CompnyData> getCompanyName ();

    @GET("GetAllSalesRep")
    Call<ArrayList<SalesRep>> GetSalesRep();

    @GET("GetAllCenters")
     Call<ArrayList<BigCenters>> getCenters();

    @POST ("AddNewSupplierCustomer")
    Call<StatusCodeResult> addSupplierCustomer(@Body SupplierCustomer supplierCustomer);

    @GET("GetSmallCenters")
    Call<ArrayList<BigCenters>> getsmallCenters(@Query("bigCenterId") int bigCenterId);

    @POST ("SearchSupplierCustomer")
    Call<ArrayList<SupplierCustomer>> searchCustomers (@Body searchItems searchItems);

    @POST("GetCustomer")
    Call<SupplierCustomer> getCUstomer(@Query("CustomerID") Integer customerId);

    @GET("GetParentCenter")
    Call<BigCenters> getParentCenter(@Query("ParentsmallCenterId")Integer ParentsmallCenterId);

    @POST("EditCustomer")
    Call<StatusCodeResult> EditSupplierCustomer(@Body SupplierCustomer supplierCustomer);

    @POST ("AllItems")
    Call<ArrayList<Items>> getAllItems ();

    @POST("GetItemUnits")
    Call<ArrayList<ItemUnits>> getItemUnits (@Query("ItemId")Integer ItemId);
    @POST ("GetUnits")
    Call<ArrayList<Units>> getUnits();

    @POST ("GetSpecifiedUnits")
    Call<ArrayList<Units>> getSpecifiedUnits(@Query("ItemId")Integer ItemId);

    @POST ("GetItemUnitswithUnitId")
    Call<ItemUnits> getSpecifiedUnitsWithUnitID(@Query("ItemId")Integer ItemId,@Query("UnitID") Integer UnitID);

    @POST("GetSores")
    Call<ArrayList<Stores>> getStores();

    @POST("GetMinPeace")
    Call<Float> GetMinPeace (@Query("itemid") Integer itemid, @Query("unitid") Integer unitid);

    @POST("CheckStore")
    Call<ArrayList<StoreItemsInStore>> CheckStore (@Body ArrayList<CheckStoreClass> checkStoreClasses);

    @POST("GetFundsAndBanks")
    Call<ArrayList<TreeMain>> GetFundsAndBanks (@Query("choose") boolean choose);
    @POST("GetDefaults")
    Call<VwDefaults> GetDefaults (@Query("branchId") Integer branchId, @Query("sn") Integer sn);

    @POST("AddCashInvoice")
    Call<StatusCodeResult> AddCashInvoice ( @Body InvoiceClass invoiceClass);




}
