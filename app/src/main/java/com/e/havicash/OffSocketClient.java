package com.e.havicash;

import android.net.VpnService;

import java.io.DataOutputStream;
import java.net.Socket;

public class OffSocketClient extends VpnService {
    String statn;
    public String runoffsocket(){
        try{
            Socket s=new Socket("localhost",8218);

            DataOutputStream dout=new DataOutputStream(s.getOutputStream());

            dout.writeUTF("Welcome to havillah world");
            dout.flush();

            dout.close();
            s.close();

        }catch(Exception e){System.out.println(e);}
        return statn;
    }



    private void k103() {
        //implement K103
        //1.access Ethernet froame
        //2.access MTU
        //3.Create a new packet following the new MTU



    }

    private void HVP(){
        //implement HVP protocol

        // 1.ICMP count monitor
        //2.convert to TCP packets/frmes

    }
}
