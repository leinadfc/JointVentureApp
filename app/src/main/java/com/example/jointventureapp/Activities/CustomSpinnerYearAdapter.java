package com.example.jointventureapp.Activities;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jointventureapp.R;

import java.util.ArrayList;
import java.util.List;

public class CustomSpinnerYearAdapter extends ArrayAdapter<CustomSpinnerItems> {

    public CustomSpinnerYearAdapter (@NonNull Context context, ArrayList<CustomSpinnerItems> customList){
        super(context, 0, customList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return ycustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return ycustomView(position, convertView, parent);
    }

    public View ycustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_layout_year, parent, false);
        }
        CustomSpinnerItems items = getItem(position);
        TextView spinnerYear = convertView.findViewById(R.id.spinneryear);

        if (items != null){
            spinnerYear.setText(items.getSpinnertext());
        }

        return convertView;
    }
}
