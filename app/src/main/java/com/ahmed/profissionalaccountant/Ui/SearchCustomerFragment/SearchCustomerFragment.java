package com.ahmed.profissionalaccountant.Ui.SearchCustomerFragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ahmed.profissionalaccountant.Dialog.DialogListener;
import com.ahmed.profissionalaccountant.Dialog.alertDialog;
import com.ahmed.profissionalaccountant.Dialog.simpleAlertDialog;
import com.ahmed.profissionalaccountant.Models.SupplierCustomer;
import com.ahmed.profissionalaccountant.Models.searchItems;
import com.ahmed.profissionalaccountant.Network.RetrofitClient;
import com.ahmed.profissionalaccountant.R;
import com.ahmed.profissionalaccountant.Ui.AddInvoice.new_Invoice;
import com.ahmed.profissionalaccountant.Ui.EditCustomer.edit_Customer;
import com.ahmed.profissionalaccountant.databinding.SuppliersCustomerSearchFragmentBinding;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchCustomerFragment extends Fragment   implements searchService, DialogListener {
    SuppliersCustomerSearchFragmentBinding binding;
    SearchCutomerViewModel searchCutomerViewModel;
    SeachCustomerAdapter adapter;
    Integer selectedId=1;
    ArrayList<SupplierCustomer> myList;
    AlertDialog prgessDialog;
    alertDialog errorDialog;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=SuppliersCustomerSearchFragmentBinding.inflate(inflater, container, false);
        searchCutomerViewModel = ViewModelProviders.of(this).get(SearchCutomerViewModel.class);
        prgessDialog = new SpotsDialog(getActivity(), R.style.Custom);

         myList= new ArrayList<>();
        binding.find.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                getResult();

            }
        });
        searchCutomerViewModel.searchData.observe(getActivity(), new Observer<ArrayList<SupplierCustomer>>() {
            @Override
            public void onChanged(ArrayList<SupplierCustomer> supplierCustomers) {

            }
        });
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButtonChange();
            }
        });



        return binding.getRoot();
    }

    private void RadioButtonChange() {
        switch (binding.radioGroup.getCheckedRadioButtonId())
        {
            case R.id.s1:
                selectedId= 1;
                break;
            case R.id.s2:
                selectedId=2;
                break;
            case R.id.s3:
                selectedId=3;
            case R.id.s4:
                selectedId=4;
                break;
        }

    }

    private void getResult() {
        String searchString=binding.search.getText().toString();
        searchItems searchItems= new searchItems(selectedId, searchString);

        if (searchString.trim().length()>=3)
        {

            prgessDialog.show();
            Integer selectedId= binding.radioGroup.getCheckedRadioButtonId();
            RetrofitClient.getINSTANCE().getSearchResult(searchItems).enqueue(new Callback<ArrayList<SupplierCustomer>>() {
                @Override
                public void onResponse(Call<ArrayList<SupplierCustomer>> call, Response<ArrayList<SupplierCustomer>> response) {
                    myList= response.body();

                    {
                        binding.noresultTxt.setVisibility(View.GONE);
                        adapter = new SeachCustomerAdapter(getActivity(), myList, SearchCustomerFragment.this);
                        binding.resultRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                        binding.resultRecycler.setAdapter(adapter);

                        if (response.body().size() == 0) {
                            binding.noresultTxt.setVisibility(View.VISIBLE);
                            binding.noresultTxt.setText(R.string.nodata);
                        }

                        prgessDialog.dismiss();


                    }

                }

                @Override
                public void onFailure(Call<ArrayList<SupplierCustomer>> call, Throwable t) {
                        prgessDialog.dismiss();
                }
            });
        }
        else {
            new simpleAlertDialog (getActivity(), getString(R.string.minimumserch), R.drawable.cross, R.color.red);
        }







    }


    @Override
    public void onItemClicked(Integer id) {
        Intent i = new Intent(getActivity(), edit_Customer.class);
        i.putExtra("customerId", id);
        startActivity(i);
    }

    @Override
    public void onAddClick(SupplierCustomer supplierCustomer) {
        Intent i = new Intent(getActivity(), new_Invoice.class );
        i.putExtra("supplierCustomer", supplierCustomer);

        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void Btnonclick() {
        errorDialog.dismiss();
    }
}
