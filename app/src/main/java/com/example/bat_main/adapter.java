package com.example.bat_main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class adapter extends ArrayAdapter<retrive> {

    Context context;
    List<retrive> arrayListretrive;


    public adapter(@NonNull Context context, List<retrive> arrayListretrive ) {
        super(context, R.layout.custom_list_item, arrayListretrive);


        this.context = context;
        this.arrayListretrive = arrayListretrive;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, null, true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.txt_name);

        tvID.setText(arrayListretrive.get(position).getId());
        tvName.setText(arrayListretrive.get(position).getFname());

        return view;
    }
}
