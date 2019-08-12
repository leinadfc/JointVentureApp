package com.example.jointventureapp.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.jointventureapp.Models.CalendarRow;
import com.example.jointventureapp.Models.CalendarRow3;

import java.util.List;

public interface DayDao3 {
    @Insert
    long[] insertDays(CalendarRow3[] days);

    @Query("SELECT * FROM calendarrows3")
    LiveData<List<CalendarRow3>> getDefaultData();

    @Query("SELECT * FROM calendarrows3 WHERE month LIKE:selectedMonth AND year LIKE:selectedYear")
    LiveData<List<CalendarRow3>> getDays(String selectedMonth, String selectedYear);

    @Delete
    int delete (CalendarRow3[] days);

    @Update
    int update (CalendarRow3[] days);
}
