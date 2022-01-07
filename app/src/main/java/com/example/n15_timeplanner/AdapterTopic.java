package com.example.n15_timeplanner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class AdapterTopic extends RecyclerView.Adapter<AdapterTopic.ViewHolder> implements Serializable {
    private Context mContext;
    private List<topic> topicList;

    public AdapterTopic(Context mContext, List<topic> topicList) {
        this.mContext = mContext;
        this.topicList = topicList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic,parent,false);
        ViewHolder holder=new AdapterTopic.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        topic topic=topicList.get(position);
        if(topic==null){
            return;
        }
        holder.topic.setText(topic.getName());
        holder.layout_item_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout_item_topic;
        TextView topic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_item_topic=itemView.findViewById(R.id.layout_item_topic);
            topic=itemView.findViewById(R.id.topic);

        }



    }
}
