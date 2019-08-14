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
import android.support.v4.content.ContextCompat;
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
import com.example.jointventureapp.Models.MyBarDataSet;
import com.example.jointventureapp.R;
import com.example.jointventureapp.Utils.PreferenceUtils;
import com.example.jointventureapp.persistence.DayRepository;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GraphsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private BarChart concBarchart;
    private BarChart sym1BarChart;
    private BarChart sym2BarChart;
    private BarChart sym3BarChart;
    private ImageView dialogbtn2;

    private Spinner yearSpinner;
    private Spinner monthSpinner;
    private BottomNavigationView botNavView;

    private ArrayList<CalendarRow> mCalendarRows = new ArrayList<>();
    private DayRepository mDayRepository;

    private ArrayList<BarEntry> concentrations = new ArrayList<>();
    private ArrayList<BarEntry> symptoms1 = new ArrayList<>();
    private ArrayList<BarEntry> symptoms2 = new ArrayList<>();
    private ArrayList<BarEntry> symptoms3 = new ArrayList<>();



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphs_activity);



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
                            Intent i = new Intent(GraphsActivity.this, AddActivity5.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 4){
                            Intent i = new Intent(GraphsActivity.this, AddActivity4.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 3){
                            Intent i = new Intent(GraphsActivity.this, AddActivity.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 2){
                            Intent i = new Intent(GraphsActivity.this, AddActivity2.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 1){
                            Intent i = new Intent(GraphsActivity.this, AddActivity1.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 0){
                            Intent i = new Intent(GraphsActivity.this, AddActivity0.class);
                            startActivity(i);
                        }
                        break;
                    case R.id.calpg:
                        if (PreferenceUtils.getSymptomCount(getApplicationContext()) > 2) {
                            Intent i = new Intent(GraphsActivity.this, CalendarActivity.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 2){
                            Intent i = new Intent(GraphsActivity.this, CalendarActivity2.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 1){
                            Intent i = new Intent(GraphsActivity.this, CalendarActivity1.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 0){
                            Intent i = new Intent(GraphsActivity.this, CalendarActivity0.class);
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
        sym1BarChart = findViewById(R.id.symptom1chart);
        sym2BarChart = findViewById(R.id.symptom2chart);
        sym3BarChart = findViewById(R.id.symptom3chart);

        concBarchart.setDrawBarShadow(false);
        sym1BarChart.setDrawBarShadow(false);
        sym2BarChart.setDrawBarShadow(false);
        sym3BarChart.setDrawBarShadow(false);

        concBarchart.setDrawValueAboveBar(false);
        sym1BarChart.setDrawValueAboveBar(false);
        sym2BarChart.setDrawValueAboveBar(false);
        sym3BarChart.setDrawValueAboveBar(false);
        ///Remember to change this accordingly

        concBarchart.setMaxVisibleValueCount(50);
        sym1BarChart.setMaxVisibleValueCount(50);
        sym2BarChart.setMaxVisibleValueCount(50);
        sym3BarChart.setMaxVisibleValueCount(50);

        sym1BarChart.setPinchZoom(false);
        concBarchart.setPinchZoom(false);
        sym2BarChart.setPinchZoom(false);
        sym3BarChart.setPinchZoom(false);

        concBarchart.setDrawGridBackground(false);
        sym1BarChart.setDrawGridBackground(false);
        sym3BarChart.setDrawGridBackground(false);

        sym1BarChart.getAxisRight().setEnabled(false);
        concBarchart.getAxisRight().setEnabled(false);
        sym2BarChart.getAxisRight().setEnabled(false);
        sym3BarChart.getAxisRight().setEnabled(false);

        concBarchart.getAxisLeft().setDrawGridLines(true);
        sym1BarChart.getAxisLeft().setDrawGridLines(true);
        sym2BarChart.getAxisLeft().setDrawGridLines(true);
        sym3BarChart.getAxisLeft().setDrawGridLines(true);

        sym1BarChart.getAxisLeft().setEnabled(false);
        sym2BarChart.getAxisLeft().setEnabled(false);
        sym3BarChart.getAxisLeft().setEnabled(false);

        concBarchart.getAxisLeft().setDrawGridLines(false);

        sym1BarChart.getXAxis().setDrawGridLines(true);
        sym2BarChart.getXAxis().setDrawGridLines(true);
        concBarchart.getXAxis().setDrawGridLines(true);
        sym3BarChart.getXAxis().setDrawGridLines(true);

        sym1BarChart.getXAxis().setEnabled(true);
        sym2BarChart.getXAxis().setEnabled(true);
        concBarchart.getXAxis().setEnabled(true);

        sym3BarChart.getAxisLeft().setAxisMinimum(0);
        sym2BarChart.getAxisLeft().setAxisMinimum(0);
        sym1BarChart.getAxisLeft().setAxisMinimum(0);
        concBarchart.getAxisLeft().setAxisMinimum(0);

        sym3BarChart.getAxisLeft().setAxisMaximum(5);
        sym2BarChart.getAxisLeft().setAxisMaximum(5);
        sym1BarChart.getAxisLeft().setAxisMaximum(5);



        monthSpinner.setSelection(month);
        //monthSpinner.setSelection(1);
        yearSpinner.setSelection(iyear);



        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String queryMonth =  getSpinnerMonth(monthSpinner.getSelectedItemPosition());
                String queryYear = getSpinnerYear (yearSpinner.getSelectedItemPosition());

                retrieveDays(queryMonth, queryYear);

                Log.d("MONTITEM SELECTED MONTH", queryMonth);
                Log.d("MONTHITEM SELECTED YEAR", queryYear);

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
        symptoms1 = getSymptom1Entries();
        symptoms2 = getSymptom2Entries();
        symptoms3 = getSymptom3Entries();

        Log.d("CONCENTRATIONS Y", Integer.toString(concentrations.size()));
        Log.d("SYMPTOMS1 Y", Integer.toString(symptoms1.size()));
        Log.d("SYMPTOMS2 Y", Integer.toString(symptoms2.size()));
        Log.d("SYMPTOMS3 Y", Integer.toString(symptoms3.size()));


        BarDataSet barDataSet = new BarDataSet(concentrations, "ADL Concentration");
        MyBarDataSet sym1DataSet = new MyBarDataSet(symptoms1, "");
        MyBarDataSet sym2DataSet = new MyBarDataSet(symptoms2, "");
        MyBarDataSet sym3DataSet = new MyBarDataSet(symptoms3, "");
        /// might change
        barDataSet.setColors(0xFF182D57);
        // for now this color look video to change
        //sym1DataSet.setColors(0xFF182D57);
        sym1DataSet.setColors(new int[]{ContextCompat.getColor(getApplicationContext(), R.color.Blue40),
                ContextCompat.getColor(getApplicationContext(), R.color.Blue60),
                ContextCompat.getColor(getApplicationContext(), R.color.Blue80), ContextCompat.getColor(getApplicationContext(),
                R.color.MainBlue), ContextCompat.getColor(getApplicationContext(), R.color.Blue20)});

        sym2DataSet.setColors(new int[]{ContextCompat.getColor(getApplicationContext(), R.color.Blue40),
                ContextCompat.getColor(getApplicationContext(), R.color.Blue60),
                ContextCompat.getColor(getApplicationContext(), R.color.Blue80), ContextCompat.getColor(getApplicationContext(),
                R.color.MainBlue), ContextCompat.getColor(getApplicationContext(), R.color.Blue20)});

        sym3DataSet.setColors(new int[]{ContextCompat.getColor(getApplicationContext(), R.color.Blue40),
                ContextCompat.getColor(getApplicationContext(), R.color.Blue60),
                ContextCompat.getColor(getApplicationContext(), R.color.Blue80), ContextCompat.getColor(getApplicationContext(),
                R.color.MainBlue), ContextCompat.getColor(getApplicationContext(), R.color.Blue20)});


        BarData data = new BarData(barDataSet);
        BarData sym1data = new BarData(sym1DataSet);
        BarData sym2data = new BarData(sym2DataSet);
        BarData sym3data = new BarData(sym3DataSet);
        /// might change
        data.setBarWidth(0.8f);
        sym1data.setBarWidth(0.8f);
        sym2data.setBarWidth(0.8f);
        sym3data.setBarWidth(0.8f);

        sym1data.setDrawValues(false);
        sym2data.setDrawValues(false);
        data.setDrawValues(false);
        sym3data.setDrawValues(false);

        sym1BarChart.setData(sym1data);


        sym2BarChart.setData(sym2data);


        concBarchart.setData(data);


        sym3BarChart.setData(sym3data);


        sym1BarChart.setFitBars(true);
        sym2BarChart.setFitBars(true);
        sym3BarChart.setFitBars(true);

        CustomBarChartRender barChartRender = new CustomBarChartRender(concBarchart,
                concBarchart.getAnimator(), concBarchart.getViewPortHandler());
        CustomBarChartRender sym1BarChartRender = new CustomBarChartRender(sym1BarChart,
                sym1BarChart.getAnimator(), sym1BarChart.getViewPortHandler());
        CustomBarChartRender sym2BarChartRender = new CustomBarChartRender(sym2BarChart,
                sym2BarChart.getAnimator(), sym2BarChart.getViewPortHandler());

        CustomBarChartRender sym3BarChartRender = new CustomBarChartRender(sym3BarChart,
                sym3BarChart.getAnimator(), sym3BarChart.getViewPortHandler());


        sym2BarChartRender.setRadius(20);
        barChartRender.setRadius(20);
        sym1BarChartRender.setRadius(20);
        sym3BarChartRender.setRadius(20);


        concBarchart.getDescription().setEnabled(false);
        sym1BarChart.getDescription().setEnabled(false);
        sym2BarChart.getDescription().setEnabled(false);
        sym3BarChart.getDescription().setEnabled(false);

        sym1BarChart.getLegend().setEnabled(false);
        sym2BarChart.getLegend().setEnabled(false);
        sym3BarChart.getLegend().setEnabled(false);
        concBarchart.getLegend().setEnabled(false);

        concBarchart.setRenderer(barChartRender);
        sym1BarChart.setRenderer(sym1BarChartRender);
        sym2BarChart.setRenderer(sym2BarChartRender);
        sym3BarChart.setRenderer(sym3BarChartRender);

        concBarchart.invalidate();

        String[] days = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
                "23", "24", "25", "26", "27", "28", "29", "30" };

        XAxis sym1XAxis = sym1BarChart.getXAxis();
        XAxis sym2XAxis = sym2BarChart.getXAxis();
        XAxis xAxis = concBarchart.getXAxis();
        XAxis sym3XAxis = sym3BarChart.getXAxis();

        //xAxis.setValueFormatter(new MyXaxisValueFormatter(days));
        sym1XAxis.setValueFormatter(new MyXaxisValueFormatter2(days));
        //sym3XAxis.setValueFormatter(new MyXaxisValueFormatter(days));
        sym2XAxis.setValueFormatter(new MyXaxisValueFormatter2(days));

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        sym1XAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        sym3XAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        sym2XAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setGranularity(1);
        sym1XAxis.setGranularity(1);
        sym3XAxis.setGranularity(1);
        sym2XAxis.setGranularity(1);
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


    public class MyXaxisValueFormatter implements IAxisValueFormatter {

        private String [] mValues;
        private MyXaxisValueFormatter(String[] values) {
            this.mValues=values;
        }

        @Override
        public String getFormattedValue (float value, AxisBase axis){
            return mValues[(int) value];
        }
    }

    public class MyXaxisValueFormatter2 implements IAxisValueFormatter {

        private String [] mValues;
        private MyXaxisValueFormatter2(String[] values) {
            this.mValues=values;
        }

        @Override
        public String getFormattedValue (float value, AxisBase axis){
            return "";
        }
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

                sym1BarChart.notifyDataSetChanged();
                sym1BarChart.invalidate();

                sym2BarChart.notifyDataSetChanged();
                sym2BarChart.invalidate();

                sym3BarChart.notifyDataSetChanged();
                sym3BarChart.invalidate();

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

    private ArrayList<BarEntry> getSymptom1Entries (){
        symptoms1.clear();
        for (int j = 1; j<32; j++) {
            for (int i = 0; i < mCalendarRows.size(); i++) {
                if (Integer.parseInt(mCalendarRows.get(i).getDay()) == j){
                    symptoms1.add(new BarEntry(j, mCalendarRows.get(i).getProgress1()));
                }
                else {
                    symptoms1.add(new BarEntry(j, 0));
                }
            }
        }

        return symptoms1;
    }

    private ArrayList<BarEntry> getSymptom2Entries (){
        symptoms2.clear();
        for (int j = 1; j<32; j++) {
            for (int i = 0; i < mCalendarRows.size(); i++) {
                if (Integer.parseInt(mCalendarRows.get(i).getDay()) == j){
                    symptoms2.add(new BarEntry(j, mCalendarRows.get(i).getProgress2()));
                }
                else {
                    symptoms2.add(new BarEntry(j, 0));
                }
            }
        }

        return symptoms2;
    }

    private ArrayList<BarEntry> getSymptom3Entries (){
        symptoms3.clear();
        for (int j = 1; j<32; j++) {
            for (int i = 0; i < mCalendarRows.size(); i++) {
                if (Integer.parseInt(mCalendarRows.get(i).getDay()) == j){
                    symptoms3.add(new BarEntry(j, mCalendarRows.get(i).getProgress3()));
                }
                else {
                    symptoms3.add(new BarEntry(j, 0));
                }
            }
        }

        return symptoms3;
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
