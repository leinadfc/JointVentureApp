package com.example.jointventureapp.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.jointventureapp.Models.CalendarRow;
import com.example.jointventureapp.R;
import com.example.jointventureapp.Utils.PreferenceUtils;
import com.example.jointventureapp.persistence.DayRepository;

import java.util.Calendar;

public class AddActivity5 extends AppCompatActivity {

    private TextView mDisplayDay;
    private TextView mDisplayMonth;
    private TextView mDisplayYear;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    private BottomNavigationView botNavView;
    private EditText concText;
    private SeekBar seekBar1;
    private SeekBar seekBar2;
    private SeekBar seekBar3;
    private SeekBar seekBar4;
    private SeekBar seekBar5;
    ///////////////////////////////////////
    /// Add the functionality for these ///
    ///////////////////////////////////////
    private TextView symptom1;
    private TextView symptom2;
    private TextView symptom3;
    private TextView symptom4;
    private TextView symptom5;

    private EditText comments;

    private DayRepository mDayRepository;
    private CalendarRow mCalendarRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity_5_symptoms);

        mDisplayDay = findViewById(R.id.daytext);
        mDisplayMonth = findViewById(R.id.monthtext);
        mDisplayYear = findViewById(R.id.yeartext);
        mCalendarRow = new CalendarRow();

        concText = findViewById(R.id.concentrationtext);
        seekBar1 = findViewById(R.id.seekbar_1);
        seekBar2 = findViewById(R.id.seekbar_2);
        seekBar3 = findViewById(R.id.seekbar_3);
        seekBar4 = findViewById(R.id.seekbar_4);
        seekBar5 = findViewById(R.id.seekbar_5);

        comments = findViewById(R.id.comments_text);

        symptom1 = findViewById(R.id.symptom1text);
        symptom2 = findViewById(R.id.symptom2text);
        symptom3 = findViewById(R.id.symptom3text);
        symptom4 = findViewById(R.id.symptom4text);
        symptom5 = findViewById(R.id.symptom5text);

        symptom1.setText(getResources().getString(R.string.JointPain));
        symptom2.setText(getResources().getString(R.string.RestrictedJointMovement));
        symptom3.setText(getResources().getString(R.string.Inflammation));
        symptom4.setText(getResources().getString(R.string.Weakness));
        symptom5.setText(getResources().getString(R.string.Fatigue));

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
                        Intent ii = new Intent(AddActivity5.this, CalendarActivity.class);
                        startActivity(ii);
                        break;

                    case R.id.graphpg:
                        if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 5) {
                            Intent i = new Intent(AddActivity5.this, GraphsActivity5.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 4){
                            Intent i = new Intent(AddActivity5.this, GraphsActivity4.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 3){
                            Intent i = new Intent(AddActivity5.this, GraphsActivity.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 2){
                            Intent i = new Intent(AddActivity5.this, GraphsActivity2.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 1){
                            Intent i = new Intent(AddActivity5.this, GraphsActivity1.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 0){
                            Intent i = new Intent(AddActivity5.this, GraphsActivity0.class);
                            startActivity(i);
                        }
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
                        AddActivity5.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });


        mDisplayMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        AddActivity5.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDisplayYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        AddActivity5.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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
                Log.d("lolaso",Integer.toString(seekBar4.getProgress()) );
                mCalendarRow.setProgress4(seekBar4.getProgress());
                mCalendarRow.setProgress5(seekBar5.getProgress());

                mCalendarRow.setSymptomText1("Joint pain");
                mCalendarRow.setSymptomText2("Restricted joint movement");
                mCalendarRow.setSymptomText3("Inflammation");
                mCalendarRow.setSymptomText4("Weakness");
                mCalendarRow.setSymptomText5("Fatigue");

                mCalendarRow.setComments(comments.getText().toString());

                mCalendarRow.setMonth(mDisplayMonth.getText().toString());
                mCalendarRow.setYear(mDisplayYear.getText().toString());
                mCalendarRow.setDay(mDisplayDay.getText().toString().trim());

                if (concText.getText().toString().isEmpty()){
                    mCalendarRow.setConcentration("0");
                }
                else{
                    mCalendarRow.setConcentration(concText.getText().toString().trim());
                }
                mDayRepository.insertDayTask(mCalendarRow);
                if (PreferenceUtils.getSymptomCount(getApplicationContext()) > 2) {
                    Intent i = new Intent(AddActivity5.this, CalendarActivity.class);
                    startActivity(i);
                }
                else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 2){
                    Intent i = new Intent(AddActivity5.this, CalendarActivity2.class);
                    startActivity(i);
                }
                else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 1){
                    Intent i = new Intent(AddActivity5.this, CalendarActivity1.class);
                    startActivity(i);
                }
                else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 0){
                    Intent i = new Intent(AddActivity5.this, CalendarActivity0.class);
                    startActivity(i);
                }

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