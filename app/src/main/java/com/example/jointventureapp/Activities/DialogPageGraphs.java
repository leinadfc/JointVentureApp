package com.example.jointventureapp.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import com.example.jointventureapp.R;
import com.example.jointventureapp.Utils.PreferenceUtils;

public class DialogPageGraphs extends DialogFragment {

    CheckBox jointPain;
    CheckBox movementJoints;
    CheckBox inflammation;
    CheckBox weakness;
    CheckBox fatigue;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.symptoms_dialog, null);

        jointPain = view.findViewById(R.id.joint_pain);
        movementJoints = view.findViewById(R.id.restricted_movement);
        inflammation = view.findViewById(R.id.inflammation);
        weakness = view.findViewById(R.id.weakness);
        fatigue = view.findViewById(R.id.fatigue);

        if (PreferenceUtils.getSymptom1(getContext())){
            jointPain.setChecked(true);
        }
        else {
            jointPain.setChecked(false);
        }
        if (PreferenceUtils.getSymptom2(getContext())){
            movementJoints.setChecked(true);
        }
        else {
            movementJoints.setChecked(false);
        }
        if (PreferenceUtils.getSymptom3(getContext())){
            inflammation.setChecked(true);
        }
        else {
            inflammation.setChecked(false);
        }
        if (PreferenceUtils.getSymptom4(getContext())){
            weakness.setChecked(true);
        }
        else {
            weakness.setChecked(false);
        }
        if (PreferenceUtils.getSymptom5(getContext())){
            fatigue.setChecked(true);
        }
        else {
            fatigue.setChecked(false);
        }

        builder.setView(view).setTitle("Customise your symptoms").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PreferenceUtils.saveSymptom1(jointPain.isChecked(), getContext());
                        PreferenceUtils.saveSymptom2(movementJoints.isChecked(), getContext());
                        PreferenceUtils.saveSymptom3(inflammation.isChecked(), getContext());
                        PreferenceUtils.saveSymptom4(weakness.isChecked(), getContext());
                        PreferenceUtils.saveSymptom5(fatigue.isChecked(), getContext());
                        PreferenceUtils.saveSymptomCount(getCount(), getContext());

                        if (PreferenceUtils.getSymptomCount(getContext()) == 5) {
                            Intent i = new Intent(getActivity(), GraphsActivity5.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getContext()) == 4){
                            Intent i = new Intent(getActivity(), GraphsActivity4.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getContext()) == 3){
                            Intent i = new Intent(getActivity(), GraphsActivity.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getContext()) == 2){
                            Intent i = new Intent(getActivity(), GraphsActivity2.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getContext()) == 1){
                            Intent i = new Intent(getActivity(), GraphsActivity1.class);
                            startActivity(i);
                        }
                        else if (PreferenceUtils.getSymptomCount(getContext()) == 0){
                            Intent i = new Intent(getActivity(), GraphsActivity0.class);
                            startActivity(i);
                        }
                        getActivity().finish();
                    }
                });

        return builder.create();
    }

    private int getCount (){
        int count = 0;
        if (jointPain.isChecked()){
            count++;
        }
        if (movementJoints.isChecked()){
            count++;
        }
        if (fatigue.isChecked()){
            count++;
        }
        if (weakness.isChecked()){
            count++;
        }
        if (inflammation.isChecked()){
            count++;
        }

        return count;
    }
}
