package com.example.jointventureapp.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jointventureapp.Models.CalendarRow;
import com.example.jointventureapp.Models.CalendarRow1;
import com.example.jointventureapp.R;

public class DialogList1 extends DialogFragment {

    private ProgressBar mProgressBar1;

    private TextView symptom1;

    private TextView concentration;
    private CalendarRow1 calendarRow;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.list_dialog1, null);



        mProgressBar1 = view.findViewById(R.id.progress_bar_1_dialog);


        symptom1 = view.findViewById(R.id.dialog_symptom_1);


        concentration = view.findViewById(R.id.dialog_concentration_item);
        Bundle arguments = getArguments();
        String day;
        String month;
        String year;

        if (arguments !=  null && arguments.containsKey("selected_day")){

            calendarRow = getArguments().getParcelable("selected_day");
            mProgressBar1.setProgress(calendarRow.getProgress1());


            symptom1.setText(calendarRow.getSymptomText1());


            concentration.setText(calendarRow.getConcentration());

            day = calendarRow.getDay();
            month = calendarRow.getMonth();
            year = calendarRow.getYear();


        }

        else {
            day = "7";
            month = "AUG";
            year = "2020";
        }


        builder.setView(view).setTitle(day+" "+month+" "+year).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        return builder.create();
    }
}
