package com.example.jointventureapp.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.jointventureapp.Models.CalendarRow4;

import java.util.List;

public interface DayDao4 {

    @Insert
    long[] insertDays(CalendarRow4[] days);

    @Query("SELECT * FROM calendarrows4")
    LiveData<List<CalendarRow4>> getDefaultData();

    @Query("SELECT * FROM calendarrows4 WHERE month LIKE:selectedMonth AND year LIKE:selectedYear")
    LiveData<List<CalendarRow4>> getDays(String selectedMonth, String selectedYear);

    @Delete
    int delete (CalendarRow4[] days);

    @Update
    int update (CalendarRow4[] days);
}
