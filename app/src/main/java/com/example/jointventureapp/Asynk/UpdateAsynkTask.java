package com.example.jointventureapp.Asynk;

import android.os.AsyncTask;

import com.example.jointventureapp.Models.CalendarRow;
import com.example.jointventureapp.persistence.DayDao;

public class UpdateAsynkTask extends AsyncTask<CalendarRow, Void, Void> {

    private DayDao mDayDao;
    public UpdateAsynkTask(DayDao dao) {
        mDayDao = dao;
    }

    @Override
    protected Void doInBackground(CalendarRow... calendarRows) {
        mDayDao.update(calendarRows);
        return null;
    }
}
