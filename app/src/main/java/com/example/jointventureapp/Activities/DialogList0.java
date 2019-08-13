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
import com.example.jointventureapp.Models.CalendarRow0;
import com.example.jointventureapp.R;

import org.w3c.dom.Text;

public class DialogList0 extends DialogFragment {


    private TextView concentration;
    private CalendarRow0 calendarRow;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.list_dialog0, null);

        concentration = view.findViewById(R.id.dialog_concentration_item);
        Bundle arguments = getArguments();
        String day;
        String month;
        String year;

        if (arguments !=  null && arguments.containsKey("selected_day")){

            calendarRow = getArguments().getParcelable("selected_day");
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
