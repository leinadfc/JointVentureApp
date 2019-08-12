package com.example.jointventureapp.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "CalendarRows4")
public class CalendarRow4 implements Parcelable
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

    @ColumnInfo (name = "symptomText2")
    private String symptomText2;

    @ColumnInfo (name = "symptomText3")
    private String symptomText3;

    @ColumnInfo (name = "symptomText4")
    private String symptomText4;

    @ColumnInfo (name = "progress1")
    private int progress1;

    @ColumnInfo (name = "progress2")
    private int progress2;

    @ColumnInfo (name = "progress3")
    private int progress3;

    @ColumnInfo (name = "progress4")
    private int progress4;

    public CalendarRow4(String day, String month, String year, String concentration, String symptomText1, String symptomText2, String symptomText3, String symptomText4, int progress1, int progress2, int progress3, int progress4) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.concentration = concentration;
        this.symptomText1 = symptomText1;
        this.symptomText2 = symptomText2;
        this.symptomText3 = symptomText3;
        this.symptomText4 = symptomText4;
        this.progress1 = progress1;
        this.progress2 = progress2;
        this.progress3 = progress3;
        this.progress4 = progress4;


    }

    public CalendarRow4() {
    }

    protected CalendarRow4(Parcel in) {
        id = in.readInt();
        day = in.readString();
        month = in.readString();
        year = in.readString();
        concentration = in.readString();
        symptomText1 = in.readString();
        symptomText2 = in.readString();
        symptomText3 = in.readString();
        symptomText4 = in.readString();
        progress1 = in.readInt();
        progress2 = in.readInt();
        progress3 = in.readInt();
        progress4 = in.readInt();
    }

    public static final Creator<CalendarRow4> CREATOR = new Creator<CalendarRow4>() {
        @Override
        public CalendarRow4 createFromParcel(Parcel in) {
            return new CalendarRow4(in);
        }

        @Override
        public CalendarRow4[] newArray(int size) {
            return new CalendarRow4[size];
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

    public String getSymptomText2() {
        return symptomText2;
    }

    public void setSymptomText2(String symptomText2) {
        this.symptomText2 = symptomText2;
    }

    public String getSymptomText3() {
        return symptomText3;
    }

    public void setSymptomText3(String symptomText3) {
        this.symptomText3 = symptomText3;
    }

    public String getSymptomText4() {
        return symptomText4;
    }

    public void setSymptomText4(String symptomText4) {
        this.symptomText4 = symptomText4;
    }

    public int getProgress1() {
        return progress1;
    }

    public void setProgress1(int progress1) {
        this.progress1 = progress1;
    }

    public int getProgress2() {
        return progress2;
    }

    public void setProgress2(int progress2) {
        this.progress2 = progress2;
    }

    public int getProgress3() {
        return progress3;
    }

    public void setProgress3(int progress3) {
        this.progress3 = progress3;
    }

    public int getProgress4() {
        return progress4;
    }

    public void setProgress4(int progress4) {
        this.progress4 = progress4;
    }

    @Override
    public String toString() {
        return "CalendarRow4{" +
                "day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", concentration='" + concentration + '\'' +
                ", symptomText1='" + symptomText1 + '\'' +
                ", symptomText2='" + symptomText2 + '\'' +
                ", symptomText3='" + symptomText3 + '\'' +
                ", symptomText4='" + symptomText4 + '\'' +
                ", progress1=" + progress1 +
                ", progress2=" + progress2 +
                ", progress3=" + progress3 +
                ", progress4=" + progress4 +
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
        parcel.writeString(symptomText2);
        parcel.writeString(symptomText3);
        parcel.writeString(symptomText4);
        parcel.writeInt(progress1);
        parcel.writeInt(progress2);
        parcel.writeInt(progress3);
        parcel.writeInt(progress4);
    }
}
