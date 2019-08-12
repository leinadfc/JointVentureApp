package com.example.jointventureapp.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.jointventureapp.Models.CalendarRow4;

@Database(entities = {CalendarRow4.class}, version = 1)
public abstract class DayDatabase4 extends RoomDatabase {

    public static final String DATABASE_NAME = "days_db4";

    private static DayDatabase4 instance;

    static DayDatabase4 getInstance(final Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), DayDatabase4.class,
                    DATABASE_NAME ).build();
        }
        return instance;
    }

    public abstract DayDao4 getDayDao4();
}