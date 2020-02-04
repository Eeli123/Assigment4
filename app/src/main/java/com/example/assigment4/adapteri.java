package com.example.assigment4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class adapteri extends ArrayAdapter<lista> {

    private Context context;
    ArrayList<lista> dataset;
    lista lista;

    public adapteri(@NonNull Context context, int resource, @NonNull List<lista> objects) {
        super(context, resource, objects);

        this.context = context;
        this.dataset = (ArrayList<lista>)objects;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.adapteri,parent,false);
        LinearLayout linearLayout = (LinearLayout) v;
        TextView lista1 = v.findViewById(R.id.textview1);
        TextView lista2 = v.findViewById(R.id.textview2);
        lista1.setText(dataset.get(position).getTitle());
        lista2.setText(dataset.get(position).getBody());

        return v;
    }
}
