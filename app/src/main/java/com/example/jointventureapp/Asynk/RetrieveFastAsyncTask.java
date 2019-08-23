package com.example.jointventureapp.Asynk;

import android.os.AsyncTask;

import com.example.jointventureapp.Models.CalendarRow;
import com.example.jointventureapp.persistence.AsyncResponse;
import com.example.jointventureapp.persistence.DayDao;

import java.util.List;

public class RetrieveFastAsyncTask extends AsyncTask<Void, Void, List<CalendarRow>> {

    private DayDao mDayDao;
    private String mMonth;
    private String mYear;
    public AsyncResponse delegate = null;



    public RetrieveFastAsyncTask(DayDao dayDao, String month, String year) {
        mDayDao = dayDao;
        mMonth = month;
        mYear = year;
    }




    @Override
    protected List<CalendarRow> doInBackground(Void... voids) {
       return mDayDao.getDaysFast(mMonth, mYear);
    }

    @Override
    protected void onPostExecute(List<CalendarRow> calendarRows) {
        delegate.processFinish(calendarRows);
    }
}
