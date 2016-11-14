package com.example.sorea.badhabitfighter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class InsertMistake extends Fragment {

    protected View myView;
    protected EditText code;
    protected EditText date;
    protected EditText notes;
    protected Button save;
    protected Button back;

    public InsertMistake() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_insert_mistake, container, false);

        code = (EditText) myView.findViewById(R.id.edtInsertCode);
        date = (EditText) myView.findViewById(R.id.edtInsertDate);
        notes = (EditText) myView.findViewById(R.id.edtInsertNotes);
        save = (Button) myView.findViewById(R.id.insertSaveButton);
        back = (Button) myView.findViewById(R.id.insertBackButton);

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
                long rowID = dbhelper.insertMistake(
                        date.getText().toString(),
                        code.getText().toString(),
                        notes.getText().toString()
                );
                MainActivity ma = (MainActivity) getActivity();
                ma.openMistakeList();
            }
        });

        return myView;
    }

}
