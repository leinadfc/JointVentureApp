package com.example.jointventureapp.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.jointventureapp.Utils.PreferenceUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;

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
    private TextView comments;

    private ArrayList<String> symptomNames = new ArrayList<>();

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
        comments = view.findViewById(R.id.comments_text);

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
            getSymptomTextArray();


            concentration.setText(calendarRow.getConcentration());

            if (calendarRow.getSymptomText1().equals(symptomNames.get(0))){
                symptom1.setText(symptomNames.get(0));
                mProgressBar1.setProgress(calendarRow.getProgress1());
            }
            else if (calendarRow.getSymptomText2().equals(symptomNames.get(0))){
                symptom1.setText(symptomNames.get(0));
                mProgressBar1.setProgress(calendarRow.getProgress2());
            }
            else if (calendarRow.getSymptomText3().equals(symptomNames.get(0))){
                symptom1.setText(symptomNames.get(0));
                mProgressBar1.setProgress(calendarRow.getProgress3());
            }
            else if (calendarRow.getSymptomText4().equals(symptomNames.get(0))){
                symptom1.setText(symptomNames.get(0));
                mProgressBar1.setProgress(calendarRow.getProgress4());
            }
            else if (calendarRow.getSymptomText5().equals(symptomNames.get(0))){
                symptom1.setText(symptomNames.get(0));
                mProgressBar1.setProgress(calendarRow.getProgress5());
            }
            else {
                symptom1.setText(symptomNames.get(0));
                mProgressBar1.setProgress(0);
            }


            if (calendarRow.getSymptomText1().equals(symptomNames.get(1))){
                symptom2.setText(symptomNames.get(1));
                mProgressBar2.setProgress(calendarRow.getProgress1());
            }
            else if (calendarRow.getSymptomText2().equals(symptomNames.get(1))){
                symptom2.setText(symptomNames.get(1));
                mProgressBar2.setProgress(calendarRow.getProgress2());
            }
            else if (calendarRow.getSymptomText3().equals(symptomNames.get(1))){
                symptom2.setText(symptomNames.get(1));
                mProgressBar2.setProgress(calendarRow.getProgress3());
            }
            else if (calendarRow.getSymptomText4().equals(symptomNames.get(1))){
                symptom2.setText(symptomNames.get(1));
                mProgressBar2.setProgress(calendarRow.getProgress4());
            }
            else if (calendarRow.getSymptomText5().equals(symptomNames.get(1))){
                symptom2.setText(symptomNames.get(1));
                mProgressBar2.setProgress(calendarRow.getProgress5());
            }
            else {
                symptom2.setText(symptomNames.get(1));
                mProgressBar2.setProgress(0);
            }

            if (calendarRow.getSymptomText1().equals(symptomNames.get(2))){
                symptom3.setText(symptomNames.get(2));
                mProgressBar3.setProgress(calendarRow.getProgress1());
            }
            else if (calendarRow.getSymptomText2().equals(symptomNames.get(2))){
                symptom3.setText(symptomNames.get(2));
                mProgressBar3.setProgress(calendarRow.getProgress2());
            }
            else if (calendarRow.getSymptomText3().equals(symptomNames.get(2))){
                symptom3.setText(symptomNames.get(2));
                mProgressBar3.setProgress(calendarRow.getProgress3());
            }
            else if (calendarRow.getSymptomText4().equals(symptomNames.get(2))){
                symptom3.setText(symptomNames.get(2));
                mProgressBar3.setProgress(calendarRow.getProgress4());
            }
            else if (calendarRow.getSymptomText5().equals(symptomNames.get(2))){
                symptom3.setText(symptomNames.get(2));
                mProgressBar3.setProgress(calendarRow.getProgress5());
            }
            else {
                symptom3.setText(symptomNames.get(2));
                mProgressBar3.setProgress(0);
            }

            if (calendarRow.getSymptomText1().equals(symptomNames.get(3))){
                symptom4.setText(symptomNames.get(3));
                mProgressBar4.setProgress(calendarRow.getProgress1());
            }
            else if (calendarRow.getSymptomText2().equals(symptomNames.get(3))){
                symptom4.setText(symptomNames.get(3));
                mProgressBar4.setProgress(calendarRow.getProgress2());
            }
            else if (calendarRow.getSymptomText3().equals(symptomNames.get(3))){
                symptom4.setText(symptomNames.get(3));
                mProgressBar4.setProgress(calendarRow.getProgress3());
            }
            else if (calendarRow.getSymptomText4().equals(symptomNames.get(3))){
                symptom4.setText(symptomNames.get(3));
                mProgressBar4.setProgress(calendarRow.getProgress4());
            }
            else if (calendarRow.getSymptomText5().equals(symptomNames.get(3))){
                symptom4.setText(symptomNames.get(3));
                mProgressBar4.setProgress(calendarRow.getProgress5());
            }
            else {
                symptom4.setText(symptomNames.get(3));
                mProgressBar4.setProgress(0);
            }

            if (calendarRow.getSymptomText1().equals(symptomNames.get(4))){
                symptom5.setText(symptomNames.get(4));
                mProgressBar5.setProgress(calendarRow.getProgress1());
            }
            else if (calendarRow.getSymptomText2().equals(symptomNames.get(4))){
                symptom5.setText(symptomNames.get(4));
                mProgressBar5.setProgress(calendarRow.getProgress2());
            }
            else if (calendarRow.getSymptomText3().equals(symptomNames.get(4))){
                symptom5.setText(symptomNames.get(4));
                mProgressBar5.setProgress(calendarRow.getProgress3());
            }
            else if (calendarRow.getSymptomText4().equals(symptomNames.get(4))){
                symptom5.setText(symptomNames.get(4));
                mProgressBar5.setProgress(calendarRow.getProgress4());
            }
            else if (calendarRow.getSymptomText5().equals(symptomNames.get(4))){
                symptom5.setText(symptomNames.get(4));
                mProgressBar5.setProgress(calendarRow.getProgress5());
            }
            else {
                symptom5.setText(symptomNames.get(4));
                mProgressBar5.setProgress(0);
            }


            day = calendarRow.getDay();
            month = calendarRow.getMonth();
            year = calendarRow.getYear();
            if (calendarRow.getComments() != null) {
                comments.setText(calendarRow.getComments());
            }
            else {
                comments.setText("");
            }


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
                Intent intent = new Intent (getActivity(), UpdateActivity5.class);
                intent.putExtra("selected_day", calendarRow);
                startActivity(intent);
            }
        });

        return builder.create();
    }

    private void getSymptomTextArray (){
        symptomNames.clear();
        if (PreferenceUtils.getSymptom1(getContext())){
            symptomNames.add("Joint pain");
        }
        if (PreferenceUtils.getSymptom2(getContext())){
            symptomNames.add("Restricted joint movement");
        }
        if (PreferenceUtils.getSymptom3(getContext())){
            symptomNames.add("Inflammation");
        }
        if (PreferenceUtils.getSymptom4(getContext())){
            symptomNames.add("Weakness");
        }
        if (PreferenceUtils.getSymptom5(getContext())){
            symptomNames.add("Fatigue");
        }
    }
}
