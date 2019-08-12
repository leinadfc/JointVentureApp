package com.example.jointventureapp.Asynk;

import android.os.AsyncTask;

import com.example.jointventureapp.Models.CalendarRow3;
import com.example.jointventureapp.persistence.DayDao3;

public class InsertAsynkTask3 extends AsyncTask<CalendarRow3, Void, Void> {

    private DayDao3 mDayDao3;
    public InsertAsynkTask3(DayDao3 dao) {
        mDayDao3 = dao;
    }

    @Override
    protected Void doInBackground(CalendarRow3... calendarRows3) {
        mDayDao3.insertDays(calendarRows3);
        return null;
    }
}
