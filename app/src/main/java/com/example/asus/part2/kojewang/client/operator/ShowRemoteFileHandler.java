package com.example.asus.part2.kojewang.client.operator;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.part2.kojewang.client.app.MainActivity;
import com.example.asus.part2.kojewang.client.data.NetFileData;
import com.example.asus.part2.kojewang.client.socket.ClientSocket;
import com.example.asus.part2.kojewang.client.view.NetFileListAdapter;

import java.util.ArrayList;

/**
 * Created by asus on 2018/3/23.
 */

public class ShowRemoteFileHandler extends Handler {

    ListView lv;
    Context context;
    ArrayList<NetFileData> netFileDataArrayList;
    NetFileListAdapter netFileListAdapter;

    public ArrayList<NetFileData> getNetFileDataArrayList() {
        return netFileDataArrayList;
    }

    public ShowRemoteFileHandler(ListView lv, final Context context){
        this.lv = lv;
        this.context = context;
        netFileDataArrayList = new ArrayList<>();
        netFileListAdapter = new NetFileListAdapter(context, netFileDataArrayList);
        lv.setAdapter(netFileListAdapter);

    }


    @Override
    public void handleMessage(Message msg) {
        Bundle data =  msg.getData();
        ArrayList<String>message =  data.getStringArrayList(ClientSocket.KEY_SERVER_ACK_MSG);
        netFileDataArrayList.clear();
        for(int i = 1; i < message.size(); ++ i){
            netFileDataArrayList.add(new NetFileData(message.get(i),message.get(0)));
        }
        netFileListAdapter.notifyDataSetChanged();
    }
}
