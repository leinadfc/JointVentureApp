package com.example.jointventureapp.Asynk;

import android.os.AsyncTask;

import com.example.jointventureapp.Models.CalendarRow0;
import com.example.jointventureapp.persistence.DayDao0;

public class InsertAsynkTask0 extends AsyncTask<CalendarRow0, Void, Void> {

    private DayDao0 mDayDao0;
    public InsertAsynkTask0(DayDao0 dao) {
        mDayDao0 = dao;
    }

    @Override
    protected Void doInBackground(CalendarRow0... calendarRows0) {
        mDayDao0.insertDays(calendarRows0);
        return null;
    }
}
