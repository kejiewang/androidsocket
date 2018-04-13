package com.example.asus.part2.kojewang.client.operator;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.part2.kojewang.client.data.NetFileData;
import com.example.asus.part2.kojewang.client.socket.ClientSocket;
import com.example.asus.part2.kojewang.client.view.NetFileListAdapter;

import java.util.ArrayList;

/**
 * Created by asus on 2018/4/13.
 */

public class ShowNoUiUpdateHandler extends Handler {
    ListView lv;
    Context context;
//    ArrayList<NetFileData> netFileDataArrayList;
//    NetFileListAdapter netFileListAdapter;

//    public ArrayList<NetFileData> getNetFileDataArrayList() {
//        return netFileDataArrayList;
//    }


    public ShowNoUiUpdateHandler(ListView lv, final Context context){
        this.lv = lv;
        this.context = context;
//        netFileDataArrayList = new ArrayList<>();
//        netFileListAdapter = new NetFileListAdapter(context, netFileDataArrayList);
//        lv.setAdapter(netFileListAdapter);

    }


    @Override
    public void handleMessage(Message msg) {
        Bundle data =  msg.getData();
        ArrayList<String>message =  data.getStringArrayList(ClientSocket.KEY_SERVER_ACK_MSG);
        Toast.makeText(context,message.get(0),Toast.LENGTH_SHORT).show();

    }
}
