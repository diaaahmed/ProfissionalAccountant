package com.ahmed.profissionalaccountant.Ui.cashPayment;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.Settings;
import android.text.Editable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmed.printer.Common;
import com.ahmed.printer.sheet;
import com.ahmed.profissionalaccountant.Dialog.DialogListener;
import com.ahmed.profissionalaccountant.Dialog.alertDialog;
import com.ahmed.profissionalaccountant.Dialog.cash_bank_dialog;
import com.ahmed.profissionalaccountant.Dialog.cash_bank_listener;
import com.ahmed.profissionalaccountant.Dialog.simpleAlertDialog;
import com.ahmed.profissionalaccountant.Models.BillPaymentApi;
import com.ahmed.profissionalaccountant.Models.CompnyData;
import com.ahmed.profissionalaccountant.Models.Farsi;
import com.ahmed.profissionalaccountant.Models.InvoiceClass;
import com.ahmed.profissionalaccountant.Models.StatusCodeResult;
import com.ahmed.profissionalaccountant.Models.TreeMain;
import com.ahmed.profissionalaccountant.Models.VwDefaults;
import com.ahmed.profissionalaccountant.Models.pdfObject;
import com.ahmed.profissionalaccountant.PublicFunc.PublicViewModel;
import com.ahmed.profissionalaccountant.PublicFunc.pdf_view;
import com.ahmed.profissionalaccountant.PublicFunc.utils;
import com.ahmed.profissionalaccountant.R;
import com.ahmed.profissionalaccountant.Ui.AddInvoice.new_Invoice;
import com.ahmed.profissionalaccountant.arabicletters.Tafqeet;
import com.ahmed.profissionalaccountant.databinding.ActivityCashPaymentBinding;
import com.dantsu.escposprinter.exceptions.EscPosBarcodeException;
import com.dantsu.escposprinter.exceptions.EscPosConnectionException;
import com.dantsu.escposprinter.exceptions.EscPosEncodingException;
import com.dantsu.escposprinter.exceptions.EscPosParserException;
import com.dantsu.escposprinter.src.main.java.com.dantsu.escposprinter.EscPosPrinter;
import com.dantsu.escposprinter.src.main.java.com.dantsu.escposprinter.EscPosPrinterSize;
import com.dantsu.escposprinter.src.main.java.com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections;
import com.dantsu.escposprinter.src.main.java.com.dantsu.escposprinter.textparser.PrinterTextParserBarcode;
import com.dantsu.escposprinter.src.main.java.com.dantsu.escposprinter.textparser.PrinterTextParserImg;
import com.dantsu.thermalprinter.async.AsyncBluetoothEscPosPrint;
import com.dantsu.thermalprinter.async.AsyncEscPosPrint;
import com.dantsu.thermalprinter.async.AsyncEscPosPrinter;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.GrooveBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.BaseDirection;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;
import com.itextpdf.text.pdf.languages.LanguageProcessor;
import com.izettle.html2bitmap.BitmapCallback;
import com.izettle.html2bitmap.Html2Bitmap;
import com.izettle.html2bitmap.content.WebViewContent;


