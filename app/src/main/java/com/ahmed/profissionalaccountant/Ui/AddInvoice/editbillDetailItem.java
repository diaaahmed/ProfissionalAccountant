package com.ahmed.profissionalaccountant.Ui.AddInvoice;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ahmed.profissionalaccountant.Models.BillDetailsApi;
import com.ahmed.profissionalaccountant.Models.ItemUnits;
import com.ahmed.profissionalaccountant.Models.Stores;
import com.ahmed.profissionalaccountant.PublicFunc.PublicViewModel;
import com.ahmed.profissionalaccountant.R;
import com.ahmed.profissionalaccountant.databinding.ActivityAddItemBinding;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class editbillDetailItem extends AppCompatActivity {
    AddItemViewModel addItemViewModel;
    PublicViewModel publicViewModel;
    ActivityAddItemBinding  editbillDetailItemBinding;
    BillDetailsApi billToEdit;
    ArrayAdapter<Stores> storesArrayAdapter;
    ArrayAdapter<ItemUnits> unitsAdapter;
    Double IsTax;
    Double TempItemPrice;
    Double InvTot;
    Integer UnitID, storeId;
    Double countUnit;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final DecimalFormat dh = new DecimalFormat("0.000000");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        editbillDetailItemBinding = ActivityAddItemBinding.inflate(getLayoutInflater());
        View view = editbillDetailItemBinding.getRoot();
        setContentView(view);

        df.setRoundingMode(RoundingMode.HALF_DOWN);dh.setRoundingMode(RoundingMode.HALF_DOWN);
        addItemViewModel= ViewModelProviders.of(this).get(AddItemViewModel.class);
        publicViewModel=ViewModelProviders.of(this).get(PublicViewModel.class);
        try {billToEdit=(BillDetailsApi) getIntent().getSerializableExtra("item");
        }catch (Exception e){}
        editbillDetailItemBinding.itemsLayout.setVisibility(View.GONE);
        editbillDetailItemBinding.itemName.setText(billToEdit.getItemame());
        editbillDetailItemBinding.itemQuantity.setText(billToEdit.getQuantity().toString());
        editbillDetailItemBinding.itemDiscount.setText(billToEdit.getDiscount().toString());
        TempItemPrice= billToEdit.getPrice();
        IsTax = billToEdit.getTaxItem();
        editbillDetailItemBinding.addItemBtn.setText(R.string.edit);
        onFirstLoad();
        UnitID= billToEdit.getUnitId();
        storeId= billToEdit.getStoreId();
        countUnit=billToEdit.getCountUnit();

        addItemViewModel.getStores();
        addItemViewModel.GetItemUnits(billToEdit.getItemId());
        addItemViewModel.GetUnitsWithId(billToEdit.getItemId(), billToEdit.getUnitId());
        addItemViewModel.GetMinPeace(billToEdit.getItemId(), billToEdit.getUnitId());




        addItemViewModel.stores.observe(this, new Observer<ArrayList<Stores>>() {
            @Override
            public void onChanged(ArrayList<Stores> stores) {
                storesArrayAdapter = new ArrayAdapter<Stores>(editbillDetailItem.this, R.layout.drop_down_item, stores);
                editbillDetailItemBinding.store.setAdapter(storesArrayAdapter);
                for (int i=0; i<stores.size();i++)
                {
                    if (stores.get(i).getID()==billToEdit.getStoreId().intValue())
                    {
                        editbillDetailItemBinding.store.setText(editbillDetailItemBinding.store.getAdapter().getItem(i).toString(), false);
                    }else {
                        editbillDetailItemBinding.store.setText(
                                editbillDetailItemBinding.store.getAdapter().getItem(0).toString(), false);
                    }
                }
            }
        });
        addItemViewModel.ItemUnits.observe(this, new Observer<ArrayList<ItemUnits>>() {
            @Override
            public void onChanged(ArrayList<ItemUnits> itemUnits) {
                unitsAdapter= new ArrayAdapter<ItemUnits>(editbillDetailItem.this, R.layout.drop_down_item, itemUnits);
                editbillDetailItemBinding.units.setAdapter(unitsAdapter);
                for ( int i=0; i<itemUnits.size(); i++)
                {
                    if (itemUnits.get(i).getUnitID()== billToEdit.getUnitId().intValue())
                    {
                        editbillDetailItemBinding.units.setText(editbillDetailItemBinding.units.
                                getAdapter().getItem(i).toString(), false);
                    }
                }


            }
        });




        editbillDetailItemBinding.addOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double Quantity =
                        Double.parseDouble(editbillDetailItemBinding.itemQuantity.getText().toString().trim());
                if (Quantity >= 1 && Quantity != null) {
                    Double newQuantity = Quantity + 1;
                    editbillDetailItemBinding.itemQuantity.setText(newQuantity.toString());
                } else {
                    editbillDetailItemBinding.itemQuantity.setText("1.0");
                }
            }
        });

        editbillDetailItemBinding.MinusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double Quantity =
                        Double.parseDouble(editbillDetailItemBinding.itemQuantity.getText().toString().trim());
                if (Quantity > 1 && Quantity != null) {
                    Double newQuantity = Quantity - 1;
                    editbillDetailItemBinding.itemQuantity.setText(newQuantity.toString());
                } else {
                    editbillDetailItemBinding.itemQuantity.setText("1.0");
                }
            }
        });

        editbillDetailItemBinding.itemQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("") || s.toString()== null || s.toString().isEmpty()) {
                    editbillDetailItemBinding.itemQuantity.setText("1.0");
                }
                else {
                    onFirstLoad();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editbillDetailItemBinding.itemDiscount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("") || s.toString().isEmpty() || s.toString()== null) {
                    //activityAddItemBinding.itemDiscount.setText("0");
                }
                else {
                    onFirstLoad();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        editbillDetailItemBinding.itemPrice.addTextChangedListener(PricWatcher);

        editbillDetailItemBinding.itemPriceAfterTax.addTextChangedListener(AfterTaxWatcher);

        editbillDetailItemBinding.units.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UnitID= unitsAdapter.getItem(position).getUnitID();

                addItemViewModel.GetMinPeace(billToEdit.getItemId(), UnitID);
            }
        });

        editbillDetailItemBinding.store.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    storeId= storesArrayAdapter.getItem(position).getID();
            }
        });
        addItemViewModel.GetMinPeace.observe(this, new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                countUnit= Double.parseDouble(String.valueOf(aFloat));
            }
        });

        editbillDetailItemBinding.addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillDetailsApi bill = billsDetaislObject();
                Boolean checkDiscount, checkPrice, checkQuantity;
                checkQuantity = publicViewModel.CheckDouble(bill.getQuantity().toString());
                checkDiscount = publicViewModel.CheckDouble(bill.getDiscount().toString());
                checkPrice = publicViewModel.CheckDouble(bill.getPrice().toString());

                if (checkDiscount && checkPrice && checkQuantity) {
                    Integer pos= getIntent().getIntExtra("position",0);
                    Intent i = new Intent();
                    i.putExtra("billdetail", bill);
                    i.putExtra("position", pos);
                    setResult(2, i);
                    finish();
                }

            }
        });


    }






    public void onFirstLoad() {
        editbillDetailItemBinding.itemPrice.removeTextChangedListener(PricWatcher);
        editbillDetailItemBinding.itemPriceAfterTax.removeTextChangedListener(AfterTaxWatcher);
        Double quantity, afterTax, TotalPrice, discount=0.0, priceAfterTax;
        try
        {
            quantity = Double.parseDouble(editbillDetailItemBinding.itemQuantity.getText().toString());
        } catch (Exception e) {
            quantity = 1.0;
        }
        try
        {
            discount = (Double.parseDouble(editbillDetailItemBinding.itemDiscount.getText().toString()));
        } catch (Exception e) {
            discount = 0.0;
        }
        if (IsTax>0)
        {
            afterTax = Double.parseDouble(publicViewModel.TryArabicNumber(df.format(TempItemPrice * .15 + TempItemPrice))) ;
        }
        else
        {
            afterTax =Double.parseDouble(publicViewModel.TryArabicNumber(df.format(TempItemPrice)) );
        }


        String itemPrice = PublicViewModel.TryArabicNumber(df.format(Double.parseDouble(TempItemPrice.toString()))) ;
        String itemSumPrice= PublicViewModel.TryArabicNumber(df.format(afterTax*quantity)) ;
        TotalPrice = Double.parseDouble(itemSumPrice)- discount;

        editbillDetailItemBinding.itemPrice.setText(Double.valueOf( itemPrice).toString());
        editbillDetailItemBinding.itemPriceAfterTax.setText(afterTax.toString());
        editbillDetailItemBinding.itemsumPrice.setText(itemSumPrice);
        editbillDetailItemBinding.itemTotal.setText(PublicViewModel.TryArabicNumber( df.format(TotalPrice)));
        InvTot=TotalPrice;

        editbillDetailItemBinding.itemPrice.addTextChangedListener(PricWatcher);
      //  editbillDetailItemBinding.itemPriceAfterTax.addTextChangedListener(AfterTaxWatcher);
    }



    TextWatcher AfterTaxWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            editbillDetailItemBinding.itemPrice.removeTextChangedListener(PricWatcher);
            Double quantity=0.0, afterTax=0.0, discount= 0.0, TotalPrice=0.0, newPrice=0.0;
            try {
                afterTax= Double.parseDouble(s.toString());
            }catch (Exception e) {afterTax=0.0;}
            try {
                quantity=Double.parseDouble(editbillDetailItemBinding.itemQuantity.getText().toString());
            }catch (Exception e){quantity=0.0;}
            try {
                discount= Double.parseDouble(editbillDetailItemBinding.itemDiscount.getText().toString());
            }catch (Exception e){discount=0.0;}

            if (!editbillDetailItemBinding.itemPriceAfterTax.getText().equals("")
                    && editbillDetailItemBinding.itemPriceAfterTax.getText().toString() !=null
                    && ! editbillDetailItemBinding.itemPriceAfterTax.getText().toString().isEmpty())
            {
                if (IsTax>0)
                {
                    newPrice= Double.valueOf(Double.parseDouble(s.toString())*100/115 );
                    editbillDetailItemBinding.itemPrice.setText(publicViewModel.TryArabicNumber(df.format(newPrice)) );
                }else {
                    newPrice= Double.parseDouble(s.toString());
                    editbillDetailItemBinding.itemPrice.setText(publicViewModel.TryArabicNumber(df.format(newPrice)) );
                }
                String itemSumPrice=publicViewModel.TryArabicNumber(df.format(afterTax*quantity)) ;
                TotalPrice = Double.parseDouble(itemSumPrice)- discount;


                editbillDetailItemBinding.itemsumPrice.setText(itemSumPrice);
                editbillDetailItemBinding.itemTotal.setText(PublicViewModel.TryArabicNumber( df.format(TotalPrice)));

                editbillDetailItemBinding.itemPrice.addTextChangedListener(PricWatcher);
                TempItemPrice  = newPrice;
                InvTot=TotalPrice;
            }
            else {
                editbillDetailItemBinding.itemPrice.setText("0");
                editbillDetailItemBinding.itemTotal.setText("0");
                editbillDetailItemBinding.itemsumPrice.setText("0");
            }

        }
    };
    TextWatcher PricWatcher =new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            editbillDetailItemBinding.itemPriceAfterTax.removeTextChangedListener(PricWatcher);


        }
    };


    public BillDetailsApi billsDetaislObject ()
    {
        Double Quantity=0.0, UnitPrice=0.0, Discount=0.0, aftertax=0.0;

        String Name= editbillDetailItemBinding.itemName.getText().toString();
        Integer ItemID= billToEdit.getItemId();


        String mQuantity= editbillDetailItemBinding.itemQuantity.getText().toString();
        String mUnitPrice= editbillDetailItemBinding.itemPrice.getText().toString();
        String mDiscount= editbillDetailItemBinding.itemDiscount.getText().toString();

       String unitName= editbillDetailItemBinding.units.getText().toString();

        try {Quantity =Double.parseDouble(mQuantity);}catch (Exception E){}
        try {UnitPrice=Double.valueOf(PublicViewModel.TryArabicNumber(dh.format(TempItemPrice)) ) ;} catch (Exception e){}
        try{Discount= Double.parseDouble(mDiscount);}catch (Exception e){}

        BillDetailsApi billsDetails= new BillDetailsApi(storeId,
                Discount, ItemID, Name, UnitPrice, Quantity, .15, UnitID, countUnit  ,InvTot,unitName
      );

        return billsDetails;
    }
}
