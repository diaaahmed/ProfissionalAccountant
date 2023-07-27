package com.ahmed.profissionalaccountant.Ui.MainFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ahmed.profissionalaccountant.R;
import com.itextpdf.layout.element.Image;
import com.squareup.picasso.Picasso;

public class Main_Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.main_fragment, container, false);
        ImageView myImage= view.findViewById(R.id.companyMain);

        Picasso.get().load("http://65.109.97.12:8080/images.png").into(myImage);




        return view;

    }
}
