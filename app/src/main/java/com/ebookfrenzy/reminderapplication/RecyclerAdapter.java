package com.ebookfrenzy.reminderapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private int alarmItemLayout;
    private List<Alarm> alarmList;

    public RecyclerAdapter(int layoutId){alarmItemLayout = layoutId;}

    public void setAlarmList(List<Alarm> alarms){
        alarmList = alarms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){ return alarmList == null ? 0 : alarmList.size();}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(
                parent.getContext()).inflate(alarmItemLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition){
        TextView item = holder.item;
        item.setText(alarmList.get(listPosition).getTitle());
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView item;
        ViewHolder(View itemView){
            super(itemView);
            item = itemView.findViewById(R.id.alarmCard);
        }
    }
}
