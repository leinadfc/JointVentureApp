package com.example.jointventureapp.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.jointventureapp.Models.CalendarRow3;

@Database(entities = {CalendarRow3.class}, version = 1)
public abstract class DayDatabase3 extends RoomDatabase {

    public static final String DATABASE_NAME = "days_db3";

    private static DayDatabase3 instance;

    static DayDatabase3 getInstance(final Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), DayDatabase3.class,
                    DATABASE_NAME ).build();
        }
        return instance;
    }

    public abstract DayDao3 getDayDao3();
}
