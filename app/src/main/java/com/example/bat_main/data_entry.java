package com.example.bat_main;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class data_entry extends AppCompatActivity {

    TextView txtDate,txtTime;
    Button btnDate, btnTime;
    String[] item = {"Clothing","Electronics","Enjoyment","Food", "Health","Household","Travel","Other"};
    public AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    EditText fname, lname, whend, whered, dropd;
    Button submit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);


         dropd = findViewById(R.id.autocomp);

         adapterItems = new ArrayAdapter<String>(this,R.layout.list_category,item);

         autoCompleteTextView.setAdapter(adapterItems);
         autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                 String item = parent.getItemAtPosition(position).toString();
                 Toast.makeText(getApplicationContext(), "Item: "+item, Toast.LENGTH_SHORT).show();
             }
         });




        dropd = (EditText) findViewById(R.id.autocomp);
        fname =(EditText) findViewById(R.id.title);
        lname = (EditText) findViewById(R.id.amount);
        whend = (EditText) findViewById(R.id.date);
        whered =(EditText) findViewById(R.id.time);
        submit= (Button) findViewById(R.id.submit);

//        txtDate = (TextView) findViewById(R.id.date);
//        txtTime = (TextView) findViewById(R.id.time);
        btnDate = (Button) findViewById(R.id.sub_date);
        btnTime = (Button) findViewById(R.id.sub_time);


       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               insertData();
           }
       });



        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime();
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
            }
        });

    }

    private void insertData() {

        String title = fname.getText().toString().trim();
        String amount = lname.getText().toString().trim();
        String cat = dropd.getText().toString().trim();
        String date = whend.getText().toString().trim();
        String time = whered.getText().toString().trim();



        if(title.isEmpty()){
            Toast.makeText(this, "Enter title",Toast.LENGTH_SHORT).show();
        }

        else if(amount.isEmpty()){
            Toast.makeText(this, "Enter amount",Toast.LENGTH_SHORT).show();
        }

        else if(date.isEmpty()){
            Toast.makeText(this, "Enter Date",Toast.LENGTH_SHORT).show();
        }

        else if(time.isEmpty()){
            Toast.makeText(this, "Enter Time",Toast.LENGTH_SHORT).show();
        }

        else{

            StringRequest request = new StringRequest(Request.Method.POST, "https://smart-bat.000webhostapp.com/bat.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(data_entry.this,response, Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(data_entry.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }


            ){
                @NonNull
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<String, String >();

                    params.put("fname", title);
                    params.put("lname", amount);
                    params.put("dropd" ,cat);
                    params.put("whend", date );
                    params.put("whered", time);


                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(data_entry.this);
            requestQueue.add(request);
        }


    }




    private void process() {
    }


    private void setDate(){

        Calendar calendar= Calendar.getInstance();
        int year= calendar.get(Calendar.YEAR);
        int month= calendar.get(Calendar.MONTH);
        int date= calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                String showDate = i2+" /"+(i1+1)+" /"+i;
                whend.setText(showDate);

            }
        },year, month, date);

        datePickerDialog.show();

    }

    private void setTime(){

        Calendar calendar= Calendar.getInstance();
        int hour= calendar.get(Calendar.HOUR);
        int min= calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hr, int min) {
                String showTime = hr+" :"+ min;
                whered.setText(showTime);

            }
        }, hour, min, true);
        timePickerDialog.show();





    }


}