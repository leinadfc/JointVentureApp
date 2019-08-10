package com.example.jointventureapp.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.jointventureapp.Models.CalendarRow;
import com.example.jointventureapp.R;
import com.example.jointventureapp.persistence.DayRepository;

import java.security.PrivateKey;
import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jointventureapp.Adapters.CustomSpinnerAdapter;
import com.example.jointventureapp.Adapters.CustomSpinnerYearAdapter;
import com.example.jointventureapp.R;

import java.util.ArrayList;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    private TextView mDisplayDay;
    private TextView mDisplayMonth;
    private TextView mDisplayYear;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    BottomNavigationView botNavView;
    EditText concText;
    SeekBar seekBar1;
    SeekBar seekBar2;
    SeekBar seekBar3;

    private DayRepository mDayRepository;
    private CalendarRow mCalendarRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        mDisplayDay = findViewById(R.id.daytext);
        mDisplayMonth = findViewById(R.id.monthtext);
        mDisplayYear = findViewById(R.id.yeartext);
        mCalendarRow = new CalendarRow();

        concText = findViewById(R.id.concentrationtext);
        seekBar1 = findViewById(R.id.seekbar_1);
        seekBar2 = findViewById(R.id.seekbar_2);
        seekBar3 = findViewById(R.id.seekbar_3);

        concText.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);


        mDayRepository = new DayRepository(this);

        botNavView = findViewById(R.id.bottomNavAdd);
        Menu menu = botNavView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        botNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.addpg:
                        break;
                    case R.id.calpg:
                        Intent i = new Intent(AddActivity.this, CalendarActivity.class);
                        startActivity(i);
                        break;

                    case R.id.graphpg:
                        Intent ii = new Intent(AddActivity.this, GraphsActivity.class);
                        startActivity(ii);
                        break;
                }
                return false;
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final String tmonth = getmonthtext(month);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        mDisplayDay.setText(Integer.toString(day));
        mDisplayMonth.setText(tmonth);
        mDisplayYear.setText(Integer.toString(year));

        mDisplayDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        AddActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });


        mDisplayMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        AddActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDisplayYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        AddActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });




        /// Status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }



        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String dday = Integer.toString(dayOfMonth);
                String dmonth = getmonthtext(month);
                String dyear = Integer.toString(year);
                mDisplayDay.setText(dday);
                mDisplayMonth.setText(dmonth);
                mDisplayYear.setText(dyear);
            }
        };




        Button adbtn = findViewById(R.id.AddButton);
        adbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarRow.setProgress1(seekBar1.getProgress());
                mCalendarRow.setProgress2(seekBar2.getProgress());
                mCalendarRow.setProgress3(seekBar3.getProgress());
                //mCalendarRow.setProgress1(1);
                //mCalendarRow.setProgress2(2);
                //mCalendarRow.setProgress3(3);
                mCalendarRow.setProgress4(0);
                mCalendarRow.setProgress5(0);
                mCalendarRow.setSymptomText1("Symptom 1");
                mCalendarRow.setSymptomText2("Symptom 2");
                mCalendarRow.setSymptomText3("Symptom 3");
                mCalendarRow.setSymptomText4("Symptom 4");
                mCalendarRow.setSymptomText5("Symptom 5");
                mCalendarRow.setMonth(mDisplayMonth.getText().toString());
                Log.d("month", mDisplayMonth.getText().toString());
                mCalendarRow.setYear(mDisplayYear.getText().toString());
                Log.d("YEAR", mDisplayYear.getText().toString());
                mCalendarRow.setDay(mDisplayDay.getText().toString().trim());
                if (concText.getText().toString().isEmpty()){
                    mCalendarRow.setConcentration("0");
                }
                else{
                    mCalendarRow.setConcentration(concText.getText().toString().trim());
                }
                mDayRepository.insertDayTask(mCalendarRow);
                Intent i = new Intent(AddActivity.this, CalendarActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
            }
        });

    }



    /// Clicking outside edit text removes focus from edit text ///
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    Log.d("focus", "touchevent");
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    private String getmonthtext (int month) {
        String tmonth;
        if (month == 0)
            tmonth = "January";
        else if (month == 1)
            tmonth = "February";
        else if (month == 2)
            tmonth = "March";
        else if (month == 3)
            tmonth = "April";
        else if (month == 4)
            tmonth = "May";
        else if (month == 5)
            tmonth = "June";
        else if (month == 6)
            tmonth = "July";
        else if (month == 7)
            tmonth = "August";
        else if (month == 8)
            tmonth = "September";
        else if (month == 9)
            tmonth = "October";
        else if (month == 10)
            tmonth = "November";
        else if (month == 11)
            tmonth = "December";
        else
            tmonth = "";
        return tmonth;
    }

}