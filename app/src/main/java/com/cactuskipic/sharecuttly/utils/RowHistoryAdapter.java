package com.cactuskipic.sharecuttly.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.cactuskipic.sharecuttly.MainActivity;
import com.cactuskipic.sharecuttly.R;
import com.cactuskipic.sharecuttly.cuttlyapi.ResponseURL;
import com.cactuskipic.sharecuttly.history.RetrieveResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RowHistoryAdapter extends RecyclerView.Adapter<RowHistoryAdapter.ViewHolder>{
    
    private List<String> values;
    private Context context;
    
    public RowHistoryAdapter(Context context,List<String> historyList){
        this.context = context;
        values = historyList;
    }
    
    class ViewHolder extends RecyclerView.ViewHolder{
        
        TextView text_title;
        TextView text_long_url;
        TextView text_short_url;
        TextView text_date;
        View layout;
        
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            layout = itemView;
            text_title = (TextView) itemView.findViewById(R.id.text_title);
            text_long_url = (TextView) itemView.findViewById(R.id.text_long_url);
            text_short_url = (TextView) itemView.findViewById(R.id.text_short_url);
            text_date = (TextView) itemView.findViewById(R.id.text_date);
        }
    }
    
    @NonNull
    @Override
    public RowHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        
        View v = inflater.inflate(R.layout.row_history, parent, false);
        v.setOnLongClickListener(MainActivity.longClickListener);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    
    @Override
    public void onBindViewHolder(RowHistoryAdapter.ViewHolder holder, int position){
        ResponseURL responseURL = RetrieveResponse.getResponseURL(context, getItemAt(position));
        holder.itemView.setTag(getItemAt(position));
        Log.i(MainActivity.MARK,"Name: "+getItemAt(position));
        holder.itemView.setBackgroundResource((position%2 == 1) ? R.color.colorGray2 : R.color.colorGray1);
        
        if(responseURL != null){
            holder.text_title.setText(responseURL.getTitle());
            holder.text_long_url.setText(responseURL.getFullLink());
            holder.text_short_url.setText(responseURL.getShortLink());
            holder.text_date.setText(responseURL.getDate());
        } else {
            holder.text_title.setText("Corrupted Item");
        }
        
    }
    
    
    public String getItemAt(int index){
        return values.get(index);
    }
    
    @Override
    public int getItemCount(){
        return values.size();
    }
}
