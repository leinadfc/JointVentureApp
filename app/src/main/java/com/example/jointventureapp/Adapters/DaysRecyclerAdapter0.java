package com.example.jointventureapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jointventureapp.Models.CalendarRow0;
import com.example.jointventureapp.Models.CalendarRow1;
import com.example.jointventureapp.R;

import java.util.ArrayList;

public class DaysRecyclerAdapter0 extends RecyclerView.Adapter<DaysRecyclerAdapter0.ViewHolder>  {

    private ArrayList<CalendarRow0> mCalendarRows = new ArrayList<>();
    private OnDayListener mOnDayListener;

    public DaysRecyclerAdapter0(ArrayList<CalendarRow0> calendarRows, OnDayListener onDayListener) {
        this.mCalendarRows = calendarRows;
        this.mOnDayListener = onDayListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.calendar_row, viewGroup, false);
        return new ViewHolder(view, mOnDayListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.day.setText(mCalendarRows.get(i).getDay());
        viewHolder.month.setText(mCalendarRows.get(i).getMonth());
        viewHolder.concentration.setText(mCalendarRows.get(i).getConcentration());

    }

    @Override
    public int getItemCount() {
        return mCalendarRows.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView day;
        TextView month;
        TextView symptom1;
        TextView symptom2;
        TextView symptom3;
        ProgressBar symptomBar1;
        ProgressBar symptomBar2;
        ProgressBar symptomBar3;
        TextView concentration;
        OnDayListener onDayListener;

        public ViewHolder(@NonNull View itemView, OnDayListener onDayListener) {
            super(itemView);
            day = itemView.findViewById(R.id.dayitem);
            month = itemView.findViewById(R.id.monthitem);
            symptom1 = itemView.findViewById(R.id.symptom1_item);
            symptom2 = itemView.findViewById(R.id.symptom2_item);
            symptom3 = itemView.findViewById(R.id.symptom3_item);
            symptomBar1 = itemView.findViewById(R.id.progress_bar_1);
            symptomBar2 = itemView.findViewById(R.id.progress_bar_2);
            symptomBar3 = itemView.findViewById(R.id.progress_bar_3);
            concentration = itemView.findViewById(R.id.concentrationitem);
            this.onDayListener = onDayListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onDayListener.onDayClick(getAdapterPosition());
        }
    }
    public interface OnDayListener{
        void onDayClick(int position);
    }
}
