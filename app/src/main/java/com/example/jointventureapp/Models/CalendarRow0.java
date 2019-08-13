package com.example.jointventureapp.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "CalendarRows0")
public class CalendarRow0 implements Parcelable
{

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "day")
    private String day;

    @ColumnInfo (name = "month")
    private String month;

    @ColumnInfo (name = "year")
    private String year;

    @ColumnInfo (name = "concentration")
    private String concentration;

    public CalendarRow0(int id, String day, String month, String year, String concentration) {
        this.id = id;
        this.day = day;
        this.month = month;
        this.year = year;
        this.concentration = concentration;
    }

    @Ignore
    public CalendarRow0() {
    }

    protected CalendarRow0(Parcel in) {
        id = in.readInt();
        day = in.readString();
        month = in.readString();
        year = in.readString();
        concentration = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(day);
        dest.writeString(month);
        dest.writeString(year);
        dest.writeString(concentration);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CalendarRow0> CREATOR = new Creator<CalendarRow0>() {
        @Override
        public CalendarRow0 createFromParcel(Parcel in) {
            return new CalendarRow0(in);
        }

        @Override
        public CalendarRow0[] newArray(int size) {
            return new CalendarRow0[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    public static Creator<CalendarRow0> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "CalendarRow0{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", concentration='" + concentration + '\'' +
                '}';
    }
}
