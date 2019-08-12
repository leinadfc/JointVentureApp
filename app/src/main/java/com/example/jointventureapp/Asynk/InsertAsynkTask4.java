package com.example.jointventureapp.Asynk;

import android.os.AsyncTask;

import com.example.jointventureapp.Models.CalendarRow4;
import com.example.jointventureapp.persistence.DayDao4;

public class InsertAsynkTask4 extends AsyncTask<CalendarRow4, Void, Void> {

    private DayDao4 mDayDao4;
    public InsertAsynkTask4(DayDao4 dao) {
        mDayDao4 = dao;
    }

    @Override
    protected Void doInBackground(CalendarRow4... calendarRows4) {
        mDayDao4.insertDays(calendarRows4);
        return null;
    }
}