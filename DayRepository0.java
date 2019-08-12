package com.example.jointventureapp.persistence;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.jointventureapp.Asynk.InsertAsynkTask0;
import com.example.jointventureapp.Asynk.InsertAsynkTask1;
import com.example.jointventureapp.Models.CalendarRow0;

import java.util.List;

public class DayRepository0 {

    private DayDatabase0 mDayDatabase0;

    public DayRepository0(Context context) {
        mDayDatabase0 = DayDatabase0.getInstance(context);
    }

    public void insertDayTask (CalendarRow0 calendarRow0){
        new InsertAsynkTask0(mDayDatabase0.getDayDao0()).execute(calendarRow0);
    }

    public void updateDay (CalendarRow0 calendarRow0){

    }

    public LiveData<List<CalendarRow0>> retrieveDaysTask(String month, String year) {

        return mDayDatabase0.getDayDao0().getDays(month, year);
    }

    public LiveData<List<CalendarRow0>> retrieveDaysDefaultTask() {

        return mDayDatabase0.getDayDao0().getDefaultData();
    }

    public void deleteDay (CalendarRow0 calendarRow0){

    }
}
