package com.paidinfull.dunya.entreprise;

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

public class CustomAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] cat;
    private final Integer[]logo;

    public CustomAdapter(Context context, String[] cat, Integer[] logo){
        super(context, R.layout.custom_layout, cat);
        this.context= context;
        this.cat= cat;
        this.logo= logo;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView= inflater.inflate(R.layout.custom_layout, null, true);

        TextView title = (TextView) rowView.findViewById(R.id.text);
        ImageView image = (ImageView) rowView.findViewById(R.id.image);

        title.setText(cat[position]);
        image.setImageResource(logo[position]);
        return rowView;
    }
}
