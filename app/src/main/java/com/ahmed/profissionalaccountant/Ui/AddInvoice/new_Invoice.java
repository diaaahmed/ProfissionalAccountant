package com.ahmed.profissionalaccountant.Ui.AddInvoice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;

import com.ahmed.profissionalaccountant.Dialog.AlertDialog_Ok_Cancel;
import com.ahmed.profissionalaccountant.Dialog.DialogListener;
import com.ahmed.profissionalaccountant.Dialog.alertDialog;
import com.ahmed.profissionalaccountant.Dialog.simpleAlertDialog;
import com.ahmed.profissionalaccountant.Models.Bill;
import com.ahmed.profissionalaccountant.Models.BillDetailsApi;
import com.ahmed.profissionalaccountant.Models.BillPaymentApi;
import com.ahmed.profissionalaccountant.Models.CheckStoreClass;
import com.ahmed.profissionalaccountant.Models.InvoiceClass;
import com.ahmed.profissionalaccountant.Models.Items;
import com.ahmed.profissionalaccountant.Models.SalesRep;
import com.ahmed.profissionalaccountant.Models.StatusCodeResult;
import com.ahmed.profissionalaccountant.Models.StoreItemsInStore;
import com.ahmed.profissionalaccountant.Models.SupplierCustomer;
import com.ahmed.profissionalaccountant.Models.pdfObject;
import com.ahmed.profissionalaccountant.Models.sellTypes;
import com.ahmed.profissionalaccountant.PublicFunc.PublicViewModel;
import com.ahmed.profissionalaccountant.R;
import com.ahmed.profissionalaccountant.Ui.cashPayment.CashPayment;
import com.ahmed.profissionalaccountant.databinding.ActivityNewInvoiceBinding;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.itextpdf.text.pdf.PdfObject;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dmax.dialog.SpotsDialog;

public class new_Invoice extends AppCompatActivity implements ItemInterface , DialogListener {
    static final String FILE_NAME= "com.ahmed.profissionalaccountant.MY_FILE";
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static Activity myActivity;
    ActivityNewInvoiceBinding activityNewInvoiceBinding;
    AddItemViewModel addItemViewModel;
    new_Invoice_ViewModel viewModel;
    SupplierCustomer supplierCustomer;
    public static ArrayList<Items> ItemsList= new ArrayList<>();
    ArrayAdapter<SalesRep> salesAdapter; Integer SalesPosition=-1;
    Integer SalesRepId; String SalesRepName;
    ArrayList<sellTypes> sellTypes= new ArrayList<>(); Integer sellType=0;
    ArrayAdapter<sellTypes> sellTypesArrayAdapter;
    AlertDialog_Ok_Cancel dialog_ok_cancel;
    AlertDialog prgessDialog;
    ArrayList<BillDetailsApi> ItemsBillsDetails= new ArrayList<>();
    ItemDetailAdapter itemDetailAdapter ;
    ArrayList<CheckStoreClass> checkCls= new ArrayList<>();
    AlertDialog prgessDialog1;
    Boolean storeCheck = false;
    Double TotalNet= 0.0;
    InvoiceClass invoiceClass;
    Bill bills= new Bill();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myActivity= this;
        activityNewInvoiceBinding= ActivityNewInvoiceBinding.inflate(getLayoutInflater());
        View view= activityNewInvoiceBinding.getRoot();
        setContentView(view);
        viewModel= ViewModelProviders.of(this).get(new_Invoice_ViewModel.class);
        addItemViewModel=ViewModelProviders.of(this).get(AddItemViewModel.class);
        viewModel.getSalesRep();
        addItemViewModel.getAllItems();
        sellTypes.add(new sellTypes(0,getString(R.string.retail)));
        sellTypes.add(new sellTypes(1,getString(R.string.wholesale)));
        sellTypes.add(new sellTypes(2,getString(R.string.merchant)));
        prgessDialog = new SpotsDialog(new_Invoice.this, R.style.Custom);
        prgessDialog.show();
        df.setRoundingMode(RoundingMode.HALF_DOWN);

