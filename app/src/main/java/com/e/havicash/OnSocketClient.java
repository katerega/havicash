package com.e.havicash;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import jpcap.JpcapCaptor;
import jpcap.JpcapSender;
import jpcap.NetworkInterface;
import jpcap.packet.EthernetPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.TCPPacket;

public class OnSocketClient {

    String statn;
    public String runonsocket(){
        try{
            Socket s=new Socket("localhost",6666);

            DataOutputStream dout=new DataOutputStream(s.getOutputStream());

            dout.writeUTF("Hello Server");
            dout.flush();

            dout.close();
            s.close();

        }catch(Exception e){System.out.println(e);}
        return statn;
    }



    public void k103() {
        //implement K103  client to server
        //1.access Ethernet for test word at nrmal rate
        EthernetPacket ether=new EthernetPacket();
        ether.frametype=EthernetPacket.ETHERTYPE_IP;
        ether.src_mac=new byte[]{(byte)0,(byte)1,(byte)2,(byte)3,(byte)4,(byte)5};
        ether.dst_mac=new byte[]{(byte)0,(byte)6,(byte)7,(byte)8,(byte)9,(byte)10};
      //  p.datalink=ether;
        String TAG;
        Log.d("ClientEthernet",ether.toString());
        //2. convert ethernet frame type to k103 frame type

        //3.Create a new packet following the new MTU



    }

    public void HVP()  {
        //implement HVP protocol

        // 1.Send ICMP count monitor

        //2.convert k103  to TCP packets/frmes

        //open a network interface to send a packet to
        int index=0;

//Obtain the list of network interfaces
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();
        JpcapSender sender= null;
        try {
            sender = JpcapSender.openDevice(devices[index]);
        } catch (IOException e) {
            e.printStackTrace();
        }

//create a TCP packet with specified port numbers, flags, and other parameters
        TCPPacket p=new TCPPacket(12,34,56,78,false,false,false,false,true,true,true,true,10,10);

//specify IPv4 header parameters
        try {
            p.setIPv4Parameter(0,false,false,false,0,false,false,false,0,1010101,100, IPPacket.IPPROTO_TCP,
                    InetAddress.getByName("www.youtube.com"), InetAddress.getByName("www.google.com"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

//set the data field of the packet
        p.data=("data").getBytes();

//create an Ethernet packet (frame)
        EthernetPacket ether=new EthernetPacket();
//set frame type as IP
        ether.frametype= EthernetPacket.ETHERTYPE_IP;
//set source and destination MAC addresses
        ether.src_mac=new byte[]{(byte)0,(byte)1,(byte)2,(byte)3,(byte)4,(byte)5};
        ether.dst_mac=new byte[]{(byte)0,(byte)6,(byte)7,(byte)8,(byte)9,(byte)10};

//set the datalink frame of the packet p as ether
        p.datalink=ether;

//send the packet p
        sender.sendPacket(p);

        sender.close();

    }
}
