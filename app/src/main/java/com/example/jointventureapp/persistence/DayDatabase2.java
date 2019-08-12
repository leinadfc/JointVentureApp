package com.example.jointventureapp.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.jointventureapp.Models.CalendarRow2;

@Database(entities = {CalendarRow2.class}, version = 1)
public abstract class DayDatabase2 extends RoomDatabase {

    public static final String DATABASE_NAME = "days_db2";

    private static DayDatabase2 instance;

    static DayDatabase2 getInstance(final Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), DayDatabase2.class,
                    DATABASE_NAME ).build();
        }
        return instance;
    }

    public abstract DayDao2 getDayDao2();
}