        ItemTouchHelper itemTouchHelper= new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(activityNewInvoiceBinding.itemRecyler);
        prgessDialog1 = new SpotsDialog(new_Invoice.this, R.style.Custom);






        activityNewInvoiceBinding.salesType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sellType= sellTypes.get(position).getSellType();
            }
        });
        //get id and name of customer

            supplierCustomer=
                    (SupplierCustomer) getIntent().getSerializableExtra("supplierCustomer");

        activityNewInvoiceBinding.limit.setText(supplierCustomer.getCredit().toString());
        activityNewInvoiceBinding.CustomerName.setText(supplierCustomer.getName());
       // activityNewInvoiceBinding.addItem.setEnabled(false);
        viewModel.salesReps.observe(this, new Observer<ArrayList<SalesRep>>() {
            @Override
            public void onChanged(ArrayList<SalesRep> salesReps) {
                if (supplierCustomer.getSalesRepId()!= null)
                {
                    for (Integer i=0; i<salesReps.size(); i++)
                    {
                        if (salesReps.get(i).getID()== supplierCustomer.getSalesRepId())
                        {
                            SalesRepId= salesReps.get(i).getID();
                            SalesRepName=salesReps.get(i).getName();

                            SalesPosition=i;
                        }
                    }
                }else {
                   // SalesRepId=salesAdapter.getItem(0).getID();
                }
                salesAdapter= new ArrayAdapter<SalesRep>(new_Invoice.this, R.layout.drop_down_item, salesReps);
                activityNewInvoiceBinding.salesRep.setAdapter(salesAdapter);
                if (SalesPosition!=-1)
                {

                    activityNewInvoiceBinding.salesRep.setText(activityNewInvoiceBinding.salesRep.getAdapter().
                            getItem(SalesPosition).toString(),false);
                }
                else {
                    activityNewInvoiceBinding.salesRep.setText(activityNewInvoiceBinding.salesRep.getAdapter().
                            getItem(0).toString(),false);
                    SalesRepId=salesAdapter.getItem(0).getID();
                    SalesRepName= salesAdapter.getItem(0).getName();

                }
            }
        });

        activityNewInvoiceBinding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (activityNewInvoiceBinding.radioGroup.getCheckedRadioButtonId())
                {
                    case R.id.cash: activityNewInvoiceBinding.amountLayoutifpostpaid.setVisibility(View.GONE);
                    activityNewInvoiceBinding.paidAmount.setText("");
                    activityNewInvoiceBinding.DiscountofPostponed.setText("");
                    break;
                    case R.id.postpaid: activityNewInvoiceBinding.amountLayoutifpostpaid.setVisibility(View.VISIBLE);
                    break;
                }
            }
        });

        sellTypesArrayAdapter = new ArrayAdapter<sellTypes>(this, R.layout.drop_down_item, sellTypes);
        activityNewInvoiceBinding.salesType.setAdapter(sellTypesArrayAdapter);
        activityNewInvoiceBinding.salesType.setText(activityNewInvoiceBinding.salesType.getAdapter().getItem(0).toString(),false);
        activityNewInvoiceBinding.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityNewInvoiceBinding.addItem.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        activityNewInvoiceBinding.addItem.setEnabled(true);
                    }
                },2000);

                Intent i = new Intent(new_Invoice.this, Add_Item.class);
                i.putExtra("sellType",sellType );
                startActivityForResult(i, 1);
            }
        });

        addItemViewModel.items.observe(this, new Observer<ArrayList<Items>>() {
            @Override
            public void onChanged(ArrayList<Items> items) {
                ItemsList= items;
              //  activityNewInvoiceBinding.addItem.setEnabled(true);
                prgessDialog.dismiss();
            }
        });

        activityNewInvoiceBinding.salesRep.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SalesRepId= salesAdapter.getItem(position).getID();
                SalesRepName= salesAdapter.getItem(position).getName();
            }
        });
        activityNewInvoiceBinding.DiscountofPostponed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                GetArrayTotal();
            }
        });
        activityNewInvoiceBinding.proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendInvoiceData();
            }
        });
        viewModel.checkStore.observe(new_Invoice.this, new Observer<ArrayList<StoreItemsInStore>>() {
            @Override
            public void onChanged(ArrayList<StoreItemsInStore> qInStore) {
                if (qInStore.size()>0)
                {
                    storeAlert alert= new storeAlert(new_Invoice.this ,qInStore );
                }else {storeCheck= true;}
                prgessDialog1.dismiss();

                if (!bills.getBillStatus() && CheckCashInvoiceProc() &&storeCheck && ItemsBillsDetails.size()>=1)
                {
                    invoiceClass=new InvoiceClass(bills, ItemsBillsDetails );
                    Intent i= new Intent(new_Invoice.this, CashPayment.class);
                    i.putExtra("invoice", invoiceClass);
                    i.putExtra("total", TotalNet);
                    i.putExtra("name", activityNewInvoiceBinding.CustomerName.getText());
                    startActivityForResult(i, 3);
                }
                else if (bills.getBillStatus() && CheckCashInvoiceProc() &&storeCheck && ItemsBillsDetails.size()>=1)
                {
                    Double paidcash=0.0, postponeddiscount= 0.0;
                    try {paidcash= Double.valueOf(PublicViewModel.TryArabicNumber( df.format(Double.parseDouble(activityNewInvoiceBinding.paidAmount.getText().toString()))));}catch (Exception e){}
                    try {postponeddiscount=Double.valueOf(PublicViewModel.TryArabicNumber( df.format(Double.parseDouble(activityNewInvoiceBinding.DiscountofPostponed.getText().toString()))));}catch (Exception e){}
                    if  ( TotalNet-(paidcash+ postponeddiscount) <= supplierCustomer.getCredit())
                    {
                        Double discount, paidAmount;
                        try {
                            discount=Double.valueOf(PublicViewModel.TryArabicNumber( df.format(Double.parseDouble(activityNewInvoiceBinding.DiscountofPostponed.getText().toString()))));
                        }catch (Exception e){
                            discount=0.0;
                        }
                        try {
                            paidAmount=Double.valueOf(PublicViewModel.TryArabicNumber( df.format(Double.parseDouble(activityNewInvoiceBinding.paidAmount.getText().toString()))));
                        }catch (Exception e){
                            paidAmount=0.0;
                        }
                        invoiceClass= new InvoiceClass(bills, ItemsBillsDetails);
                        ArrayList<BillPaymentApi> billPaymentApi=new ArrayList<>();
                        billPaymentApi.add(new BillPaymentApi(paidAmount, Double.valueOf(PublicViewModel.TryArabicNumber(df.format(TotalNet-discount)) ).toString()));
                        invoiceClass.setBillPaymentApis(billPaymentApi);
                        bills.setDiscount(discount);
                        if(paidAmount< (TotalNet-discount))
                        {
                            viewModel.AddPostPaidInvoice(invoiceClass);
                        }
                        else {
                            new simpleAlertDialog (new_Invoice.this, getString(R.string.paidCanontexceeddue), R.drawable.warning, R.color.orange);
                        }
                    }
                    else {
                        new simpleAlertDialog (new_Invoice.this, getString(R.string.paidCanontexceedlimit), R.drawable.warning, R.color.orange);

                    }



                }
                if (ItemsBillsDetails.size()==0)
                {
                    new simpleAlertDialog (new_Invoice.this, getString(R.string.selectoneItem), R.drawable.warning, R.color.orange);
                }


            }
        });
        viewModel.addPostPonedResult.observe(this, new Observer<StatusCodeResult>() {
            @Override
            public void onChanged(StatusCodeResult statusCodeResult) {
                    if (statusCodeResult.getStatusCodeId()==200)
                    {
                        pdfObject TempObj = new pdfObject();
                        TempObj.setQr(statusCodeResult.getQRCode());
                        TempObj.setBillNo(statusCodeResult.getInvoiceSerialNumber());
                        TempObj.setPhoneNo(statusCodeResult.getPhone());
                        TempObj.setTaxNo(statusCodeResult.getTaxNumber());
                        TempObj.setSegelNo(statusCodeResult.getSegelNumber());
                        TempObj.setDate(invoiceClass.getBill().getBillDate().toString());
                        TempObj.setName(statusCodeResult.getcustomeName());

                        invoiceClass.setPdfObject(TempObj);

                        Intent i = new Intent(new_Invoice.this, CashPayment.class);
                        i.putExtra("fromOutSide", true);
                        i.putExtra("invoice", invoiceClass);
                        startActivity(i);
                        finish();

                    }
                    else {
                        new alertDialog(new_Invoice.this,
                                getString(R.string.additionerror),R.drawable.cross,R.color.red, new_Invoice.this );

                    }
            }
        });
    }
    ItemTouchHelper.SimpleCallback simpleCallback= new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int Position= viewHolder.getAdapterPosition();
            ItemsBillsDetails.remove(Position);
            itemDetailAdapter.notifyDataSetChanged();
            if (checkCls.size()>0)
            {
                checkCls.remove(Position);
            }


            if (ItemsBillsDetails.size()==0)

            {
                activityNewInvoiceBinding.salesType.setEnabled(true);
                activityNewInvoiceBinding.txtselltype.setEnabled(true);

            }
            TotalNet=0.0;
            GetArrayTotal();
        }
    };


    @Override
    public void onBackPressed() {
     dialog_ok_cancel=   new AlertDialog_Ok_Cancel(this, getString(R.string.confirmback));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ItemsList= null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== 1)
        {storeCheck=false;
            BillDetailsApi billsDetails=(BillDetailsApi) data.getSerializableExtra("billdetail");
            ItemsBillsDetails.add(billsDetails);
            activityNewInvoiceBinding.sellTypeText.setText(activityNewInvoiceBinding.salesType.getText());
            activityNewInvoiceBinding.salesType.setEnabled(false);
            activityNewInvoiceBinding.txtselltype.setEnabled(false);

            itemDetailAdapter = new ItemDetailAdapter(new_Invoice.this, ItemsBillsDetails, new_Invoice.this);
            activityNewInvoiceBinding.itemRecyler.setLayoutManager(new LinearLayoutManager(new_Invoice.this));
            activityNewInvoiceBinding.itemRecyler.setAdapter(itemDetailAdapter);
            TotalNet=0.0;
            GetArrayTotal();

        }
        if (resultCode==2)
        {storeCheck=false;
            BillDetailsApi billsDetails=(BillDetailsApi) data.getSerializableExtra("billdetail");
            Integer pos= data.getIntExtra("position", 0);
            BillDetailsApi EditedItem= ItemsBillsDetails.get(pos);

            ItemsBillsDetails.set(pos, billsDetails);
            itemDetailAdapter = new ItemDetailAdapter(new_Invoice.this, ItemsBillsDetails, new_Invoice.this);
            activityNewInvoiceBinding.itemRecyler.setLayoutManager(new LinearLayoutManager(new_Invoice.this));
            activityNewInvoiceBinding.itemRecyler.setAdapter(itemDetailAdapter);
            TotalNet=0.0;
            GetArrayTotal();

        }
    }

    public void sendInvoiceData ()
    {
        prgessDialog1.show();
         //   if (activityNewInvoiceBinding.cash.isChecked())
        //{
           bills.setBillDate(Calendar.getInstance().getTime());
            SharedPreferences sharedPreferences= getSharedPreferences(FILE_NAME, MODE_PRIVATE);
            Integer BranchId= sharedPreferences.getInt("BranchID", 0);
            Integer userId=  sharedPreferences.getInt("userId", 0);
            bills.setBranchID(BranchId);
            bills.setSalesRepId(SalesRepId);
            activityNewInvoiceBinding.radioGroup.getCheckedRadioButtonId();
            if (activityNewInvoiceBinding.radioGroup.getCheckedRadioButtonId()==R.id.cash)
            {
                bills.setBillStatus(false);
            }else {bills.setBillStatus(true);}
            bills.setPriceType(sellType);
            bills.setBillType(3);
            bills.setUserId(userId);
            bills.setSalesRepName(SalesRepName);
            bills.setSuplierCustomerId(supplierCustomer.getID());
            checkCls.clear();

            for (int i=0; i<ItemsBillsDetails.size(); i++)
            {
                BillDetailsApi bill= ItemsBillsDetails.get(i);
                checkCls.add(new CheckStoreClass(bill.getItemId(),bill.getQuantity(), bill.getStoreId(), bill.getUnitId()));
            }
            viewModel.CheckStore(checkCls);
    //    }










    }
    
    public  void  GetArrayTotal ()
    {
        Double discount;
        try {
            discount=Double.valueOf(PublicViewModel.TryArabicNumber( df.format(Double.parseDouble(activityNewInvoiceBinding.DiscountofPostponed.getText().toString()))));
        }catch (Exception e){
                discount=0.0;
        }
        TotalNet= 0.0;
        if (ItemsBillsDetails.size()>0)
        {
            for (int i=0; i<ItemsBillsDetails.size(); i++)
            {
               if (ItemsBillsDetails.get(i).getTaxItem()>0)
               {
                   String p= PublicViewModel.TryArabicNumber(PublicViewModel.TryArabicNumber( df.format((ItemsBillsDetails.get(i).getPrice()* ItemsBillsDetails.get(i).getTaxItem()+ ItemsBillsDetails.get(i).getPrice())))) ;
                   Double Dp = Double.parseDouble(p);
                   String xx= PublicViewModel.TryArabicNumber(PublicViewModel.TryArabicNumber( df.format(Dp*ItemsBillsDetails.get(i).getQuantity()-ItemsBillsDetails.get(i).getDiscount())));

                   Double TempPrice=Double.parseDouble(xx);
                   Double price= Double.valueOf(PublicViewModel.TryArabicNumber(PublicViewModel.TryArabicNumber( df.format(TempPrice))) );
                   TotalNet= TotalNet+price;
               }else {
                   Double price= Double.valueOf(PublicViewModel.TryArabicNumber(PublicViewModel.TryArabicNumber( df.format(ItemsBillsDetails.get(i).getPrice()))) );
                   TotalNet+=price;
               }
               activityNewInvoiceBinding.itemSum.setText(PublicViewModel.TryArabicNumber(PublicViewModel.TryArabicNumber( df.format(TotalNet-discount))) );
               Integer items= i+1;
               activityNewInvoiceBinding.itemCount.setText(items.toString());

            }
        }
        else  {
            activityNewInvoiceBinding.itemCount.setText("0");
            activityNewInvoiceBinding.itemSum.setText("0");

        }
    }

    public boolean CheckCashInvoiceProc ()
    {
        Integer billDetailSize=ItemsBillsDetails.size();
        return  true;
    }

    @Override
    public void onItemClick(BillDetailsApi billsDetails, Integer position) {
        Intent  i = new Intent(new_Invoice.this, editbillDetailItem.class);
        i.putExtra("position", position);
        i.putExtra("item", billsDetails);
        startActivityForResult(i, 2);
    }


    @Override
    public void Btnonclick() {

    }
}