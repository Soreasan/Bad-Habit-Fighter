package com.example.sorea.badhabitfighter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.sorea.badhabitfighter.Constants.FIRST_COLUMN;
import static com.example.sorea.badhabitfighter.Constants.SECOND_COLUMN;
import static com.example.sorea.badhabitfighter.Constants.THIRD_COLUMN;


/**
 * A simple {@link Fragment} subclass.
 */
public class MistakeListFrag extends Fragment {
    private View myView;
    private ArrayList<HashMap<String, String>> list;

    public MistakeListFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_mistake_list_frag, container, false);

        Log.d("test", "Getting mainActivity");
        MainActivity ma = (MainActivity) getActivity();
        Log.d("test", "Creating listView");
        ListView listView = (ListView) ma.findViewById(R.id.listView1);
        Log.d("test", "Creating arraylist of hashmaps");
        list = new ArrayList<HashMap<String, String>>();
        Log.d("test", "Creating dummy node");
        HashMap<String, String> temp = new HashMap<String, String>();
            temp.put(FIRST_COLUMN, "11/11/11");
            temp.put(SECOND_COLUMN, "V");
            temp.put(THIRD_COLUMN, "Voted Trump =(");
        Log.d("test", "Adding dummy note to arraylist");
        list.add(temp);
        Log.d("test", "Creating adapter");
        MistakeList adapter = new MistakeList(ma, list);
        Log.d("test", "Setting adapter");
        listView.setAdapter(adapter);
        Log.d("test", "Returning the view");
        return myView;
    }

}
