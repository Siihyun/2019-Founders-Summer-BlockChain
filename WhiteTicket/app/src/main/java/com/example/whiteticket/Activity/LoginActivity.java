package com.example.whiteticket.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whiteticket.Data.User;
import com.example.whiteticket.Module.HttpAsyncTask;
import com.example.whiteticket.Module.MyCallBack;
import com.example.whiteticket.Module.ResultBody;
import com.example.whiteticket.R;
import com.google.gson.reflect.TypeToken;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText id , pw;
    Button loginButton = null;
    Button singupButton = null;

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        id = findViewById(R.id.login_id);
        pw = findViewById(R.id.login_pw);

        loginButton = findViewById(R.id.login_login);
        loginButton.setOnClickListener(this);

        singupButton = findViewById(R.id.login_login);
        singupButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        System.out.println("button pressed");
        switch (v.getId()){
            case R.id.login_login:
                System.out.println("login pressed");
                logIn();
                break;
            case R.id.login_signup:
                signUp();
                break;
        }
    }

    private void logIn(){

        final String ID = id.getText().toString();
        final String PW = pw.getText().toString();
        new HttpAsyncTask("POST", "",
                new User(ID , PW).getJsonObject(),
                null, new TypeToken<ResultBody<User>>() {
        }.getType(),
                new MyCallBack() {
                    @Override
                    public void doTask(Object resultBody) {
                        System.out.println("make user");
                        System.out.println(ID + PW);
                    }
                }).execute();

        user.setId(ID);
        user.setPw(PW);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    private void signUp(){

    }



}
