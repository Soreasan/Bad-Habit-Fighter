package com.example.sorea.badhabitfighter;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //My theory is that I can do state management by having this global fragment.
    private Fragment currentFragment;

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

    protected void successfulLogin() {
        currentFragment = new MistakeList();
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

    protected void openInsertMistake(){
        currentFragment = new InsertMistake();
        Log.d("test", "openInsertMistake");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main, currentFragment, "mainLayout")
                .addToBackStack(null)
                .commit();
        getSupportFragmentManager().executePendingTransactions();
    }
}
