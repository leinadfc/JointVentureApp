package com.example.jointventureapp.Asynk;

import android.os.AsyncTask;

import com.example.jointventureapp.Models.CalendarRow;
import com.example.jointventureapp.persistence.DayDao;

public class DeleteAsynkTask extends AsyncTask<CalendarRow, Void, Void> {

    private DayDao mDayDao;

    public DeleteAsynkTask(DayDao dao) {
        mDayDao = dao;
    }

    @Override
    protected Void doInBackground(CalendarRow... calendarRows) {
        mDayDao.delete(calendarRows);
        return null;
    }
}
