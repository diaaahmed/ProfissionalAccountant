package com.ahmed.profissionalaccountant.Ui.EditCustomer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ahmed.profissionalaccountant.Dialog.DialogListener;
import com.ahmed.profissionalaccountant.Dialog.alertDialog;
import com.ahmed.profissionalaccountant.Models.BigCenters;
import com.ahmed.profissionalaccountant.Models.SalesRep;
import com.ahmed.profissionalaccountant.Models.StatusCodeResult;
import com.ahmed.profissionalaccountant.Models.SupplierCustomer;
import com.ahmed.profissionalaccountant.R;
import com.ahmed.profissionalaccountant.Ui.SupplierCustomerFragment.SupplierCustomerViewModel;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class edit_Customer extends AppCompatActivity  implements DialogListener {
    static final String FILE_NAME= "com.ahmed.profissionalaccountant.MY_FILE";

    editCustomer_ViewModel editCustomer_viewModel;
    SupplierCustomerViewModel supplierCustomerViewModel;
    RelativeLayout title;
    Button SaveBtn;
    Integer BranchID;

    Integer smallCenterId=-1 ,smallcenterPosition=-1, bigCenterID=-1, bigCenterPosition=-1 , salesRepId=-1;
    ArrayAdapter<SalesRep> adapter; Integer position=-1;
    ArrayAdapter<BigCenters> BigAdatpter, smallAdapter;
    ArrayList<BigCenters> BigCenterArray= new ArrayList<>();
    boolean CustomerType;
    Integer CustomerId;
    alertDialog dialog;
    TextView drTv , crTv, limitTv, inputmail, inputUsername, inputnotes, inputphone,inputsegl, inputtax,inputAddress;
    boolean isFromCurrent=false;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_customer_supplier_fragment);
        editCustomer_viewModel= ViewModelProviders.of(this ).get(editCustomer_ViewModel.class);
        supplierCustomerViewModel=ViewModelProviders.of(this).get(SupplierCustomerViewModel.class);
        Integer id= getIntent().getIntExtra("customerId", 0);
        editCustomer_viewModel.getCustomer(id);
     //   supplierCustomerViewModel.getSalseRep();
      //  supplierCustomerViewModel.getCenters();

        drTv=findViewById(R.id.inputDr);
        crTv=findViewById(R.id.inputCr);
        limitTv=findViewById(R.id.inputcrLimit);
        inputmail= findViewById(R.id.inputmail);
        inputUsername= findViewById(R.id.inputUsername);
        inputnotes=findViewById(R.id.inputnotes);
        inputphone=findViewById(R.id.inputphone);
        inputsegl=findViewById(R.id.inputsegl);
        inputtax= findViewById(R.id.inputtax);
        inputAddress= findViewById(R.id.inputAddress);

        title=findViewById(R.id.edit);
        title.setVisibility(View.VISIBLE);
        SaveBtn=findViewById(R.id.addbtn);
        SaveBtn.setText(getResources().getString(R.string.save));
        AutoCompleteTextView salesList= findViewById(R.id.salesRep);
        AutoCompleteTextView BigCenter= findViewById(R.id.center);
        AutoCompleteTextView smallCenter= findViewById(R.id.smallcenter);

        editCustomer_viewModel.CustomerData.observe(this, new Observer<SupplierCustomer>() {
            @Override
            public void onChanged(SupplierCustomer supplierCustomer) {
                supplierCustomerViewModel.getSalseRep();
                supplierCustomerViewModel.getCenters();
                TextView title= (TextView) findViewById(R.id.customerName); title.setText(supplierCustomer.getName());
                EditText name= (EditText) findViewById(R.id.inputUsername); name.setText(supplierCustomer.getName());
                EditText phone= (EditText) findViewById(R.id.inputphone); phone.setText(supplierCustomer.getPhone());
                EditText address= (EditText) findViewById(R.id.inputAddress); address.setText(supplierCustomer.getAddress());
                EditText email= (EditText) findViewById(R.id.inputmail); email.setText(supplierCustomer.getMail());
                EditText tax= (EditText) findViewById(R.id.inputtax); tax.setText(supplierCustomer.getTaxNumber());
                EditText segl= (EditText) findViewById(R.id.inputsegl); segl.setText(supplierCustomer.getmSegalNumber());
                EditText dr= (EditText) findViewById(R.id.inputDr); dr.setText(supplierCustomer.getMFirst().toString());
                EditText cr= (EditText) findViewById(R.id.inputCr); cr.setText(supplierCustomer.getDFirst().toString());
                EditText limit= (EditText) findViewById(R.id.inputcrLimit); limit.setText(supplierCustomer.getCredit().toString());
                EditText note= (EditText) findViewById(R.id.inputnotes); note.setText(supplierCustomer.getNote());
                try {
                    salesRepId= supplierCustomer.getSalesRepId();
                }catch (Exception e) {salesRepId= null;}

                CustomerType= supplierCustomer.getType();

                CustomerId= supplierCustomer.getID();


                try {
                    smallCenterId= supplierCustomer.getCenterId();

                }catch (Exception e){}
            }
        });
        supplierCustomerViewModel.SalesMutableLiveData.observe(this, new Observer<ArrayList<SalesRep>>() {
            @Override
            public void onChanged(ArrayList<SalesRep> salesReps) {

                if (salesRepId!= null)
                {
                    if (salesRepId!=-1)
                    {
                        for (int i=0; i<salesReps.size(); i++)
                        {
                            if (salesReps.get(i).getID()==salesRepId)
                            {
                                position=i;
                            }
                        }
                    }

                }

                adapter =new ArrayAdapter<SalesRep>(edit_Customer.this, R.layout.drop_down_item, salesReps);
                salesList.setAdapter(adapter);
                salesList.setSelected(true);
                if (position!=-1)
                {
                    salesList.setText(salesList.getAdapter().getItem(position).toString(), false);
                }

            }
        });
        editCustomer_viewModel.ParentCenter.observe(this, new Observer<BigCenters>() {
            @Override
            public void onChanged(BigCenters bigCenters) {
                if (bigCenters!= null)
                {
                    bigCenterID= bigCenters.getID();

                }
            }
        });
        supplierCustomerViewModel.SmallCentersData.observe(this, new Observer<ArrayList<BigCenters>>() {
            @Override
            public void onChanged(ArrayList<BigCenters> bigCenters) {
                smallAdapter = new ArrayAdapter<>(edit_Customer.this, R.layout.drop_down_item, bigCenters);
                smallCenter.setAdapter(smallAdapter);
                if (smallCenterId!= null && smallCenterId != -1)
                {

                    for (int i=0; i<bigCenters.size(); i++)
                    {
                        if (bigCenters.get(i).getID()==smallCenterId)
                        {
                            smallcenterPosition=i;
                        }
                    }

                    if (smallcenterPosition!= -1)
                    {
                        smallCenter.setText(smallCenter.getAdapter().getItem(smallcenterPosition).toString(), false);

                    }

                }
                if (isFromCurrent== true)
                {
                    smallCenter.setText("", false);
                }


            }
        });
        supplierCustomerViewModel.BigCentersLiveData.observe(this, new Observer<ArrayList<BigCenters>>() {
            @Override
            public void onChanged(ArrayList<BigCenters> bigCenters) {
                BigCenterArray=bigCenters;
                BigAdatpter= new ArrayAdapter<BigCenters>(edit_Customer.this, R.layout.drop_down_item, bigCenters);
                BigCenter.setAdapter(BigAdatpter);
            }
        });
        editCustomer_viewModel.ParentCenter.observe(this, new Observer<BigCenters>() {
            @Override
            public void onChanged(BigCenters bigCenters) {
                if (bigCenters!= null)
                {

                    bigCenterID=bigCenters.getID();
                    supplierCustomerViewModel.getSmallCenter(bigCenterID);
                    for (int i=0; i<BigCenterArray.size(); i++)
                    {
                        if (BigCenterArray.get(i).getID()==bigCenterID)
                        {
                            bigCenterPosition=i;
                        }
                    }
                    if (bigCenterPosition!=-1)
                    {
                        BigCenter.setText(BigCenter.getAdapter().getItem(bigCenterPosition).toString(), false);
                    }
                }



            }
        });


        editCustomer_viewModel.result.observe(this, new Observer<StatusCodeResult>() {

            @Override
            public void onChanged(StatusCodeResult statusCodeResult) {
                if (statusCodeResult.getStatusCodeId()==200)
                {
                    dialog= new alertDialog (edit_Customer.this, statusCodeResult.getStatusCodeDiscripton(),
                            R.drawable.checked, R.color.green , edit_Customer.this);
                }
                else {
                    new alertDialog (edit_Customer.this, statusCodeResult.getStatusCodeDiscripton(), R.drawable.cross, R.color.red , edit_Customer.this);

                }
            }
        });


        BigCenter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bigCenterID=-1; isFromCurrent=true; smallCenterId=-1;
                supplierCustomerViewModel.getSmallCenter(BigAdatpter.getItem(position).getID());


            }
        });

        smallCenter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                smallCenterId= smallAdapter.getItem(position).getID();
                smallCenter.setText(smallCenter.getAdapter().getItem(0).toString(), false);

            }
        });

        salesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                salesRepId=adapter.getItem(position).getID();
            }
        });
        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });

    }


    //send data
    private void SaveData() {
        String mName = inputUsername.getText().toString();

        if (!mName.trim().equals(null) && !mName.trim().equals("")) {
            SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
            Double mCredit = 0.0;
            Double mDFirst = 0.0;
            Double mMFirst = 0.0;
            try {
                BranchID = sharedPreferences.getInt("BranchID", 0);
            } catch (Exception e) {
            }
            NumberFormat f = NumberFormat.getInstance();
            if (mCredit != 0 || mCredit != null) {
                try {
                    mCredit = f.parse(limitTv.getText().toString()).doubleValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (mDFirst != 0 || mDFirst != null) {
                try {
                    mDFirst = f.parse(crTv.getText().toString()).doubleValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (mMFirst != 0 || mMFirst != null) {
                try {
                    mMFirst = f.parse(drTv.getText().toString()).doubleValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            Boolean mIsActive = true;
            String mMail = inputmail.getText().toString();
            String mNote = inputnotes.getText().toString();
            String mPhone = inputphone.getText().toString();
            if (mPhone.equals("")) {
                mPhone = null;
            }
            String segel = inputsegl.getText().toString();
            if (segel.equals("")) {
                segel = null;
            }
            String mAddress = inputAddress.getText().toString();

            Integer mCenterId = 0, mSalesRepId = 0;


            if (smallCenterId != null && smallCenterId != -1) {
                mCenterId = smallCenterId;
            } else { mCenterId= null;}



            if (salesRepId != null && salesRepId != -1 && salesRepId!=0) {
                mSalesRepId = salesRepId;
            } else { mSalesRepId= null;}


            String mTaxNumber = inputtax.getText().toString();


            SupplierCustomer supplierCustomer = new SupplierCustomer(CustomerId, mAddress, BranchID, mCenterId,
                    mCredit, mDFirst, mIsActive, mMFirst, mMail, mName, mNote, mPhone,
                    mSalesRepId, mTaxNumber, CustomerType, segel);


            editCustomer_viewModel.Edit(supplierCustomer);


        }
        else
        {
            new alertDialog(this, getString(R.string.errorMsgCompleteCustmerName),
                    R.drawable.warning, R.color.orange, edit_Customer.this);
        }



    }

    @Override
    public void Btnonclick() {
            finish();

    }
}
