package com.example.jointventureapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jointventureapp.R;

import org.w3c.dom.Text;

import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    ListView listView;

    /// Just dummy data
    String mDay[] = {"19", "20", "21", "22", "23"};
    String mMonth[] = {"July/2019", "August/2020", "September/2021", "October/2022", "November/2023"};
    String mConc[] = {"20 mol/L", "30 mol/L", "40 mol/L", "50 mol/L", "60 mol/L"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        listView = findViewById(R.id.calendarlist);

        MyAdapter adapter = new MyAdapter(this, mDay, mMonth, mConc);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                if (position == 0) {
                    /// do whatever same for other positions
                    /// Potentially do the symptoms
                }
            }
        });

        /// Status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    /// When I press back on register I want to go to login
    @Override
    public void onBackPressed() {
        Intent i = new Intent(CalendarActivity.this, MainActivity.class);
        startActivity(i);
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

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rDay[];
        String rMonth[];
        String rConc[];

        MyAdapter (Context c, String day[], String month[], String conc[]){
            super(c, R.layout.calendar_row, R.id.dayitem, day);
            this.context = c;
            this.rDay = day;
            this.rMonth = month;
            this.rConc = conc;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.calendar_row, parent, false);
            TextView dday = row.findViewById(R.id.dayitem);
            TextView dmonth = row.findViewById(R.id.monthitem);
            TextView dconc = row.findViewById(R.id.concentrationitem);

            dday.setText(rDay[position]);
            dmonth.setText(rMonth[position]);
            dconc.setText(rConc[position]);

            return row;
        }
    }
}