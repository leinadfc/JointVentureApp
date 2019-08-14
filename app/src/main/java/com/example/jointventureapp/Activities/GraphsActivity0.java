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
import com.example.jointventureapp.Models.CalendarRow;
import com.example.jointventureapp.R;
import com.example.jointventureapp.Utils.PreferenceUtils;
import com.example.jointventureapp.persistence.DayRepository;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GraphsActivity0 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private BarChart concBarchart;
    private ImageView dialogbtn2;

    private Spinner yearSpinner;
    private Spinner monthSpinner;
    BottomNavigationView botNavView;

    private ArrayList<CalendarRow> mCalendarRows = new ArrayList<>();
    private DayRepository mDayRepository;

    private ArrayList<BarEntry> concentrations = new ArrayList<>();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphs_activity_0);

        monthSpinner = findViewById(R.id.graphsMonthSpinner);
        yearSpinner = findViewById(R.id.graphsYearSpinner);

        mDayRepository = new DayRepository(this);

        botNavView = findViewById(R.id.bottomNavGraph);
        Menu menu = botNavView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        botNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.addpg:
                        if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 5) {
                            Intent i = new Intent(GraphsActivity0.this, AddActivity5.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 4){
                            Intent i = new Intent(GraphsActivity0.this, AddActivity4.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 3){
                            Intent i = new Intent(GraphsActivity0.this, AddActivity.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 2){
                            Intent i = new Intent(GraphsActivity0.this, AddActivity2.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 1){
                            Intent i = new Intent(GraphsActivity0.this, AddActivity1.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 0){
                            Intent i = new Intent(GraphsActivity0.this, AddActivity0.class);
                            startActivity(i);
                        }
                        break;
                    case R.id.calpg:
                        if (PreferenceUtils.getSymptomCount(getApplicationContext()) > 2) {
                            Intent i = new Intent(GraphsActivity0.this, CalendarActivity.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 2){
                            Intent i = new Intent(GraphsActivity0.this, CalendarActivity2.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 1){
                            Intent i = new Intent(GraphsActivity0.this, CalendarActivity1.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 0){
                            Intent i = new Intent(GraphsActivity0.this, CalendarActivity0.class);
                            startActivity(i);
                        }
                        break;

                    case R.id.graphpg:
                        break;
                }
                return false;
            }
        });

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
        ///////////////////////////////////////////////
        /// Replace at the end by just current year ///
        ///////////////////////////////////////////////
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
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        int iyear = getYear(year);




        concBarchart = findViewById(R.id.concchart);


        concBarchart.setDrawBarShadow(false);


        concBarchart.setDrawValueAboveBar(false);

        ///Remember to change this accordingly

        concBarchart.setMaxVisibleValueCount(50);



        concBarchart.setPinchZoom(false);


        concBarchart.setDrawGridBackground(false);



        concBarchart.getAxisRight().setEnabled(false);


        concBarchart.getAxisLeft().setDrawGridLines(true);


        concBarchart.getAxisLeft().setDrawGridLines(false);


        concBarchart.getXAxis().setDrawGridLines(true);

        concBarchart.getXAxis().setEnabled(true);

        concBarchart.getAxisLeft().setAxisMinimum(0);

        monthSpinner.setSelection(month);
        //monthSpinner.setSelection(1);
        yearSpinner.setSelection(iyear);



        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String queryMonth =  getSpinnerMonth(monthSpinner.getSelectedItemPosition());
                String queryYear = getSpinnerYear (yearSpinner.getSelectedItemPosition());

                retrieveDays(queryMonth, queryYear);



               //makeChart();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



                String queryMonth =  getSpinnerMonth(monthSpinner.getSelectedItemPosition());
                String queryYear = getSpinnerYear (yearSpinner.getSelectedItemPosition());

                retrieveDays(queryMonth, queryYear);


                //makeChart();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        /// Status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        dialogbtn2 = findViewById(R.id.graphSymptombtn);
        dialogbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog2();
            }
        });

    }


    private void makeChart() {

        concentrations = getConcentrationEntries();


        Log.d("CONCENTRATIONS Y", Integer.toString(concentrations.size()));



        BarDataSet barDataSet = new BarDataSet(concentrations, "ADL Concentration");


        barDataSet.setColors(0xFF182D57);



        BarData data = new BarData(barDataSet);

        /// might change
        data.setBarWidth(0.8f);


        data.setDrawValues(false);

        concBarchart.setData(data);



        CustomBarChartRender barChartRender = new CustomBarChartRender(concBarchart,
                concBarchart.getAnimator(), concBarchart.getViewPortHandler());

        barChartRender.setRadius(20);



        concBarchart.getDescription().setEnabled(false);

        concBarchart.getLegend().setEnabled(false);

        concBarchart.setRenderer(barChartRender);

        concBarchart.invalidate();

        String[] days = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
                "23", "24", "25", "26", "27", "28", "29", "30" };

        XAxis xAxis = concBarchart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setGranularity(1);

    }


    /// When I press back on register I want to go to login
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slideindown, R.anim.slideoutdown);
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

    public void openDialog2(){
        DialogPage dialogPage = new DialogPage();
        dialogPage.show(getSupportFragmentManager(), "Symptoms");
    }

    private void retrieveDays(String month, String year){
        Log.d("QUERY NOT ENTERED", "NOT ENTERED");
        mDayRepository.retrieveDaysTask(month, year).observe(this, new Observer<List<CalendarRow>>() {
            @Override
            public void onChanged(@Nullable List<CalendarRow> calendarRows) {
                Log.d("QUERY ENTERED", "ENTERED");
                if (mCalendarRows.size()>0){
                    mCalendarRows.clear();
                }
                if (calendarRows != null){
                    mCalendarRows.addAll(calendarRows);
                }
                concBarchart.notifyDataSetChanged();
                concBarchart.invalidate();

                makeChart();
            }
        });
    }

    private ArrayList<BarEntry> getConcentrationEntries (){
        concentrations.clear();
        float concentration;
        for (int j = 1; j<32; j++) {
            for (int i = 0; i < mCalendarRows.size(); i++) {
                if (Integer.parseInt(mCalendarRows.get(i).getDay()) == j){
                    if (mCalendarRows.get(i).getConcentration().equals("")) {
                        concentration = 0f;
                    }
                    else {
                        concentration = Float.parseFloat(mCalendarRows.get(i).getConcentration());
                    }
                    concentrations.add(new BarEntry(j, concentration));
                }
                else {
                    concentrations.add(new BarEntry(j, 0f));
                }
            }
        }

        return concentrations;
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
