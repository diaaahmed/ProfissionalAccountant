package com.ahmed.profissionalaccountant.Ui.SearchCustomerFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.profissionalaccountant.Models.SupplierCustomer;
import com.ahmed.profissionalaccountant.R;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Handler;

public class SeachCustomerAdapter extends RecyclerView.Adapter<SeachCustomerAdapter.SearchCustomerViewHolder> {
    Context context;
    ArrayList<SupplierCustomer> mylist = new ArrayList<>();
    searchService service;
    public SeachCustomerAdapter(Context context, ArrayList<SupplierCustomer> mylist, searchService searchService) {
        this.context = context;
        this.mylist = mylist;
        this.service= searchService;
    }

    @NonNull
    @Override
    public SearchCustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.search_customer_layout, parent, false);
        return  new SearchCustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchCustomerViewHolder holder, int position) {
        SupplierCustomer obj= mylist.get(position);
        holder.name.setText(obj.getName());
        holder.tax.setText(obj.getTaxNumber());
        holder.segel.setText(obj.getmSegalNumber());

        holder.addNewInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.onAddClick(obj);
            }
        });
        holder.EditCustData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.onItemClicked(obj.getID());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class SearchCustomerViewHolder extends RecyclerView.ViewHolder {
        TextView name, tax, segel;
        Button addNewInv , EditCustData;
        public SearchCustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.customerName);
            tax=itemView.findViewById(R.id.CustomerTax);
            segel=itemView.findViewById(R.id.CustomerSegel);
            addNewInv=itemView.findViewById(R.id.addNewInvoice);
            EditCustData=itemView.findViewById(R.id.EditCustomerData);

        }
    }
}
