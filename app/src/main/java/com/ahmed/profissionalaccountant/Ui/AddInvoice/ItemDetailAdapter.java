package com.ahmed.profissionalaccountant.Ui.AddInvoice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.profissionalaccountant.Models.BillDetailsApi;
import com.ahmed.profissionalaccountant.PublicFunc.PublicViewModel;
import com.ahmed.profissionalaccountant.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemDetailAdapter extends RecyclerView.Adapter<ItemDetailAdapter.ItemDetailViewHolder>
{   Context context;
    ArrayList<BillDetailsApi> billsDetails= new ArrayList<>();
    ItemInterface itemInterface;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public ItemDetailAdapter(Context context, ArrayList<BillDetailsApi> billsDetails , ItemInterface itemInterface) {
        this.context = context;
        this.billsDetails = billsDetails;
        this.itemInterface= itemInterface;
    }

    @NonNull
    @Override
    public ItemDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_details_recycler, parent, false);
        return new ItemDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDetailViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BillDetailsApi bill= billsDetails.get(position);
        df.setRoundingMode(RoundingMode.HALF_DOWN);
        Double TotalPrice;String afterTax, p, d;
        Double Quantity= bill.getQuantity(); Double unitPrice=(bill.getPrice()) ;

        Double discount= bill.getDiscount();
        Double unitPriceAfterTax;
        if (bill.getTaxItem()!=0)
        {


          unitPriceAfterTax= Double.parseDouble(PublicViewModel.TryArabicNumber(df.format(unitPrice+ unitPrice*.15)) );;

          p= PublicViewModel.TryArabicNumber(df.format(( Double.valueOf(unitPriceAfterTax)* Quantity)-discount)) ;

         TotalPrice= Double.valueOf(p);
        }else {
            afterTax= PublicViewModel.TryArabicNumber(df.format(unitPrice)) ;
            unitPriceAfterTax= Double.valueOf(afterTax);
             p= PublicViewModel.TryArabicNumber( df.format( Double.valueOf(afterTax)* Quantity));
            TotalPrice= Double.valueOf(p);

        }


         holder.ItemQuantity.setText(bill.getQuantity().toString());
         holder.itemName.setText(bill.getItemame());
         holder.ItemTotalPrice.setText(TotalPrice.toString());
         holder.unitPrice.setText(unitPriceAfterTax.toString());
         holder.unitName.setText(bill.getUnitname());

         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 itemInterface.onItemClick(bill, position);
             }
         });
    }

    @Override
    public int getItemCount() {
        return billsDetails.size();
    }

    public class ItemDetailViewHolder extends RecyclerView.ViewHolder

    {
       public TextView itemName, ItemTotalPrice, unitName, ItemQuantity, unitPrice;
        public ItemDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName=itemView.findViewById(R.id.name);
            ItemTotalPrice=itemView.findViewById(R.id.total);
            unitPrice=itemView.findViewById(R.id.itemunitprice);
            ItemQuantity= itemView.findViewById(R.id.quantity);
            unitName= itemView.findViewById(R.id.unit);


        }
    }
}
