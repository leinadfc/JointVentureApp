package com.example.jointventureapp.persistence;

import com.example.jointventureapp.Models.CalendarRow;

import java.util.Calendar;
import java.util.List;

public interface AsyncResponse {
    void processFinish (List<CalendarRow> calendarRows);
}
