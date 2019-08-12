package com.example.jointventureapp.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.jointventureapp.Models.CalendarRow0;

@Database(entities = {CalendarRow0.class}, version = 1)
public abstract class DayDatabase0 extends RoomDatabase {

    public static final String DATABASE_NAME = "days_db0";

    private static DayDatabase0 instance;

    static DayDatabase0 getInstance(final Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), DayDatabase0.class,
                    DATABASE_NAME ).build();
        }
        return instance;
    }

    public abstract DayDao0 getDayDao0();
}