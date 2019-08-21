package com.example.jointventureapp.persistence;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.example.jointventureapp.Models.CalendarRow;

@Database(entities = {CalendarRow.class}, version = 2)
public abstract class DayDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "days_db";

    private static DayDatabase instance;

    static DayDatabase getInstance(final Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), DayDatabase.class,
                    DATABASE_NAME ).addMigrations(MIGRATION_1_2).build();
        }
        return instance;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE CalendarRows "
                    + " ADD COLUMN comments TEXT");
        }
    };

    public abstract DayDao getDayDao();
}
