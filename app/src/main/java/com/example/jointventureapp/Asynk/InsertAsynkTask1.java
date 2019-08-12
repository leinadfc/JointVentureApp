package com.example.jointventureapp.Asynk;

import android.os.AsyncTask;

import com.example.jointventureapp.Models.CalendarRow1;
import com.example.jointventureapp.persistence.DayDao1;

public class InsertAsynkTask1 extends AsyncTask<CalendarRow1, Void, Void> {

    private DayDao1 mDayDao1;
    public InsertAsynkTask1(DayDao1 dao) {
        mDayDao1 = dao;
    }

    @Override
    protected Void doInBackground(CalendarRow1... calendarRows1) {
        mDayDao1.insertDays(calendarRows1);
        return null;
    }
}