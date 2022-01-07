package com.example.n15_timeplanner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class AdapterNotetitlr extends RecyclerView.Adapter<AdapterNotetitlr.ViewHolder> implements Serializable {
    private Context mContext;
    private List<Notetitle> notetitleList;
    Notetitle notetitle;
    ViewHolder holder;
    View view;

    public AdapterNotetitlr(Context mContext, List<Notetitle> notetitleList) {
        this.mContext = mContext;
        this.notetitleList = notetitleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work,parent,false);
        holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        notetitle=notetitleList.get(position);
        if(notetitle==null){
            return;
        }
        holder.texttitle.setText(notetitle.getTitle());
        holder.textSub.setText(notetitle.getNoteSub());
        holder.datetime.setText(notetitle.getDate());
        holder.setNote();
        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return  notetitleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout_item;
        TextView texttitle,textSub,datetime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_item=itemView.findViewById(R.id.layout_item);
            texttitle=itemView.findViewById(R.id.textTitle);
            textSub=itemView.findViewById(R.id.textSub);
            datetime=itemView.findViewById(R.id.datetime);
        }
        public void setNote(){
            GradientDrawable gradientDrawable=(GradientDrawable) layout_item.getBackground();
            if(notetitle.getColornote()!=null)
                gradientDrawable.setColor(Color.parseColor(notetitle.getColornote()));
            else   {gradientDrawable.setColor(Color.parseColor("#333333"));}
        }


    }


}
