package com.wecash.Inet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        try{
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true){
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
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
