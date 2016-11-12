package com.nodhan.numconv;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by nodhan on 12/11/16.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private Cursor cursor;

    HistoryAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_history_layout, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String types[] = getTypes(cursor.getInt(cursor.getColumnIndex("type")));
                holder.info.setText(new StringBuilder("Conversions for ").append(cursor.getString(cursor.getColumnIndex("number"))));

                holder.type1.setText(types[0]);
                holder.number1.setText(cursor.getString(cursor.getColumnIndex("conv1")));

                holder.type2.setText(types[1]);
                holder.number2.setText(cursor.getString(cursor.getColumnIndex("conv2")));

                holder.type3.setText(types[2]);
                holder.number3.setText(cursor.getString(cursor.getColumnIndex("conv3")));
            }
        }
    }

    private String[] getTypes(int type) {
        switch (type) {
            case 0:
                return new String[]{"OCTAL", "DECIMAL", "HEXADECIMAL"};
            case 1:
                return new String[]{"BINARY", "DECIMAL", "HEXADECIMAL"};
            case 2:
                return new String[]{"BINARY", "OCTAL", "HEXADECIMAL"};
            case 3:
                return new String[]{"BINARY", "OCTAL", "DECIMAL"};
            default:
                return new String[]{"BINARY", "OCTAL", "DECIMAL", "HEXADECIMAL"};
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView info, type1, number1, type2, number2, type3, number3;

        HistoryViewHolder(View itemView) {
            super(itemView);

            info = (TextView) itemView.findViewById(R.id.info_history);

            type1 = (TextView) itemView.findViewById(R.id.converted_type_history_1);
            number1 = (TextView) itemView.findViewById(R.id.converted_number_history_1);

            type2 = (TextView) itemView.findViewById(R.id.converted_type_history_2);
            number2 = (TextView) itemView.findViewById(R.id.converted_number_history_2);

            type3 = (TextView) itemView.findViewById(R.id.converted_type_history_3);
            number3 = (TextView) itemView.findViewById(R.id.converted_number_history_3);

        }
    }
}
