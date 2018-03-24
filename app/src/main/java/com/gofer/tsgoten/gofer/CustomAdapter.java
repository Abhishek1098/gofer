package com.gofer.tsgoten.gofer;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter{

    private Context context;
    private ArrayList<Service> services;
    private Typeface typeface_verdana;

    public CustomAdapter(@NonNull Context context, int resource, ArrayList<Service> services) {
        super(context, resource, services);
        this.context=context;
        this.services=services;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
        View adapterView = layoutInflater.inflate(R.layout.adapter_custom, null);

        typeface_verdana = Typeface.createFromAsset(context.getAssets(), "verdana.ttf");

        TextView textViewTitle = adapterView.findViewById(R.id.id_customAdapter_TextView_title);
        textViewTitle.setText(services.get(position).getTitle());
        textViewTitle.setTextSize(35);
        textViewTitle.setTypeface(typeface_verdana);
        //textViewTitle.setTextColor();

        TextView textViewCost = adapterView.findViewById(R.id.id_customAdapter_TextView_cost);
        textViewCost.setText(services.get(position).getCost());
        textViewCost.setTextSize(25);
        textViewCost.setTypeface(typeface_verdana);
        //textViewCost.setTextColor();

        TextView textViewTime = adapterView.findViewById(R.id.id_customAdapter_TextView_time);
        textViewTime.setText(services.get(position).getTime());
        textViewTime.setTextSize(15);
        textViewTime.setTypeface(typeface_verdana);
        //textViewTime.setTextColor();

        return adapterView;
    }
}
