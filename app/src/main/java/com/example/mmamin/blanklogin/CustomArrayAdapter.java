package com.example.mmamin.blanklogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by mmamin on 6/6/2016.
 */
public class CustomArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String [] values;

    public CustomArrayAdapter(Context context, String [] values) {
        super(context, R.layout.information_listview, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String positionValue = values[position];
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.information_listview, parent, false);
        TextView textViewCircle = (TextView)rowView.findViewById(R.id.circle_textview);
        TextView textViewNextToCircle = (TextView)rowView.findViewById(R.id.text_nextto_circle);
        textViewCircle.setText(positionValue.substring(0,1));
        textViewNextToCircle.setText(positionValue);

        if (positionValue.contains("Gold")) {
            textViewCircle.setBackgroundResource(R.drawable.circle_gold);
        } else if (positionValue.contains("Silver")){
            textViewCircle.setBackgroundResource(R.drawable.circle_silver);
        } else if (positionValue.contains("UKDebit")){
            textViewCircle.setBackgroundResource(R.drawable.circle_ukdebit);
        }else if (positionValue.contains("USDebit")){
            textViewCircle.setBackgroundResource(R.drawable.circle_usdebit);
        }else if (positionValue.contains("Carriers")){
            textViewCircle.setBackgroundResource(R.drawable.circle_carriers);
        }

        return rowView;
    }
}
