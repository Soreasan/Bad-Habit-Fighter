package com.example.sorea.badhabitfighter;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    //My theory is that I can do state management by having this global fragment.
    private Fragment currentFragment;
    private ArrayList<HashMap<String, String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Default stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            currentFragment = new LoginScreen();
            Log.d("test", "Creating fragments for the first time");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main, currentFragment,"mainLayout")
                    .addToBackStack(null)
                    .commit();
            getSupportFragmentManager().executePendingTransactions();
        }
    }

    protected void loadMistakeList() {
        list = new ArrayList<HashMap<String, String>>();

        currentFragment = new MistakeListFrag();
        Log.d("test", "Login");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main, currentFragment, "mainLayout")
                .addToBackStack(null)
                .commit();
        getSupportFragmentManager().executePendingTransactions();
    }
    protected void failedLogin(){
        Toast.makeText(getApplicationContext(), "Incorrect pin", Toast.LENGTH_LONG).show();
    }
}
