package com.ahmed.profissionalaccountant.Dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;

import com.ahmed.profissionalaccountant.R;

public class alertDialog extends AlertDialog  {
    public DialogListener dialogListener;
    public alertDialog(Context context, String ErrorMsg, Integer imageID, Integer btnColor, DialogListener dialogListener)
    {
        super(context);
        this.dialogListener=dialogListener;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.login_err_layout, null, false);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        dialog.show();

        TextView ErrTxt = view.findViewById(R.id.errorMsg);
        Button DismissBtn = view.findViewById(R.id.ok);
        ImageView imageView = view.findViewById(R.id.errorImage);
        imageView.setImageResource(imageID);
        DismissBtn.setBackgroundTintList(context.getResources().getColorStateList(btnColor));

        DismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        ErrTxt.setText(ErrorMsg);


    }



}



