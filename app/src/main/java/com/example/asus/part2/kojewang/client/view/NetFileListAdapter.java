package com.example.asus.part2.kojewang.client.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.part2.R;
import com.example.asus.part2.kojewang.client.data.NetFileData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/3/23.
 */

public class NetFileListAdapter extends ArrayAdapter {
    Context context;
    ArrayList<NetFileData> netFileDataArrayList;
    public NetFileListAdapter(Context context, ArrayList<NetFileData> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        netFileDataArrayList = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NetFileData netFileData = netFileDataArrayList.get(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.fileitem,null,false);
            viewHolder = new ViewHolder();
            viewHolder.fileImage = (ImageView) view.findViewById(R.id.fileImage);
            viewHolder.fileDate = (TextView) view.findViewById(R.id.fileTime);
            viewHolder.fileName = (TextView) view.findViewById(R.id.fileName);
            viewHolder.fileSize = (TextView) view.findViewById(R.id.fileSize);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }
        viewHolder.fileSize.setText(netFileData.getStrFileSize());
        viewHolder.fileName.setText(netFileData.getFileName());
        viewHolder.fileDate.setText(netFileData.getFileModifiedDate());
        if(netFileData.isDirectory()){
            viewHolder.fileImage.setImageResource(R.drawable.folder);
        }else
        {
            viewHolder.fileImage.setImageResource(R.drawable.doc);
        }
        return  view;
    }

    class  ViewHolder{

        ImageView fileImage;
        TextView fileName;
        TextView fileSize;
        TextView fileDate;
    }
}
