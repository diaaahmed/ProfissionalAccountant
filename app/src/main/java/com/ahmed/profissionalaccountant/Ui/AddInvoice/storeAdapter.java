package com.ahmed.profissionalaccountant.Ui.AddInvoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.profissionalaccountant.Models.StoreItemsInStore;
import com.ahmed.profissionalaccountant.R;

import java.util.ArrayList;

public class storeAdapter extends RecyclerView.Adapter<storeAdapter.StoreViewHolder> {
    Context context;
    ArrayList<StoreItemsInStore> itemsInStores;

    public storeAdapter(Context context, ArrayList<StoreItemsInStore> itemsInStores) {
        this.context = context;
        this.itemsInStores = itemsInStores;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.returned_items_has_no_stroe, parent, false);
        return  new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        StoreItemsInStore store= itemsInStores.get(position);

        holder.itemunitname.setText(store.getUnintName());
        holder.itemName.setText(store.getItemName());
        holder.itemAvailable.setText(store.getAvailableQuan().toString());
        holder.storeName.setText(store.getStoreName());
        holder.itemAvailable.setText(store.getAvailableQuan().toString());
    }

    @Override
    public int getItemCount() {
        return itemsInStores.size();
    }

    public class StoreViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemunitname, itemAvailable, itemSold, storeName;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);


            itemName=itemView.findViewById(R.id.itemName);
            itemunitname=itemView.findViewById(R.id.unit);
            itemAvailable=itemView.findViewById(R.id.available);

            storeName=itemView.findViewById(R.id.store);
        }
    }
}
