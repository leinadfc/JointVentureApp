package com.example.jointventureapp.Activities;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.jointventureapp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

import static android.graphics.Color.BLUE;

public class GraphsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    BarChart concBarchart;
    BarChart sym1BarChart;
    BarChart sym2BarChart;
    BarChart sym3BarChart;
    ImageView dialogbtn2;

    private Spinner yearSpinner;
    private Spinner monthSpinner;
    BottomNavigationView botNavView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphs_activity);

        monthSpinner = findViewById(R.id.graphsMonthSpinner);
        yearSpinner = findViewById(R.id.graphsYearSpinner);

        botNavView = findViewById(R.id.bottomNavGraph);
        Menu menu = botNavView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        botNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.addpg:
                        Intent i = new Intent(GraphsActivity.this, AddActivity.class);
                        startActivity(i);
                        break;
                    case R.id.calpg:
                        Intent ii = new Intent(GraphsActivity.this, CalendarActivity.class);
                        startActivity(ii);
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


        monthSpinner.setSelection(month);
        yearSpinner.setSelection(iyear);

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

        sym3BarChart.getAxisLeft().setAxisMaximum(5);
        sym2BarChart.getAxisLeft().setAxisMaximum(5);
        sym1BarChart.getAxisLeft().setAxisMaximum(5);


        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarEntry> sym1Entries = new ArrayList<>();
        ArrayList<BarEntry> sym2Entries = new ArrayList<>();
        ArrayList<BarEntry> sym3Entries = new ArrayList<>();

        /// Barchart data
        barEntries.add(new BarEntry(0, 400f));
        barEntries.add(new BarEntry(1, 450f));
        barEntries.add(new BarEntry(2, 300f));
        barEntries.add(new BarEntry(3, 350f));
        barEntries.add(new BarEntry(4, 400f));
        barEntries.add(new BarEntry(5, 300f));
        barEntries.add(new BarEntry(6, 400f));
        barEntries.add(new BarEntry(7, 450f));
        barEntries.add(new BarEntry(8, 300f));
        barEntries.add(new BarEntry(9, 350f));
        barEntries.add(new BarEntry(10, 400f));
        barEntries.add(new BarEntry(11, 300f));
        barEntries.add(new BarEntry(12, 400f));
        barEntries.add(new BarEntry(13, 450f));
        barEntries.add(new BarEntry(14, 300f));
        barEntries.add(new BarEntry(15, 350f));
        barEntries.add(new BarEntry(16, 400f));
        barEntries.add(new BarEntry(17, 300f));
        barEntries.add(new BarEntry(18, 400f));
        barEntries.add(new BarEntry(19, 450f));
        barEntries.add(new BarEntry(20, 300f));
        barEntries.add(new BarEntry(21, 350f));
        barEntries.add(new BarEntry(22, 400f));
        barEntries.add(new BarEntry(23, 300f));
        barEntries.add(new BarEntry(24, 400f));
        barEntries.add(new BarEntry(25, 450f));
        barEntries.add(new BarEntry(26, 300f));
        barEntries.add(new BarEntry(27, 350f));
        barEntries.add(new BarEntry(28, 400f));
        barEntries.add(new BarEntry(29, 300f));

        sym1Entries.add(new BarEntry(0, 1));
        sym1Entries.add(new BarEntry(1, 2));
        sym1Entries.add(new BarEntry(2, 3));
        sym1Entries.add(new BarEntry(3, 3));
        sym1Entries.add(new BarEntry(4, 4));
        sym1Entries.add(new BarEntry(5, 5));
        sym1Entries.add(new BarEntry(6, 5));
        sym1Entries.add(new BarEntry(7, 1));
        sym1Entries.add(new BarEntry(8, 2));
        sym1Entries.add(new BarEntry(9, 2));
        sym1Entries.add(new BarEntry(10, 3));
        sym1Entries.add(new BarEntry(11, 4));
        sym1Entries.add(new BarEntry(12, 5));
        sym1Entries.add(new BarEntry(13, 4));
        sym1Entries.add(new BarEntry(14, 2));
        sym1Entries.add(new BarEntry(15, 1));
        sym1Entries.add(new BarEntry(16, 2));
        sym1Entries.add(new BarEntry(17, 2));
        sym1Entries.add(new BarEntry(18, 3));
        sym1Entries.add(new BarEntry(19, 5));
        sym1Entries.add(new BarEntry(20,1));
        sym1Entries.add(new BarEntry(21, 1));
        sym1Entries.add(new BarEntry(22, 4));
        sym1Entries.add(new BarEntry(23, 3));
        sym1Entries.add(new BarEntry(24, 4));
        sym1Entries.add(new BarEntry(25, 5));
        sym1Entries.add(new BarEntry(26, 5));
        sym1Entries.add(new BarEntry(27, 1));
        sym1Entries.add(new BarEntry(28, 2));
        sym1Entries.add(new BarEntry(29, 3));

        sym2Entries.add(new BarEntry(0, 3));
        sym2Entries.add(new BarEntry(1, 3));
        sym2Entries.add(new BarEntry(2, 5));
        sym2Entries.add(new BarEntry(3, 1));
        sym2Entries.add(new BarEntry(4, 4));
        sym2Entries.add(new BarEntry(5, 2));
        sym2Entries.add(new BarEntry(6, 2));
        sym2Entries.add(new BarEntry(7, 3));
        sym2Entries.add(new BarEntry(8, 4));
        sym2Entries.add(new BarEntry(9, 5));
        sym2Entries.add(new BarEntry(10, 1));
        sym2Entries.add(new BarEntry(11, 1));
        sym2Entries.add(new BarEntry(12, 3));
        sym2Entries.add(new BarEntry(13, 2));
        sym2Entries.add(new BarEntry(14, 5));
        sym2Entries.add(new BarEntry(15, 1));
        sym2Entries.add(new BarEntry(16, 2));
        sym2Entries.add(new BarEntry(17, 5));
        sym2Entries.add(new BarEntry(18, 3));
        sym2Entries.add(new BarEntry(19, 3));
        sym2Entries.add(new BarEntry(20,3));
        sym2Entries.add(new BarEntry(21, 2));
        sym2Entries.add(new BarEntry(22, 1));
        sym2Entries.add(new BarEntry(23, 2));
        sym2Entries.add(new BarEntry(24, 1));
        sym2Entries.add(new BarEntry(25, 2));
        sym2Entries.add(new BarEntry(26, 2));
        sym2Entries.add(new BarEntry(27, 5));
        sym2Entries.add(new BarEntry(28, 4));
        sym2Entries.add(new BarEntry(29, 4));

        sym3Entries.add(new BarEntry(0, 4));
        sym3Entries.add(new BarEntry(1, 4));
        sym3Entries.add(new BarEntry(2, 1));
        sym3Entries.add(new BarEntry(3, 2));
        sym3Entries.add(new BarEntry(4, 5));
        sym3Entries.add(new BarEntry(5, 3));
        sym3Entries.add(new BarEntry(6, 3));
        sym3Entries.add(new BarEntry(7, 4));
        sym3Entries.add(new BarEntry(8, 1));
        sym3Entries.add(new BarEntry(9, 1));
        sym3Entries.add(new BarEntry(10, 1));
        sym3Entries.add(new BarEntry(11, 2));
        sym3Entries.add(new BarEntry(12, 5));
        sym3Entries.add(new BarEntry(13, 4));
        sym3Entries.add(new BarEntry(14, 5));
        sym3Entries.add(new BarEntry(15, 5));
        sym3Entries.add(new BarEntry(16, 4));
        sym3Entries.add(new BarEntry(17, 2));
        sym3Entries.add(new BarEntry(18, 3));
        sym3Entries.add(new BarEntry(19, 1));
        sym3Entries.add(new BarEntry(20,1));
        sym3Entries.add(new BarEntry(21, 4));
        sym3Entries.add(new BarEntry(22, 4));
        sym3Entries.add(new BarEntry(23, 3));
        sym3Entries.add(new BarEntry(24, 5));
        sym3Entries.add(new BarEntry(25, 5));
        sym3Entries.add(new BarEntry(26, 1));
        sym3Entries.add(new BarEntry(27, 2));
        sym3Entries.add(new BarEntry(28, 2));
        sym3Entries.add(new BarEntry(29, 3));

        BarDataSet barDataSet = new BarDataSet(barEntries, "ADL Concentration");
        MyBarDataSet sym1DataSet = new MyBarDataSet(sym1Entries, "");
        MyBarDataSet sym2DataSet = new MyBarDataSet(sym2Entries, "");
        MyBarDataSet sym3DataSet = new MyBarDataSet(sym3Entries, "");
        /// might change
        barDataSet.setColors(0xB2182D57);
        // for now this color look video to change
        //sym1DataSet.setColors(0xFF182D57);
        sym1DataSet.setColors(new int[]{ContextCompat.getColor(this, R.color.Blue40),
                ContextCompat.getColor(this, R.color.Blue60),
                ContextCompat.getColor(this, R.color.Blue80), ContextCompat.getColor(this,
                R.color.MainBlue), ContextCompat.getColor(this, R.color.Blue20)});

        sym2DataSet.setColors(new int[]{ContextCompat.getColor(this, R.color.Blue40),
                ContextCompat.getColor(this, R.color.Blue60),
                ContextCompat.getColor(this, R.color.Blue80), ContextCompat.getColor(this,
                R.color.MainBlue), ContextCompat.getColor(this, R.color.Blue20)});

        sym3DataSet.setColors(new int[]{ContextCompat.getColor(this, R.color.Blue40),
                ContextCompat.getColor(this, R.color.Blue60),
                ContextCompat.getColor(this, R.color.Blue80), ContextCompat.getColor(this,
                R.color.MainBlue), ContextCompat.getColor(this, R.color.Blue20)});


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
        sym1BarChart.invalidate();
        sym2BarChart.setFitBars(true);
        sym2BarChart.invalidate();
        sym3BarChart.setFitBars(true);
        sym3BarChart.invalidate();

        CustomBarChartRender barChartRender = new CustomBarChartRender(concBarchart,concBarchart.getAnimator(), concBarchart.getViewPortHandler());
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

        String[] days = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
        "23", "24", "25", "26", "27", "28", "29", "30" };

        XAxis sym1XAxis = sym1BarChart.getXAxis();
        XAxis sym2XAxis = sym2BarChart.getXAxis();
        XAxis xAxis = concBarchart.getXAxis();
        XAxis sym3XAxis = sym3BarChart.getXAxis();

        xAxis.setValueFormatter(new MyXaxisValueFormatter(days));
        sym1XAxis.setValueFormatter(new MyXaxisValueFormatter2(days));
        sym3XAxis.setValueFormatter(new MyXaxisValueFormatter(days));
        sym2XAxis.setValueFormatter(new MyXaxisValueFormatter2(days));

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        sym1XAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        sym3XAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        sym2XAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setGranularity(1);
        sym1XAxis.setGranularity(1);
        sym3XAxis.setGranularity(1);
        sym2XAxis.setGranularity(1);

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

}
