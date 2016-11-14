package com.example.sorea.badhabitfighter;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MistakeList extends ListFragment {

    protected String[] columns = new String[]{"one", "two", "three"};
    protected String[] columns2 = new String[]{"1", "2", "3"};
    protected String[] digits = new String[]{"1", "2", "3"};
    protected Cursor mistakeCursor;
    //protected MenuItem fav;

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

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        menu.add("Add New Mistake");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        MainActivity ma = (MainActivity) getActivity();
        ma.openInsertMistake();
        return true;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        Log.d("test", "Clicked list item " + id);
        super.onListItemClick(l, v, position, id);
        MainActivity ma = (MainActivity) getActivity();
        ma.populateMistake(id);
    }

    @Override
    public void onResume(){
        super.onResume();
        getListView().setOnItemLongClickListener(new ListView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                Log.d("test", "long press detected");
                MainActivity ma = (MainActivity) getActivity();
                ma.populateMistake(id);
                return false;
            }
        });
    }

}
