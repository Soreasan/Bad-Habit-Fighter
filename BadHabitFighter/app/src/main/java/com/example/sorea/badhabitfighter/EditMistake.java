package com.example.sorea.badhabitfighter;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditMistake extends Fragment {

    protected View myView;
    protected TextView id;
    protected EditText code;
    protected EditText date;
    protected EditText notes;
    protected Button save;
    protected Button back;
    protected Button delete;
    protected long my_id;

    public EditMistake() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_edit_mistake, container, false);
        id = (TextView) myView.findViewById(R.id.edtEditId);
        code = (EditText) myView.findViewById(R.id.edtEditCode);
        date = (EditText) myView.findViewById(R.id.edtEditDate);
        notes = (EditText) myView.findViewById(R.id.edtEditNotes);
        save = (Button) myView.findViewById(R.id.editSaveButton);
        back = (Button) myView.findViewById(R.id.editBackButton);
        delete = (Button) myView.findViewById(R.id.editDeleteButton);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MainActivity ma = (MainActivity) getActivity();
                ma.openMistakeList();
            }
        });

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DatabaseHelper dbhelper = new DatabaseHelper(getActivity(), "mistakes", null, 1);

                long rowID = dbhelper.updateMistake(
                        my_id,
                        date.getText().toString(),
                        code.getText().toString(),
                        notes.getText().toString()
                );

                MainActivity ma = (MainActivity) getActivity();
                ma.openMistakeList();
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MainActivity ma = (MainActivity) getActivity();
                ma.deleteCourse(my_id);
            }
        });

        return myView;
    }

    protected void updateText(long dbID){
        my_id = dbID;
        Log.d("test", "updateText has been called.  Creating a databasehelper");
        DatabaseHelper dbhelper = new DatabaseHelper(getActivity(), "mistakes", null, 1);
        Log.d("test", "Creating a cursor");
        Cursor cursor = dbhelper.getOneMistake(dbID);
        Log.d("test", "moveToFirst()");
        cursor.moveToFirst();

        Log.d("test", "retrieving the three data thingies we need from the database");
        String myID = cursor.getString(cursor.getColumnIndex("_id"));
        Log.d("test", "Retrieving date");
        String myDate = cursor.getString(cursor.getColumnIndex("date"));
        Log.d("test", "Retrieving code");
        String myCode = cursor.getString(cursor.getColumnIndex("code"));
        Log.d("test", "Retrieving notes");
        String myNotes = cursor.getString(cursor.getColumnIndex("notes"));

        Log.d("test", "Successfully retrieved data, next updating text in the front end");
        Log.d("test", "setting my_id");
        id.setText(myID);
        Log.d("test", "setting date");
        date.setText(myDate);
        Log.d("test", "setting code");
        code.setText(myCode);
        Log.d("test", "Setting notes");
        notes.setText(myNotes);

    }

}
