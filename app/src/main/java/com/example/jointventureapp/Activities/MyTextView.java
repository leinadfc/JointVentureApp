package com.example.jointventureapp.Activities;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.EditText;

class MyTextView extends android.support.v7.widget.AppCompatEditText
{

    public MyTextView(Context context) {
        super(context);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode==KeyEvent.KEYCODE_ENTER)
        {
            // Just ignore the [Enter] key
            return true;
        }
        // Handle all other keys in the default way
        return super.onKeyDown(keyCode, event);
    }
}
