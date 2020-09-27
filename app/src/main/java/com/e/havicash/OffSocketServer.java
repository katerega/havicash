package com.e.havicash;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class OffSocketServer {
    public void runoffserver(){
        try{
            ServerSocket ss=new ServerSocket(8218);
            Socket s=ss.accept();//establishes connection

            DataInputStream dis=new DataInputStream(s.getInputStream());

            String	str=(String)dis.readUTF();
            System.out.println("message= "+str);

            ss.close();

        }catch(Exception e){System.out.println(e);}}
}
