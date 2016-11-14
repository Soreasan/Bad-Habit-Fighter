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
                    .add(R.id.activity_main, currentFragment,"ls")
                    .addToBackStack(null)
                    .commit();
            getSupportFragmentManager().executePendingTransactions();
        }
    }

    protected void openMistakeList() {
        currentFragment = new MistakeList();
        Log.d("test", "Login");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main, currentFragment, "ml")
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
                .replace(R.id.activity_main, currentFragment, "im")
                .addToBackStack(null)
                .commit();
        getSupportFragmentManager().executePendingTransactions();
    }

    protected void populateMistake(long id){
        currentFragment = new EditMistake();
        Log.d("test", "open EditMistake");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main, currentFragment, "pm")
                .addToBackStack(null)
                .commit();
        getSupportFragmentManager().executePendingTransactions();
        Log.d("test", "Next going to try to create an EditMistake by getting the one from currentFragment");
        EditMistake editMistake = (EditMistake) getSupportFragmentManager().findFragmentByTag("pm");
        Log.d("test", "Next we'll update the text on the fragment itself");
        editMistake.updateText(id);
    }

    protected void deleteCourse(long id){
        Log.d("test", "deleteCourse() in main called");
        DatabaseHelper dbhelper = new DatabaseHelper(MainActivity.this, "mistakes", null, 1);
        dbhelper.deleteMistake(id);
        openMistakeList();
    }
}
