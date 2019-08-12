package com.example.jointventureapp.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "CalendarRows")
public class CalendarRow implements Parcelable {
    /// progressbar object

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

    @ColumnInfo (name = "symptomText5")
    private String symptomText5;

    @ColumnInfo (name = "progress1")
    private int progress1;

    @ColumnInfo (name = "progress2")
    private int progress2;

    @ColumnInfo (name = "progress3")
    private int progress3;

    @ColumnInfo (name = "progress4")
    private int progress4;

    @ColumnInfo (name = "progress5")
    private int progress5;


    public CalendarRow(String day, String month, String concentration, String symptomText1,
                       String symptomText2, String symptomText3, int progress1,
                       int progress2, int progress3, int progress4, int progress5, String symptomText4,
                       String symptomText5, String year) {
        this.day = day;
        this.month = month;
        this.concentration = concentration;
        this.symptomText1 = symptomText1;
        this.symptomText2 = symptomText2;
        this.symptomText3 = symptomText3;
        this.symptomText4 = symptomText4;
        this.symptomText5 = symptomText5;
        this.progress1 = progress1;
        this.progress2 = progress2;
        this.progress3 = progress3;
        this.progress4 = progress4;
        this.progress5 = progress5;
        this.year = year;
    }

    @Ignore
    public CalendarRow() {
    }


    protected CalendarRow(Parcel in) {
        id = in.readInt();
        day = in.readString();
        month = in.readString();
        concentration = in.readString();
        symptomText1 = in.readString();
        symptomText2 = in.readString();
        symptomText3 = in.readString();
        symptomText4 = in.readString();
        symptomText5 = in.readString();
        progress1 = in.readInt();
        progress2 = in.readInt();
        progress3 = in.readInt();
        progress4 = in.readInt();
        progress5 = in.readInt();
        year = in.readString();
    }

    public static final Creator<CalendarRow> CREATOR = new Creator<CalendarRow>() {
        @Override
        public CalendarRow createFromParcel(Parcel in) {
            return new CalendarRow(in);
        }

        @Override
        public CalendarRow[] newArray(int size) {
            return new CalendarRow[size];
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

    public int getProgress5() {
        return progress5;
    }

    public void setProgress5(int progress5) {
        this.progress5 = progress5;
    }

    public String getSymptomText4() {
        return symptomText4;
    }

    public void setSymptomText4(String symptomText4) {
        this.symptomText4 = symptomText4;
    }

    public String getSymptomText5() {
        return symptomText5;
    }

    public void setSymptomText5(String symptomText5) {
        this.symptomText5 = symptomText5;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CalendarRow{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", concentration='" + concentration + '\'' +
                ", symptomText1='" + symptomText1 + '\'' +
                ", symptomText2='" + symptomText2 + '\'' +
                ", symptomText3='" + symptomText3 + '\'' +
                ", symptomText4='" + symptomText4 + '\'' +
                ", symptomText5='" + symptomText5 + '\'' +
                ", progress1=" + progress1 +
                ", progress2=" + progress2 +
                ", progress3=" + progress3 +
                ", progress4=" + progress4 +
                ", progress5=" + progress5 +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(day);
        dest.writeString(month);
        dest.writeString(concentration);
        dest.writeString(symptomText1);
        dest.writeString(symptomText2);
        dest.writeString(symptomText3);
        dest.writeString(symptomText4);
        dest.writeString(symptomText5);
        dest.writeInt(progress1);
        dest.writeInt(progress2);
        dest.writeInt(progress3);
        dest.writeInt(progress4);
        dest.writeInt(progress5);
        dest.writeString(year);
    }
}
