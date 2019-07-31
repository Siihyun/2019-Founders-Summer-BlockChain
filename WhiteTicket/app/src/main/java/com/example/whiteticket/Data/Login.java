package com.example.whiteticket.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.whiteticket.Module.HttpAsyncTask;
import com.example.whiteticket.Module.MyCallBack;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import static android.content.ContentValues.TAG;


public class Login {
    private Context context;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private boolean flag = false;

    public Login(Context context){
        this.context = context;

        sharedPreferences = context.getSharedPreferences("pref",0);
        editor = sharedPreferences.edit();
    }

    /* 자동로그인을 하는지 가져와주는 메쏘드 */
    public boolean getAutoLogin(){
        boolean getAuto = sharedPreferences.getBoolean("login",false);
        return getAuto;
    }

    /* 로그인 할때 자동로그인하는지 안하는지 저장! */
    public void saveAutoLogin(boolean autoLogin){
        editor.putBoolean("login",autoLogin);
        editor.commit();
    }

    public boolean checkLogin(final String inputId, final String inputPw){
        /* 로그인 처리를 해준다. */
        Log.d(Setting.Tag,"로그인 시도");

        new HttpAsyncTask("POST", "android",
                new User(inputId , inputPw).getJsonObject(),
                null, new TypeToken<JsonObject>() {
        }.getType(),
                new MyCallBack() {
                    @Override
                    public void doTask(Object resultBody) {
                        JsonObject result = (JsonObject)resultBody;
                        String getPw = result.get("pw").toString();
                        getPw = getPw.replaceAll("\"" , "");
                        System.out.println("in doTask : " + result);
                        System.out.println(inputPw + " , " + getPw);
                        if(inputPw.equals(getPw)){
                            flag = true;
                            Log.d(TAG, "doTask: " + result);
                        }
                    }
                }).execute();

        if(flag == true) {
            saveAutoLogin(true); // 로그인 성공할경우 자동로그인을 켜놓는다.
            System.out.println("return true");
            return true; // 임시로 true 해준다. true - 로그인 성공, false - 로그인 실패
        }
        else{
            return false;
        }
    }

    public boolean SignUp(String inpuId, String inputPw){
        /* 회원가입 시도 */
        Log.d(Setting.Tag,"회원가입 시도");

        return true; // 임시로 true 해준다. true - 회원가입 성공, false - 회원가입 실패
    }

    public void saveId(String id){
        Log.d(Setting.Tag,"ID 저장 : "+id);

        editor.putString("UserId",id);
        editor.commit();
    }




}