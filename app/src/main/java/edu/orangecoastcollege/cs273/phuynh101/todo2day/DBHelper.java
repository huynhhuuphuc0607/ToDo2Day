package edu.orangecoastcollege.cs273.phuynh101.todo2day;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuynh101 on 9/28/2017.
 */

class DBHelper extends SQLiteOpenHelper {
    /**
     * database name
     */
    public static final String DATABASE_NAME = "ToDo2Day";
    /**
     * database table
     */
    public static final String DATABASE_TABLE = "Tasks";
    //int  or double => 1.1
    /**
     * database version
     */
    public static final int DATABSE_VERSION = 1;

    //create some usefull table constants
    /**
     * field 1 - primary key
     */
    public static final String KEY_FIELD_ID =  "_id";
    /**
     * field 2
     */
    public static final String FIELD_DESCRIPTION = "description";
    /**
     * field 3
     */
    public static final String FIELD_DONE = "done";

    /**
     * constructor
     * @param context the context where this database will be called
     */
    //choose the first constructor and then just keep the parameter context,
    //all other parameter can be passed here
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    /**
     * will be called when first creating a table
     * @param db database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Generate an SQL statement for creating a new table
        //CREATE TABLE Tasks (_id INTEGER PRIMARY KEY, description TEXT, done INTEGER)
        String createTable = "CREATE TABLE " + DATABASE_TABLE + " ("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_DESCRIPTION + " TEXT, "
                + FIELD_DONE + " INTEGER"
                + ")";
        db.execSQL(createTable);
    }


    /**
     * will be called when the database structure is changed
     * @param db
     * @param i
     * @param i1
     */
    //if we add a new field, MUST MANUALLY change the database version
    //onUpgrade will be triggered, otherwise it wont kick im
    //Note: Migrate all the data to somewhere before dropping the table
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //1. Drop the existing table
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

        //2. Create/ Build a new one
        onCreate(db);
    }

    public void addTask(Task newTask)
    {
        SQLiteDatabase db = getWritableDatabase();
        //specify the values to insert into the database
        //everything except the primary key _id (auto assigned)
        ContentValues values = new ContentValues();
        values.put(FIELD_DESCRIPTION, newTask.getDescription());
        values.put(FIELD_DONE, newTask.isDone() ? 1 : 0);
        db.insert(DATABASE_TABLE, null, values);
        db.close();
    }

    public List<Task> getallTasks()
    {
        List<Task> allTasksList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        //to retrieve data from a database table, we use a cursor.
        Cursor cursor = db.query(DATABASE_TABLE,
                new String[]{KEY_FIELD_ID, FIELD_DESCRIPTION, FIELD_DONE},
                null, null, null, null, null);

        if(cursor.moveToFirst()) {
            //guarantee at least one result from query
           do{
               Task task = new Task(cursor.getInt(0), cursor.getString(1), cursor.getInt(2) == 1);
               allTasksList.add(task);
           }while(cursor.moveToNext());
        }

        db.close();
        cursor.close();

        return allTasksList;
    }

    public void deleteTask(Task taskToDelete)
    {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(DATABASE_TABLE, KEY_FIELD_ID + " = " + taskToDelete.getId(),null);

        db.close();
    }

    public void updateTask(Task taskToEdit)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_DESCRIPTION, taskToEdit.getDescription());
        values.put(FIELD_DONE, taskToEdit.isDone() ? 1 : 0);

        db.update(DATABASE_TABLE, values, KEY_FIELD_ID + " = " + taskToEdit.getId(), null);

        db.close();
    }

    public Task getSingleTask(int id)
    {
        Task singleTask = null;

        SQLiteDatabase db = getReadableDatabase();
        //to retrieve data from a database table, we use a cursor.
        Cursor cursor = db.query(DATABASE_TABLE,
                new String[]{KEY_FIELD_ID, FIELD_DESCRIPTION, FIELD_DONE},
                KEY_FIELD_ID +" = " + id, null, null, null, null);

        if(cursor.moveToFirst()) {
            //guarantee at least one result from query
            do{
                singleTask = new Task(cursor.getInt(0), cursor.getString(1), cursor.getInt(2) == 1);
            }while(cursor.moveToNext());
        }

        db.close();
        cursor.close();

        return singleTask;
    }
}
