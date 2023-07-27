package com.ahmed.profissionalaccountant.Dialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.ahmed.profissionalaccountant.R;

public class AlertDialog_Ok_Cancel extends AlertDialog {
    TextView txtMsg;
    Button confirmBtn, CancelBtn;
    public AlertDialog_Ok_Cancel(@NonNull Context context, String Msg )  {
        super(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.alert_confirm_cancel, null, false);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        dialog.show();

        txtMsg=view.findViewById(R.id.msg);
        txtMsg.setText(Msg);
        confirmBtn= view.findViewById(R.id.confirm);
        CancelBtn=view.findViewById(R.id.cancel);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });
        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



    }
}
