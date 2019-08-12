
package com.example.jointventureapp.persistence;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.jointventureapp.Asynk.InsertAsynkTask1;
import com.example.jointventureapp.Models.CalendarRow1;

import java.util.List;

public class DayRepository1 {

    private DayDatabase1 mDayDatabase1;

    public DayRepository1(Context context) {
        mDayDatabase1 = DayDatabase1.getInstance(context);
    }

    public void insertDayTask (CalendarRow1 calendarRow1){
        new InsertAsynkTask1(mDayDatabase1.getDayDao1()).execute(calendarRow1);
    }

    public void updateDay (CalendarRow1 calendarRow1){

    }

    public LiveData<List<CalendarRow1>> retrieveDaysTask(String month, String year) {

        return mDayDatabase1.getDayDao1().getDays(month, year);
    }

    public LiveData<List<CalendarRow1>> retrieveDaysDefaultTask() {

        return mDayDatabase1.getDayDao1().getDefaultData();
    }

    public void deleteDay (CalendarRow1 calendarRow1){

    }


}