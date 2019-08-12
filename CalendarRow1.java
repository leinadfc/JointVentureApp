package com.example.jointventureapp.Models;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "CalendarRows1")
public class CalendarRow1 implements Parcelable
{

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo (name = "day")
    private String day;

    @ColumnInfo (name = "month")
    private String month;

    @ColumnInfo (name = "year")
    private String year;

    @ColumnInfo (name = "concentration")
    private String concentration;

    @ColumnInfo (name = "symptomText1")
    private String symptomText1;

    @ColumnInfo (name = "progress1")
    private int progress1;

    public CalendarRow1(String symptom1, String day, String month, String year, String concentration, String symptomText1, int progress1)
    {
        this.day = day;
        this.month = month;
        this.year = year;
        this.concentration = concentration;
        this.symptomText1 = symptomText1;
        this.progress1 = progress1;
    }

    public CalendarRow1()
    {
    }

    protected CalendarRow1(Parcel in) {
        id = in.readInt();
        day = in.readString();
        month = in.readString();
        year = in.readString();
        concentration = in.readString();
        symptomText1 = in.readString();
        progress1 = in.readInt();
    }

    public static final Creator<CalendarRow1> CREATOR = new Creator<CalendarRow1>() {
        @Override
        public CalendarRow1 createFromParcel(Parcel in) {
            return new CalendarRow1(in);
        }

        @Override
        public CalendarRow1[] newArray(int size) {
            return new CalendarRow1[size];
        }
    };

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

    public String getSymptomText1() {
        return symptomText1;
    }

    public void setSymptomText1(String symptomText1) {
        this.symptomText1 = symptomText1;
    }

    public int getProgress1() {
        return progress1;
    }

    public void setProgress1(int progress1) {
        this.progress1 = progress1;
    }

    @Override
    public String toString() {
        return "CalendarRow{" +
                "day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", concentration='" + concentration + '\'' +
                ", symptomText1='" + symptomText1 + '\'' +
                ", progress1=" + progress1 +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(day);
        parcel.writeString(month);
        parcel.writeString(year);
        parcel.writeString(concentration);
        parcel.writeString(symptomText1);
        parcel.writeInt(progress1);
    }
}


