package org.polito.salvatore.germanomosconi;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by salvatore on 31/03/16.
 */
/*
 class CustomAdapter extends ArrayAdapter<String> {


    public CustomAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        // TODO Auto-generated constructor stub
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);


        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        Typeface font = Typeface.createFromAsset(parent.getContext().getAssets(), "segoe_print.ttf");
        textView.setTypeface(font);

        return textView;
    }

};
*/
class CustomAdapter extends ArrayAdapter<String> {

    Context context;
    int layoutResourceId;
    String[] data;
    Typeface tf;

    public CustomAdapter(Context context, int layoutResourceId, String[] data, String FONT) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        tf = Typeface.createFromAsset(context.getAssets(), FONT);
    }


};
