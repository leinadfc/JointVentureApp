package com.example.jointventureapp.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.jointventureapp.Models.CalendarRow2;

import java.util.List;

@Dao
public interface DayDao2 {
    @Insert
    long[] insertDays(CalendarRow2[] days);

    @Query("SELECT * FROM calendarrows2")
    LiveData<List<CalendarRow2>> getDefaultData();

    @Query("SELECT * FROM calendarrows2 WHERE month LIKE:selectedMonth AND year LIKE:selectedYear")
    LiveData<List<CalendarRow2>> getDays(String selectedMonth, String selectedYear);

    @Delete
    int delete (CalendarRow2[] days);

    @Update
    int update (CalendarRow2[] days);
}