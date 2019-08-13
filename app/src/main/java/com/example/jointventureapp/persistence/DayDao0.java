package com.example.jointventureapp.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.jointventureapp.Models.CalendarRow0;

import java.util.List;

@Dao
public interface DayDao0 {

    @Insert
    long[] insertDays(CalendarRow0[] days);

    @Query("SELECT * FROM calendarrows0")
    LiveData<List<CalendarRow0>> getDefaultData();

    @Query("SELECT * FROM calendarrows0 WHERE month LIKE:selectedMonth AND year LIKE:selectedYear")
    LiveData<List<CalendarRow0>> getDays(String selectedMonth, String selectedYear);

    @Delete
    int delete(CalendarRow0[] days);

    @Update
    int update(CalendarRow0[] days);
}