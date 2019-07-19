package com.example.jointventureapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;

import com.example.jointventureapp.R;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    ///private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /// Instagram Facebook About us buttons
        TextView facebookmain = findViewById(R.id.facebookiconmain);
        TextView aboutusmain = findViewById(R.id.aboutusiconmain);
        TextView instagrammain = findViewById(R.id.instagramiconmain);

        /// Facebook button listener
        facebookmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Uri uriUrl = Uri.parse("https://www.facebook.com/JointVenture2019/?ref=br_rs");
                Intent launchbrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchbrowser);*/
                Intent i = newFacebookIntent(getPackageManager(), "https://www.facebook.com/JointVenture2019/?ref=br_rs");
                startActivity(i);
            }
        });

        /// About us button listener
        aboutusmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AboutUs.class);
                startActivity(i);
            }
        });
        /// Instagram button listener
        instagrammain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = newInstagramProfileIntent(getPackageManager(), "https://instagram.com/jointventure2019?igshid=6ijub1dzxhou");
                startActivity(in);
            }
        });


        /// Status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        Button addbtn = findViewById(R.id.addconcentration);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, AddActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
            }
        });

        Button calbtn = findViewById(R.id.calendar);
        calbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
            }
        });

        Button graphbtn = findViewById(R.id.graphs);
        calbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, GraphsActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
            }
        });

    }

    /// When I press back on register I want to go to login
    @Override
    public void onBackPressed() {
        ;
    }

    /// Facebook intent ///
    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                // http://stackoverflow.com/a/24547437/1048340
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    /// Instagram intent ///
    public static Intent newInstagramProfileIntent(PackageManager pm, String url) {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            if (pm.getPackageInfo("com.instagram.android", 0) != null) {
                if (url.endsWith("/")) {
                    url = url.substring(0, url.length() - 1);
                }
                final String username = url.substring(url.lastIndexOf("/") + 1);
                // http://stackoverflow.com/questions/21505941/intent-to-open-instagram-user-profile-on-android
                intent.setData(Uri.parse("http://instagram.com/_u/" + username));
                intent.setPackage("com.instagram.android");
                return intent;
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        intent.setData(Uri.parse(url));
        return intent;
    }

}




