package com.ahmed.profissionalaccountant.Ui.cashPayment;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.profissionalaccountant.Dialog.cash_bank_dialog;
import com.ahmed.profissionalaccountant.Dialog.cash_bank_listener;
import com.ahmed.profissionalaccountant.Models.TreeMain;
import com.ahmed.profissionalaccountant.R;

import java.util.ArrayList;

public class cash_bank_Adapter extends RecyclerView.Adapter<cash_bank_Adapter.cash_bank_viewHolder>  {
    Context context;
    ArrayList<TreeMain> myList;
    Integer orgin;

    com.ahmed.profissionalaccountant.Dialog.cash_bank_listener cash_bank_listener;
    androidx.appcompat.app.AlertDialog cash_bank_dialog;
    public cash_bank_Adapter(Context context, ArrayList<TreeMain> myList,
                             com.ahmed.profissionalaccountant.Dialog.cash_bank_listener cash_bank_listener
                             , androidx.appcompat.app.AlertDialog cash_bank_dialog, Integer orgin)
    {
        this.context = context;
        this.myList = myList;
        this.cash_bank_listener= cash_bank_listener;
        this.cash_bank_dialog= cash_bank_dialog;
        this.orgin= orgin;
    }

    @NonNull
    @Override
    public cash_bank_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.one_cash_or_bank_layout, parent, false);
        return new cash_bank_viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cash_bank_viewHolder holder, int position) {
        TreeMain tree= myList.get(position);
        holder.mytext.setText(tree.getName());
        holder.mytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cash_bank_listener.onItemClick(tree.getID(),tree.getName(), cash_bank_dialog, orgin);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class cash_bank_viewHolder extends RecyclerView.ViewHolder {
        Button mytext;
        public cash_bank_viewHolder(@NonNull View itemView) {
            super(itemView);
            mytext=itemView.findViewById(R.id.myText);
        }
    }
}
