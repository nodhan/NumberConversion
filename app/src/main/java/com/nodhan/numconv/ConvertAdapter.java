package com.nodhan.numconv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nodhan on 12/11/16.
 */

class ConvertAdapter extends RecyclerView.Adapter<ConvertAdapter.ConvertedNumberViewHolder> {

    private List<ConvertedNumberInfo> convertedNumberInfoList;
    
    ConvertAdapter (List<ConvertedNumberInfo> convertedNumberInfoList) {
        this.convertedNumberInfoList = convertedNumberInfoList;
    }
    @Override
    public ConvertedNumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ConvertedNumberViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ConvertedNumberViewHolder holder, int position) {
        ConvertedNumberInfo convertedNumberInfo = convertedNumberInfoList.get(position);
        holder.type.setText(convertedNumberInfo.type);
        holder.number.setText(convertedNumberInfo.number);
    }

    @Override
    public int getItemCount() {
        return convertedNumberInfoList.size();
    }

    static class ConvertedNumberViewHolder extends RecyclerView.ViewHolder {

        TextView type, number;

        public ConvertedNumberViewHolder(View itemView) {
            super(itemView);
            type = (TextView) itemView.findViewById(R.id.converted_type);
            number = (TextView) itemView.findViewById(R.id.converted_number);
        }
    }
}
