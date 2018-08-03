package com.wecash.Inet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * TCP客户与服务器建立连接，发送请求并接收应答
 */
public class TCPClient {
    public static void main(String[] args) {//消息和服务器的DNS主机名
        Socket s = null;
        try{
            int serverPort = 7896;
            s = new Socket(args[1],serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(args[0]);
            String data = in.readUTF();
            System.out.println("收到："+data);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(s!=null){
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
