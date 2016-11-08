package com.example.sorea.badhabitfighter;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.sorea.badhabitfighter.Constants.FIRST_COLUMN;
import static com.example.sorea.badhabitfighter.Constants.SECOND_COLUMN;
import static com.example.sorea.badhabitfighter.Constants.THIRD_COLUMN;


/**
 * A simple {@link Fragment} subclass.
 * This entire thing is taken almost exclusively from http://techlovejump.com/android-multicolumn-listview
 */
public class MistakeList extends BaseAdapter {

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;
    protected Cursor mistakeCursor;

    public MistakeList(Activity activity, ArrayList<HashMap<String, String>> list) {
        // Required empty public constructor
        super();
        Log.d("test", "Initializing MistakeList fragment");
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        Log.d("test", "Calling getCount() from MistakeList.java");
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        Log.d("test", "Calling getItem() from MistakeList.java");
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.d("test", "Calling getItemId(int position) from MistakeList.java which just returns 0");
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();

        if(convertView == null){
            convertView = inflater.inflate(R.layout.fragment_mistake_list, null);
            txtFirst = (TextView) convertView.findViewById(R.id.date);
            txtSecond = (TextView) convertView.findViewById(R.id.code);
            txtThird = (TextView) convertView.findViewById(R.id.notes);
            return convertView;
        }
        HashMap<String, String> map = list.get(position);
        txtFirst.setText(map.get(FIRST_COLUMN));
        txtSecond.setText(map.get(SECOND_COLUMN));
        txtThird.setText(map.get(THIRD_COLUMN));

        return convertView;
    }
}
