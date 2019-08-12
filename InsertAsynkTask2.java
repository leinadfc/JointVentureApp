package com.example.jointventureapp.Asynk;

import android.os.AsyncTask;

import com.example.jointventureapp.Models.CalendarRow2;
import com.example.jointventureapp.persistence.DayDao2;

public class InsertAsynkTask2 extends AsyncTask<CalendarRow2, Void, Void> {

    private DayDao2 mDayDao2;
    public InsertAsynkTask2(DayDao2 dao) {
        mDayDao2 = dao;
    }

    @Override
    protected Void doInBackground(CalendarRow2... calendarRows2) {
        mDayDao2.insertDays(calendarRows2);
        return null;
    }
}
