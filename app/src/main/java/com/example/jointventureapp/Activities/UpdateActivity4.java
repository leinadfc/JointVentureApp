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
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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

import java.util.ArrayList;
import java.util.Calendar;

public class UpdateActivity4 extends AppCompatActivity {

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

    private String notUsed;
    ///////////////////////////////////////
    /// Add the functionality for these ///
    ///////////////////////////////////////
    private TextView symptom1;
    private TextView symptom2;
    private TextView symptom3;
    private TextView symptom4;

    private DayRepository mDayRepository;
    private CalendarRow mCalendarRow;

    private EditText comments;

    private ArrayList<String> symptomNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity_4);

        mDisplayDay = findViewById(R.id.daytext);
        mDisplayMonth = findViewById(R.id.monthtext);
        mDisplayYear = findViewById(R.id.yeartext);
        comments = findViewById(R.id.comments_text);

        if (getIntent().hasExtra("selected_day")) {
            mCalendarRow = getIntent().getParcelableExtra("selected_day");
            getSymptomTextArray();
        }

        concText = findViewById(R.id.concentrationtext);
        seekBar1 = findViewById(R.id.seekbar_1);
        seekBar2 = findViewById(R.id.seekbar_2);
        seekBar3 = findViewById(R.id.seekbar_3);
        seekBar4 = findViewById(R.id.seekbar_4);

        symptom1 = findViewById(R.id.symptom1text);
        symptom2 = findViewById(R.id.symptom2text);
        symptom3 = findViewById(R.id.symptom3text);
        symptom4 = findViewById(R.id.symptom4text);
        ArrayList<String> symptomArray = new ArrayList<>();


        if (PreferenceUtils.getSymptom1(getApplicationContext())){
            symptomArray.add("Joint pain");
        }
        else {
            notUsed = "Joint pain";
        }
        if (PreferenceUtils.getSymptom2(getApplicationContext())){
            symptomArray.add("Restricted joint movement");
        }
        else {
            notUsed = "Restricted joint movement";
        }
        if (PreferenceUtils.getSymptom3(getApplicationContext())){
            symptomArray.add("Inflammation");
        }
        else {
            notUsed = "Inflammation";
        }
        if (PreferenceUtils.getSymptom4(getApplicationContext())){
            symptomArray.add("Weakness");
        }
        else {
            notUsed = "Weakness";
        }
        if (PreferenceUtils.getSymptom5(getApplicationContext())){
            symptomArray.add("Fatigue");
        }
        else {
            notUsed = "Fatigue";
        }

        symptom1.setText(symptomArray.get(0));
        symptom2.setText(symptomArray.get(1));
        symptom3.setText(symptomArray.get(2));
        symptom4.setText(symptomArray.get(3));

        if (mCalendarRow.getComments() != null){
            comments.setText(mCalendarRow.getComments());
        }

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
                        Intent iii = new Intent(UpdateActivity4.this, AddActivity4.class);
                        startActivity(iii);
                        finish();
                        break;
                    case R.id.calpg:
                        Intent ii = new Intent(UpdateActivity4.this, CalendarActivity.class);
                        startActivity(ii);
                        finish();
                        break;

                    case R.id.graphpg:
                        if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 5) {
                            Intent i = new Intent(UpdateActivity4.this, GraphsActivity5.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 4){
                            Intent i = new Intent(UpdateActivity4.this, GraphsActivity4.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 3){
                            Intent i = new Intent(UpdateActivity4.this, GraphsActivity.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 2){
                            Intent i = new Intent(UpdateActivity4.this, GraphsActivity2.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 1){
                            Intent i = new Intent(UpdateActivity4.this, GraphsActivity1.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 0){
                            Intent i = new Intent(UpdateActivity4.this, GraphsActivity0.class);
                            startActivity(i);
                        }
                        finish();
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

        mDisplayDay.setText(mCalendarRow.getDay());
        mDisplayMonth.setText(mCalendarRow.getMonth());
        mDisplayYear.setText(mCalendarRow.getYear());
        concText.setText(mCalendarRow.getConcentration());
        if (mCalendarRow.getSymptomText1().equals(symptomNames.get(0))){
            symptom1.setText(symptomNames.get(0));
            seekBar1.setProgress(mCalendarRow.getProgress1());
        }
        else if (mCalendarRow.getSymptomText2().equals(symptomNames.get(0))){
            symptom1.setText(symptomNames.get(0));
            seekBar1.setProgress(mCalendarRow.getProgress2());
        }
        else if (mCalendarRow.getSymptomText3().equals(symptomNames.get(0))){
            symptom1.setText(symptomNames.get(0));
            seekBar1.setProgress(mCalendarRow.getProgress3());
        }
        else if (mCalendarRow.getSymptomText4().equals(symptomNames.get(0))){
            symptom1.setText(symptomNames.get(0));
            seekBar1.setProgress(mCalendarRow.getProgress4());
        }
        else if (mCalendarRow.getSymptomText5().equals(symptomNames.get(0))){
            symptom1.setText(symptomNames.get(0));
            seekBar1.setProgress(mCalendarRow.getProgress5());
        }
        else {
            symptom1.setText(symptomNames.get(0));
            seekBar1.setProgress(0);
        }

        if (mCalendarRow.getSymptomText1().equals(symptomNames.get(1))){
            symptom2.setText(symptomNames.get(1));
            seekBar2.setProgress(mCalendarRow.getProgress1());
        }
        else if (mCalendarRow.getSymptomText2().equals(symptomNames.get(1))){
            symptom2.setText(symptomNames.get(1));
            seekBar2.setProgress(mCalendarRow.getProgress2());
        }
        else if (mCalendarRow.getSymptomText3().equals(symptomNames.get(1))){
            symptom2.setText(symptomNames.get(1));
            seekBar2.setProgress(mCalendarRow.getProgress3());
        }
        else if (mCalendarRow.getSymptomText4().equals(symptomNames.get(1))){
            symptom2.setText(symptomNames.get(1));
            seekBar2.setProgress(mCalendarRow.getProgress4());
        }
        else if (mCalendarRow.getSymptomText5().equals(symptomNames.get(1))){
            symptom2.setText(symptomNames.get(1));
            seekBar2.setProgress(mCalendarRow.getProgress5());
        }
        else {
            symptom2.setText(symptomNames.get(1));
            seekBar2.setProgress(0);
        }

        if (mCalendarRow.getSymptomText1().equals(symptomNames.get(2))){
            symptom3.setText(symptomNames.get(2));
            seekBar3.setProgress(mCalendarRow.getProgress1());
        }
        else if (mCalendarRow.getSymptomText2().equals(symptomNames.get(2))){
            symptom3.setText(symptomNames.get(2));
            seekBar3.setProgress(mCalendarRow.getProgress2());
        }
        else if (mCalendarRow.getSymptomText3().equals(symptomNames.get(2))){
            symptom3.setText(symptomNames.get(2));
            seekBar3.setProgress(mCalendarRow.getProgress3());
        }
        else if (mCalendarRow.getSymptomText4().equals(symptomNames.get(2))){
            symptom3.setText(symptomNames.get(2));
            seekBar3.setProgress(mCalendarRow.getProgress4());
        }
        else if (mCalendarRow.getSymptomText5().equals(symptomNames.get(2))){
            symptom3.setText(symptomNames.get(2));
            seekBar3.setProgress(mCalendarRow.getProgress5());
        }
        else {
            symptom3.setText(symptomNames.get(2));
            seekBar3.setProgress(0);
        }

        if (mCalendarRow.getSymptomText1().equals(symptomNames.get(3))){
            symptom4.setText(symptomNames.get(3));
            seekBar4.setProgress(mCalendarRow.getProgress1());
        }
        else if (mCalendarRow.getSymptomText2().equals(symptomNames.get(3))){
            symptom4.setText(symptomNames.get(3));
            seekBar4.setProgress(mCalendarRow.getProgress2());
        }
        else if (mCalendarRow.getSymptomText3().equals(symptomNames.get(3))){
            symptom4.setText(symptomNames.get(3));
            seekBar4.setProgress(mCalendarRow.getProgress3());
        }
        else if (mCalendarRow.getSymptomText4().equals(symptomNames.get(3))){
            symptom4.setText(symptomNames.get(3));
            seekBar4.setProgress(mCalendarRow.getProgress4());
        }
        else if (mCalendarRow.getSymptomText5().equals(symptomNames.get(3))){
            symptom4.setText(symptomNames.get(3));
            seekBar4.setProgress(mCalendarRow.getProgress5());
        }
        else {
            symptom4.setText(symptomNames.get(3));
            seekBar4.setProgress(0);
        }

        mDisplayDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        UpdateActivity4.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });


        mDisplayMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        UpdateActivity4.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDisplayYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        UpdateActivity4.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });








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
                mCalendarRow.setProgress4(seekBar4.getProgress());
                mCalendarRow.setProgress5(0);

                mCalendarRow.setSymptomText1(symptom1.getText().toString());
                mCalendarRow.setSymptomText2(symptom2.getText().toString());
                mCalendarRow.setSymptomText3(symptom3.getText().toString());
                mCalendarRow.setSymptomText4(symptom4.getText().toString());
                mCalendarRow.setSymptomText5(notUsed);

                mCalendarRow.setComments(comments.getText().toString());

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
                mDayRepository.updateDay(mCalendarRow);
                Intent i = new Intent(UpdateActivity4.this, CalendarActivity.class);
                startActivity(i);
            }
        });

        concText.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {

                for(int i = s.length(); i > 0; i--) {

                    if(s.subSequence(i-1, i).toString().equals("\n"))
                        s.replace(i-1, i, "");
                }

                String myTextString = s.toString();
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

    private void getSymptomTextArray (){
        symptomNames.clear();
        if (PreferenceUtils.getSymptom1(getApplicationContext())){
            symptomNames.add("Joint pain");
        }
        if (PreferenceUtils.getSymptom2(getApplicationContext())){
            symptomNames.add("Restricted joint movement");
        }
        if (PreferenceUtils.getSymptom3(getApplicationContext())){
            symptomNames.add("Inflammation");
        }
        if (PreferenceUtils.getSymptom4(getApplicationContext())){
            symptomNames.add("Weakness");
        }
        if (PreferenceUtils.getSymptom5(getApplicationContext())){
            symptomNames.add("Fatigue");
        }
    }

}