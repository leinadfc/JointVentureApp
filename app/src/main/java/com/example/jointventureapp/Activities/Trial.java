package com.example.jointventureapp.Activities;

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

public class Trial extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ListView listView;
    private Spinner yearSpinner;
    private Spinner monthSpinner;
    BottomNavigationView botNavView;
    ImageView dialogbtn;

    /// Just dummy data for the listview
    String mDay[] = {"19", "20", "21", "22", "23", "24", "24", "24", "24", "24", "24", "24", "24", "24"};
    String mMonth[] = {"JUL", "AUG", "SEP", "OCT", "NOV", "DEC", "DEC", "DEC", "DEC", "DEC", "DEC", "DEC", "DEC", "DEC"};
    String mConc[] = {"20", "30", "40", "50", "60", "70", "70", "70", "70", "70", "70", "70", "70", "70"};
    int mProg1[] = {5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3 };
    int mProg2[] = {5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3};
    int mProg3[] = {5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        monthSpinner = findViewById(R.id.mspinner);
        yearSpinner = findViewById(R.id.yspinner);

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
                        Intent i = new Intent(Trial.this, AddActivity.class);
                        startActivity(i);
                        break;
                    case R.id.calpg:
                        break;


                    case R.id.graphpg:
                        Intent ii = new Intent(Trial.this, GraphsActivity.class);
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


        /// List View sections
        listView = findViewById(R.id.calendarlist);

        Trial.MyAdapter adapter = new Trial.MyAdapter(this, mDay, mMonth, mConc, mProg1, mProg2, mProg3);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                openDialogList();
            }
        });

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
    }

    /// When I press back on register I want to go to login
   /* @Override
    public void onBackPressed() {
        ;
    }*/

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

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rDay[];
        String rMonth[];
        String rConc[];
        int rProg1[];
        int rProg2[];
        int rProg3[];

        MyAdapter (Context c, String day[], String month[],String conc[], int prog1[], int prog2[], int prog3[]){
            super(c, R.layout.calendar_row, R.id.dayitem, day);
            this.context = c;
            this.rDay = day;
            this.rMonth = month;
            this.rConc = conc;
            this.rProg1 = prog1;
            this.rProg2 = prog2;
            this.rProg3 = prog3;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.calendar_row, parent, false);
            TextView dday = row.findViewById(R.id.dayitem);
            TextView dmonth = row.findViewById(R.id.monthitem);
            TextView dconc = row.findViewById(R.id.concentrationitem);
            ProgressBar progbar1 = row.findViewById(R.id.progress_bar_1);
            ProgressBar progbar2 = row.findViewById(R.id.progress_bar_2);
            ProgressBar progbar3 = row.findViewById(R.id.progress_bar_3);

            dday.setText(rDay[position]);
            dmonth.setText(rMonth[position]);
            dconc.setText(rConc[position]);
            progbar1.setProgress(rProg1[position]);
            progbar2.setProgress(rProg2[position]);
            progbar3.setProgress(rProg3[position]);


            return row;
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

    public void openDialog(){
        DialogPage dialogPage = new DialogPage();
        dialogPage.show(getSupportFragmentManager(), "Symptoms");
    }

    public void openDialogList(){
        DialogList dialogList = new DialogList();
        dialogList.show(getSupportFragmentManager(), "Extended list item");
    }


}