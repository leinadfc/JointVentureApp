package com.example.jointventureapp.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.jointventureapp.Models.CalendarRow;

@Database(entities = {CalendarRow.class}, version = 1)
public abstract class DayDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "days_db";

    private static DayDatabase instance;

    static DayDatabase getInstance(final Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), DayDatabase.class,
                    DATABASE_NAME ).build();
        }
        return instance;
    }

    public abstract DayDao getDayDao();
}
