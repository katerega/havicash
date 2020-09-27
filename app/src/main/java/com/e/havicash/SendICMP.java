package com.e.havicash;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import jpcap.*;
import jpcap.packet.EthernetPacket;
import jpcap.packet.ICMPPacket;
import jpcap.packet.IPPacket;

public class SendICMP {

 public static void main(String[] args) throws java.io.IOException{
     NetworkInterface[] devices = JpcapCaptor.getDeviceList();

     if(args.length<1){
         System.out.println("Usage: java SentICMP <device index (e.g., 0, 1..)>");
         for(int i=0;i<devices.length;i++)
             System.out.println(i+":"+devices[i].name+"("+devices[i].description+")");
         System.exit(0);
     }
     int index=Integer.parseInt(args[0]);
     JpcapSender sender= null;
     try {
         sender = JpcapSender.openDevice(devices[index]);
     } catch (IOException e) {
         e.printStackTrace();
     }

     ICMPPacket p=new ICMPPacket();
     p.type=ICMPPacket.ICMP_TSTAMP;
     p.seq=1000;
     p.id=999;
     p.orig_timestamp=123;
     p.trans_timestamp=456;
     p.recv_timestamp=789;
     try {
         p.setIPv4Parameter(0,false,false,false,0,false,false,false,0,1010101,100,IPPacket.IPPROTO_ICMP,
                 InetAddress.getByName("www.yahoo.com"),InetAddress.getByName("www.amazon.com"));
     } catch (UnknownHostException e) {
         e.printStackTrace();
     }
     p.data="data".getBytes();

     EthernetPacket ether=new EthernetPacket();
     ether.frametype=EthernetPacket.ETHERTYPE_IP;
     ether.src_mac=new byte[]{(byte)0,(byte)1,(byte)2,(byte)3,(byte)4,(byte)5};
     ether.dst_mac=new byte[]{(byte)0,(byte)6,(byte)7,(byte)8,(byte)9,(byte)10};
     p.datalink=ether;
     String TAG;
     Log.d("msg",ether.toString());

     //for(int i=0;i<10;i++)
     sender.sendPacket(p);

 }
}
