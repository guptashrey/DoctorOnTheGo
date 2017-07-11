package net.shreygupta.doctoronthego;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by archit on 12/7/17.
 */

public class CustomAdaptorDoctor extends ArrayAdapter {

    Context con_ref;
    String[] name;
    String[] date;
    String[] time;

    public CustomAdaptorDoctor(@NonNull Context context, String[] name, String[] date, String[] time) {
        super(context, R.layout.custom_layout, R.id.name, name);
        con_ref = context;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater l = (LayoutInflater) con_ref.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = l.inflate(R.layout.custom_layout, parent, false);

        TextView nam = v.findViewById(R.id.name);
        TextView dat = v.findViewById(R.id.date);
        TextView tim = v.findViewById(R.id.time);

        nam.setText(name[position]);
        dat.setText(date[position]);
        tim.setText(time[position]);

        return v;
    }
}
