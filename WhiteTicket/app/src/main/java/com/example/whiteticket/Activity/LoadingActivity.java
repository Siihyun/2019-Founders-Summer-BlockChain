package com.example.whiteticket.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.whiteticket.Data.Login;
import com.example.whiteticket.Data.Setting;
import com.example.whiteticket.R;

/* MANIFEST 에 반드시 먼저 실행하도록 등록해야한다. */
public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        moveActivity();
    }

    private void startLoading(final boolean autoLogin) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = null;
                if(autoLogin){ // true - 자동로그인
                    Log.d(Setting.Tag,"자동로그인");

                    intent = new Intent(getApplicationContext(),MainActivity.class);
                }else{ // false - 로그인 화면으로 이동
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                }

                startActivity(intent);
                finish();
            }
        }, Setting.loadingSecond); // 로딩 시간 세팅 - milliSecond 로 설정된다.
    }

    private void moveActivity() {
        Login login = new Login(getApplicationContext());
        boolean autoLogin = login.getAutoLogin(); // 자동 로그인이 되어있나? 를 확인하고 불러옴.

        startLoading(autoLogin);
    }
}
