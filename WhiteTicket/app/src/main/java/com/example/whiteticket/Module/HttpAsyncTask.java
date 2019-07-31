package com.example.whiteticket.Module;

import android.os.AsyncTask;


import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class HttpAsyncTask extends AsyncTask<Void, Void, JsonObject> implements JsonDeserializer {
    private String url = "http://10.16.134.113:3000/";
    private String action;
    private String path;
    private Type typeToken;
    private MyCallBack callback;
    private JSONObject requestBodyJson;
    private File file;


    public HttpAsyncTask(String action, String path, JSONObject requestBodyJson, File file ,Type typeToken, MyCallBack callback) {
        this.action = action;
        this.path = path;
        this.requestBodyJson = requestBodyJson;
        this.file = file;
        this.typeToken = typeToken;
        this.callback = callback;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    OkHttpClient client = new OkHttpClient();

    @Override
    protected JsonObject doInBackground(Void... params) {
        String strUrl = this.url + this.path;
        System.out.println(strUrl);
        JsonObject resultBody = null;

        try {
            Request request = null;
            RequestBody requestBody = null;

            if(this.requestBodyJson != null){
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                requestBody = MultipartBody.create(JSON, requestBodyJson.toString());
            }

            if(this.action.equalsIgnoreCase("GET")){ //GET, 대소문자 상관X
                // 요청
                request = new Request.Builder()
                        .url(strUrl)
                        .build();
            }
            else if(this.action.equalsIgnoreCase("POST")){
                request = new Request.Builder()
                        .url(strUrl)
                        .post(requestBody)
                        .build();
            }

            else if(this.action.equalsIgnoreCase("PUT")){
                request = new Request.Builder()
                        .url(strUrl)
                        .put(requestBody)
                        .build();
            }
            else if(this.action.equalsIgnoreCase("DELETE")) {
                request = new Request.Builder()
                        .url(strUrl)
                        .delete()
                        .build();
            }

            // 응답 : header, body 정보 담겨있음
            Response response = client.newCall(request).execute();
            Gson gson = new Gson();
            String responseString = response.body().string();
            System.out.println(responseString.toString());
            JsonElement element = gson.fromJson(responseString, JsonElement.class);
            resultBody = element.getAsJsonObject();

        } catch (IOException e ) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

       // return new ResultBody<>(resultBody.getSuccess(), resultBody.getSize(), resultBody.getDatas(), resultBody.getError(), resultBody.getNewUser());
        return resultBody;
    }

    @Override
    protected void onPostExecute(JsonObject resultBody) {
        super.onPostExecute(resultBody);

        this.callback.doTask(resultBody);
    }

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}