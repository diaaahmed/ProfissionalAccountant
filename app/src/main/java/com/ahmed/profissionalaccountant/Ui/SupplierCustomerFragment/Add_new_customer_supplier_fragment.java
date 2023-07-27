package com.ahmed.profissionalaccountant.Ui.SupplierCustomerFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ahmed.profissionalaccountant.Dialog.simpleAlertDialog;
import com.ahmed.profissionalaccountant.Models.BigCenters;
import com.ahmed.profissionalaccountant.Models.SalesRep;
import com.ahmed.profissionalaccountant.Models.StatusCodeResult;
import com.ahmed.profissionalaccountant.Models.SupplierCustomer;
import com.ahmed.profissionalaccountant.Network.RetrofitClient;
import com.ahmed.profissionalaccountant.PublicFunc.PublicViewModel;
import com.ahmed.profissionalaccountant.R;
import com.ahmed.profissionalaccountant.databinding.AddNewCustomerSupplierFragmentBinding;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_new_customer_supplier_fragment extends Fragment  {
    static final String FILE_NAME= "com.ahmed.profissionalaccountant.MY_FILE";
    AddNewCustomerSupplierFragmentBinding binding;
    SupplierCustomerViewModel supplierCustomerViewModel;
    PublicViewModel publicViewModel;
    ArrayAdapter <SalesRep> adapter;
    ArrayAdapter<BigCenters> smallCenterAdapter;
    ArrayAdapter <BigCenters> Centeradapter;
    Integer mBranchID=0, mCenterId=0;
    boolean Swichval= false;
    Integer salesRepId=0, CenterId=0, mSalesRepId=0;
    simpleAlertDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=AddNewCustomerSupplierFragmentBinding.inflate(inflater, container, false);
        binding.SupplierOrCustomer.setChecked(false);
        binding.smallcenter.setText("");
        binding.center.setText("");
        binding.firstLayout.setVisibility(View.VISIBLE);

        // Get Model Class
        supplierCustomerViewModel= ViewModelProviders.of(this).get(SupplierCustomerViewModel.class);
        publicViewModel =ViewModelProviders.of(this).get(PublicViewModel.class);
        SetSwitchVal();
        // get sales
        supplierCustomerViewModel.getSalseRep();
        //get centers
        supplierCustomerViewModel.getCenters();


        supplierCustomerViewModel.SalesMutableLiveData.observe(getActivity(), new Observer<ArrayList<SalesRep>>() {
            @Override
            public void onChanged(ArrayList<SalesRep> salesReps) {
                adapter =new ArrayAdapter<>(getActivity(), R.layout.drop_down_item, salesReps);
                binding.salesRep.setAdapter(adapter);
            }
        });
        supplierCustomerViewModel.BigCentersLiveData.observe(getActivity(), new Observer<ArrayList<BigCenters>>() {
            @Override
            public void onChanged(ArrayList<BigCenters> bigCenters) {

                Centeradapter=new ArrayAdapter<>(getActivity(), R.layout.drop_down_item, bigCenters);
                binding.center.setAdapter(Centeradapter);
            }
        });
        supplierCustomerViewModel.SmallCentersData.observe(getActivity(), new Observer<ArrayList<BigCenters>>() {
            @Override
            public void onChanged(ArrayList<BigCenters> bigCenters) {
                smallCenterAdapter =new ArrayAdapter<>(getActivity(), R.layout.drop_down_item, bigCenters);
                binding.smallcenter.setAdapter(smallCenterAdapter);
            }
        });

        binding.SupplierOrCustomer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SetSwitchVal();
            }
        });
        binding.salesRep.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                salesRepId=position;
            }
        });
        binding.center.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CenterId=0;
                binding.smallcenter.setText("");
                supplierCustomerViewModel.getSmallCenter(Centeradapter.getItem(position).getID());
            }
        });

        binding.smallcenter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCenterId=position;
            }
        });
        binding.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBtn ();
            }
        });
        return binding.getRoot();
    }



    public void addBtn ()
    {   String mName=binding.inputUsername.getText().toString();

        if (!mName.trim().equals(null) && !mName.trim().equals(""))
        {
        SharedPreferences sharedPreferences= getActivity().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        Double mCredit=0.0;
        Double mDFirst= 0.0;
        Double mMFirst=0.0;
         String mAddress=binding.inputAddress.getText().toString();
         try
         {
              mBranchID=  sharedPreferences.getInt("BranchID", 0);
         }catch (Exception e){}


        NumberFormat f= NumberFormat.getInstance();
         if (mCredit!=0 || mCredit!=null){
             try {
                 mCredit= f.parse(binding.inputcrLimit.getText().toString()).doubleValue();
             } catch (ParseException e) {
                 e.printStackTrace();
             }
         }
         if (mDFirst!=0 || mDFirst!=null){
             try {
                 mDFirst=f.parse(binding.inputCr.getText().toString()).doubleValue();
             } catch (ParseException e) {
                 e.printStackTrace();
             }
         }
         if (mMFirst !=0 || mMFirst!= null){
             try {
                 mMFirst= f.parse(binding.inputDr.getText().toString()).doubleValue();
             } catch (ParseException e) {
                 e.printStackTrace();
             }
         }

         Boolean mIsActive=true;
         String mMail=binding.inputmail.getText().toString();
         String mNote=binding.inputnotes.getText().toString();
         String mPhone=binding.inputphone.getText().toString();
         String segel= binding.inputsegl.getText().toString();

         if (mPhone.equals("")){mPhone=null;}
         if (segel.equals("")){segel=null;}

            if (mCenterId!=0 && mCenterId!= null)
            {
                mCenterId=smallCenterAdapter.getItem(mCenterId).getID();
            }
            if (salesRepId!=0 && salesRepId!= null)
            {
                mSalesRepId= adapter.getItem(salesRepId).getID() ;
            }
            if (mSalesRepId.intValue()==0){mSalesRepId= null;}
            if (mCenterId.intValue()==0){mCenterId= null;}

         String mTaxNumber=binding.inputtax.getText().toString();
         Boolean mType=binding.SupplierOrCustomer.isChecked();


         SupplierCustomer supplierCustomer= new SupplierCustomer(mAddress, mBranchID,mCenterId, mCredit, mDFirst
         , mIsActive, mMFirst, mMail, mName, mNote, mPhone, mSalesRepId, mTaxNumber, mType, segel);





             RetrofitClient.getINSTANCE().addSupplierCustomer(supplierCustomer).enqueue(new Callback<StatusCodeResult>() {
                 @Override
                 public void onResponse(Call<StatusCodeResult> call, Response<StatusCodeResult> response) {
                     if(response.body().getStatusCodeId()==200)

                     {
                       dialog=  new simpleAlertDialog(getActivity(), getString(R.string.addition), R.drawable.checked, R.color.green
                                 );
                         Fragment fragment= getActivity().getSupportFragmentManager().findFragmentByTag("New");
                         if (fragment != null)
                         {
                             getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                             getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                     new Add_new_customer_supplier_fragment()
                                     , "New").commit();
                         }
                     }
                     else
                     {
                         dialog= new simpleAlertDialog(getActivity(), response.body().getStatusCodeDiscripton(), R.drawable.cross, R.color.red
                                 );
                     }
                 }

                 @Override
                 public void onFailure(Call<StatusCodeResult> call, Throwable t) {
                    int  x=0;
                 }
             });

         }
         else {
            dialog= new simpleAlertDialog(getContext(), getString(R.string.errorMsgCompleteCustmerName), R.drawable.warning,
                    R.color.orange );
         }

    }



    public void SetSwitchVal ()
    {
        if (binding.SupplierOrCustomer.isChecked())
        {
            binding.FragmentTitle.setText(getResources().getString(R.string.Supplier));
            Swichval=binding.SupplierOrCustomer.isChecked();
        }
        else {
            binding.FragmentTitle.setText(getResources().getString(R.string.customer));
            Swichval=binding.SupplierOrCustomer.isChecked();
        }
    }


}
