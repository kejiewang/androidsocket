package com.example.asus.part2.kojewang.client.socket;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.asus.part2.kojewang.client.operator.ShowRemoteFileHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by asus on 2018/3/23.
 */

public class ClientSocket {
    int port;
    String ip;
    int connect_timeout = 20000;
    Socket socket;
    String cmd;
    Handler showRemoteFileHandler;
    public static final String KEY_SERVER_ACK_MSG = "KEY_SERVER_ACK_MSG";
    public ClientSocket(String ip, int port, Handler showRemoteFileHandler){
        super();
        this.ip = ip;
        this.port = port;
        this.showRemoteFileHandler = showRemoteFileHandler;
        //this.cmd = cmd;
    }

    //连接服务器
    private void Connect(){
        InetSocketAddress address = new InetSocketAddress(ip, port);
        socket = new Socket();
        try {
            socket.connect(address,connect_timeout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //socket 发送相应的命令
    private void SendMessage(){
        try {
            OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream(), "utf-8");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("1");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.write(cmd);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //socket 获取对应的消息

    //返回的参数设置为String，后期修改为ArrayList
    private ArrayList<String> AcceptMessage(){
        ArrayList<String > message = new ArrayList<>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream(),"utf-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String strNum = reader.readLine();
            int num = Integer.parseInt(strNum);

            for(int i = 0; i < num; ++i){
                //message += reader.readLine()+"\n";
                message.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  message;
    }

    private void Close(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void Work(String cmd){
        this.cmd = cmd;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connect();
                SendMessage();
                ArrayList<String> temp = AcceptMessage();
                Message message = showRemoteFileHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(KEY_SERVER_ACK_MSG,temp);
                message.setData(bundle);
                showRemoteFileHandler.sendMessage(message);
                Close();
            }
        }).start();


    }
}
