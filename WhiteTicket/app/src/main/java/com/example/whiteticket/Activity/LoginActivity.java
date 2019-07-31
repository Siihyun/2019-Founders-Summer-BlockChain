package com.example.whiteticket.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.whiteticket.Data.User;
import com.example.whiteticket.Interface.LoginInterface;
import com.example.whiteticket.Presenter.LoginPresenter;
import com.example.whiteticket.R;


public class LoginActivity extends AppCompatActivity implements LoginInterface.View, View.OnClickListener {


    private LoginInterface.Presenter presenter;

    private Button loginBtn;
    private Button registerBtn;

    private EditText idEdit;
    private EditText passwordEdit;
    private Button login;

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(LoginActivity.this,getApplicationContext(),this);
        presenter.presenterView();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.loginBtn:
                break;
            case R.id.registerBtn:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.login_login:
                String inputId = idEdit.getText().toString();
                String inputPw = passwordEdit.getText().toString();

                presenter.Login(inputId, inputPw);
                break;
        }
    }

//    private void logIn(){
//
//        final String ID = id.getText().toString();
//        final String PW = pw.getText().toString();
//        new HttpAsyncTask("POST", "",
//                new User(ID , PW).getJsonObject(),
//                null, new TypeToken<ResultBody<User>>() {
//        }.getType(),
//                new MyCallBack() {
//                    @Override
//                    public void doTask(Object resultBody) {
//                        System.out.println("make user");
//                        System.out.println(ID + PW);
//                    }
//                }).execute();
//
//        user.setId(ID);
//        user.setPw(PW);
//
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("User", user);
//        startActivity(intent);
//    }
//
//    private void signUp(){
//
//    }


    @Override
    public void setView() {
        loginBtn = (Button)findViewById(R.id.loginBtn);
        registerBtn = (Button)findViewById(R.id.registerBtn);
        idEdit = (EditText)findViewById(R.id.idEdit);
        passwordEdit = (EditText)findViewById(R.id.passwordEdit);
        login = (Button)findViewById(R.id.login_login);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        login.setOnClickListener(this);
    }
}
