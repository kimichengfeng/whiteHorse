package com.wecash.Inet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * TCP客户与服务器建立连接，发送请求并接收应答。
 *  新的Socket实例创建后，就立即能用于发送和接收数据。也就是说，当Socket实例返回时，它已经连接到了一个远程终端，并通过协议的底层实现完成了TCP消息或握手信息的交换。
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
