package com.example.jointventureapp.Activities;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.jointventureapp.Adapters.CustomSpinnerAdapter;
import com.example.jointventureapp.Adapters.CustomSpinnerYearAdapter;
import com.example.jointventureapp.Adapters.DaysRecyclerAdapter3;
import com.example.jointventureapp.Models.CalendarRow3;
import com.example.jointventureapp.R;
import com.example.jointventureapp.persistence.DayRepository3;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarActivity3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DaysRecyclerAdapter3.OnDayListener {

    private RecyclerView recyclerView;
    private Spinner yearSpinner;
    private Spinner monthSpinner;
    private BottomNavigationView botNavView;
    private ImageView dialogbtn;

    private ArrayList<CalendarRow3> mCalendarRows = new ArrayList<>();
    private DaysRecyclerAdapter3 mDaysRecyclerAdapter3;
    private DayRepository3 mDayRepository3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        monthSpinner = findViewById(R.id.mspinner);
        yearSpinner = findViewById(R.id.yspinner);

        mDayRepository3 = new DayRepository3(this);

        /// Bottom Navigation View section
        botNavView = findViewById(R.id.bottomNavCal);
        Menu menu = botNavView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        botNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.addpg:
                        Intent i = new Intent(CalendarActivity3.this, AddActivity.class);
                        startActivity(i);
                        break;
                    case R.id.calpg:
                        break;


                    case R.id.graphpg:
                        Intent ii = new Intent(CalendarActivity3.this, GraphsActivity.class);
                        startActivity(ii);
                        break;
                }
                return false;
            }
        });


        /// Putting data inside a list for the spinners
        ArrayList<CustomSpinnerItems> customMonthList = new ArrayList<>();
        customMonthList.add(new CustomSpinnerItems("January"));
        customMonthList.add(new CustomSpinnerItems("February"));
        customMonthList.add(new CustomSpinnerItems("March"));
        customMonthList.add(new CustomSpinnerItems("April"));
        customMonthList.add(new CustomSpinnerItems("May"));
        customMonthList.add(new CustomSpinnerItems("June"));
        customMonthList.add(new CustomSpinnerItems("July"));
        customMonthList.add(new CustomSpinnerItems("August"));
        customMonthList.add(new CustomSpinnerItems("September"));
        customMonthList.add(new CustomSpinnerItems("October"));
        customMonthList.add(new CustomSpinnerItems("November"));
        customMonthList.add(new CustomSpinnerItems("December"));

        ArrayList<CustomSpinnerItems> customYearList = new ArrayList<>();
        customYearList.add(new CustomSpinnerItems("2018"));
        customYearList.add(new CustomSpinnerItems("2019"));
        customYearList.add(new CustomSpinnerItems("2020"));

        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(this, customMonthList);
        CustomSpinnerYearAdapter customSpinnerYearAdapter = new CustomSpinnerYearAdapter(this, customYearList);

        if (monthSpinner != null) {
            monthSpinner.setAdapter(customSpinnerAdapter);
            monthSpinner.setOnItemSelectedListener(this);
        }

        if (yearSpinner != null) {
            yearSpinner.setAdapter(customSpinnerYearAdapter);
            yearSpinner.setOnItemSelectedListener(this);
        }

        /// Getting current date and setting it as default in spinners
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        int iyear = getYear(year);


        monthSpinner.setSelection(month);
        yearSpinner.setSelection(iyear);
        /// call here
        /// add listener here and call every time it changes

        recyclerView = findViewById(R.id.calendarlist);



        /// Status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        dialogbtn = findViewById(R.id.symptombtn);
        dialogbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog();
            }
        });

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initRecyclerView();
                String queryMonth =  getSpinnerMonth(monthSpinner.getSelectedItemPosition());
                String queryYear = getSpinnerYear (yearSpinner.getSelectedItemPosition());
                retrieveDays(queryMonth, queryYear);
                Log.d("HELLO", queryYear);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initRecyclerView();
                String queryMonth =  getSpinnerMonth(monthSpinner.getSelectedItemPosition());
                String queryYear = getSpinnerYear (yearSpinner.getSelectedItemPosition());
                retrieveDays(queryMonth, queryYear);
                Log.d("HELLO", queryYear);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
        CustomSpinnerItems items = (CustomSpinnerItems) adapterView.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public int getYear (int year) {
        int ryear = 0;
        if (year == 2018){
            ryear = 0;
        }
        if (year == 2019){
            ryear = 1;
        }
        if (year == 2020) {
            ryear = 2;
        }
        return ryear;
    }

    public void openDialog(){
        DialogPage dialogPage = new DialogPage();
        dialogPage.show(getSupportFragmentManager(), "Symptoms");
    }

    public void openDialogList(int position){
        Bundle bundle = new Bundle();
        bundle.putParcelable("selected_day", mCalendarRows.get(position));
        DialogList dialogList = new DialogList();
        dialogList.setArguments(bundle);
        dialogList.show(getSupportFragmentManager(), "Extended list item");
    }

    private void initRecyclerView (){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        //recyclerView.addItemDecoration(itemDecorator);
        mDaysRecyclerAdapter3 = new DaysRecyclerAdapter3(mCalendarRows, this);
        recyclerView.setAdapter(mDaysRecyclerAdapter3);
    }

    @Override
    public void onDayClick(int position) {
        openDialogList(position);
    }

    // need to get month and year from spinners
    private void retrieveDays(String month, String year){
        mDayRepository3.retrieveDaysTask(month, year).observe(this, new Observer<List<CalendarRow3>>() {
            @Override
            public void onChanged(@Nullable List<CalendarRow3> calendarRows) {
                if (mCalendarRows.size()>0){
                    Log.d("BYEEEEEEEEEE", "HEEEEEEY ");
                    mCalendarRows.clear();
                }
                if (calendarRows != null){
                    Log.d("BYEEEEEEEEEE", "BYEEEEEEEEEE ");
                    mCalendarRows.addAll(calendarRows);
                }
                mDaysRecyclerAdapter3.notifyDataSetChanged();
            }
        });
    }

    private String getSpinnerMonth (int month){
        String spinnerMonth;
        if (month == 0)
            spinnerMonth = "January";
        else if (month == 10)
            spinnerMonth = "November";
        else if (month == 1)
            spinnerMonth = "February";
        else if (month == 2)
            spinnerMonth = "March";
        else if (month == 3)
            spinnerMonth = "April";
        else if (month == 4)
            spinnerMonth = "May";
        else if (month == 5)
            spinnerMonth = "June";
        else if (month == 6)
            spinnerMonth = "July";
        else if (month == 7)
            spinnerMonth = "August";
        else if (month == 8)
            spinnerMonth = "September";
        else if (month == 9)
            spinnerMonth = "October";
        else
            spinnerMonth = "December";

        return spinnerMonth;
    }

    private String getSpinnerYear (int year){
        String spinnerYear;
        if (year == 0)
            spinnerYear = "2018";

        else if (year == 1)
            spinnerYear = "2019";

        else
            spinnerYear = "2020";

        return spinnerYear;
    }
}