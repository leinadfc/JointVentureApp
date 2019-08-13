package com.example.jointventureapp.Models;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "CalendarRows2")
public class CalendarRow2 implements Parcelable {

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

    @ColumnInfo (name = "progress1")
    private int progress1;

    @ColumnInfo (name = "progress2")
    private int progress2;

    public CalendarRow2(String day, String month, String year, String concentration, String symptomText1, String symptomText2, int progress1, int progress2) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.concentration = concentration;
        this.symptomText1 = symptomText1;
        this.symptomText2 = symptomText2;
        this.progress1 = progress1;
        this.progress2 = progress2;
    }

    @Ignore
    public CalendarRow2() {
    }

    protected CalendarRow2(Parcel in) {
        id = in.readInt();
        day = in.readString();
        month = in.readString();
        year = in.readString();
        concentration = in.readString();
        symptomText1 = in.readString();
        symptomText2 = in.readString();
        progress1 = in.readInt();
        progress2 = in.readInt();
    }

    public static final Creator<CalendarRow2> CREATOR = new Creator<CalendarRow2>() {
        @Override
        public CalendarRow2 createFromParcel(Parcel in) {
            return new CalendarRow2(in);
        }

        @Override
        public CalendarRow2[] newArray(int size) {
            return new CalendarRow2[size];
        }
    };

    public String getSymptomText1() {
        return symptomText1;
    }

    public void setSymptomText1(String symptomText1) {
        this.symptomText1 = symptomText1;
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


    @Override
    public String toString() {
        return "CalendarRow{" +
                "day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", concentration='" + concentration + '\'' +
                ", symptomText1='" + symptomText1 + '\'' +
                ", symptomText2='" + symptomText2 + '\'' +
                ", progress1=" + progress1 +
                ", progress2=" + progress2 +
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
        parcel.writeInt(progress1);
        parcel.writeInt(progress2);
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getSymptomText2() {
        return symptomText2;
    }

    public void setSymptomText2(String symptomText2) {
        this.symptomText2 = symptomText2;
    }

    public static Creator<CalendarRow2> getCREATOR() {
        return CREATOR;
    }
}