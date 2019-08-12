package com.example.jointventureapp.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.jointventureapp.Models.CalendarRow1;

import java.util.List;

public interface DayDao1 {

    @Insert
    long[] insertDays(CalendarRow1[] days);

    @Query("SELECT * FROM calendarrows1")
    LiveData<List<CalendarRow1>> getDefaultData();

    @Query("SELECT * FROM calendarrows1 WHERE month LIKE:selectedMonth AND year LIKE:selectedYear")
    LiveData<List<CalendarRow1>> getDays(String selectedMonth, String selectedYear);

    @Delete
    int delete (CalendarRow1[] days);

    @Update
    int update (CalendarRow1[] days);
}
