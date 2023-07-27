package com.ahmed.profissionalaccountant.Ui.AddInvoice;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.profissionalaccountant.Models.StoreItemsInStore;
import com.ahmed.profissionalaccountant.R;

import java.util.ArrayList;

public class storeAlert extends AlertDialog  {
    ArrayList<StoreItemsInStore> list;
    storeAdapter adapter;
    RecyclerView recyclerView;
    Context context;
    public storeAlert(Context context, ArrayList<StoreItemsInStore> list) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.alert_with_store_shortage, null, false);
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setView(view);

        this.context= context;
        this.list= list;
        recyclerView=view.findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter= new storeAdapter(context, list);
        recyclerView.setAdapter(adapter);



        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);

        dialog.show();




        Button btn= view.findViewById(R.id.confirm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



    }
}
