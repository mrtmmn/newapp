package com.example.mmamin.blanklogin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

/**
 * Created by mmamin on 6/8/2016.
 */public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private Activity mContext;
    private Map<String, List<String>> mCollections;
    private List<String> mStuff;

    public CustomExpandableListAdapter(Activity context, List<String> stuff,
                                 Map<String, List<String>> collections) {
        this.mContext = context;
        this.mCollections = collections;
        this.mStuff = stuff;
    }

    @Override
    public int getGroupCount() {
        return mStuff.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mCollections.get(mStuff.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mStuff.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mCollections.get(mStuff.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String positionValue = mStuff.get(groupPosition);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.information_listview, parent, false);
        TextView textViewCircle = (TextView)rowView.findViewById(R.id.circle_textview);
        TextView textViewNextToCircle = (TextView)rowView.findViewById(R.id.text_nextto_circle);
        textViewCircle.setText(positionValue.substring(0,1));
        textViewNextToCircle.setText(positionValue);

        if (positionValue.contains("Gold")) {
            textViewCircle.setBackgroundResource(R.drawable.circle_gold);
            textViewCircle.setText("A");
        } else if (positionValue.contains("Silver")){
            textViewCircle.setBackgroundResource(R.drawable.circle_silver);
            textViewCircle.setText("J");
        } else if (positionValue.contains("UKDebit")){
            textViewCircle.setBackgroundResource(R.drawable.circle_ukdebit);
            textViewCircle.setText("K");
        }else if (positionValue.contains("USDebit")){
            textViewCircle.setBackgroundResource(R.drawable.circle_usdebit);
            textViewCircle.setText("D");
        }else if (positionValue.contains("Carriers")){
            textViewCircle.setBackgroundResource(R.drawable.circle_carriers);
            textViewCircle.setText("W");
        }

        return rowView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String stuff = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = mContext.getLayoutInflater();

        convertView = inflater.inflate(R.layout.child_item_information_listview, null);

        TextView testView = (TextView) convertView.findViewById(R.id.testview);
        testView.setText(stuff);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
