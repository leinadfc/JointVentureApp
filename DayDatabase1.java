package com.example.jointventureapp.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.jointventureapp.Models.CalendarRow1;

@Database(entities = {CalendarRow1.class}, version = 1)
public abstract class DayDatabase1 extends RoomDatabase {

    public static final String DATABASE_NAME = "days_db1";

    private static DayDatabase1 instance;

    static DayDatabase1 getInstance(final Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), DayDatabase1.class,
                    DATABASE_NAME ).build();
        }
        return instance;
    }

    public abstract DayDao1 getDayDao1();
}
