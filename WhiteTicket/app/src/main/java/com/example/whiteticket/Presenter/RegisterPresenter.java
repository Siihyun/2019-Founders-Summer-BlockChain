package com.example.whiteticket.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.whiteticket.Activity.MainActivity;
import com.example.whiteticket.Data.Login;
import com.example.whiteticket.Interface.RegisterInterface;


public class RegisterPresenter implements RegisterInterface.Presenter {

    private RegisterInterface.View view;
    private Context context;
    private Activity activity;

    private Login login;

    public RegisterPresenter(RegisterInterface.View view, Context context, Activity activity){
        this.view = view;
        this.context = context;
        this.activity = activity;

        login = new Login(context);
    }

    @Override
    public void presenterView() {
        view.setView();
    }

    @Override
    public void SignUp(String id, String pw) {
        boolean checkSignUp = login.SignUp(id,pw);

        if(checkSignUp){
            login.saveId(id); // id 를 클라이언트에 저장
            login.saveAutoLogin(true); // 자동로그인 저장

            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            activity.finish();
        }
    }


}