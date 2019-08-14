package com.example.jointventureapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
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

import com.example.jointventureapp.Application.AnaApplication;
import com.example.jointventureapp.R;
import com.example.jointventureapp.Utils.PreferenceUtils;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;


////////////////////
/// Login Screen ///
////////////////////
public class LoginActivity extends AppCompatActivity {

    private Socket mSocket;
    Button loginButton;
    private EditText username;
    private EditText password;
    TextView registerText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        AnaApplication app = (AnaApplication) getApplication();
        mSocket = app.getSocket();
        mSocket.on("auth_login", onNewBoolean );
        mSocket.connect();

        ////////////////////////////////////////////////////////////////
        /// Checking if the user already has logged in or registered ///
        ////////////////////////////////////////////////////////////////
        if (PreferenceUtils.getEmail(getApplicationContext()) != null && PreferenceUtils.getEmail(getApplicationContext()) != ""){
            if (PreferenceUtils.getPassword(getApplicationContext()) != null && PreferenceUtils.getPassword(getApplicationContext()) != ""){
                if (PreferenceUtils.getSymptomCount(getApplicationContext()) > 2) {
                    Intent i = new Intent(LoginActivity.this, CalendarActivity.class);
                    startActivity(i);
                }
                else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 2){
                    Intent i = new Intent(LoginActivity.this, CalendarActivity2.class);
                    startActivity(i);
                }
                else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 1){
                    Intent i = new Intent(LoginActivity.this, CalendarActivity1.class);
                    startActivity(i);
                }
                else if (PreferenceUtils.getSymptomCount(getApplicationContext()) == 0){
                    Intent i = new Intent(LoginActivity.this, CalendarActivity0.class);
                    startActivity(i);
                }

                finish();


                /*Toast wrong = Toast.makeText(getApplicationContext(), "It would login automatically" , Toast.LENGTH_LONG);
                wrong.show();*/
            }
        }

        /// Login Button click listener function ///
        loginButton = findViewById(R.id.LoginButton);
        username = findViewById(R.id.Logintext);
        password = findViewById(R.id.Passwordtext);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                /// Getting username and password from EditTexts
                String user = username.getText().toString().trim();
                String passw = password.getText().toString().trim();

                attemptLogin(user, passw);


            }
        });

        /// Register TextView takes to Register Page ///
        registerText = findViewById(R.id.RegisterButton);
        registerText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
            }

        });

        /// Facebook icon working ///
        TextView facebooktext = findViewById(R.id.facebookicon);
        facebooktext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = newFacebookIntent(getPackageManager(), "https://www.facebook.com/JointVenture2019/?ref=br_rs");
                startActivity(i);
            }
        });

        /// About us icon working ///
        TextView infoicon = findViewById(R.id.aboutusicon);
        infoicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, AboutUs.class);
                // set the new task and clear flags
                startActivity(i);
            }
        });

        /// Instagram icon workin ///
        TextView instagramtext = findViewById(R.id.instagramicon);
        instagramtext.setOnClickListener(new View.OnClickListener() {
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
    }

    /// Facebook Intent ///
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

    /// Instagram Intent ///
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

    /// Clicking outside edit text removes focus from edit text ///
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    Log.d("focus", "touchevent");
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    /// Socket listener waiting for auth boolean and functionality ///
    Emitter.Listener onNewBoolean = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    Integer auth;
                    try {
                        auth = data.getInt("auth_boolean");

                        /// Checking the value of the boolean
                        if (auth == 00){
                            PreferenceUtils.saveEmail(username.getText().toString().trim(), getApplicationContext());
                            PreferenceUtils.savePassword(password.getText().toString().trim(), getApplicationContext());
                            username.setText("");
                            password.setText("");
                            /// Page navigation
                            PreferenceUtils.saveSymptom1(true , getApplicationContext());
                            PreferenceUtils.saveSymptom2(true , getApplicationContext());
                            PreferenceUtils.saveSymptom3(true , getApplicationContext());
                            PreferenceUtils.saveSymptom4(true , getApplicationContext());
                            PreferenceUtils.saveSymptom5(true , getApplicationContext());
                            PreferenceUtils.saveFirstTime(true, getApplicationContext());
                            PreferenceUtils.saveSymptomCount(5, getApplicationContext());
                            Intent i = new Intent(LoginActivity.this, CalendarActivity.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
                            finish();
                        }

                        else if (auth == 02) {
                            username.setText("");
                            password.setText("");
                            Toast wrong = Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_LONG);
                            wrong.show();
                        }

                    } catch (JSONException e) {
                        Toast noserver = Toast.makeText(getApplicationContext(), "No server connection", Toast.LENGTH_LONG);
                        noserver.show();
                    }

                }
            });
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.off("auth_login", onNewBoolean);
        //mSocket.disconnect();
    }

    private void attemptLogin(String email, String password) {
        if (TextUtils.isEmpty(email) || (TextUtils.isEmpty(password))) {
            Toast missingfield = Toast.makeText(getApplicationContext(), "Missing username or password", Toast.LENGTH_SHORT);
            missingfield.show();
        } else {
            /// Converting the strings into JsonObject and sending it through socket
            try {
                JSONObject logininfo = new JSONObject();
                logininfo.put("name", "");
                logininfo.put("email", email);
                logininfo.put("password", password);

                mSocket.emit("json", logininfo);


            } catch (JSONException e) {
                Log.e("MYAPP", "unexpected JSON exception", e);
                throw new RuntimeException(e);
            }

        }
    }
}

