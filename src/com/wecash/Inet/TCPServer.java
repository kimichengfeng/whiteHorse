package com.wecash.Inet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 现在服务端可以调用ServerSocket的accept（）方法，来将阻塞等待客户端连接请求的到来。当客户端的连接请求到来时，将为连接创建一个新的套接字数据结构。该套接字的地址根据到来的分组报文设置：分组报文的目标互联网地址和端口号成为该套接字的本地互联网地址和端口号；而分组报文的源地址和端口号则成为改套接字的远程互联网地址和端口号。注意，新套接字的本地端口号总是与ServerSocket的端口号一致。除了要创建一个新的底层套接字数据结构外，服务端的TCP实现还要向客户端发送一个TCP握手确认消息。
 */
public class TCPServer {
    public static void main(String[] args) {
        try{
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true){
                System.out.println("----- test -----");
                Socket clientSocket = listenSocket.accept();
                System.out.println("----- test1 -----");
                Connection c = new Connection(clientSocket);
                System.out.println("----- test2 -----");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class Connection extends Thread{
        DataInputStream in;
        DataOutputStream out;
        Socket clientSocket;
        public Connection(Socket aClientSocket){
            try{
                clientSocket = aClientSocket;
                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());
                this.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void run(){
            try{
                String data = in.readUTF();
                out.writeUTF(data);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                 try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
