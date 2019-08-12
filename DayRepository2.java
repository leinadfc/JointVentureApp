package com.example.jointventureapp.persistence;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.jointventureapp.Asynk.InsertAsynkTask2;
import com.example.jointventureapp.Models.CalendarRow2;

import java.util.List;

public class DayRepository2 {

    private DayDatabase2 mDayDatabase2;

    public DayRepository2(Context context) {
        mDayDatabase2 = DayDatabase2.getInstance(context);
    }

    public void insertDayTask (CalendarRow2 calendarRow2){
        new InsertAsynkTask2(mDayDatabase2.getDayDao2()).execute(calendarRow2);
    }

    public void updateDay (CalendarRow2 calendarRow2){

    }

    public LiveData<List<CalendarRow2>> retrieveDaysTask(String month, String year) {

        return mDayDatabase2.getDayDao2().getDays(month, year);
    }

    public LiveData<List<CalendarRow2>> retrieveDaysDefaultTask() {

        return mDayDatabase2.getDayDao2().getDefaultData();
    }

    public void deleteDay (CalendarRow2 calendarRow2){

    }


}
