package com.ahmed.profissionalaccountant.Ui.AddInvoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.ahmed.profissionalaccountant.Dialog.simpleAlertDialog;
import com.ahmed.profissionalaccountant.Models.BillDetailsApi;
import com.ahmed.profissionalaccountant.Models.ItemUnits;
import com.ahmed.profissionalaccountant.Models.Items;
import com.ahmed.profissionalaccountant.Models.Stores;
import com.ahmed.profissionalaccountant.Models.Units;
import com.ahmed.profissionalaccountant.PublicFunc.PublicViewModel;
import com.ahmed.profissionalaccountant.R;
import com.ahmed.profissionalaccountant.databinding.ActivityAddItemBinding;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

public class Add_Item extends AppCompatActivity {
    ActivityAddItemBinding activityAddItemBinding;
    AddItemViewModel addItemViewModel;
    PublicViewModel publicViewModel;
    ArrayAdapter<Items> itemsArrayAdapter;
    ArrayList<Items> ItemsList;
    Integer ItemId = -1;
    String ItemName;
    ArrayAdapter<Stores> storesArrayAdapter;
    Integer sellType;
    Integer defaultUnitId=0, UnitId=0, storeId=0;
    boolean IsTax;
    Double countUnit;
    String UnitName;
    Double TempItemPrice=0.0, ItemTax=0.0;
    Double InvTot=0.0, tempPrice=0.0;



    private static final DecimalFormat df = new DecimalFormat("0.00");

    private static final DecimalFormat dh = new DecimalFormat("0.000000");

    private static  NumberFormat myf= DecimalFormat.getInstance(Locale.US);

    ArrayAdapter<Units> unitsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddItemBinding = ActivityAddItemBinding.inflate(getLayoutInflater());
        View view = activityAddItemBinding.getRoot();
        setContentView(view);
        addItemViewModel = ViewModelProviders.of(this).get(AddItemViewModel.class);
        publicViewModel = ViewModelProviders.of(this).get(PublicViewModel.class);
        sellType = getIntent().getIntExtra("sellType", 0);
        df.setRoundingMode(RoundingMode.HALF_DOWN);
        dh.setRoundingMode(RoundingMode.UP);
        ItemsList = new_Invoice.ItemsList;
        itemsArrayAdapter = new ArrayAdapter<>(this, R.layout.drop_down_item, ItemsList);
        activityAddItemBinding.AllItems.setAdapter(itemsArrayAdapter);


        myf= new DecimalFormat("0.00");

        addItemViewModel.units.observe(this, new Observer<ArrayList<Units>>() {
            @Override
            public void onChanged(ArrayList<Units> units) {
                unitsAdapter = new ArrayAdapter<>(Add_Item.this, R.layout.drop_down_item, units);
                activityAddItemBinding.units.setAdapter(unitsAdapter);
                for (int i = 0; i < units.size(); i++) {
                    if (units.get(i).getID() == defaultUnitId.intValue()) {
                        activityAddItemBinding.units.
                                setText(activityAddItemBinding.units.getAdapter().getItem(i).toString(), false);
                    }
                }

            }
        });
        activityAddItemBinding.AllItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Items Item = itemsArrayAdapter.getItem(position);

                ItemName = itemsArrayAdapter.getItem(position).getNameArab();
                ItemId = itemsArrayAdapter.getItem(position).getID();
                IsTax = Item.getIsTax();
                addItemViewModel.GetItemUnits(ItemId);
                addItemViewModel.getStores();

