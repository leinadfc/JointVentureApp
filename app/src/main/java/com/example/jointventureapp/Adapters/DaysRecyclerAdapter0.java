package com.example.jointventureapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jointventureapp.Models.CalendarRow;
import com.example.jointventureapp.R;

import java.util.ArrayList;

public class DaysRecyclerAdapter0 extends RecyclerView.Adapter<DaysRecyclerAdapter0.ViewHolder>  {

    private ArrayList<CalendarRow> mCalendarRows = new ArrayList<>();
    private OnDayListener mOnDayListener;


    public DaysRecyclerAdapter0(ArrayList<CalendarRow> calendarRows, OnDayListener onDayListener) {
        this.mCalendarRows = calendarRows;
        this.mOnDayListener = onDayListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.calendar_row_0, viewGroup, false);
        return new ViewHolder(view, mOnDayListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.day.setText(mCalendarRows.get(i).getDay());
        viewHolder.month.setText(getMonthText(mCalendarRows.get(i).getMonth()));
        viewHolder.concentration.setText(mCalendarRows.get(i).getConcentration());

    }

    @Override
    public int getItemCount() {
        return mCalendarRows.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView day;
        TextView month;
        TextView concentration;
        OnDayListener onDayListener;

        public ViewHolder(@NonNull View itemView, OnDayListener onDayListener) {
            super(itemView);
            day = itemView.findViewById(R.id.dayitem_0);
            month = itemView.findViewById(R.id.monthitem_0);
            concentration = itemView.findViewById(R.id.concentrationitem_0);
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

    private String getMonthText (String month){
        String shortMonth = "";
        if (month.equals("January")){
            shortMonth = "JAN";
        }
        else if (month.equals("February")){
            shortMonth = "FEB";
        }
        else if (month.equals("March")){
            shortMonth = "MAR";
        }
        else if (month.equals("April")){
            shortMonth = "Apr";
        }
        else if (month.equals("May")){
            shortMonth = "MAY";
        }
        else if (month.equals("June")){
            shortMonth = "JUN";
        }
        else if (month.equals("July")){
            shortMonth = "JUL";
        }
        else if (month.equals("August")){
            shortMonth = "AUG";
        }
        else if (month.equals("September")){
            shortMonth = "SEP";
        }
        else if (month.equals("October")){
            shortMonth = "OCT";
        }
        else if (month.equals("November")){
            shortMonth = "NOV";
        }
        else if (month.equals("December")){
            shortMonth = "DEC";
        }
        return shortMonth;
    }
}
