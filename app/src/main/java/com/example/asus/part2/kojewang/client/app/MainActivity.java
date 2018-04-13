package com.example.asus.part2.kojewang.client.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.part2.kojewang.client.data.NetFileData;
import com.example.asus.part2.kojewang.client.operator.ShowRemoteFileHandler;
import com.example.asus.part2.kojewang.client.socket.ClientSocket;
import com.example.asus.part2.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btGetC;
    Button btGetD;
    EditText edIp;
    EditText edPort;
    //TextView tv;
    ListView lv;
    ShowRemoteFileHandler showRemoteFileHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btGetC = (Button) findViewById(R.id.buttonGetC);
        btGetD = (Button) findViewById(R.id.buttonGetD);
        edIp = (EditText) findViewById(R.id.editIp);
        edPort = (EditText) findViewById(R.id.editPort);
        //tv = (TextView) findViewById(R.id.textView);
        lv = (ListView) findViewById(R.id.listView);

        showRemoteFileHandler = new ShowRemoteFileHandler(lv, this);
        //tv.setText("hello");
        btGetD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String ip = edIp.getText().toString();
                //int port = Integer.parseInt(edPort.getText().toString());
                //自动获取ip,,cesh
                String ip  = "192.168.1.11";
                int port = 8941;


                ClientSocket clientSocket = new ClientSocket(ip,port,showRemoteFileHandler);
                clientSocket.Work("dir:D:\\");
            }
        });

        btGetC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String ip = edIp.getText().toString();
                //int port = Integer.parseInt(edPort.getText().toString());
                //自动获取ip,,cesh
                String ip  = "192.168.1.11";
                int  port = 8941;


                ClientSocket clientSocket = new ClientSocket(ip,port,showRemoteFileHandler);
                clientSocket.Work("dir:C:\\");
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<NetFileData> netFileDataArrayList = showRemoteFileHandler.getNetFileDataArrayList();
                NetFileData netFileData = netFileDataArrayList.get(position);
                if(!netFileData.isDirectory()){
                    mainWork("open:" + netFileData.getFilePath()+"//" + netFileData.getFileName());
                    return;
                }
                    //return;
                mainWork("dir:"+netFileData.getFilePath()+"//"+netFileData.getFileName());
            }
        });

    }
    public void mainWork(String cmd){
        //String ip = edIp.getText().toString();
        //int port = Integer.parseInt(edPort.getText().toString());
        //自动获取ip,,cesh
        String ip  = "192.168.1.11";
        int  port = 8941;


        ClientSocket clientSocket = new ClientSocket(ip,port,showRemoteFileHandler);
        clientSocket.Work( cmd);

    }
}
