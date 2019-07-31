package com.example.whiteticket.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.whiteticket.Interface.RegisterInterface;
import com.example.whiteticket.Presenter.RegisterPresenter;
import com.example.whiteticket.R;


public class RegisterActivity extends AppCompatActivity implements RegisterInterface.View,View.OnClickListener{

    private RegisterInterface.Presenter presenter;


    private Button loginBtn;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        presenter = new RegisterPresenter(RegisterActivity.this,getApplicationContext(),this);
        presenter.presenterView();
    }

    @Override
    public void setView() {
        loginBtn = (Button)findViewById(R.id.loginBtn);
        registerBtn = (Button)findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginBtn:
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.registerBtn:
                break;
        }
    }


}
