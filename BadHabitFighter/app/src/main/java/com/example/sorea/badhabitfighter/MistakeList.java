package com.example.sorea.badhabitfighter;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class MistakeList extends ListFragment {

    protected String[] columns = new String[]{"one", "two", "three"};
    protected String[] columns2 = new String[]{"1", "2", "3"};
    protected String[] digits = new String[]{"1", "2", "3"};
    protected Cursor mistakeCursor;

    public MistakeList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DatabaseHelper dbhelp = new DatabaseHelper(getActivity(), "mistakes", null, 1);
        dbhelp.insertMistake("11/11/11", "code", "comments");
        Cursor cursor = dbhelp.getAllMistakes();
        columns = new String[]{"display"};
        int[] views = new int[]{android.R.id.text1};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_1, cursor, columns, views,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        this.setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_mistake_list, container, false);
    }

}
