package com.wecash.Inet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * UDP服务器不断接受请求并将它发回客户端
 */
public class UDPServer {
    public static void main(String[] args) {
        DatagramSocket aSock = null;
        try {
            aSock = new DatagramSocket(6789);
            byte[] buffer = new byte[1000];
            while(true){
                DatagramPacket request = new DatagramPacket(buffer,buffer.length);
                aSock.receive(request);
                DatagramPacket reply = new DatagramPacket(request.getData(),request.getLength(),request.getAddress(),request.getPort());
                aSock.send(reply);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
