package com.example.jointventureapp.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.jointventureapp.R;
import com.example.jointventureapp.Utils.PreferenceUtils;

public class DialogPage extends DialogFragment {

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
                    }
                });

        return builder.create();
    }

    /// Clicking outside edit text removes focus from edit text ///
}
