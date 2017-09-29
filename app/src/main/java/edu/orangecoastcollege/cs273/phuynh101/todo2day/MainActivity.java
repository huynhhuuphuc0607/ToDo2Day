package edu.orangecoastcollege.cs273.phuynh101.todo2day;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Task> mAllTasksList = new ArrayList<>();
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //clear the existing database
        deleteDatabase(DBHelper.DATABASE_NAME);

        //instantiate a new helper
        DBHelper db = new DBHelper(this);

        mAllTasksList.add(new Task("111111",false));
        mAllTasksList.add(new Task("2222222",false));
        mAllTasksList.add(new Task("3333333",false));
        mAllTasksList.add(new Task("4444444",false));
        //Loop through the list and add each one to the database
        for(Task t : mAllTasksList)
        {
            db.addTask(t);
        }
        //clear the list and rebuild it from the database
        mAllTasksList.clear();
        //retrieve all tasks from the database
        mAllTasksList = db.getallTasks();

        Log.i(TAG, "Showing all tasks:");
        for(Task t: mAllTasksList)
            Log.i(TAG, t.toString());

        Log.i(TAG, "After deleting task 4");
        db.deleteTask(mAllTasksList.get(3));
        //clear the list and rebuild it from the database
        mAllTasksList.clear();
        //retrieve all tasks from the database
        mAllTasksList = db.getallTasks();
        for(Task t: mAllTasksList)
            Log.i(TAG, t.toString());

    }
}
