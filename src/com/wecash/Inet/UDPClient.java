package com.wecash.Inet;

import java.io.IOException;
import java.net.*;

/**
 * UDP客户端发送一个消息到服务端并获得一个应答
 */
public class UDPClient {
    public static void main(String[] args) {//参数：消息，DNS主机名
        DatagramSocket aSocket = null;

        try {
            aSocket = new DatagramSocket();
            byte[] m = args[0].getBytes();
            InetAddress aHost = InetAddress.getAllByName(args[1])[0];
            int serverPort=6789;

            DatagramPacket request = new DatagramPacket(m,args[0].length(),aHost,serverPort);
            aSocket.send(request);
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer,buffer.length);
            aSocket.receive(reply);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
