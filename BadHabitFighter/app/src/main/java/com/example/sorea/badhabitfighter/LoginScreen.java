package com.example.sorea.badhabitfighter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginScreen extends Fragment {
    private View myView;
    private String pin = "5555";
    private TextView userPin;
    private Button login;

    public LoginScreen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_login_screen, container, false);
        Log.d("test", "Creating LoginScreen fragment.");
        userPin = (TextView) myView.findViewById(R.id.pinNumber);
        login = (Button) myView.findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("test", "Pushed the login button");
                MainActivity ma = (MainActivity) getActivity();
                if (userPin.getText().toString().equals(pin)) {
                    ma.openMistakeList();
                }else{
                    ma.failedLogin();
                }

            }
        });
        return myView;
    }

}
