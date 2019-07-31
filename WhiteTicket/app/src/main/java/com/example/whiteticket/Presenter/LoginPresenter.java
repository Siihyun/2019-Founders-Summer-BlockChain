package com.example.whiteticket.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.whiteticket.Activity.MainActivity;
import com.example.whiteticket.Data.Login;
import com.example.whiteticket.Data.Setting;
import com.example.whiteticket.Data.User;
import com.example.whiteticket.Interface.LoginInterface;
import com.example.whiteticket.Module.HttpAsyncTask;
import com.example.whiteticket.Module.MyCallBack;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import static android.content.ContentValues.TAG;


public class LoginPresenter implements LoginInterface.Presenter {

    private LoginInterface.View view;
    private Context context;
    private Activity activity;
    private Login login;

    public LoginPresenter(LoginInterface.View view, Context context, Activity activity){
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
    public void Login(final String id, final String password){

        // Model통해 데이터 통신
        /* 로그인 처리를 해준다. */
        Log.d(Setting.Tag,"로그인 시도");

        new HttpAsyncTask("POST", "android",
                new User(id , password).getJsonObject(),
                null, new TypeToken<JsonObject>() {
        }.getType(),
                new MyCallBack() {
                    @Override
                    public void doTask(Object resultBody) {
                        JsonObject result = (JsonObject)resultBody;
                        String getPw = result.get("pw").toString();
                        getPw = getPw.replaceAll("\"" , "");
                        System.out.println("in doTask : " + result);
                        System.out.println(password + " , " + getPw);
                        if(password.equals(getPw)){
                            Log.d(TAG, "doTask: " + result);
                            login.saveId(id); // id 를 클라이언트에 저장
                            login.saveAutoLogin(true); // 자동로그인 저장
                            System.out.println("pass login!");
                            Intent intent = new Intent(context, MainActivity.class);
                            activity.startActivity(intent);
                            activity.finish();
                        }
                    }
                }).execute();

    }

}
