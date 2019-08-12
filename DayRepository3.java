package com.example.jointventureapp.persistence;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.jointventureapp.Asynk.InsertAsynkTask3;
import com.example.jointventureapp.Models.CalendarRow3;

import java.util.List;

public class DayRepository3 {

    private DayDatabase3 mDayDatabase3;

    public DayRepository3(Context context) {
        mDayDatabase3 = DayDatabase3.getInstance(context);
    }

    public void insertDayTask (CalendarRow3 calendarRow3){
        new InsertAsynkTask3(mDayDatabase3.getDayDao3()).execute(calendarRow3);
    }

    public void updateDay (CalendarRow3 calendarRow3){

    }

    public LiveData<List<CalendarRow3>> retrieveDaysTask(String month, String year) {

        return mDayDatabase3.getDayDao3().getDays(month, year);
    }

    public LiveData<List<CalendarRow3>> retrieveDaysDefaultTask() {

        return mDayDatabase3.getDayDao3().getDefaultData();
    }

    public void deleteDay (CalendarRow3 calendarRow3){

    }


}
