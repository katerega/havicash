package com.e.havicash;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.LocalServerSocket;
import android.net.VpnService;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.net.TrafficStats;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.crowdfire.cfalertdialog.CFAlertDialog;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.text.DecimalFormat;


public class DatameterStat extends AppCompatActivity {

    private Handler mHandler = new Handler();
    private long mStartRX = 0;
    private long mStartTX = 0;
    Runnable mRunnable;
    String dataon;
   // TextView RX; TextView TX; TextView RX1; TextView TX1;
    boolean switchstate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datameter_stat);
        mStartRX = TrafficStats.getTotalRxBytes();
        mStartTX = TrafficStats.getTotalTxBytes();
        Switch dataswtch1 = (Switch) findViewById(R.id.dataswtch1);


        if (dataswtch1 != null) {
            dataswtch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    // TODO Auto-generated method stub

                    if(isChecked)
                    {
                        Toast.makeText(DatameterStat.this, "Data on", Toast.LENGTH_LONG).show();
                          dataon = "on";

                        switchstate = true;
                        TextView RX = (TextView) findViewById(R.id.RX);
                        TextView TX = (TextView) findViewById(R.id.TX);
                        RX.setVisibility(View.VISIBLE);
                        TX.setVisibility(View.VISIBLE);



                        //call socekt1 and block all traffic outside socekt
                        //call vpn
                        MyVpnService vpn1 = new MyVpnService();
                        // vpn1.connect();

                        OnSocketClient ons  = new OnSocketClient();
                        ons.k103();
                       // ons.HVP();
                        ons.runonsocket();



                        OnSocketServer onserver = new OnSocketServer();



                        //send icmp
                        SendICMP sicmp = new SendICMP();
                        String arg[];
                       // sicmp.main(arg;

                       // Intent intent = VpnService.prepare(DatameterStat.this);
                      //  vpn1.startService(intent);
                        Intent intent = VpnService.prepare(getApplicationContext());if (intent != null) {startActivityForResult(intent, 0);} else {onActivityResult(0, RESULT_OK, null);}




                    }
                    else
                    {
                        Toast.makeText(DatameterStat.this, "daata off", Toast.LENGTH_LONG).show();

                        OffSocketClient offn = new OffSocketClient();
                        OffSocketServer offs = new OffSocketServer();
                        switchstate = false;

                        TextView RX = (TextView) findViewById(R.id.RX);
                        TextView TX = (TextView) findViewById(R.id.TX);

                        RX.setVisibility(View.GONE);
                                TX.setVisibility(View.GONE);

                        TextView rsd = (TextView) findViewById(R.id.rsd);
                        TextView bbt = (TextView) findViewById(R.id.bbt);
                        long rxBytes1 = TrafficStats.getTotalRxBytes() - mStartRX;
                        double d1 = (double) rxBytes1 / 1048576;
                        DecimalFormat df = new DecimalFormat("#.####");
                        // df.format(d1);
                        rsd.setText("Balance Reserved SD:      "+ String.valueOf(df.format(d1)) + "MB");
                        double havijumpdata = (d1 / 1024) * 103;

                        bbt.setText("Balance BT:      "+ String.valueOf(df.format(havijumpdata)) + "GB");


                    }

                }

            });
        }

        final CFAlertDialog.Builder builder = new CFAlertDialog.Builder(DatameterStat.this);
        builder.setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT);
        builder.setTitle("Would you like to allow haviCash to Switch on the Expansion/Boosting Software?");



        builder.addButton("YES", -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Switch dataswtch1  = (Switch) findViewById(R.id.dataswtch1);
               // dataswtch1.setChecked(true);

                CFAlertDialog.Builder builder = new CFAlertDialog.Builder(DatameterStat.this);
                builder.setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT);
                builder.setTitle("PERMISSIONS");


                builder.addButton("Would you like to Browse the Internet using your “Purchased” Expanded/Boosted Data via your own Device?", -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                        homeIntent.addCategory( Intent.CATEGORY_HOME );
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(homeIntent);

                        Intent restartService = new Intent(getApplicationContext(), this.getClass());
                            PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 1, restartService, PendingIntent.FLAG_ONE_SHOT);
                            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                            alarmManager.set(AlarmManager.ELAPSED_REALTIME, 5000, pendingIntent);



                        dialog.dismiss();

                    }
                }).addButton("Would you like to Browse the Internet using your “Purchased” Expanded/Boosted Data via igNiTe™?", -1, -1, CFAlertDialog.CFAlertActionStyle.NEGATIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(), DataMonitor.class);

                        startActivity(i);
                        dialog.dismiss();
                    }
                });

                builder.show();

            }
        });

        builder.addButton("NO", -1, -1, CFAlertDialog.CFAlertActionStyle.NEGATIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.show();

        if (mStartRX == TrafficStats.UNSUPPORTED || mStartTX == TrafficStats.UNSUPPORTED) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Uh Oh!");
            alert.setMessage("Your device does not support traffic stat monitoring.");
            alert.show();
        } else {
            mHandler.postDelayed(mRunnable, 1000);
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {if (resultCode == RESULT_OK) {Intent intent = new Intent(this, MyVpnService.class);startService(intent);}}
    public class MyVpnService extends VpnService {
        private Thread mThread;
        private ParcelFileDescriptor mInterface;
        Builder builder = new Builder();

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            mThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mInterface = builder.setSession("MyVPNService")
                                .addAddress("192.168.0.1", 24)
                                .addDnsServer("8.8.8.8")
                                .addRoute("0.0.0.0", 0).establish();
                        FileInputStream in = new FileInputStream(
                                mInterface.getFileDescriptor());
                        FileOutputStream out = new FileOutputStream(
                                mInterface.getFileDescriptor());
                        DatagramChannel tunnel = DatagramChannel.open();
                        tunnel.connect(new InetSocketAddress("127.0.0.1", 8087));
                        protect(tunnel.socket());
                        while (true) {
                            Thread.sleep(100);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (mInterface != null) {
                                mInterface.close();
                                mInterface = null;
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }, "MyVpnRunnable");
            mThread.start();
            return START_STICKY;
        }

        @Override
        public void onDestroy() {
            if (mThread != null) {
                mThread.interrupt();
            }
            super.onDestroy();
        }
    }

    private final Runnable mRunnable1 = new Runnable() {
        public void run() {



                TextView RX = (TextView) findViewById(R.id.RX);
                TextView TX = (TextView) findViewById(R.id.TX);
                long rxBytes = TrafficStats.getTotalRxBytes() - mStartRX;
                double d1 = (double) rxBytes / 1048576;
                DecimalFormat df = new DecimalFormat("#.####");
                // df.format(d1);
                RX.setText(String.valueOf(df.format(d1)) + "MB");
                long txBytes = TrafficStats.getTotalTxBytes() - mStartTX;
                double havijumpdata = (d1 / 1024) * 103;

                TX.setText(String.valueOf(df.format(havijumpdata)) + "GB");
                mHandler.postDelayed(mRunnable1, 1000);

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        MenuItem item = menu.findItem(R.id.myswitch);
        item.setActionView(R.layout.switch_layout);
        return true;
    }
}
