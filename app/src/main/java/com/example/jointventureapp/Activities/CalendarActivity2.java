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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jointventureapp.Adapters.CustomSpinnerAdapter;
import com.example.jointventureapp.Adapters.CustomSpinnerYearAdapter;
import com.example.jointventureapp.Adapters.DaysRecyclerAdapter2;
import com.example.jointventureapp.Application.AnaApplication;
import com.example.jointventureapp.Models.CalendarRow;
import com.example.jointventureapp.R;
import com.example.jointventureapp.Utils.PreferenceUtils;
import com.example.jointventureapp.persistence.DayRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CalendarActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DaysRecyclerAdapter2.OnDayListener {

    private RecyclerView recyclerView;
    private Spinner yearSpinner;
    private Spinner monthSpinner;
    private BottomNavigationView botNavView;
    private ImageView dialogbtn;

    private ArrayList<CalendarRow> mCalendarRows = new ArrayList<>();
    private DaysRecyclerAdapter2 mDaysRecyclerAdapter2;
    private DayRepository mDayRepository2;

    private CalendarRow serverCalendarRow;
    private RelativeLayout refreshRelativeLayout;
    private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        AnaApplication app = (AnaApplication) getApplication();

        refreshRelativeLayout = findViewById(R.id.refreshlayout);

        mSocket = app.getSocket();
        mSocket.on("patientData", onNewDays );
        mSocket.connect();
        serverCalendarRow = new CalendarRow();

        try {
            JSONObject request = new JSONObject();
            request.put("email", PreferenceUtils.getEmail(getApplicationContext()));
            Log.d("EMAAAAAAAIL", PreferenceUtils.getEmail(getApplicationContext()));
            mSocket.emit("database2app", request);


        } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
            throw new RuntimeException(e);
        }
        mSocket.emit("database2app", PreferenceUtils.getEmail(getApplicationContext()));

        monthSpinner = findViewById(R.id.mspinner);
        yearSpinner = findViewById(R.id.yspinner);

        mDayRepository2 = new DayRepository(this);

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
                        if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 5) {
                            Intent i = new Intent(CalendarActivity2.this, AddActivity5.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 4){
                            Intent i = new Intent(CalendarActivity2.this, AddActivity4.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 3){
                            Intent i = new Intent(CalendarActivity2.this, AddActivity.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 2){
                            Intent i = new Intent(CalendarActivity2.this, AddActivity2.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 1){
                            Intent i = new Intent(CalendarActivity2.this, AddActivity1.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 0){
                            Intent i = new Intent(CalendarActivity2.this, AddActivity0.class);
                            startActivity(i);
                        }
                        break;
                    case R.id.calpg:
                        break;


                    case R.id.graphpg:
                        if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 5) {
                            Intent i = new Intent(CalendarActivity2.this, GraphsActivity5.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 4){
                            Intent i = new Intent(CalendarActivity2.this, GraphsActivity4.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 3){
                            Intent i = new Intent(CalendarActivity2.this, GraphsActivity.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 2){
                            Intent i = new Intent(CalendarActivity2.this, GraphsActivity2.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 1){
                            Intent i = new Intent(CalendarActivity2.this, GraphsActivity1.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 0){
                            Intent i = new Intent(CalendarActivity2.this, GraphsActivity0.class);
                            startActivity(i);
                        }
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

        refreshRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject request = new JSONObject();
                    request.put("email", PreferenceUtils.getEmail(getApplicationContext()));
                    mSocket.emit("database2app", request);


                } catch (JSONException e) {
                    Log.e("MYAPP", "unexpected JSON exception", e);
                    throw new RuntimeException(e);
                }
            }
        });
    }

    Emitter.Listener onNewDays = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("ENTERED", "ENTEREEEEEED");
                    JSONArray newDaysArray = (JSONArray) args[0];

                    try {
                        Log.d("ENTERED JSON SHIT", "HEEEEEEY");
                        for (int i = 0; i<args.length; i++) {
                            String concentration = newDaysArray.getJSONObject(i).getString("concentration");
                            String day = newDaysArray.getJSONObject(i).getString("day");
                            Log.d("DATAAAAAAAAA", day);
                            String month = newDaysArray.getJSONObject(i).getString("month");
                            String year = newDaysArray.getJSONObject(i).getString("year");


                            serverCalendarRow.setConcentration(concentration);
                            serverCalendarRow.setDay(day);
                            serverCalendarRow.setMonth(month);
                            serverCalendarRow.setYear(year);
                            serverCalendarRow.setProgress1(0);
                            serverCalendarRow.setProgress2(0);
                            serverCalendarRow.setProgress3(0);
                            serverCalendarRow.setProgress4(0);
                            serverCalendarRow.setProgress5(0);
                            serverCalendarRow.setSymptomText1("Joint pain");
                            serverCalendarRow.setSymptomText2("Restricted joint movement");
                            serverCalendarRow.setSymptomText3("Inflammation");
                            serverCalendarRow.setSymptomText4("Weakness");
                            serverCalendarRow.setSymptomText5("Fatigue");
                            serverCalendarRow.setComments("");

                            mDayRepository2.insertDayTask(serverCalendarRow);
                        }
                        mDaysRecyclerAdapter2.notifyDataSetChanged();

                    }

                    catch (JSONException e){
                        Log.d("NOT JSON SHIT", "HEEEEEEY");
                        Toast noserver = Toast.makeText(getApplicationContext(), "No server connection", Toast.LENGTH_LONG);
                        noserver.show();
                    }
                }
            });
        }
    };


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
        if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 5){
            DialogList dialogList = new DialogList();
            dialogList.setArguments(bundle);
            dialogList.show(getSupportFragmentManager(), "Extended list item");
        }
        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 4){
            DialogList4 dialogList = new DialogList4();
            dialogList.setArguments(bundle);
            dialogList.show(getSupportFragmentManager(), "Extended list item");
        }
        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 3){
            DialogList3 dialogList = new DialogList3();
            dialogList.setArguments(bundle);
            dialogList.show(getSupportFragmentManager(), "Extended list item");
        }
        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 2){
            DialogList2 dialogList = new DialogList2();
            dialogList.setArguments(bundle);
            dialogList.show(getSupportFragmentManager(), "Extended list item");
        }
        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 1){
            DialogList1 dialogList = new DialogList1();
            dialogList.setArguments(bundle);
            dialogList.show(getSupportFragmentManager(), "Extended list item");
        }
        else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 0){
            DialogList0 dialogList = new DialogList0();
            dialogList.setArguments(bundle);
            dialogList.show(getSupportFragmentManager(), "Extended list item");
        }
    }

    private void initRecyclerView (){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        //recyclerView.addItemDecoration(itemDecorator);
        mDaysRecyclerAdapter2 = new DaysRecyclerAdapter2(mCalendarRows, this);
        recyclerView.setAdapter(mDaysRecyclerAdapter2);
    }

    @Override
    public void onDayClick(int position) {
        openDialogList(position);
    }

    // need to get month and year from spinners
    private void retrieveDays(String month, String year){
        mDayRepository2.retrieveDaysTask(month, year).observe(this, new Observer<List<CalendarRow>>() {
            @Override
            public void onChanged(@Nullable List<CalendarRow> calendarRows) {
                if (mCalendarRows.size()>0){
                    Log.d("BYEEEEEEEEEE", "HEEEEEEY ");
                    mCalendarRows.clear();
                }
                if (calendarRows != null){
                    Log.d("BYEEEEEEEEEE", "BYEEEEEEEEEE ");
                    mCalendarRows.addAll(calendarRows);
                }
                mDaysRecyclerAdapter2.notifyDataSetChanged();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.off("auth_login", onNewDays);
        //mSocket.disconnect();
    }
}