package com.example.jointventureapp.persistence;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.jointventureapp.Asynk.InsertAsynkTask;
import com.example.jointventureapp.Models.CalendarRow;

import java.util.List;

public class DayRepository {

    private DayDatabase mDayDatabase;

    public DayRepository(Context context) {
        mDayDatabase = DayDatabase.getInstance(context);
    }

    public void insertDayTask (CalendarRow calendarRow){
        new InsertAsynkTask(mDayDatabase.getDayDao()).execute(calendarRow);
    }

    public void updateDay (CalendarRow calendarRow){

    }

    public LiveData<List<CalendarRow>> retrieveDaysTask(String month, String year) {

        return mDayDatabase.getDayDao().getDays(month, year);
    }

    public LiveData<List<CalendarRow>> retrieveDaysDefaultTask() {

        return mDayDatabase.getDayDao().getDefaultData();
    }

    public void deleteDay (CalendarRow calendarRow){

    }


}
