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

public class RegisterPage extends AppCompatActivity {

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
        setContentView(R.layout.register_page);
        mSocket.connect();

        /// Defining objects
        final Button signup = (Button) findViewById(R.id.SignupButton);
        final EditText name = (EditText) findViewById(R.id.NameRegisterText);
        final EditText rusername = (EditText) findViewById(R.id.LoginTextRegister);
        final EditText password1 = (EditText) findViewById(R.id.PasswordTextRegister);
        final EditText password2 = (EditText) findViewById(R.id.RepeatPasswordTextRegister);


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

                rusername.setText("");
                password1.setText("");
                password2.setText("");
                name.setText("");


                /// Converting the strings into JsonObject and sending it through socket
                try {
                    JSONObject signupinfo = new JSONObject();
                    signupinfo.put("name", vname);
                    signupinfo.put("username", vuser);
                    signupinfo.put("password", passw1);

                    mSocket.emit("json", signupinfo);
                } catch (JSONException e) {
                    Log.e("MYAPP", "unexpected JSON exception", e);
                    throw new RuntimeException(e);
                }



                /// Page navigation
                Intent i = new Intent(RegisterPage.this, MainPage.class);
                // set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);

            }
        });
    }

    /// When I press back on register I want to go to login
    @Override
    public void onBackPressed() {
        Intent i = new Intent(RegisterPage.this, MainActivity.class);
        // set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}

