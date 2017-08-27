package com.tablayout.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.tablayout.moel.Process;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tablayout.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/25.
 */

public class ProcessAdapter extends RecyclerView.Adapter<ProcessAdapter.ProcessHolder> {
    private ArrayList<Process> processArrayList;

    public ProcessAdapter(ArrayList<Process> processArrayList) {
        this.processArrayList = processArrayList;
    }

    @Override
    public ProcessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.process_item,parent,false);
        ProcessHolder processHolder = new ProcessHolder(view);
        return processHolder;
    }

    @Override
    public void onBindViewHolder(ProcessHolder holder, int position) {
        Process process = processArrayList.get(position);
        holder.pcotent.setText(process.getPcontent());
        ImageLoader.getInstance().displayImage(process.getPic(),holder.pic);
    }

    @Override
    public int getItemCount() {
        return processArrayList.size();
    }
     public static class ProcessHolder extends RecyclerView.ViewHolder {
         private TextView pcotent;
         private ImageView pic;
         public ProcessHolder(View itemView) {
             super(itemView);

             pcotent = itemView.findViewById(R.id.process_pcontent_tv);
             pic = itemView.findViewById(R.id.process_pic_image);
         }
     }
}
