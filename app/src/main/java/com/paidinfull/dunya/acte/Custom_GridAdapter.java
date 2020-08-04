package com.paidinfull.dunya.acte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.paidinfull.dunya.R;
import com.paidinfull.dunya.entreprise.CustomAdapter;

public class Custom_GridAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] catAct;
    private final Integer[] logo1;

    public Custom_GridAdapter (Context context, String[] catAct, Integer[] logo1){
        super(context, R.layout.custom_grid, catAct);
        this.context= context;
        this.catAct = catAct;
        this.logo1 = logo1;
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView= inflater.inflate(R.layout.custom_grid, null, true);

        //Initialisation de textView et imageView declaré dans custom_grid
        TextView titre = (TextView)rowView.findViewById(R.id.textl);
        ImageView photo = (ImageView)rowView.findViewById(R.id.ima);

        titre.setText(catAct[position]);
        photo.setImageResource(logo1[position]);

        return rowView;
    }
}
