package com.example.jointventureapp.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jointventureapp.Models.CalendarRow;
import com.example.jointventureapp.R;

import org.w3c.dom.Text;

public class DialogList extends DialogFragment {

    private ProgressBar mProgressBar1;
    private ProgressBar mProgressBar2;
    private ProgressBar mProgressBar3;
    private ProgressBar mProgressBar4;
    private ProgressBar mProgressBar5;
    private TextView symptom1;
    private TextView symptom2;
    private TextView symptom3;
    private TextView symptom4;
    private TextView symptom5;
    private TextView concentration;
    private CalendarRow calendarRow;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.list_dialog, null);



        mProgressBar1 = view.findViewById(R.id.progress_bar_1_dialog);
        mProgressBar2 = view.findViewById(R.id.progress_bar_2_dialog);
        mProgressBar3 = view.findViewById(R.id.progress_bar_3_dialog);
        mProgressBar4 = view.findViewById(R.id.progress_bar_4_dialog);
        mProgressBar5 = view.findViewById(R.id.progress_bar_5_dialog);

        symptom1 = view.findViewById(R.id.dialog_symptom_1);
        symptom2 = view.findViewById(R.id.dialog_symptom_2);
        symptom3 = view.findViewById(R.id.dialog_symptom_3);
        symptom4 = view.findViewById(R.id.dialog_symptom_4);
        symptom5 = view.findViewById(R.id.dialog_symptom_5);

        concentration = view.findViewById(R.id.dialog_concentration_item);
        Bundle arguments = getArguments();
        String day;
        String month;
        String year;

        if (arguments !=  null && arguments.containsKey("selected_day")){

            calendarRow = getArguments().getParcelable("selected_day");
            mProgressBar1.setProgress(calendarRow.getProgress1());
            mProgressBar2.setProgress(calendarRow.getProgress2());
            mProgressBar3.setProgress(calendarRow.getProgress3());
            mProgressBar4.setProgress(calendarRow.getProgress4());
            mProgressBar5.setProgress(calendarRow.getProgress5());

            symptom1.setText(calendarRow.getSymptomText1());
            symptom2.setText(calendarRow.getSymptomText2());
            symptom3.setText(calendarRow.getSymptomText3());
            symptom4.setText(calendarRow.getSymptomText4());
            symptom5.setText(calendarRow.getSymptomText5());

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