import net.posprinter.posprinterface.ProcessData;
import net.posprinter.posprinterface.UiExecute;
import net.posprinter.utils.BitmapToByteData;
import net.posprinter.utils.DataForSendToPrinterPos80;
import net.posprinter.utils.PosPrinterDev;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class CashPayment extends AppCompatActivity implements cash_bank_listener, DialogListener {
    static final String FILE_NAME = "com.ahmed.profissionalaccountant.MY_FILE";
    private static final String TAG="CashPayment";
    public static final String maj = "res/font/majalla.ttf";
    public static final String cairoBold = "res/font/sukarbold.ttf";

    SharedPreferences sharedPreferences;

    private ActivityResultLauncher<Intent> launcher; // Initialise this object in Activity.onCreate()
    private Uri baseDocumentTreeUri;

    String TempQr;
    AlertDialog prgessDialog1;
    Drawable d;
    InvoiceClass invoiceClass;
    Double Total = 0.0, net;
    ArrayList<BillPaymentApi> billPayments = new ArrayList<>();
    ActivityCashPaymentBinding activityCashPaymentBinding;
    cashPaymentViewModel cashPaymentViewModel;
    ArrayList<TreeMain> Cashlist = new ArrayList<>();
    ArrayList<TreeMain> Bankslist = new ArrayList<>();
    cash_bank_dialog dialog;
    Integer cashId, networkId, hewalaId, checkId;
    VwDefaults cash, network, hewala, check;
    ArrayList<checkAndET> myCheckBoxArray = new ArrayList<>();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    Image image;
    /// printing var
    String name;
    String mypayment;

    String Custmername;
    String Date;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    String myPaymentMethod;
    String date;
    String SalesRepName;
    String ImagePath, CompanyName;
    BarcodeQRCode SmallInvQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCashPaymentBinding = ActivityCashPaymentBinding.inflate(getLayoutInflater());
        View view = activityCashPaymentBinding.getRoot();
        setContentView(view);


        prgessDialog1 = new SpotsDialog(CashPayment.this, R.style.Custom);

        sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        boolean fromOutSide = getIntent().getBooleanExtra("fromOutSide", false);
        if (fromOutSide == true) {
            activityCashPaymentBinding.afterconfirmationLayout.setVisibility(View.VISIBLE);
            activityCashPaymentBinding.seconedHiddenlayout.setVisibility(View.VISIBLE);
            activityCashPaymentBinding.holeLayout.setVisibility(View.GONE);
            activityCashPaymentBinding.bottomLAyout.setVisibility(View.GONE);
            onload();
        }

        df.setRoundingMode(RoundingMode.HALF_DOWN);
        cashPaymentViewModel = ViewModelProviders.of(this).get(com.ahmed.profissionalaccountant.Ui.cashPayment.cashPaymentViewModel.class);
        cashPaymentViewModel.GetCahFund(true);
        cashPaymentViewModel.GetBanks(false);
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        Integer branchId = sharedPreferences.getInt("BranchID", 0);
        invoiceClass = (InvoiceClass) getIntent().getSerializableExtra("invoice");
        Total = getIntent().getDoubleExtra("total", 0);
        name = getIntent().getStringExtra("name");

        activityCashPaymentBinding.customerName.setText(name);
        activityCashPaymentBinding.total.setText(PublicViewModel.TryArabicNumber(df.format(Total)));
        cashPaymentViewModel.getImage();

        // printing
        if (invoiceClass.getBill().getBillStatus()) {
            mypayment = getResources().getString(R.string.simpleposponed);
        } else {
            mypayment = getString(R.string.simplecash);
        }
        Custmername = getResources().getString(R.string.customername);
        date = PublicViewModel.arabicToDecimal(sdf.format(invoiceClass.getBill().getBillDate()));
        myPaymentMethod = getResources().getString(R.string.paymentmethod);
        Date = getResources().getString(R.string.Date);
        SalesRepName = invoiceClass.getBill().getSalesRepName();


        //---------------------
        StartpaymentHandling();


        myCheckBoxArray.add(new checkAndET(activityCashPaymentBinding.ChCashPayment, activityCashPaymentBinding.cashPayment));
        myCheckBoxArray.add(new checkAndET(activityCashPaymentBinding.ChNetwork, activityCashPaymentBinding.NetworkPayment));
        myCheckBoxArray.add(new checkAndET(activityCashPaymentBinding.ChHewala, activityCashPaymentBinding.hewalaPayment));
        myCheckBoxArray.add(new checkAndET(activityCashPaymentBinding.ChCheck, activityCashPaymentBinding.checkPayment));

        cashPaymentViewModel.cashDefault(branchId, 1);
        cashPaymentViewModel.BankDefault(branchId, 2);
        cashPaymentViewModel.HewalaDefault(branchId, 3);
        cashPaymentViewModel.checkDefault(branchId, 4);


        cashPaymentViewModel.getData.observe(this, new Observer<CompnyData>() {
            @Override
            public void onChanged(CompnyData compnyData) {
                ImagePath = compnyData.getLogo();
                CompanyName = compnyData.getName();
            }
        });


        cashPaymentViewModel.cashDefault.observe(this, new Observer<VwDefaults>() {
            @Override
            public void onChanged(VwDefaults vwDefaults) {
                cash = vwDefaults;
                checkBoxMission(activityCashPaymentBinding.ChCashPayment, activityCashPaymentBinding.cashPayment,
                        activityCashPaymentBinding.cashsource,
                        activityCashPaymentBinding.btnCashPayment, 1);

            }
        });
        cashPaymentViewModel.networkDefault.observe(this, new Observer<VwDefaults>() {
            @Override
            public void onChanged(VwDefaults vwDefaults) {
                network = vwDefaults;
                checkBoxMission(activityCashPaymentBinding.ChNetwork, activityCashPaymentBinding.NetworkPayment,
                        activityCashPaymentBinding.NetworkSource, activityCashPaymentBinding.btnNetwokPayment, 2);
            }
        });
        cashPaymentViewModel.HewalaDefault.observe(this, new Observer<VwDefaults>() {
            @Override
            public void onChanged(VwDefaults vwDefaults) {
                hewala = vwDefaults;
                checkBoxMission(activityCashPaymentBinding.ChHewala, activityCashPaymentBinding.hewalaPayment,
                        activityCashPaymentBinding.hewalaSource, activityCashPaymentBinding.btnHewalaPayment, 3);
            }
        });
        cashPaymentViewModel.checkDefault.observe(this, new Observer<VwDefaults>() {
            @Override
            public void onChanged(VwDefaults vwDefaults) {
                check = vwDefaults;
                checkBoxMission(activityCashPaymentBinding.ChCheck, activityCashPaymentBinding.checkPayment,
                        activityCashPaymentBinding.checkSource, activityCashPaymentBinding.btnCheckPayment, 4);
            }
        });


        cashPaymentViewModel.Cash.observe(this, new Observer<ArrayList<TreeMain>>() {
            @Override
            public void onChanged(ArrayList<TreeMain> treeMains) {
                Cashlist = treeMains;

            }
        });
        cashPaymentViewModel.Banks.observe(this, new Observer<ArrayList<TreeMain>>() {
            @Override
            public void onChanged(ArrayList<TreeMain> treeMains) {
                Bankslist = treeMains;
            }
        });
        cashPaymentViewModel.addInvoiceResult.observe(this, new Observer<StatusCodeResult>() {
            @Override
            public void onChanged(StatusCodeResult statusCodeResult) {
                if (statusCodeResult.getStatusCodeId() == 200) {
                    pdfObject TempObj = new pdfObject();
                    TempQr= statusCodeResult.getQRCode();
                    TempObj.setQr(statusCodeResult.getQRCode());

                    TempObj.setBillNo(statusCodeResult.getInvoiceSerialNumber());

                    TempObj.setPhoneNo(statusCodeResult.getPhone());
                    TempObj.setTaxNo(statusCodeResult.getTaxNumber());
                    TempObj.setSegelNo(statusCodeResult.getSegelNumber());
                    TempObj.setDate(invoiceClass.getBill().getBillDate().toString());
                    TempObj.setName(statusCodeResult.getcustomeName());


                    invoiceClass.setPdfObject(TempObj);


                    new_Invoice.myActivity.finish();

                    activityCashPaymentBinding.afterconfirmationLayout.setVisibility(View.VISIBLE);
                    activityCashPaymentBinding.seconedHiddenlayout.setVisibility(View.VISIBLE);
                    activityCashPaymentBinding.holeLayout.setVisibility(View.GONE);
                    activityCashPaymentBinding.bottomLAyout.setVisibility(View.GONE);
                    onload();
                } else {
                    new alertDialog(CashPayment.this,
                            getString(R.string.additionerror), R.drawable.cross, R.color.red, CashPayment.this);
                }
            }
        });

        //cash
        activityCashPaymentBinding.btnCashPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new cash_bank_dialog(CashPayment.this, Cashlist, CashPayment.this, 1);
            }
        });
        activityCashPaymentBinding.ChCashPayment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxMission(activityCashPaymentBinding.ChCashPayment,
                        activityCashPaymentBinding.cashPayment, activityCashPaymentBinding.cashsource, activityCashPaymentBinding.btnCashPayment, 1);
                AllCheckChange();

            }
        });
        activityCashPaymentBinding.cashsource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cash_bank_dialog dialog = new cash_bank_dialog(CashPayment.this, Cashlist, CashPayment.this, 1);
            }
        });
        activityCashPaymentBinding.cashPayment.addTextChangedListener(cahTextWatcher);
        //network
        activityCashPaymentBinding.btnNetwokPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new cash_bank_dialog(CashPayment.this, Bankslist,
                        CashPayment.this, 2);

            }
        });
        activityCashPaymentBinding.ChNetwork.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxMission(activityCashPaymentBinding.ChNetwork, activityCashPaymentBinding.NetworkPayment,
                        activityCashPaymentBinding.NetworkSource, activityCashPaymentBinding.btnNetwokPayment, 2);
                AllCheckChange();
            }
        });
        activityCashPaymentBinding.NetworkSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cash_bank_dialog dialog = new cash_bank_dialog(CashPayment.this, Bankslist, CashPayment.this, 2);
            }
        });
        activityCashPaymentBinding.NetworkPayment.addTextChangedListener(networkTextWatcher);

        // Hewala
        activityCashPaymentBinding.btnHewalaPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new cash_bank_dialog(CashPayment.this, Bankslist,
                        CashPayment.this, 3);

            }
        });
        activityCashPaymentBinding.ChHewala.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxMission(activityCashPaymentBinding.ChHewala, activityCashPaymentBinding.hewalaPayment,
                        activityCashPaymentBinding.hewalaSource, activityCashPaymentBinding.btnHewalaPayment, 3);
                AllCheckChange();
            }
        });
        activityCashPaymentBinding.hewalaSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cash_bank_dialog dialog = new cash_bank_dialog(CashPayment.this, Bankslist, CashPayment.this, 3);
            }
        });
        activityCashPaymentBinding.hewalaPayment.addTextChangedListener(hewalaTextWatcher);
        // check
        activityCashPaymentBinding.btnCheckPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new cash_bank_dialog(CashPayment.this, Bankslist,
                        CashPayment.this, 4);
            }
        });
        activityCashPaymentBinding.ChCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxMission(activityCashPaymentBinding.ChCheck, activityCashPaymentBinding.checkPayment,
                        activityCashPaymentBinding.checkSource, activityCashPaymentBinding.btnCheckPayment, 4);
                AllCheckChange();
            }
        });
        activityCashPaymentBinding.checkSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cash_bank_dialog dialog = new cash_bank_dialog(CashPayment.this, Bankslist, CashPayment.this, 4);
            }
        });
        activityCashPaymentBinding.checkPayment.addTextChangedListener(checkTextWatcher);

        // discount
        activityCashPaymentBinding.discount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                StartpaymentHandling();
            }
        });

        //exexute button
        activityCashPaymentBinding.approvebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });


        // ---------------------------- hidden Layout -----------------------

        activityCashPaymentBinding.backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        activityCashPaymentBinding.createPdf.setOnClickListener(new View.OnClickListener() {
            //  @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                /*
                ActivityCompat.requestPermissions(CashPayment.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                        Manifest.permission.MANAGE_MEDIA
                }, PackageManager.PERMISSION_GRANTED);

               int checkPermissin= ContextCompat.checkSelfPermission(CashPayment.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (checkPermissin!=PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(CashPayment.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                                    Manifest.permission.MANAGE_MEDIA},1);
                }
                else {*/
                try {
                    prgessDialog1.show();
                    CreatePdf();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //  }
        });
        activityCashPaymentBinding.printbluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    print();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public void onItemClick(Integer id, String name, androidx.appcompat.app.AlertDialog alertDialog, Integer orgin) {
        if (orgin == 1) {
            alertDialog.dismiss();
            cashId = id;
            activityCashPaymentBinding.cashsource.setText(name);


        }
        if (orgin == 2) {
            alertDialog.dismiss();
            networkId = id;
            activityCashPaymentBinding.NetworkSource.setText(name);
        }
        if (orgin == 3) {
            alertDialog.dismiss();
            hewalaId = id;
            activityCashPaymentBinding.hewalaSource.setText(name);
        }
        if (orgin == 4) {
            alertDialog.dismiss();
            checkId = id;
            activityCashPaymentBinding.checkSource.setText(name);

        }

    }


    public void checkBoxMission(CheckBox checkBox, EditText editText, TextView textView
            , ImageButton imagebtn, Integer origin) {
        if (checkBox.isChecked()) {
            editText.setEnabled(true);
            textView.setEnabled(true);
            imagebtn.setEnabled(true);
            switch (origin) {
                case 1:
                    activityCashPaymentBinding.cashsource.setText(cash.getDefaultName());
                    cashId = cash.getDefaultID();
                    break;
                case 2:
                    activityCashPaymentBinding.NetworkSource.setText(network.getDefaultName());
                    networkId = network.getDefaultID();
                    break;
                case 3:
                    activityCashPaymentBinding.hewalaSource.setText(hewala.getDefaultName());
                    hewalaId = hewala.getDefaultID();
                    break;
                case 4:
                    activityCashPaymentBinding.checkSource.setText(check.getDefaultName());
                    checkId = check.getDefaultID();
                    activityCashPaymentBinding.checkDetailLayout.setVisibility(View.VISIBLE);
                    break;
            }

        } else {
            editText.setEnabled(false);
            imagebtn.setEnabled(false);
            textView.setEnabled(false);
            editText.setText("0");
            activityCashPaymentBinding.checkDetailLayout.setVisibility(View.GONE);
            switch (origin) {
                case 1:
                    activityCashPaymentBinding.cashsource.setText("");
                    cashId = 0;
                    break;
                case 2:
                    activityCashPaymentBinding.NetworkSource.setText("");
                    networkId = 0;
                    break;
                case 3:
                    activityCashPaymentBinding.hewalaSource.setText("");
                    hewalaId = 0;
                    break;
                case 4:
                    activityCashPaymentBinding.checkSource.setText("");
                    checkId = 0;
                    activityCashPaymentBinding.checkDetailLayout.setVisibility(View.GONE);
                    activityCashPaymentBinding.checkNumber.setText("");
                    break;
            }
        }

    }


    public void StartpaymentHandling() {
        Double getDiscount, getcash, getnetwork, getHewala, getcheck;
        EditText totalTv = activityCashPaymentBinding.netTotalpaid;
        EditText discountET = activityCashPaymentBinding.discount;
        EditText cashET = activityCashPaymentBinding.cashPayment;
        EditText networkET = activityCashPaymentBinding.NetworkPayment;
        EditText hewalaET = activityCashPaymentBinding.hewalaPayment;
        EditText chekET = activityCashPaymentBinding.checkPayment;
        EditText checkNumberEt = activityCashPaymentBinding.checkNumber;

        try {
            getDiscount = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(Double.parseDouble(discountET.getText().toString()))));
        } catch (Exception e) {
            getDiscount = 0.0;
        }

        try {
            getcash = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(Double.parseDouble(cashET.getText().toString()))));
        } catch (Exception e) {
            getcash = 0.0;
        }

        try {
            getnetwork = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(Double.parseDouble(networkET.getText().toString()))));
        } catch (Exception e) {
            getnetwork = 0.0;
        }

        try {
            getHewala = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(Double.parseDouble(hewalaET.getText().toString()))));
        } catch (Exception e) {
            getHewala = 0.0;
        }
        try {
            getcheck = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(Double.parseDouble(chekET.getText().toString()))));
        } catch (Exception e) {
            getcheck = 0.0;
        }


        Double netTotal = Double.valueOf(PublicViewModel.TryArabicNumber(df.format((getcash + getnetwork + getHewala + getcheck))));
        Double TotalAfterDiscount = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(Total - getDiscount)));
        net = TotalAfterDiscount;
        totalTv.setText(TotalAfterDiscount.toString());

        if (netTotal.doubleValue() != TotalAfterDiscount.doubleValue()) {
            totalTv.setBackgroundTintList(getResources().getColorStateList(R.color.red));
        } else if (netTotal.doubleValue() == TotalAfterDiscount.doubleValue()) {
            totalTv.setBackgroundTintList(getResources().getColorStateList(R.color.green));
        }
    }

    public ArrayList<ReturnedChckedItems> GetActualCheckedItems() {

        ArrayList<ReturnedChckedItems> items = new ArrayList<>();
        for (int i = 0; i < myCheckBoxArray.size(); i++) {
            if (myCheckBoxArray.get(i).ch.isChecked()) {
                items.add(new ReturnedChckedItems(i,
                                Double.valueOf(PublicViewModel.TryArabicNumber(df.format(Double.parseDouble(myCheckBoxArray.get(i).et.getText().toString()))))
                                , myCheckBoxArray.get(i).et, myCheckBoxArray.get(i).ch
                        )
                );
            }
        }
        return items;
    }


    public void AllCheckChange() {
        Double discount;
        EditText Temp = null;
        try {
            discount =
                    Double.valueOf(PublicViewModel.TryArabicNumber(df.format(Double.parseDouble(activityCashPaymentBinding.discount.getText().toString()))
                    ));
        } catch (Exception e) {
            discount = 0.0;
        }

        ArrayList<ReturnedChckedItems> checkedItems = GetActualCheckedItems();
        if (checkedItems.size() == 1) {
            for (int i = 0; i < checkedItems.size(); i++) {
                Double total = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(Total)));
                Double net = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(total - discount)));
                checkedItems.get(i).textToEdit.setText(net.toString());
                StartpaymentHandling();
            }
        }
        if (checkedItems.size() > 1) {
            Double TempTot = 0.0;
            for (int i = 0; i < checkedItems.size(); i++) {
                Double valueOf = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(Double.parseDouble(checkedItems.get(i).textToEdit.getText().toString()))));
                if (valueOf > 0) {
                    TempTot += valueOf;
                } else {
                    Temp = checkedItems.get(i).textToEdit;
                }

            }
            Double netVal = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(Total - (TempTot + discount))));
            if (Temp != null) {
                Temp.setText(netVal.toString());
            }
            StartpaymentHandling();
        } else {
            StartpaymentHandling();
        }

    }


    public void sendData() {
        Double discount = 0.0;
        try {
            discount = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(Double.parseDouble(activityCashPaymentBinding.discount.getText().toString()))));

        } catch (Exception e) {
            discount = 0.0;
        }
        Double myTotal = 0.0;
        String checkNumber = "";
        boolean checkTotal = false;
        invoiceClass.getBill().setDiscount(discount);
        ArrayList<ReturnedChckedItems> GetCheckedItems;
        GetCheckedItems = GetActualCheckedItems();

        if (GetCheckedItems.size() > 0) {
            for (int i = 0; i < GetCheckedItems.size(); i++) {
                BillPaymentApi temp = new BillPaymentApi();
                Double amount = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(Double.parseDouble(GetCheckedItems.get(i).textToEdit.getText().toString()))));
                temp.setAmount(amount);
                switch (GetCheckedItems.get(i).checkBox.getId()) {
                    case R.id.Ch_cashPayment:
                        temp.setType(1);
                        temp.setAccountId(cashId);
                        break;
                    case R.id.Ch_Network:
                        temp.setType(2);
                        temp.setAccountId(networkId);
                        break;
                    case R.id.Ch_hewala:
                        temp.setType(3);
                        temp.setAccountId(hewalaId);
                        break;
                    case R.id.Ch_check:
                        temp.setType(4);
                        temp.setAccountId(checkId);
                        temp.setmNumberCheck(activityCashPaymentBinding.checkNumber.getText().toString());
                        break;
                }
                myTotal += amount;
                billPayments.add(temp);
            }
        }
        if (checkId != 0) {
            checkNumber = activityCashPaymentBinding.checkNumber.getText().toString();
        }
        Double parsedMyTotal = Double.parseDouble(PublicViewModel.TryArabicNumber(df.format(myTotal)));
        Double finalTotl = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(Total)));
        Double net = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(parsedMyTotal + discount)));
        if (net.doubleValue() == finalTotl.doubleValue()) {
            if (checkId != 0) {
                if (checkNumber.equals("") || checkNumber.isEmpty()) {
                    billPayments = new ArrayList<>();
                    new simpleAlertDialog(CashPayment.this, getString(R.string.enterchecknumber), R.drawable.warning, R.color.orange);
                } else {
                    invoiceClass.setBillPaymentApis(billPayments);
                    cashPaymentViewModel.AddCashInvoice(invoiceClass);
                }
            } else {
                invoiceClass.setBillPaymentApis(billPayments);
                cashPaymentViewModel.AddCashInvoice(invoiceClass);

            }

        } else {
            new simpleAlertDialog(CashPayment.this, getString(R.string.duemustequal), R.drawable.warning, R.color.orange);
        }

    }


    TextWatcher cahTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            StartpaymentHandling();
        }
    };

    TextWatcher networkTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            StartpaymentHandling();
        }
    };

    TextWatcher hewalaTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            StartpaymentHandling();
        }
    };

    TextWatcher checkTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            StartpaymentHandling();
        }
    };

    @Override
    public void Btnonclick() {

    }


    public class ReturnedChckedItems {
        Integer checkItemPosition;
        Double amount;
        EditText textToEdit;
        CheckBox checkBox;

        public ReturnedChckedItems(Integer checkItemPosition, Double amount, EditText textToEdit, CheckBox checkBox) {
            this.checkItemPosition = checkItemPosition;
            this.amount = amount;
            this.textToEdit = textToEdit;
            this.checkBox = checkBox;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        public ReturnedChckedItems(Integer checkItemPosition, Double amount, EditText textToEdit) {
            this.checkItemPosition = checkItemPosition;
            this.amount = amount;
            this.textToEdit = textToEdit;
        }


        public EditText getTextToEdit() {
            return textToEdit;
        }

        public void setTextToEdit(EditText textToEdit) {
            this.textToEdit = textToEdit;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }


        public Integer getCheckItemPosition() {
            return checkItemPosition;
        }

        public void setCheckItemPosition(Integer checkItemPosition) {
            this.checkItemPosition = checkItemPosition;
        }
    }

    public class checkAndET {
        public CheckBox ch;
        public EditText et;

        public checkAndET(CheckBox ch, EditText et) {
            this.ch = ch;
            this.et = et;
        }

        public CheckBox getCh() {
            return ch;
        }

        public void setCh(CheckBox ch) {
            this.ch = ch;
        }

        public EditText getEt() {
            return et;
        }

        public void setEt(EditText et) {
            this.et = et;
        }
    }


    // @RequiresApi(api = Build.VERSION_CODES.O)
    public void CreatePdf() throws IOException {

        pdfObject myObj = PublicViewModel.ProcessInvoice(invoiceClass);

        activityCashPaymentBinding.createPdf.setEnabled(false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        FontProgram fontProgram = FontProgramFactory.createFont(maj);
        LanguageProcessor languageProcessor = new ArabicLigaturizer();
        FontProgram fontBoldProgram = FontProgramFactory.createFont(cairoBold);
        PdfFont font = PdfFontFactory.createFont(fontProgram, PdfEncodings.IDENTITY_H, true);
        PdfFont boldfont = PdfFontFactory.createFont(fontBoldProgram, PdfEncodings.IDENTITY_H, true);


        String pdfPath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        String name = (myObj.getName() + date).toString();
        File file = new File(pdfPath, "ProfissionalAccountantInv" + ".pdf");

        PdfWriter writer = new PdfWriter(file);

        PdfDocument pdfDocument = new PdfDocument(writer);

        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        document.setBaseDirection(BaseDirection.RIGHT_TO_LEFT);


        //adding logo
        URL logoUrl = new URL(sharedPreferences.getString("logo", ""));
        Bitmap bitmap = BitmapFactory.decodeStream(logoUrl.openConnection().getInputStream());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, stream);
        byte[] bitmapData = stream.toByteArray();
        ImageData imageData = ImageDataFactory.create(bitmapData);
        Image image = new Image(imageData);
        image.scaleAbsolute(150, 150);
        image.setFixedPosition(0, 700);
        document.add(image);

        Border border = new GrooveBorder(2);
        border.setColor(ColorConstants.BLACK);

        float[] companyDataTablcolumnWidth = {170, 100};
        Table companyDataTabl = new Table(companyDataTablcolumnWidth);
        companyDataTabl.setBorder(border);
        Cell namecell = new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(myObj.getSegelNo()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER);
        Cell nameText = new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("سجل تجاري:"))).setFont(font).setFontSize(13).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER);

        Cell segel = new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(myObj.getTaxNo()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER);
        Cell segelText = new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("بطاقة ضريبية:"))).setFont(font).setFontSize(13).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER);

        Cell gawal = new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(myObj.getPhoneNo()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER);
        Cell gawalText = new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("رقم الجوال:"))).setFont(font).setFontSize(13).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER);

        companyDataTabl.addCell(namecell);
        companyDataTabl.addCell(nameText);
        companyDataTabl.addCell(segel);
        companyDataTabl.addCell(segelText);
        companyDataTabl.addCell(gawal);
        companyDataTabl.addCell(gawalText);

        companyDataTabl.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        document.add(companyDataTabl);

        // title
        document.add(new Paragraph(languageProcessor.process(CompanyName)).setFont(boldfont).setFontSize(30).setTextAlignment(TextAlignment.CENTER));
        // Invoie data table
        float[] columnWidth = {200, 100};
        Table table = new Table(columnWidth);
        table.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        table.setBaseDirection(BaseDirection.RIGHT_TO_LEFT);

        table.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(myObj.getName()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)));
        table.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("اسم العميل:"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)));

        table.addCell(new Cell().add(new Paragraph(date).setTextAlignment(TextAlignment.RIGHT)));
        table.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("التاريخ:"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)));


        table.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(myObj.getBillNo().toString()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)));
        table.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("رقم الفاتورة:"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)));


        table.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(SalesRepName))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)));
        table.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("مندوب البيع:"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)));

        table.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(mypayment))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)));
        table.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("نوع البيع:"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT)));


        document.add(table);
        document.add(new Paragraph("\n"));


        float[] QcolumnWidth = {150, 100, 100, 100, 100, 100, 300, 30};
        Table Qtable = new Table(QcolumnWidth);
        Qtable.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        Qtable.setBaseDirection(BaseDirection.RIGHT_TO_LEFT);

        Cell headerCell7 = new Cell().add(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("الإجمالي"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
        headerCell7.setBackgroundColor(ColorConstants.GRAY);
        headerCell7.setFontSize(12).setBold().setBaseDirection(BaseDirection.RIGHT_TO_LEFT);
        Qtable.addHeaderCell(headerCell7);


        Cell headerCell6 = new Cell().add(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("الخصم"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
        headerCell6.setBackgroundColor(ColorConstants.GRAY);
        headerCell6.setFontSize(12).setBold().setBaseDirection(BaseDirection.RIGHT_TO_LEFT);
        Qtable.addHeaderCell(headerCell6);

        Cell headerCell5 = new Cell().add(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("السعر ب.ض"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
        headerCell5.setBackgroundColor(ColorConstants.GRAY);
        headerCell5.setFontSize(12).setBold().setBaseDirection(BaseDirection.RIGHT_TO_LEFT);
        Qtable.addHeaderCell(headerCell5);

        Cell headerCell4 = new Cell().add(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("السعر ق.ض"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
        headerCell4.setBackgroundColor(ColorConstants.GRAY);
        headerCell4.setFontSize(12).setBold().setBaseDirection(BaseDirection.RIGHT_TO_LEFT);
        Qtable.addHeaderCell(headerCell4);

        Cell headerCell3 = new Cell().add(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("الكمية"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
        headerCell3.setBackgroundColor(ColorConstants.GRAY);
        headerCell3.setFontSize(12).setBold().setBaseDirection(BaseDirection.RIGHT_TO_LEFT);
        Qtable.addHeaderCell(headerCell3);


        Cell headerCell2 = new Cell().add(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("الوحدة"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
        headerCell2.setBackgroundColor(ColorConstants.GRAY);
        headerCell2.setFontSize(12).setBold().setBaseDirection(BaseDirection.RIGHT_TO_LEFT);
        Qtable.addHeaderCell(headerCell2);

        Cell headerCell1 = new Cell().add(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("الصنف"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
        headerCell1.setBackgroundColor(ColorConstants.GRAY);
        headerCell1.setFontSize(12).setBold().setBaseDirection(BaseDirection.RIGHT_TO_LEFT);
        Qtable.addHeaderCell(headerCell1);

        Cell headerCell0 = new Cell().add(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("م"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER)));
        headerCell0.setBackgroundColor(ColorConstants.GRAY);
        headerCell0.setFontSize(12).setBold().setBaseDirection(BaseDirection.RIGHT_TO_LEFT);
        Qtable.addHeaderCell(headerCell0);


        for (Integer i = 0; i < invoiceClass.getBillDetailsApis().size(); i++) {
            int Mosalsal = i + 1;
            Double Quantity = Double.valueOf(invoiceClass.getBillDetailsApis().get(i).getQuantity());
            Double price = invoiceClass.getBillDetailsApis().get(i).getPrice();
            String CellPrice = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(price))).toString();
            Double priceAfterTax;
            if (invoiceClass.getBillDetailsApis().get(i).getTaxItem() > 0) {
                priceAfterTax = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(price * (invoiceClass.getBillDetailsApis().get(i).getTaxItem() + 1))));
            } else {
                priceAfterTax = price;
            }
            Double discount = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(invoiceClass.getBillDetailsApis().get(i).getDiscount())));
            Double total = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(((Quantity * priceAfterTax) - discount))));
            Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(total.toString()))).setFont(font).setTextAlignment(TextAlignment.CENTER))).setBold();
            Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(discount.toString()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();
            Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(priceAfterTax.toString()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();

            Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(CellPrice))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();
            Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(invoiceClass.getBillDetailsApis().get(i).getQuantity().toString()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();
            Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(invoiceClass.getBillDetailsApis().get(i).getUnitname()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();
            Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(invoiceClass.getBillDetailsApis().get(i).getItemame()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();
            Qtable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(String.valueOf(Mosalsal)))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();

        }
        document.add(Qtable);
        document.add(new Paragraph("\n"));
        float[] summarytableWidth = {70, 140};
        Table summaryTable = new Table(summarytableWidth);
        summaryTable.setBorder(border);
        summaryTable.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        summaryTable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(myObj.getTotalbeforeTax().toString()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();
        summaryTable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("الإجمالي قبل الضريبة"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();


        summaryTable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(myObj.getDiscountBeforeTax().toString()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();
        summaryTable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("الخصم قبل الضريبة"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();

        summaryTable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(myObj.getTaxAmount().toString()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();
        summaryTable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("ضريبة القيمة المضافة 15% "))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();

        summaryTable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert(myObj.getNetIncludeTax().toString()))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();
        summaryTable.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("الصافي شامل الضريبة"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();


        float[] footerTableWidth = {500, 500};
        Table footerTabel = new Table(footerTableWidth);
        footerTabel.setBorder(Border.NO_BORDER);


        BarcodeQRCode qrCode = new BarcodeQRCode(myObj.getQr());

        PdfFormXObject bPdfFormXObject = qrCode.createFormXObject(ColorConstants.BLACK, pdfDocument);
        Image barcodeImage = new Image(bPdfFormXObject).setWidth(200f);


        footerTabel.addCell(new Cell().add(barcodeImage).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT)).setHorizontalAlignment(HorizontalAlignment.CENTER);
        footerTabel.addCell(new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE).add(summaryTable).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT)).setHorizontalAlignment(HorizontalAlignment.CENTER);


        document.add(footerTabel);
        Tafqeet tafqeet = new Tafqeet();
        BigDecimal x = new BigDecimal(myObj.getNetIncludeTax());
        String taf = tafqeet.convert(x);
        Paragraph tafqeetText = new Paragraph(languageProcessor.process(Farsi.Convert(taf))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT).setFontSize(20).setBold();
        document.add(tafqeetText);

        if (invoiceClass.getBill().getBillStatus()) {
            float[] paidTableWidth = {100, 100, 100, 100};
            Table paidTabel = new Table(paidTableWidth);
            paidTabel.setTextAlignment(TextAlignment.RIGHT);
            Double paidVal = 0.0;
            try {
                paidVal = Double.valueOf(PublicViewModel.TryArabicNumber(df.format(invoiceClass.getBillPaymentApis().get(0).getAmount())));
            } catch (Exception e) {
            }
            Double residual = Double.parseDouble(PublicViewModel.TryArabicNumber(df.format(myObj.getNetIncludeTax() - paidVal)));

            paidTabel.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(languageProcessor.process(Farsi.Convert(""))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold().setBorder(Border.NO_BORDER);
            paidTabel.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(languageProcessor.process(Farsi.Convert(""))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold().setBorder(Border.NO_BORDER);

            paidTabel.addCell(new Cell().add(new Paragraph(paidVal.toString())).setBorder(border).setTextAlignment(TextAlignment.RIGHT)).setHorizontalAlignment(HorizontalAlignment.CENTER);
            paidTabel.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("الجزء المدفوع"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();


            paidTabel.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(languageProcessor.process(Farsi.Convert(""))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold().setBorder(Border.NO_BORDER);
            paidTabel.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(languageProcessor.process(Farsi.Convert(""))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold().setBorder(Border.NO_BORDER);

            paidTabel.addCell(new Cell().add(new Paragraph(residual.toString())).setBorder(border).setTextAlignment(TextAlignment.RIGHT)).setHorizontalAlignment(HorizontalAlignment.CENTER);
            paidTabel.addCell(new Cell().add(new Paragraph(languageProcessor.process(Farsi.Convert("المتبقي"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER))).setBold();

            document.add(paidTabel);
        }

        Paragraph p = new Paragraph(languageProcessor.process(Farsi.Convert("المحاسب المحترف"))).setFont(font).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.CENTER).setBold();

        document.close();

        Intent intent = new Intent(CashPayment.this, pdf_view.class);
        intent.putExtra("file", file);
        startActivity(intent);
        activityCashPaymentBinding.createPdf.setEnabled(true);
        prgessDialog1.dismiss();


        // }


    }

    public void print() throws IOException {
       // createSmallpdf ();
        Test();
//        try {
//            Test();
//            //printBluetooth();
//        } catch (EscPosConnectionException e) {
//            Log.d("TAG", "diaa print first error:"+e.getMessage());
//        }
    }


    // test begin
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0) {
            if (requestCode == 101) {
                boolean readExt = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                try {
                    CreatePdf();
                    switch (requestCode) {
                        case com.dantsu.thermalprinter.MainActivity.PERMISSION_BLUETOOTH:
                        case com.dantsu.thermalprinter.MainActivity.PERMISSION_BLUETOOTH_ADMIN:
                        case com.dantsu.thermalprinter.MainActivity.PERMISSION_BLUETOOTH_CONNECT:
                        case com.dantsu.thermalprinter.MainActivity.PERMISSION_BLUETOOTH_SCAN:
                            this.checkBluetoothPermissions(this.onBluetoothPermissionsGranted);
                            break;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EscPosConnectionException e) {
                    throw new RuntimeException(e);
                }
                if (!readExt) {
                    takepermission();
                }
            }
        }
    }


    public void onload() {
        if (!utils.isPermissiongranted(this)) {
            new AlertDialog.Builder(this).setTitle(getString(R.string.permissionrequest))
                    .setMessage(getString(R.string.allowuse))
                    .setPositiveButton(getString(R.string.allow), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            takepermission();
                        }
                    }).setNegativeButton(getString(R.string.deny), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        } else {
            //  Toast.makeText(this, "Permission already", Toast.LENGTH_SHORT).show();
        }
    }

    private void takepermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
            try {


                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, 101);
            } catch (Exception e) {
                e.printStackTrace();
                Intent i = new Intent();
                i.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(i, 101);
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 101);
        }

    }

    //// printer

    public interface OnBluetoothPermissionsGranted {
        void onPermissionsGranted();
    }

    public static final int PERMISSION_BLUETOOTH = 1;
    public static final int PERMISSION_BLUETOOTH_ADMIN = 2;
    public static final int PERMISSION_BLUETOOTH_CONNECT = 3;
    public static final int PERMISSION_BLUETOOTH_SCAN = 4;

    public com.dantsu.thermalprinter.MainActivity.OnBluetoothPermissionsGranted onBluetoothPermissionsGranted;


    public void checkBluetoothPermissions(com.dantsu.thermalprinter.MainActivity.OnBluetoothPermissionsGranted onBluetoothPermissionsGranted) throws IOException, EscPosConnectionException {
        this.onBluetoothPermissionsGranted = onBluetoothPermissionsGranted;
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S && ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH}, com.dantsu.thermalprinter.MainActivity.PERMISSION_BLUETOOTH);
        } else if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S && ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_ADMIN}, com.dantsu.thermalprinter.MainActivity.PERMISSION_BLUETOOTH_ADMIN);
        } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S && ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, com.dantsu.thermalprinter.MainActivity.PERMISSION_BLUETOOTH_CONNECT);
        } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S && ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, com.dantsu.thermalprinter.MainActivity.PERMISSION_BLUETOOTH_SCAN);
        } else {
            this.onBluetoothPermissionsGranted.onPermissionsGranted();
        }
    }

    private com.dantsu.escposprinter.connection.bluetooth.BluetoothConnection selectedDevice;

    public void browseBluetoothDevice() throws IOException, EscPosConnectionException {
        this.checkBluetoothPermissions(() -> {
            final com.dantsu.escposprinter.connection.bluetooth.BluetoothConnection[] bluetoothDevicesList = (new BluetoothPrintersConnections()).getList();

            if (bluetoothDevicesList != null) {
                final String[] items = new String[bluetoothDevicesList.length + 1];
                items[0] = "Default printer";
                int i = 0;
                for (com.dantsu.escposprinter.connection.bluetooth.BluetoothConnection device : bluetoothDevicesList) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    items[++i] = device.getDevice().getName();
                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CashPayment.this);
                alertDialog.setTitle("Bluetooth printer selection");
                alertDialog.setItems(
                        items,
                        (dialogInterface, i1) -> {
                            int index = i1 - 1;
                            if (index == -1) {
                                selectedDevice = null;
                            } else {
                                selectedDevice = bluetoothDevicesList[index];
                            }
                            //     Button button = (Button) findViewById(R.id.button_bluetooth_browse);
                            //      button.setText(items[i1]);
                        }
                );

                AlertDialog alert = alertDialog.create();
                alert.setCanceledOnTouchOutside(false);
                alert.show();
            }
        });

    }

    public void printBluetooth() throws EscPosConnectionException
    {
        Log.d(TAG, "diaa printBluetooth start method: ");

        EscPosPrinter printer = new EscPosPrinter(selectedDevice, 203, 48f, 32);

       // Bitmap da = Test();

       // activityCashPaymentBinding.imageBillTest.setImageBitmap(da);

        try {
            printer.printFormattedText(
                            "[C]<img>" + PrinterTextParserImg.bitmapToHexadecimalString(printer,
                                    activityCashPaymentBinding.imageBillTest.getDrawable())+"</img>\n" +
                                    "[L]\n" +
                                    "[C]<u><font size='big'>ORDER N°045</font></u>\n"
                    );
        } catch (EscPosParserException e) {
            Log.d(TAG, "diaa first catch error:"+e.getMessage());
        } catch (EscPosEncodingException e) {
            Log.d(TAG, "diaa second catch error:"+e.getMessage());

        } catch (EscPosBarcodeException e) {
            Log.d(TAG, "diaa third catch error:"+e.getMessage());

        }
//        try {
//            this.checkBluetoothPermissions(() -> {
//                new AsyncBluetoothEscPosPrint(
//                        this,
//                        new AsyncEscPosPrint.OnPrintFinished() {
//                            @Override
//                            public void onError(AsyncEscPosPrinter asyncEscPosPrinter, int codeException) {
//                                Log.e("Async.OnPrintFinished", "AsyncEscPosPrint.OnPrintFinished : An error occurred !");
//                            }
//
//                            @Override
//                            public void onSuccess(AsyncEscPosPrinter asyncEscPosPrinter) {
//                                Log.i("Async.OnPrintFinished", "AsyncEscPosPrint.OnPrintFinished : Print is finished !");
//                            }
//                        }
//                )
//                        .execute(this.getAsyncEscPosPrinter(selectedDevice));
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }




    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }



    public AsyncEscPosPrinter getAsyncEscPosPrinter(com.dantsu.escposprinter.connection.DeviceConnection printerConnection) throws IOException, EscPosConnectionException {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("'on' yyyy-MM-dd 'at' HH:mm:ss");

        EscPosPrinter printerA = new EscPosPrinter(printerConnection, 203, 48f, 32);
        AsyncEscPosPrinter printer = new AsyncEscPosPrinter(printerConnection, 203, 48f, 32);

        return printer.addTextToPrint(


                        "[L]\n"+
                        "[C]<u><font size='big'></font></u>\n"+
                        "[C]<u><font size='big'>Hello</font></u>\n"+

/*
                        "[L]\n" +
                        "[C]<u><font size='big'>ORDER N°045</font></u>\n" +
                        "[L]\n" +
                        "[C]<u type='double'>" + format.format(new Date()) + "</u>\n" +
                        "[C]\n" +
                        "[C]================================\n" +
                        "[L]\n" +
                        "[L]<b>BEAUTIFUL SHIRT</b>[R]9.99€\n" +
                        "[L]  + Size : S\n" +
                        "[L]\n" +
                        "[L]<b>AWESOME HAT</b>[R]24.99€\n" +
                        "[L]  + Size : 57/58\n" +
                        "[L]\n" +
                        "[C]--------------------------------\n" +
                        "[R]TOTAL PRICE :[R]34.98€\n" +
                        "[R]TAX :[R]4.23€\n" +
                        "[L]\n" +
                        "[C]================================\n" +
                        "[L]\n" +
                        "[L]<u><font color='bg-black' size='tall'>Customer :</font></u>\n" +
                        "[L]Raymond DUPONT\n" +
                        "[L]5 rue des girafes\n" +
                        "[L]31547 PERPETES\n" +
                        "[L]Tel : +33801201456\n" +
                        "\n" +
                        "[C]<barcode type='ean13' height='10'>831254784551</barcode>\n" +
                        "[L]\n" +*/
                        "[C]<qrcode size='40'>"+TempQr+"</qrcode>\n"
        );
    }



    public static  Bitmap drawText (String text, int txtWidth, int textSize)
    {
        TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        StaticLayout mtextlayout= new StaticLayout(text, textPaint,  txtWidth , Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);

        Bitmap b = Bitmap.createBitmap(txtWidth , mtextlayout.getHeight(), Bitmap.Config.RGB_565);
        Canvas c = new Canvas(b);

        Paint paint= new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        c.drawPaint(paint);

        c.save();
        c.translate(0,0);
        mtextlayout.draw(c);
        c.restore();
        return  b;

    }


    public  void  Test ()
    {
        String invoice="";
        StringBuilder allItemsInHtml= new StringBuilder();
        for (int i=0 ; i< invoiceClass.getBillDetailsApis().size(); i++)
        {
            Double all = Double.parseDouble(invoiceClass.getBillDetailsApis().get(i).getPrice().toString())
                    * Double.parseDouble(invoiceClass.getBillDetailsApis().get(i).getQuantity().toString());

            allItemsInHtml.append(sheet.ITEM_SHEET_1.replace(Common.ITEM_PRICE,
                            String.valueOf(all)).replace(Common.ITEM_VALUE_ALL, invoiceClass.getBillDetailsApis().get(i).getPrice().toString()).
                    replace(Common.ITEM_NAME, invoiceClass.getBillDetailsApis().get(i).getItemame())
                    .replace(Common.ITEM_QUANTITY,invoiceClass.getBillDetailsApis().get(i).getQuantity().toString()));
        }
        invoice=sheet.END_HEAD + allItemsInHtml;

      //  Bitmap newTest = textAsBitmap(invoice);


        String finalInvoice = invoice;
        Observable observable =  Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(@io.reactivex.annotations.NonNull ObservableEmitter<Object> emitter) throws Exception {
                        Bitmap dsa =  new Html2Bitmap.Builder().setContext(CashPayment.this).
                                setContent(WebViewContent.html(finalInvoice)).build().getBitmap();
                        emitter.onNext(dsa);
                        Thread.sleep(3000);
                        emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        io.reactivex.Observer observer = new io.reactivex.Observer() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull Object o)
            {
                activityCashPaymentBinding.imageBillTest.setImageBitmap((Bitmap) o);
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {

            }

            @Override
            public void onComplete()
            {
                activityCashPaymentBinding.imageBillTest.setVisibility(View.VISIBLE);

                EscPosPrinter printer = null;
                try {
                    printer = new EscPosPrinter(BluetoothPrintersConnections.selectFirstPaired(), 203, 48f, 32);
                } catch (EscPosConnectionException e) {
                    throw new RuntimeException(e);
                }

                // Bitmap da = Test();

                // activityCashPaymentBinding.imageBillTest.setImageBitmap(da);

                try {
                    try {
                        printer.printFormattedText(
                                "[C]<img>" + PrinterTextParserImg.bitmapToHexadecimalString(printer,
                                        activityCashPaymentBinding.imageBillTest.getDrawable())+"</img>\n" +
                                        "[L]\n" +
                                        "[C]<u><font size='big'>ORDER N°045</font></u>\n"
                        );
                    } catch (EscPosConnectionException e) {
                        Log.d(TAG, "diaa before catch error:"+e.getMessage());

                    }
                } catch (EscPosParserException e) {
                    Log.d(TAG, "diaa first catch error:"+e.getMessage());
                } catch (EscPosEncodingException e) {
                    Log.d(TAG, "diaa second catch error:"+e.getMessage());

                } catch (EscPosBarcodeException e) {
                    Log.d(TAG, "diaa third catch error:"+e.getMessage());

                }

            }
        };

        observable.subscribe(observer);
    }

    public Bitmap textAsBitmap(String text) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.5f); // round
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }
}