package com.example.jointventureapp.Activities;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

public class MyBarDataSet extends BarDataSet {

    public MyBarDataSet(List<BarEntry> yVals, String label) {
        super(yVals, label);
    }

    @Override
    public int getColor(int index) {
        if (getEntryForIndex(index).getY() == 1) {
            return mColors.get(4);
        } else if (getEntryForIndex(index).getY() == 2) {
            return mColors.get(0);
        } else if (getEntryForIndex(index).getY() == 3) {
            return mColors.get(1);
        }
        else if (getEntryForIndex(index).getY() == 5) {
            return mColors.get(3);
        }

        else if (getEntryForIndex(index).getY() == 4){
            return mColors.get(2);
        }

        else {// greater or equal than 100 red
            return mColors.get(4);
        }
    }
}
