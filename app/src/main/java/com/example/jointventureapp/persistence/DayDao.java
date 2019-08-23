package com.example.jointventureapp.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.jointventureapp.Models.CalendarRow;

import java.util.List;

@Dao
public interface DayDao {

    @Insert
    long[] insertDays(CalendarRow[] days);

    @Query("SELECT * FROM calendarrows")
    LiveData<List<CalendarRow>> getDefaultData();

    @Query("SELECT * FROM calendarrows WHERE month LIKE:selectedMonth AND year LIKE:selectedYear")
    LiveData<List<CalendarRow>> getDays(String selectedMonth, String selectedYear);

    @Query("SELECT * FROM calendarrows WHERE month LIKE:selectedMonth AND year LIKE:selectedYear")
    List<CalendarRow> getDaysFast(String selectedMonth, String selectedYear);

    @Delete
    int delete (CalendarRow[] days);

    @Update
    int update (CalendarRow[] days);
}