                Items item = itemsArrayAdapter.getItem(position);
                activityAddItemBinding.itemName.setText(itemsArrayAdapter.getItem(position).getNameArab());
            }
        });
        addItemViewModel.ItemUnits.observe(this, new Observer<ArrayList<ItemUnits>>() {
            @Override
            public void onChanged(ArrayList<ItemUnits> itemUnits) {
                addItemViewModel.GetSpecifiedUnits(ItemId);

                if (itemUnits.size() > 0) {

                    for (int i = 0; i < itemUnits.size(); i++) {

                        if (itemUnits.get(i).getIsDefault()) {
                            defaultUnitId = itemUnits.get(i).getUnitID();
                            addItemViewModel.GetMinPeace(ItemId, defaultUnitId);
                            UnitId = defaultUnitId;
                            UnitName = itemUnits.get(i).getUnitName();
                            switch (sellType) {
                                case 0:
                                    try {
                                        activityAddItemBinding.itemPrice.
                                                setText(itemUnits.get(i).getPriceSale1().toString());
                                        TempItemPrice = itemUnits.get(i).getPriceSale1();
                                        break;
                                    }catch (Exception e){
                                        e.printStackTrace();
                                        break;
                                    }

                                case 1:
                                    activityAddItemBinding.itemPrice.
                                            setText(itemUnits.get(i).getPriceSale2().toString());
                                    TempItemPrice = itemUnits.get(i).getPriceSale2();
                                    break;
                                case 2:
                                    activityAddItemBinding.itemPrice.
                                            setText(itemUnits.get(i).getPriceSale3().toString());
                                    TempItemPrice = itemUnits.get(i).getPriceSale3();
                                    break;
                            }
                        }

                    }
                    onFirstLoad();
                }

            }
        });
        addItemViewModel.GetMinPeace.observe(this, new Observer<Float>() {
            @Override
            public void onChanged(Float integer) {
                countUnit = Double.parseDouble(String.valueOf(integer));


            }
        });
        activityAddItemBinding.addOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double Quantity =
                        Double.parseDouble(activityAddItemBinding.itemQuantity.getText().toString().trim());
                if (Quantity >= 1 && Quantity != null) {
                    Double newQuantity = Quantity + 1;
                    activityAddItemBinding.itemQuantity.setText(newQuantity.toString());
                } else {
                    activityAddItemBinding.itemQuantity.setText("1.0");
                }
            }
        });

        activityAddItemBinding.MinusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double Quantity =
                        Double.parseDouble(activityAddItemBinding.itemQuantity.getText().toString().trim());
                if (Quantity > 1 && Quantity != null) {
                    Double newQuantity = Quantity - 1;
                    activityAddItemBinding.itemQuantity.setText(newQuantity.toString());
                } else {
                    activityAddItemBinding.itemQuantity.setText("1.0");
                }
            }
        });

        addItemViewModel.stores.observe(this, new Observer<ArrayList<Stores>>() {
            @Override
            public void onChanged(ArrayList<Stores> stores) {
                storesArrayAdapter = new ArrayAdapter<>(Add_Item.this, R.layout.drop_down_item, stores);
                activityAddItemBinding.store.setAdapter(storesArrayAdapter);
                storeId = storesArrayAdapter.getItem(0).getID();
                activityAddItemBinding.store.setText(
                        activityAddItemBinding.store.getAdapter().getItem(0).toString(), false
                );
            }
        });
        activityAddItemBinding.store.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                storeId = storesArrayAdapter.getItem(position).getID();
            }
        });

        /// change if quantity and price and price after tax and discount
        activityAddItemBinding.itemQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("") || s.toString()== null || s.toString().isEmpty()) {
                    activityAddItemBinding.itemQuantity.setText("1.0");
                }
                else {
                    onFirstLoad();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (ItemId != -1) {

                }

            }
        });
        activityAddItemBinding.itemDiscount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s.toString().isEmpty() || s.toString()== null)
                {
                }
                else {
                    onFirstLoad();
                }
            }

        });




        activityAddItemBinding.itemPrice.addTextChangedListener(PricWatcher);

        activityAddItemBinding.itemPriceAfterTax.addTextChangedListener(AfterTaxWatcher);


        activityAddItemBinding.units.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                UnitId = unitsAdapter.getItem(position).getID();
                UnitName = unitsAdapter.getItem(position).getName();
                addItemViewModel.GetUnitsWithId(ItemId, UnitId);
                addItemViewModel.GetMinPeace(ItemId, UnitId);
            }
        });
        addItemViewModel.unitsWithId.observe(this, new Observer<ItemUnits>() {
            @Override
            public void onChanged(ItemUnits itemUnits) {

                if (itemUnits != null) {
                    switch (sellType) {
                        case 0:
                            activityAddItemBinding.itemPrice.
                                    setText(itemUnits.getPriceSale1().toString());
                            TempItemPrice = itemUnits.getPriceSale1();


                            break;
                        case 1:
                            activityAddItemBinding.itemPrice.
                                    setText(itemUnits.getPriceSale2().toString());
                            TempItemPrice = itemUnits.getPriceSale2();

                            break;
                        case 2:
                            activityAddItemBinding.itemPrice.
                                    setText(itemUnits.getPriceSale3().toString());
                            TempItemPrice = itemUnits.getPriceSale3();
                            break;
                    }
                    onFirstLoad();
                }

            }
        });
        activityAddItemBinding.addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillDetailsApi bill = billsDetaislObject();
                Boolean checkDiscount, checkPrice, checkQuantity;
                checkQuantity = publicViewModel.CheckDouble(bill.getQuantity().toString());
                checkDiscount = publicViewModel.CheckDouble(bill.getDiscount().toString());
                checkPrice = publicViewModel.CheckDouble(bill.getPrice().toString());

                if (checkDiscount && checkPrice && checkQuantity && UnitId !=0)
                {
                    if (bill.getPrice()<=0 || bill.getInvTot()<0 )
                    {
                        new simpleAlertDialog (Add_Item.this, getString(R.string.priceequalzero), R.drawable.warning, R.color.orange);
                    }
                    else
                    {
                        Intent i = new Intent();
                        i.putExtra("billdetail", bill);
                        setResult(1, i);
                        finish();
                    }

                }
                else {
                    new simpleAlertDialog (Add_Item.this, getString(R.string.errorcompletedata), R.drawable.warning, R.color.orange);

                }

            }
        });




    }


    public void onFirstLoad() {
        activityAddItemBinding.itemPrice.removeTextChangedListener(PricWatcher);
        activityAddItemBinding.itemPriceAfterTax.removeTextChangedListener(AfterTaxWatcher);
        Double quantity, afterTax, TotalPrice, discount=0.0, priceAfterTax;
        try
        {
            quantity = Double.parseDouble(activityAddItemBinding.itemQuantity.getText().toString());
        } catch (Exception e) {
            quantity = 1.0;
        }
        try
        {
            discount = (Double.parseDouble(activityAddItemBinding.itemDiscount.getText().toString()));
        } catch (Exception e) {
            discount = 0.0;
        }



        if (IsTax)
        {
           afterTax = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(TempItemPrice * .15 + TempItemPrice )));
        }
        else
        {
            afterTax =Double.parseDouble(df.format(TempItemPrice));
        }


        Double itemPrice = Double.valueOf(publicViewModel.TryArabicNumber(df.format(TempItemPrice)) );
        String itemSumPrice= publicViewModel.TryArabicNumber( df.format(afterTax*quantity));
        TotalPrice = Double.parseDouble(itemSumPrice)- discount;

        activityAddItemBinding.itemPrice.setText(itemPrice.toString());
        activityAddItemBinding.itemPriceAfterTax.setText(afterTax.toString());
        activityAddItemBinding.itemsumPrice.setText(itemSumPrice);
        activityAddItemBinding.itemTotal.setText(publicViewModel.TryArabicNumber( df.format(TotalPrice)));
        InvTot=TotalPrice;
        tempPrice=afterTax;


        activityAddItemBinding.itemPrice.addTextChangedListener(PricWatcher);
        activityAddItemBinding.itemPriceAfterTax.addTextChangedListener(AfterTaxWatcher);
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
            activityAddItemBinding.itemPrice.removeTextChangedListener(PricWatcher);
            Double quantity=0.0, afterTax=0.0, discount= 0.0, TotalPrice=0.0, newPrice=0.0;
            try {
                afterTax= Double.parseDouble(s.toString());
            }catch (Exception e) {afterTax=0.0;}
            try {
                quantity=Double.parseDouble(activityAddItemBinding.itemQuantity.getText().toString());
            }catch (Exception e){quantity=0.0;}
            try {
                discount= Double.parseDouble(activityAddItemBinding.itemDiscount.getText().toString());
            }catch (Exception e){discount=0.0;}

            if (!activityAddItemBinding.itemPriceAfterTax.getText().equals("")
            && activityAddItemBinding.itemPriceAfterTax.getText().toString() !=null
            && ! activityAddItemBinding.itemPriceAfterTax.getText().toString().isEmpty())
            {
                if (IsTax)
                {
                    newPrice= Double.parseDouble(s.toString())*100/115;
                    activityAddItemBinding.itemPrice.setText(PublicViewModel.TryArabicNumber( df.format(newPrice)));
                }else {
                    newPrice= Double.parseDouble(s.toString());
                    activityAddItemBinding.itemPrice.setText(PublicViewModel.TryArabicNumber(df.format(newPrice)) );
                }
                String itemSumPrice= PublicViewModel.TryArabicNumber(df.format(afterTax*quantity)) ;
                TotalPrice = Double.parseDouble(itemSumPrice)- discount;


                activityAddItemBinding.itemsumPrice.setText(itemSumPrice);
                activityAddItemBinding.itemTotal.setText(PublicViewModel.TryArabicNumber( df.format(TotalPrice)));

                activityAddItemBinding.itemPrice.addTextChangedListener(PricWatcher);
                TempItemPrice= newPrice;
                InvTot=TotalPrice;
                tempPrice=afterTax;
            }
            else {
                activityAddItemBinding.itemPrice.setText("0");
                activityAddItemBinding.itemTotal.setText("0");
                activityAddItemBinding.itemsumPrice.setText("0");
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
            activityAddItemBinding.itemPriceAfterTax.removeTextChangedListener(PricWatcher);


        }
    };


    public BillDetailsApi billsDetaislObject ()
    {Double Quantity=0.0, UnitPrice=0.0, Discount=0.0, aftertax=0.0;

        String Name= ItemName;
        Integer ItemID= ItemId;
        Integer UnitID= UnitId;
        Double taxItem;

        String mQuantity= activityAddItemBinding.itemQuantity.getText().toString();
        String mUnitPrice= activityAddItemBinding.itemPrice.getText().toString();
        String mDiscount= activityAddItemBinding.itemDiscount.getText().toString();
        try {Quantity =Double.parseDouble(mQuantity);}catch (Exception E){}
        try {UnitPrice=Double.valueOf(publicViewModel.TryArabicNumber(dh.format(TempItemPrice)) ) ;} catch (Exception e){}
        try{Discount= Double.parseDouble(mDiscount);}catch (Exception e){}

        if (IsTax)
        {
            taxItem=0.15;
        }else {taxItem=0.0;}
        BillDetailsApi billsDetails= new BillDetailsApi(storeId,
                Discount, ItemID, Name, UnitPrice, Quantity, taxItem, UnitID, countUnit  ,InvTot,UnitName
       );
        billsDetails.setTempPrice(tempPrice);
       billsDetails.setTempTot(InvTot);

        return billsDetails;
    }














}