package com.ahmed.profissionalaccountant.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.profissionalaccountant.Models.TreeMain;
import com.ahmed.profissionalaccountant.R;
import com.ahmed.profissionalaccountant.Ui.cashPayment.cash_bank_Adapter;

import java.util.ArrayList;

public class cash_bank_dialog extends AlertDialog {
    Context context;
    RecyclerView recyclerView;
    cash_bank_Adapter adapter;
    Button cancelBtn;
    cash_bank_listener cash_bank_listener;
    ArrayList<TreeMain> myList;
    androidx.appcompat.app.AlertDialog dialog;
    Integer orgin;
    public cash_bank_dialog(Context context, ArrayList<TreeMain> list, cash_bank_listener cash_bank_listener, Integer origin) {
        super(context);
        this.cash_bank_listener= cash_bank_listener;
        this.context= context;
        this.myList= list;
        this.dialog = dialog;
        this.orgin = origin;

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.alert_cash_banks_layout, null, false);
        builder.setView(view);
        dialog = builder.create();
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        recyclerView= view.findViewById(R.id.myRecycler);
        adapter= new cash_bank_Adapter (context, list, cash_bank_listener, dialog, orgin);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerView.setAdapter(adapter);

        Button cancelbtn = view.findViewById(R.id.cancel_btn);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

       dialog.show();


    }




}
