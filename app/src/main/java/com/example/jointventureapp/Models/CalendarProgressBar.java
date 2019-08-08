package com.example.jointventureapp.Models;

import android.widget.ProgressBar;
import android.widget.TextView;

/////////////////////////////////////////
/// It will be a progressbar + a text ///
/////////////////////////////////////////
public class CalendarProgressBar {
    private String symptom;
    private int level;

    public CalendarProgressBar (String symptom, int level){
        this.symptom = symptom;
        this.level = level;
    }

}
