package com.example.bat_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class flask_get_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flask_get_data);

        OkHttpClient okHttpClient =  new OkHttpClient();


        RequestBody body = new FormBody.Builder().add("img" ,"hoo ja").build();
        Request request=new Request.Builder().url("http://127.0.0.1:5000/").post(body).build();
        System.out.println("HI");
        try{
            Response response = okHttpClient.newCall(request).execute();
            System.out.println(response.body().string());
        }
         catch (IOException e)
         {
             e.printStackTrace();
         }
        //okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                //Toast.makeText(flask_get_data.this, "Network not found", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                TextView textView= findViewById(R.id.flask_tt);
//                textView.setText(response.body().string());
//
//            }
//        }
//);



    }
}