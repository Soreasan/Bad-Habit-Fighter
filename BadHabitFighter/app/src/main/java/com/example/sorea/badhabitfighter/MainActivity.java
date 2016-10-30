package com.example.sorea.badhabitfighter;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    //My theory is that I can do state management by having this global fragment.
    Fragment currentFragment;

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
}
