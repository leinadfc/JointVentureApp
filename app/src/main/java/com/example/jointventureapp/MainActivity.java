package com.example.jointventureapp;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;


import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.w3c.dom.Text;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    /// Initialising Socket
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://1c033cd3.ngrok.io");
        } catch (URISyntaxException e) { throw new RuntimeException(e);}
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSocket.connect();

        final Button loginButton = (Button) findViewById(R.id.LoginButton);
        final EditText username = (EditText) findViewById(R.id.Logintext);
        final EditText password = (EditText) findViewById(R.id.Passwordtext);

        /// Login Button click listener function ///
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                /// Getting username and password from EditTexts
                String user = username.getText().toString().trim();
                String passw = password.getText().toString().trim();

                /// If one of the two fields is empty warn the user
                if (TextUtils.isEmpty(user) || (TextUtils.isEmpty(passw)))
                {
                    Toast missingfield = Toast.makeText(getApplicationContext(), "Missing username or password", Toast.LENGTH_SHORT);
                    missingfield.show();
                    return;
                }

                username.setText("");
                password.setText("");


                /// Converting the strings into JsonObject and sending it through socket
                try {
                    JSONObject logininfo = new JSONObject();
                    logininfo.put("name", "");
                    logininfo.put("username", user);
                    logininfo.put("password", passw);

                    mSocket.emit("json", logininfo);
                } catch (JSONException e) {
                    Log.e("MYAPP", "unexpected JSON exception", e);
                    throw new RuntimeException(e);
                }



                /// Page navigation
                Intent i = new Intent(MainActivity.this, MainPage.class);
                // set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        /// Register TextView takes to Register Page ///
        TextView registerText = (TextView) findViewById(R.id.RegisterButton);
        registerText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, RegisterPage.class);
                // set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }

        });

        /// Facebook icon working ///
        TextView facebooktext = (TextView) findViewById(R.id.facebookicon);
        facebooktext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Uri uriUrl = Uri.parse("https://www.facebook.com/JointVenture2019/?ref=br_rs");
                Intent launchbrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchbrowser);*/
               Intent i = newFacebookIntent(getPackageManager(), "https://www.facebook.com/JointVenture2019/?ref=br_rs");
               startActivity(i);
            }
        });

        /// About us icon working ///
        TextView infoicon = (TextView) findViewById(R.id.aboutusicon);
        infoicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AboutUs.class);
                // set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        /// Instagram icon workin ///
        TextView instagramtext = (TextView) findViewById(R.id.instagramicon);
        instagramtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = newInstagramProfileIntent(getPackageManager(), "https://instagram.com/jointventure2019?igshid=6ijub1dzxhou");
                startActivity(in);
            }
        });

    }

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
