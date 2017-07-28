package com.example.hsarkisla.itenvantermobilapp.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;


import com.example.hsarkisla.itenvantermobilapp.R;
import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture;
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever;

public class ScanActivity extends AppCompatActivity implements BarcodeRetriever {

    private static final String TAG = "BarcodeMain";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_activity);


        final BarcodeCapture barcodeCapture = (BarcodeCapture) getSupportFragmentManager().findFragmentById(R.id.barcode);
        barcodeCapture.setRetrieval(this);


        findViewById(R.id.flash).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                {
                    barcodeCapture.setShowFlash(true);
                    barcodeCapture.refresh();

                }

            }

        });

    }


    @Override
    public void onRetrieved(final Barcode barcode) {
        Log.d(TAG, "Barcode read: " + barcode.displayValue);

        runOnUiThread(new Runnable() {

            @Override


            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(ScanActivity.this)
                        .setTitle("code retrieved")
                        .setMessage(barcode.displayValue);

                sendData(barcode.displayValue);

                builder.show();

            }
        });


    }

    public void sendData(String displayValue) {
        Intent myIntent = new Intent(this, MainActivity.class);

        myIntent.putExtra("BARCODE", displayValue);
        this.startActivity(myIntent);

    }

    @Override

    public void onRetrievedMultiple(final Barcode closetToClick, final List<BarcodeGraphic> barcodeGraphics) {

        runOnUiThread(new Runnable() {

            @Override

            public void run() {

                String message = "Code selected : " + closetToClick.displayValue + "\n\nother " +

                        "codes in frame include : \n";

                for (int index = 0; index < barcodeGraphics.size(); index++) {

                    Barcode barcode = barcodeGraphics.get(index).getBarcode();

                    message += (index + 1) + ". " + barcode.displayValue + "\n";

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ScanActivity.this)

                        .setTitle("code retrieved")

                        .setMessage(message);

                builder.show();

            }

        });


    }


    @Override

    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {


    }


    @Override

    public void onRetrievedFailed(String reason) {


    }


}



