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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

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

public class GraphsActivity4 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private BarChart concBarchart;
    private BarChart sym1BarChart;
    private BarChart sym2BarChart;
    private BarChart sym3BarChart;
    private BarChart sym4BarChart;

    private ImageView dialogbtn2;

    private Spinner yearSpinner;
    private Spinner monthSpinner;
    private BottomNavigationView botNavView;

    private ArrayList<CalendarRow> mCalendarRows = new ArrayList<>();
    private DayRepository mDayRepository;

    private TextView symptomText1;
    private TextView symptomText2;
    private TextView symptomText3;
    private TextView symptomText4;

    private ArrayList<BarEntry> concentrations = new ArrayList<>();
    private ArrayList<BarEntry> symptoms1 = new ArrayList<>();
    private ArrayList<BarEntry> symptoms2 = new ArrayList<>();
    private ArrayList<BarEntry> symptoms3 = new ArrayList<>();
    private ArrayList<BarEntry> symptoms4 = new ArrayList<>();
    private RelativeLayout symptomsRelativeLayout;

    private ArrayList<String> symptomNames = new ArrayList<>();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphs_activity_4);

        monthSpinner = findViewById(R.id.graphsMonthSpinner);
        yearSpinner = findViewById(R.id.graphsYearSpinner);

        symptomText1 = findViewById(R.id.symptom_1_text);
        symptomText2 = findViewById(R.id.symptom_2_text);
        symptomText3 = findViewById(R.id.symptom_3_text);
        symptomText4 = findViewById(R.id.symptom_4_text);
        symptomsRelativeLayout = findViewById(R.id.cog_relative_layout);

        getSymptomTextArray();

        symptomText1.setText(symptomNames.get(0));
        symptomText2.setText(symptomNames.get(1));
        symptomText3.setText(symptomNames.get(2));
        symptomText4.setText(symptomNames.get(3));


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
                            Intent i = new Intent(GraphsActivity4.this, AddActivity5.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 4){
                            Intent i = new Intent(GraphsActivity4.this, AddActivity4.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 3){
                            Intent i = new Intent(GraphsActivity4.this, AddActivity.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 2){
                            Intent i = new Intent(GraphsActivity4.this, AddActivity2.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 1){
                            Intent i = new Intent(GraphsActivity4.this, AddActivity1.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 0){
                            Intent i = new Intent(GraphsActivity4.this, AddActivity0.class);
                            startActivity(i);
                        }
                        break;
                    case R.id.calpg:
                        if (PreferenceUtils.getSymptomCount(getApplicationContext()) > 2) {
                            Intent i = new Intent(GraphsActivity4.this, CalendarActivity.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 2){
                            Intent i = new Intent(GraphsActivity4.this, CalendarActivity2.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 1){
                            Intent i = new Intent(GraphsActivity4.this, CalendarActivity1.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 0){
                            Intent i = new Intent(GraphsActivity4.this, CalendarActivity0.class);
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
        sym4BarChart = findViewById(R.id.symptom4chart);

        concBarchart.setDrawBarShadow(false);
        sym1BarChart.setDrawBarShadow(false);
        sym2BarChart.setDrawBarShadow(false);
        sym3BarChart.setDrawBarShadow(false);
        sym4BarChart.setDrawBarShadow(false);

        concBarchart.setDrawValueAboveBar(false);
        sym1BarChart.setDrawValueAboveBar(false);
        sym2BarChart.setDrawValueAboveBar(false);
        sym3BarChart.setDrawValueAboveBar(false);
        sym4BarChart.setDrawValueAboveBar(false);
        ///Remember to change this accordingly

        concBarchart.setMaxVisibleValueCount(50);
        sym1BarChart.setMaxVisibleValueCount(50);
        sym2BarChart.setMaxVisibleValueCount(50);
        sym3BarChart.setMaxVisibleValueCount(50);
        sym4BarChart.setMaxVisibleValueCount(50);

        sym1BarChart.setPinchZoom(false);
        concBarchart.setPinchZoom(false);
        sym2BarChart.setPinchZoom(false);
        sym3BarChart.setPinchZoom(false);
        sym4BarChart.setPinchZoom(false);

        concBarchart.setDrawGridBackground(false);
        sym1BarChart.setDrawGridBackground(false);
        sym3BarChart.setDrawGridBackground(false);
        sym4BarChart.setDrawGridBackground(false);

        sym1BarChart.getAxisRight().setEnabled(false);
        concBarchart.getAxisRight().setEnabled(false);
        sym2BarChart.getAxisRight().setEnabled(false);
        sym3BarChart.getAxisRight().setEnabled(false);
        sym4BarChart.getAxisRight().setEnabled(false);

        concBarchart.getAxisLeft().setDrawGridLines(true);
        sym1BarChart.getAxisLeft().setDrawGridLines(true);
        sym2BarChart.getAxisLeft().setDrawGridLines(true);
        sym3BarChart.getAxisLeft().setDrawGridLines(true);
        sym4BarChart.getAxisLeft().setDrawGridLines(true);

        sym1BarChart.getAxisLeft().setEnabled(false);
        sym2BarChart.getAxisLeft().setEnabled(false);
        sym3BarChart.getAxisLeft().setEnabled(false);
        sym4BarChart.getAxisLeft().setEnabled(false);

        concBarchart.getAxisLeft().setDrawGridLines(false);

        sym1BarChart.getXAxis().setDrawGridLines(true);
        sym2BarChart.getXAxis().setDrawGridLines(true);
        concBarchart.getXAxis().setDrawGridLines(true);
        sym3BarChart.getXAxis().setDrawGridLines(true);
        sym4BarChart.getXAxis().setDrawGridLines(true);

        sym1BarChart.getXAxis().setEnabled(true);
        sym2BarChart.getXAxis().setEnabled(true);
        sym3BarChart.getXAxis().setEnabled(true);
        sym4BarChart.getXAxis().setEnabled(true);
        concBarchart.getXAxis().setEnabled(true);

        sym3BarChart.getAxisLeft().setAxisMinimum(0);
        sym2BarChart.getAxisLeft().setAxisMinimum(0);
        sym1BarChart.getAxisLeft().setAxisMinimum(0);
        sym4BarChart.getAxisLeft().setAxisMinimum(0);
        concBarchart.getAxisLeft().setAxisMinimum(0);

        sym3BarChart.getAxisLeft().setAxisMaximum(5);
        sym2BarChart.getAxisLeft().setAxisMaximum(5);
        sym1BarChart.getAxisLeft().setAxisMaximum(5);
        sym4BarChart.getAxisLeft().setAxisMaximum(5);

        monthSpinner.setSelection(month);
        yearSpinner.setSelection(iyear);

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String queryMonth =  getSpinnerMonth(monthSpinner.getSelectedItemPosition());
                String queryYear = getSpinnerYear (yearSpinner.getSelectedItemPosition());

                retrieveDays(queryMonth, queryYear);

                Log.d("MONTITEM SELECTED MONTH", queryMonth);
                Log.d("MONTHITEM SELECTED YEAR", queryYear);

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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        dialogbtn2 = findViewById(R.id.graphSymptombtn);
        symptomsRelativeLayout.setOnClickListener(new View.OnClickListener() {
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
        symptoms4 = getSymptom4Entries();

        Log.d("CONCENTRATIONS Y", Integer.toString(concentrations.size()));
        Log.d("SYMPTOMS1 Y", Integer.toString(symptoms1.size()));
        Log.d("SYMPTOMS2 Y", Integer.toString(symptoms2.size()));
        Log.d("SYMPTOMS3 Y", Integer.toString(symptoms3.size()));


        BarDataSet barDataSet = new BarDataSet(concentrations, "ADL Concentration");
        MyBarDataSet sym1DataSet = new MyBarDataSet(symptoms1, "");
        MyBarDataSet sym2DataSet = new MyBarDataSet(symptoms2, "");
        MyBarDataSet sym3DataSet = new MyBarDataSet(symptoms3, "");
        MyBarDataSet sym4DataSet = new MyBarDataSet(symptoms4, "");

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

        sym4DataSet.setColors(new int[]{ContextCompat.getColor(getApplicationContext(), R.color.Blue40),
                ContextCompat.getColor(getApplicationContext(), R.color.Blue60),
                ContextCompat.getColor(getApplicationContext(), R.color.Blue80), ContextCompat.getColor(getApplicationContext(),
                R.color.MainBlue), ContextCompat.getColor(getApplicationContext(), R.color.Blue20)});


        BarData data = new BarData(barDataSet);
        BarData sym1data = new BarData(sym1DataSet);
        BarData sym2data = new BarData(sym2DataSet);
        BarData sym3data = new BarData(sym3DataSet);
        BarData sym4data = new BarData(sym4DataSet);
        /// might change
        data.setBarWidth(0.8f);
        sym1data.setBarWidth(0.8f);
        sym2data.setBarWidth(0.8f);
        sym3data.setBarWidth(0.8f);
        sym4data.setBarWidth(0.8f);

        sym1data.setDrawValues(false);
        sym2data.setDrawValues(false);
        data.setDrawValues(false);
        sym3data.setDrawValues(false);
        sym4data.setDrawValues(false);

        sym1BarChart.setData(sym1data);


        sym2BarChart.setData(sym2data);


        concBarchart.setData(data);


        sym3BarChart.setData(sym3data);

        sym4BarChart.setData(sym4data);

        sym1BarChart.setFitBars(true);
        sym2BarChart.setFitBars(true);
        sym3BarChart.setFitBars(true);
        sym4BarChart.setFitBars(true);

        CustomBarChartRender barChartRender = new CustomBarChartRender(concBarchart,
                concBarchart.getAnimator(), concBarchart.getViewPortHandler());
        CustomBarChartRender sym1BarChartRender = new CustomBarChartRender(sym1BarChart,
                sym1BarChart.getAnimator(), sym1BarChart.getViewPortHandler());
        CustomBarChartRender sym2BarChartRender = new CustomBarChartRender(sym2BarChart,
                sym2BarChart.getAnimator(), sym2BarChart.getViewPortHandler());

        CustomBarChartRender sym3BarChartRender = new CustomBarChartRender(sym3BarChart,
                sym3BarChart.getAnimator(), sym3BarChart.getViewPortHandler());

        CustomBarChartRender sym4BarChartRender = new CustomBarChartRender(sym4BarChart,
                sym4BarChart.getAnimator(), sym4BarChart.getViewPortHandler());


        sym2BarChartRender.setRadius(20);
        barChartRender.setRadius(20);
        sym1BarChartRender.setRadius(20);
        sym3BarChartRender.setRadius(20);
        sym4BarChartRender.setRadius(20);

        concBarchart.getDescription().setEnabled(false);
        sym1BarChart.getDescription().setEnabled(false);
        sym2BarChart.getDescription().setEnabled(false);
        sym3BarChart.getDescription().setEnabled(false);
        sym4BarChart.getDescription().setEnabled(false);

        sym1BarChart.getLegend().setEnabled(false);
        sym2BarChart.getLegend().setEnabled(false);
        sym3BarChart.getLegend().setEnabled(false);
        concBarchart.getLegend().setEnabled(false);
        sym4BarChart.getLegend().setEnabled(false);

        concBarchart.setRenderer(barChartRender);
        sym1BarChart.setRenderer(sym1BarChartRender);
        sym2BarChart.setRenderer(sym2BarChartRender);
        sym3BarChart.setRenderer(sym3BarChartRender);
        sym4BarChart.setRenderer(sym4BarChartRender);

        concBarchart.invalidate();

        String[] days = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
                "23", "24", "25", "26", "27", "28", "29", "30" };

        XAxis sym1XAxis = sym1BarChart.getXAxis();
        XAxis sym2XAxis = sym2BarChart.getXAxis();
        XAxis xAxis = concBarchart.getXAxis();
        XAxis sym3XAxis = sym3BarChart.getXAxis();
        XAxis sym4XAxis = sym4BarChart.getXAxis();

        //xAxis.setValueFormatter(new MyXaxisValueFormatter(days));
        sym1XAxis.setValueFormatter(new MyXaxisValueFormatter2(days));
        sym3XAxis.setValueFormatter(new MyXaxisValueFormatter2(days));
        sym2XAxis.setValueFormatter(new MyXaxisValueFormatter2(days));

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        sym1XAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        sym3XAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        sym4XAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        sym2XAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setGranularity(1);
        sym1XAxis.setGranularity(1);
        sym3XAxis.setGranularity(1);
        sym2XAxis.setGranularity(1);
        sym4XAxis.setGranularity(1);
    }


    /// When I press back on register I want to go to login
    @Override
    public void onBackPressed() {
        finish();
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
        DialogPageGraphs dialogPage = new DialogPageGraphs();
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

                sym4BarChart.notifyDataSetChanged();
                sym4BarChart.invalidate();

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
        getSymptomTextArray();
        symptoms1.clear();
        for (int j = 1; j<32; j++) {
            for (int i = 0; i < mCalendarRows.size(); i++) {
                if (Integer.parseInt(mCalendarRows.get(i).getDay()) == j){
                    if (symptomNames.get(0).equals(mCalendarRows.get(i).getSymptomText1())){
                        symptoms1.add(new BarEntry(j, mCalendarRows.get(i).getProgress1()));
                    }
                    if (symptomNames.get(0).equals(mCalendarRows.get(i).getSymptomText2())){
                        symptoms1.add(new BarEntry(j, mCalendarRows.get(i).getProgress2()));
                    }
                    if (symptomNames.get(0).equals(mCalendarRows.get(i).getSymptomText3())){
                        symptoms1.add(new BarEntry(j, mCalendarRows.get(i).getProgress3()));
                    }
                    if (symptomNames.get(0).equals(mCalendarRows.get(i).getSymptomText4())){
                        symptoms1.add(new BarEntry(j, mCalendarRows.get(i).getProgress4()));
                    }
                    if (symptomNames.get(0).equals(mCalendarRows.get(i).getSymptomText5())){
                        symptoms1.add(new BarEntry(j, mCalendarRows.get(i).getProgress5()));
                    }
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
                    if (symptomNames.get(1).equals(mCalendarRows.get(i).getSymptomText1())){
                        symptoms2.add(new BarEntry(j, mCalendarRows.get(i).getProgress1()));
                    }
                    if (symptomNames.get(1).equals(mCalendarRows.get(i).getSymptomText2())){
                        symptoms2.add(new BarEntry(j, mCalendarRows.get(i).getProgress2()));
                    }
                    if (symptomNames.get(1).equals(mCalendarRows.get(i).getSymptomText3())){
                        symptoms2.add(new BarEntry(j, mCalendarRows.get(i).getProgress3()));
                    }
                    if (symptomNames.get(1).equals(mCalendarRows.get(i).getSymptomText4())){
                        symptoms2.add(new BarEntry(j, mCalendarRows.get(i).getProgress4()));
                    }
                    if (symptomNames.get(1).equals(mCalendarRows.get(i).getSymptomText5())){
                        symptoms2.add(new BarEntry(j, mCalendarRows.get(i).getProgress5()));
                    }
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
                    if (symptomNames.get(2).equals(mCalendarRows.get(i).getSymptomText1())){
                        symptoms3.add(new BarEntry(j, mCalendarRows.get(i).getProgress1()));
                    }
                    if (symptomNames.get(2).equals(mCalendarRows.get(i).getSymptomText2())){
                        symptoms3.add(new BarEntry(j, mCalendarRows.get(i).getProgress2()));
                    }
                    if (symptomNames.get(2).equals(mCalendarRows.get(i).getSymptomText3())){
                        symptoms3.add(new BarEntry(j, mCalendarRows.get(i).getProgress3()));
                    }
                    if (symptomNames.get(2).equals(mCalendarRows.get(i).getSymptomText4())){
                        symptoms3.add(new BarEntry(j, mCalendarRows.get(i).getProgress4()));
                    }
                    if (symptomNames.get(2).equals(mCalendarRows.get(i).getSymptomText5())){
                        symptoms3.add(new BarEntry(j, mCalendarRows.get(i).getProgress5()));
                    }
                }
                else {
                    symptoms3.add(new BarEntry(j, 0));
                }
            }
        }

        return symptoms3;
    }

    private ArrayList<BarEntry> getSymptom4Entries (){
        symptoms4.clear();
        for (int j = 1; j<32; j++) {
            for (int i = 0; i < mCalendarRows.size(); i++) {
                if (Integer.parseInt(mCalendarRows.get(i).getDay()) == j){
                    if (symptomNames.get(3).equals(mCalendarRows.get(i).getSymptomText1())){
                        symptoms4.add(new BarEntry(j, mCalendarRows.get(i).getProgress1()));
                    }
                    if (symptomNames.get(3).equals(mCalendarRows.get(i).getSymptomText2())){
                        symptoms4.add(new BarEntry(j, mCalendarRows.get(i).getProgress2()));
                    }
                    if (symptomNames.get(3).equals(mCalendarRows.get(i).getSymptomText3())){
                        symptoms4.add(new BarEntry(j, mCalendarRows.get(i).getProgress3()));
                    }
                    if (symptomNames.get(3).equals(mCalendarRows.get(i).getSymptomText4())){
                        symptoms4.add(new BarEntry(j, mCalendarRows.get(i).getProgress4()));
                    }
                    if (symptomNames.get(3).equals(mCalendarRows.get(i).getSymptomText5())){
                        symptoms4.add(new BarEntry(j, mCalendarRows.get(i).getProgress5()));
                    }
                }
                else {
                    symptoms4.add(new BarEntry(j, 0));
                }
            }
        }

        return symptoms4;
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
