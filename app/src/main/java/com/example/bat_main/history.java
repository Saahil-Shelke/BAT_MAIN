package com.example.bat_main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class history extends AppCompatActivity {

    ListView listView;
    adapter adapter;
    public static ArrayList<retrive> retriveArrayList = new ArrayList<>();
    String url = "https://smart-bat.000webhostapp.com/retrive.php";
    retrive retrive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = findViewById(R.id.hist);
        adapter = new adapter(this,retriveArrayList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dialogItem = {"View Data", "Edit Data", "Delete Data"};
                builder.setTitle(retriveArrayList.get(position).getFname());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        switch (i){

                            case 0 :

                                startActivity(new Intent(getApplicationContext(), detail_retrive.class)
                                        .putExtra("position", position));

                                break;

                            case 1:
                                break;

                            case 2:
                                break;
                        }

                    }
                });

                builder.create().show();



            }
        });

        retriveData();



    }

    public void retriveData(){

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        retriveArrayList.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");


                            if(success.equals("1")){

                                for(int i=0;i<jsonArray.length();i++){

                                        JSONObject object = jsonArray.getJSONObject(i);

                                        String id = object.getString("id");
                                        String fname = object.getString("fname");
                                        String lname = object.getString("lname");
                                        String whend = object.getString("whend");
                                        String whered = object.getString("whered");

                                        retrive  = new retrive(id, fname, lname, whend, whered );
                                        retriveArrayList.add(retrive);
                                        adapter.notifyDataSetChanged();



                                }




                            }



                        }
                        catch (JSONException e){

                            e.printStackTrace();

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(history.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);



    }

}