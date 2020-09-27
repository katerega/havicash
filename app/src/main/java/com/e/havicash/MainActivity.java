package com.e.havicash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import org.stellar.sdk.Asset;
import org.stellar.sdk.AssetTypeCreditAlphaNum;
import org.stellar.sdk.AssetTypeNative;
import org.stellar.sdk.KeyPair;
import org.stellar.sdk.Memo;
import org.stellar.sdk.Network;
import org.stellar.sdk.PaymentOperation;
import org.stellar.sdk.Server;
import org.stellar.sdk.Transaction;
import org.stellar.sdk.requests.PaymentsRequestBuilder;
import org.stellar.sdk.responses.AccountResponse;
import org.stellar.sdk.responses.SubmitTransactionResponse;
import org.stellar.sdk.responses.operations.OperationResponse;
import org.stellar.sdk.responses.operations.PaymentOperationResponse;

import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.*;
import org.stellar.sdk.Server;
import org.stellar.sdk.responses.AccountResponse;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Button about = (Button)findViewById(R.id.btnabout);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Aboutus.class);
                // Pass image index
                startActivity(i);
                Toast.makeText(getApplicationContext(), "About us", Toast.LENGTH_SHORT).show();


            }
        });

        Button aboutb = (Button)findViewById(R.id.btnpurchasebooster);
        aboutb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SendMoney.class);
                // Pass image index
                startActivity(i);
                Toast.makeText(getApplicationContext(), "purchase", Toast.LENGTH_SHORT).show();


            }
        });

        Button activate1 = (Button)findViewById(R.id.btnactivate);
        activate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DatameterStat.class);
                // Pass image index
                startActivity(i);


            }
        });

        Button browser1 = (Button)findViewById(R.id.btnignite);
        browser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DataMonitor.class);
                // Pass image index
                startActivity(i);
                Toast.makeText(getApplicationContext(), "browser", Toast.LENGTH_SHORT).show();


            }
        });
    }

}
