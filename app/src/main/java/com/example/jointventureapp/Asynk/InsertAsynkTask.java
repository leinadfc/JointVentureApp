package com.example.jointventureapp.Asynk;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.jointventureapp.Models.CalendarRow;
import com.example.jointventureapp.persistence.DayDao;

import java.lang.ref.WeakReference;

public class InsertAsynkTask extends AsyncTask<CalendarRow, Void, Void> {

    private DayDao mDayDao;

    public InsertAsynkTask(DayDao dao) {
        mDayDao = dao;
    }

    @Override
    protected Void doInBackground(CalendarRow... calendarRows) {
        mDayDao.insertDays(calendarRows);
        return null;
    }
}
