package com.e.havicash;

import android.Manifest;
import android.accessibilityservice.AccessibilityService;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.e.havicash.R;
import com.crowdfire.cfalertdialog.CFAlertDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SendMoney extends AppCompatActivity  {
String edttext1,edttext2;
//EditText edttext1,edttext1;
int position;//= Integer.parseInt(null);
String amt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        final CheckBox cbug = (CheckBox) findViewById(R.id.ugcb);
        final CheckBox cbug1 = (CheckBox) findViewById(R.id.ngncb);

        cbug.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cbug1.setChecked(false);
                cbug.setChecked(true);

            }
        });

        cbug1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cbug1.setChecked(true);
                cbug.setChecked(false);

            }
        });


       // final AutoCompleteTextView autocomplete = findViewById(R.id.autoCompleteTextView1);

        String[] arr = { "UG Daily: SD - 15MB (1 BT – 1.5GB/UGX 1,500)",
                "UG 3 Days: SD - 45MB (3 BTs – 4.5GB/UGX 4,500)",
                "UG 4 Days: SD - 60MB (4 BTs – 6GB/UGX 7,000)",
                "UG Weekly: SD - 105MB (7 BTs – 10.5 GB/UGX 11,500)",
                "UG Monthly: SD -450MB (30 BTs – 45 GB/UGX 46,500)"};

        String[] arrngn = {
                "NGN Daily: SD - 15MB (1 BT – 1.5GB/N 155)",
                "NGN Weekly: SD - 105MB (7 BTs – 10.5 GB/N 1,150)",
                "NGN Monthly: SD - 450MB (30 BTs – 45 GB/N 5,000)"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, arr);

        ArrayAdapter<String> adapterngn = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, arrngn);

       final ListView lv = (ListView)findViewById(R.id.Listview1);
        lv.setAdapter(adapter);


       // autocomplete.setThreshold(1);
       // autocomplete.setAdapter(adapter);
        final ListView listngn = (ListView) findViewById(R.id.Listviewngn);
        listngn.setAdapter(adapterngn);

        listngn.setVisibility(View.INVISIBLE);
        lv.setVisibility(View.VISIBLE);


        //autocomplete.getText().toString();
        AutoCompleteTextView autocompletecountry = findViewById(R.id.autoCompletecountry);
        autocompletecountry.setVisibility(View.GONE);

        String[] arr1 = {
                "UGANDA",
                "NIGERIA"};

        final String[] paychoice = {
                "One-Off",
                "Auto Renew"};

        final boolean[] seletions= {
                true,
                false};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, arr1);

        CheckBox ugcb = (CheckBox) findViewById(R.id.ugcb);

        CheckBox ngcb = (CheckBox) findViewById(R.id.ngncb);

        ugcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listngn.setVisibility(View.GONE);
                lv.setVisibility(View.VISIBLE);

            }
        });

        ngcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lv.setVisibility(View.GONE);
                listngn.setVisibility(View.VISIBLE);

            }
        });

        autocompletecountry.setThreshold(-1);
        autocompletecountry.setAdapter(adapter1);
        autocompletecountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               // lv.setVisibility(View.GONE);
               // listngn.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lv.setVisibility(View.GONE);
                listngn.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable editable) {
                lv.setVisibility(View.GONE);
                listngn.setVisibility(View.VISIBLE);

            }
        });



            Toast.makeText(getApplicationContext(), "Account Successfully registered", Toast.LENGTH_SHORT).show();




         //   Button sendbtn = (Button)findViewById(R.id.btnsendbt);


        listngn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = lv.getItemAtPosition(position);


                CFAlertDialog.Builder builder = new CFAlertDialog.Builder(SendMoney.this);
                builder.setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT);
                builder.setTitle("PAYMENT OPTION");
                builder.setMessage("PAY in NGN" );

                if(position ==0){
                   final  String amt ="155";
                }
                if(position ==1){
                  final  String amt ="1550";
                }
                if(position ==2){
                   final String amt ="5000";
                }

                builder.setMultiChoiceItems(paychoice, seletions, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                    }
                });


                builder.addButton("VISA", -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // callPhoneNumber();
                        dialog.dismiss();
                    }
                });

                builder.addButton("Cancel", -1, -1, CFAlertDialog.CFAlertActionStyle.NEGATIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Object listItem = lv.getItemAtPosition(position);


                CFAlertDialog.Builder builder = new CFAlertDialog.Builder(SendMoney.this);
                builder.setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT);
                builder.setTitle("PAYMENT OPTION");
                builder.setMessage("PAY in UGX" );
                builder.setMultiChoiceItems(paychoice, seletions, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                    }
                });


                builder.addButton("Airtel", -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // callPhoneNumber();

                        if(position ==0){
                            final   String amt ="1500";

                            //add consent before dialing


                            CFAlertDialog.Builder builder = new CFAlertDialog.Builder(SendMoney.this);
                            builder.setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT);
                            builder.setTitle("Booster Token Purchase");
                            builder.setMessage("You have just initiated a Payment Transaction of UGX 1500 for the Purchase of 1.5GB ie 1BT Booster Tokens (BTs)");
                            builder.addButton("Confirm", -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // callPhoneNumber();
                                    try
                                    {
                                        if(Build.VERSION.SDK_INT > 10)
                                        {
                                            if (ActivityCompat.checkSelfPermission(SendMoney.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                                // TODO: Consider calling

                                                ActivityCompat.requestPermissions(SendMoney.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                                                return;
                                            }


                                            String ussdCode = "*" + "185"+"*"+"7"+"*"+"1"+"*"+"1"+"*"+"1"+"*"+"2"+"*"+"1009101133662"+"*"+ "1500" + Uri.encode("#");
                                            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ussdCode)));





                                        }
                                        else {
                                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                                            callIntent.setData(Uri.parse("tel:" + "0754033875"));
                                            startActivity(callIntent);
                                        }
                                        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(SendMoney.this);
                                        builder.setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT);
                                        builder.setTitle("Purchase Successful");
                                        builder.setMessage("You have successfully Purchased 1 Booster Tokens (BTs) ie 1.5GB worth UGX 1500 valid for 1day Please confirm by activating your Booster Tokens");
                                        builder.addButton("Activate", -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        });

                                    }
                                    catch (Exception ex)
                                    {
                                        ex.printStackTrace();
                                    }
                                    dialog.dismiss();
                                }
                            });

                            builder.addButton("Reject", -1, -1, CFAlertDialog.CFAlertActionStyle.NEGATIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.show();


                            //add consent aftr dailing

                        }
                        if(position ==1){
                            final String amt ="4500";
                            try
                            {
                                if(Build.VERSION.SDK_INT > 10)
                                {
                                    if (ActivityCompat.checkSelfPermission(SendMoney.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                        // TODO: Consider calling

                                        ActivityCompat.requestPermissions(SendMoney.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                                        return;
                                    }


                                    String ussdCode = "*" + "185"+"*"+"7"+"*"+"1"+"*"+"1"+"*"+"1"+"*"+"2"+"*"+"1009101133662"+"*"+ "4500" + Uri.encode("#");
                                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ussdCode)));


                                }
                                else {
                                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                                    callIntent.setData(Uri.parse("tel:" + "0754033875"));
                                    startActivity(callIntent);
                                }
                            }
                            catch (Exception ex)
                            {
                                ex.printStackTrace();
                            }
                        }
                        if(position ==2){
                            final  String amt ="7000";
                            try
                            {
                                if(Build.VERSION.SDK_INT > 10)
                                {
                                    if (ActivityCompat.checkSelfPermission(SendMoney.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                        // TODO: Consider calling

                                        ActivityCompat.requestPermissions(SendMoney.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                                        return;
                                    }


                                    String ussdCode = "*" + "185"+"*"+"7"+"*"+"1"+"*"+"1"+"*"+"1"+"*"+"2"+"*"+"1009101133662"+"*"+ "7000" + Uri.encode("#");
                                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ussdCode)));



                                }
                                else {
                                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                                    callIntent.setData(Uri.parse("tel:" + "0754033875"));
                                    startActivity(callIntent);
                                }
                            }
                            catch (Exception ex)
                            {
                                ex.printStackTrace();
                            }
                        }

                        if(position ==3){
                            final String amt ="11500";
                            try
                            {
                                if(Build.VERSION.SDK_INT > 10)
                                {
                                    if (ActivityCompat.checkSelfPermission(SendMoney.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                        // TODO: Consider calling

                                        ActivityCompat.requestPermissions(SendMoney.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                                        return;
                                    }


                                    String ussdCode = "*" + "185"+"*"+"7"+"*"+"1"+"*"+"1"+"*"+"1"+"*"+"2"+"*"+"1009101133662"+"*"+ "11500" + Uri.encode("#");
                                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ussdCode)));


                                }
                                else {
                                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                                    callIntent.setData(Uri.parse("tel:" + "0754033875"));
                                    startActivity(callIntent);
                                }
                            }
                            catch (Exception ex)
                            {
                                ex.printStackTrace();
                            }
                        }

                        if(position ==4){
                            final String amt ="46500";
                            try
                            {
                                if(Build.VERSION.SDK_INT > 10)
                                {
                                    if (ActivityCompat.checkSelfPermission(SendMoney.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                        // TODO: Consider calling

                                        ActivityCompat.requestPermissions(SendMoney.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                                        return;
                                    }


                                    String ussdCode = "*" + "185"+"*"+"7"+"*"+"1"+"*"+"1"+"*"+"1"+"*"+"2"+"*"+"1009101133662"+"*"+ "46500" + Uri.encode("#");
                                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ussdCode)));

                                    Intent i = new Intent(getApplicationContext(), DatameterStat.class);

                                    startActivity(i);

                                }
                                else {
                                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                                    callIntent.setData(Uri.parse("tel:" + "0754033875"));
                                    startActivity(callIntent);
                                }
                            }
                            catch (Exception ex)
                            {
                                ex.printStackTrace();
                            }
                        }
                        dialog.dismiss();

                    }
                });

                builder.addButton("MTN", -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // callPhoneNumber();


                        if(position ==0){
                            final   String amt ="1500";
                            try
                            {
                                if(Build.VERSION.SDK_INT > 10)
                                {
                                    if (ActivityCompat.checkSelfPermission(SendMoney.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                        // TODO: Consider calling

                                        ActivityCompat.requestPermissions(SendMoney.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                                        return;
                                    }


                                    String ussdCode = "*" + "185"+"*"+"7"+"*"+"1"+"*"+"1"+"*"+"1"+"*"+"2"+"*"+"1009101133662"+"*"+ "1500" + Uri.encode("#");
                                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ussdCode)));



                                }
                                else {
                                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                                    callIntent.setData(Uri.parse("tel:" + "0754033875"));
                                    startActivity(callIntent);
                                }
                            }
                            catch (Exception ex)
                            {
                                ex.printStackTrace();
                            }
                        }
                        if(position ==1){
                            final String amt ="4500";
                            try
                            {
                                if(Build.VERSION.SDK_INT > 10)
                                {
                                    if (ActivityCompat.checkSelfPermission(SendMoney.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                        // TODO: Consider calling

                                        ActivityCompat.requestPermissions(SendMoney.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                                        return;
                                    }


                                    String ussdCode = "*" + "185"+"*"+"7"+"*"+"1"+"*"+"1"+"*"+"1"+"*"+"2"+"*"+"1009101133662"+"*"+ "4500" + Uri.encode("#");
                                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ussdCode)));

                                    //  Intent callIntent = new Intent(Intent.ACTION_CALL);
                                    //  callIntent.setData(Uri.parse("tel:" + "0754033875"));
                                    // startActivity(callIntent);

                                }
                                else {
                                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                                    callIntent.setData(Uri.parse("tel:" + "0754033875"));
                                    startActivity(callIntent);
                                }
                            }
                            catch (Exception ex)
                            {
                                ex.printStackTrace();
                            }
                        }
                        if(position ==2){
                            final  String amt ="7000";
                            try
                            {
                                if(Build.VERSION.SDK_INT > 10)
                                {
                                    if (ActivityCompat.checkSelfPermission(SendMoney.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                        // TODO: Consider calling

                                        ActivityCompat.requestPermissions(SendMoney.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                                        return;
                                    }


                                    String ussdCode = "*" + "185"+"*"+"7"+"*"+"1"+"*"+"1"+"*"+"1"+"*"+"2"+"*"+"1009101133662"+"*"+ "7000" + Uri.encode("#");
                                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ussdCode)));

                                    //  Intent callIntent = new Intent(Intent.ACTION_CALL);
                                    //  callIntent.setData(Uri.parse("tel:" + "0754033875"));
                                    // startActivity(callIntent);

                                }
                                else {
                                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                                    callIntent.setData(Uri.parse("tel:" + "0754033875"));
                                    startActivity(callIntent);
                                }
                            }
                            catch (Exception ex)
                            {
                                ex.printStackTrace();
                            }
                        }

                        if(position ==3){
                            final String amt ="11500";
                        }

                        if(position ==4){
                            final String amt ="46500";
                        }
                        dialog.dismiss();

                    }
                });

                builder.addButton("VISA", -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

                builder.addButton("Cancel", -1, -1, CFAlertDialog.CFAlertActionStyle.NEGATIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }
        });



        if(position ==1){
            Intent i = new Intent(getApplicationContext(), DatameterStat.class);

            startActivity(i);
        }



        }
    public static String TAG = SendMoney.class.getSimpleName();

    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "onAccessibilityEvent");

        AccessibilityNodeInfo source = event.getSource();
        /* if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED && !event.getClassName().equals("android.app.AlertDialog")) { // android.app.AlertDialog is the standard but not for all phones  */
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED && !String.valueOf(event.getClassName()).contains("AlertDialog")) {
            return;
        }
        if(event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED && (source == null || !source.getClassName().equals("android.widget.TextView"))) {
            return;
        }
        if(event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED && TextUtils.isEmpty(source.getText())) {
            return;
        }

        List<CharSequence> eventText;

        if(event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            eventText = event.getText();
        } else {
            eventText = Collections.singletonList(source.getText());
        }

       // String text = processUSSDText(eventText);
        Intent i = new Intent(getApplicationContext(), DatameterStat.class);

        startActivity(i);




        // Handle USSD response here

    }


}