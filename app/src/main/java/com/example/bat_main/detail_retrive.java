package com.example.bat_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class detail_retrive extends AppCompatActivity {

    TextView tvid , tvtitle, tvamount, tvdate, tvtime, tvcategory;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_retrive);

        tvid = findViewById(R.id.txtid);
        tvtitle = findViewById(R.id.txttitle);
        tvamount = findViewById(R.id.txtamount);
        tvcategory = findViewById(R.id.txtcategory);
        tvdate = findViewById(R.id.txtd);
        tvtime = findViewById(R.id.txtt);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID: "+history.retriveArrayList.get(position).getId());
        tvtitle.setText("TITLE: "+history.retriveArrayList.get(position).getFname());
        tvamount.setText("AMOUNT: "+history.retriveArrayList.get(position).getLname());
        tvcategory.setText("CATEGORY: "+history.retriveArrayList.get(position).getCat());
        tvdate.setText("DATE: "+history.retriveArrayList.get(position).getWhend());
        tvtime.setText("TIME: "+history.retriveArrayList.get(position).getWhered());



    }
}