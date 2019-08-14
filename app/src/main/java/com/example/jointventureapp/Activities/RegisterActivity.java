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
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;
import org.json.JSONException;
import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity {

    /// Initialising Socket
    private Socket mSocket;
    Button signup;
    EditText name;
    EditText rusername;
    EditText password1;
    EditText password2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        AnaApplication app = (AnaApplication) getApplication();
        mSocket = app.getSocket();
        mSocket.on("auth_signup", onNewBoolean2);

        /// Defining objects
        signup = findViewById(R.id.SignupButton);
        name = findViewById(R.id.NameRegisterText);
        rusername = findViewById(R.id.LoginTextRegister);
        password1 = findViewById(R.id.PasswordTextRegister);
        password2 = findViewById(R.id.RepeatPasswordTextRegister);


        /// Signup button OnClick
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Getting username and password from EditTexts
                String vname = name.getText().toString().trim();
                String vuser = rusername.getText().toString().trim();
                String passw1 = password1.getText().toString().trim();
                String passw2 = password2.getText().toString().trim();

                ///Checking if passwords match, send toast if they don't
                if (!passw1.equals(passw2)) {
                    Toast incorrectpassword = Toast.makeText(getApplicationContext(), "The Passwords don't match", Toast.LENGTH_LONG);
                    incorrectpassword.show();
                    return;
                }


                /// If one of the two fields is empty warn the user
                if (TextUtils.isEmpty(vuser) || (TextUtils.isEmpty(passw1)) || (TextUtils.isEmpty(vname)))
                {
                    Toast missingfield = Toast.makeText(getApplicationContext(), "Missing username or password", Toast.LENGTH_SHORT);
                    missingfield.show();
                    return;
                }




                /// Converting the strings into JsonObject and sending it through socket
                try {
                    JSONObject signupinfo = new JSONObject();
                    signupinfo.put("name", vname);
                    signupinfo.put("email", vuser);
                    signupinfo.put("password", passw1);
                    PreferenceUtils.saveEmail(vuser, getApplicationContext());
                    PreferenceUtils.savePassword(passw1, getApplicationContext());
                    mSocket.emit("json", signupinfo);

                    /// Page navigation
                    PreferenceUtils.saveSymptom1(true , getApplicationContext());
                    PreferenceUtils.saveSymptom2(true , getApplicationContext());
                    PreferenceUtils.saveSymptom3(true , getApplicationContext());
                    PreferenceUtils.saveSymptom4(true , getApplicationContext());
                    PreferenceUtils.saveSymptom5(true , getApplicationContext());
                    PreferenceUtils.saveFirstTime(true, getApplicationContext());
                    PreferenceUtils.saveSymptomCount(5, getApplicationContext());
                    Intent i = new Intent(RegisterActivity.this, CalendarActivity.class);
                    startActivity(i);

                    overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);

                } catch (JSONException e) {
                    Log.e("MYAPP", "unexpected JSON exception", e);
                    throw new RuntimeException(e);
                }
            }
        });

        /// Instagram Facebook About us buttons
        TextView facebookreg = findViewById(R.id.facebookiconreg);
        TextView aboutusreg = findViewById(R.id.aboutusiconreg);
        TextView instagramreg = findViewById(R.id.instagramiconreg);

        /// Facebook button listener
        facebookreg.setOnClickListener(new View.OnClickListener() {
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
        aboutusreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, AboutUs.class);
                startActivity(i);
            }
        });
        /// Instagram button listener
        instagramreg.setOnClickListener(new View.OnClickListener() {
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

    /// When I press back on register I want to go to login
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slideindown, R.anim.slideoutdown);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.off("auth_signup", onNewBoolean2);
        //mSocket.disconnect();
    }

    Emitter.Listener onNewBoolean2 = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    Integer auth;
                    try {
                        auth = data.getInt("error_signup");

                        /// Checking the value of the boolean
                        if (auth == 00){
                            rusername.setText("");
                            password1.setText("");
                            password2.setText("");
                            name.setText("");
                            /// Page navigation
                            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);

                        }

                        else if (auth == 01) {
                            rusername.setText("");
                            password1.setText("");
                            password2.setText("");
                            name.setText("");
                            Toast wrong = Toast.makeText(getApplicationContext(), "This email is already registered", Toast.LENGTH_LONG);
                            wrong.show();
                        }

                        else if (auth == 05) {
                            rusername.setText("");
                            password1.setText("");
                            password2.setText("");
                            name.setText("");
                            Toast wrong = Toast.makeText(getApplicationContext(), "The email is is not valid", Toast.LENGTH_LONG);
                            wrong.show();
                        }

                        else if (auth == 04) {
                            rusername.setText("");
                            password1.setText("");
                            password2.setText("");
                            name.setText("");
                            Toast wrong = Toast.makeText(getApplicationContext(), "Server error, try again", Toast.LENGTH_LONG);
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
}
