package com.example.jointventureapp.persistence;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.jointventureapp.Asynk.InsertAsynkTask4;
import com.example.jointventureapp.Models.CalendarRow4;

import java.util.List;

public class DayRepository4 {

    private DayDatabase4 mDayDatabase4;

    public DayRepository4(Context context) {
        mDayDatabase4 = DayDatabase4.getInstance(context);
    }

    public void insertDayTask (CalendarRow4 calendarRow4){
        new InsertAsynkTask4(mDayDatabase4.getDayDao4()).execute(calendarRow4);
    }

    public void updateDay (CalendarRow4 calendarRow4){

    }

    public LiveData<List<CalendarRow4>> retrieveDaysTask(String month, String year) {

        return mDayDatabase4.getDayDao4().getDays(month, year);
    }

    public LiveData<List<CalendarRow4>> retrieveDaysDefaultTask() {

        return mDayDatabase4.getDayDao4().getDefaultData();
    }

    public void deleteDay (CalendarRow4 calendarRow4){

    }


}