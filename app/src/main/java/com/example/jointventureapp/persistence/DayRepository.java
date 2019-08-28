package com.example.jointventureapp.persistence;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.jointventureapp.Asynk.DeleteAsynkTask;
import com.example.jointventureapp.Asynk.InsertAsynkTask;
import com.example.jointventureapp.Asynk.RetrieveFastAsyncTask;
import com.example.jointventureapp.Asynk.UpdateAsynkTask;
import com.example.jointventureapp.Models.CalendarRow;

import java.util.List;

public class DayRepository implements AsyncResponse{

    private DayDatabase mDayDatabase;
    private List<CalendarRow> mCalendarRows;

    public DayRepository(Context context) {
        mDayDatabase = DayDatabase.getInstance(context);
    }

    public void insertDayTask (CalendarRow calendarRow){
        new InsertAsynkTask(mDayDatabase.getDayDao()).execute(calendarRow);
    }

    public void updateDay (CalendarRow calendarRow){
        new UpdateAsynkTask(mDayDatabase.getDayDao()).execute(calendarRow);
    }

    public LiveData<List<CalendarRow>> retrieveDaysTask(String month, String year) {

        return mDayDatabase.getDayDao().getDays(month, year);
    }

    public void deleteDay (CalendarRow calendarRow){
        new DeleteAsynkTask(mDayDatabase.getDayDao()).execute(calendarRow);
    }

    @Override
    public void processFinish(List<CalendarRow> calendarRows) {
        mCalendarRows = calendarRows;
    }
}
