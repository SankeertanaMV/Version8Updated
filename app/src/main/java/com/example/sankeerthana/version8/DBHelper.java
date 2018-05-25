package com.example.sankeerthana.version8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Version8.db";


    private static final String TABLE_Registerdetails = "registerdetails";
    private static final String TABLE_Dump = "dump";

     static final String COLUMN_DID = "_id";
     static final String COLUMN_MAILID = "mailId";
     static final String COLUMN_EP1 = "ep1";
     static final String COLUMN_EP2 = "ep2";

    private static final String COLUMN_RID = "r_id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PASSWORD = "password";


    private String CREATE_Registerdetails_TABLE = "CREATE TABLE " + TABLE_Registerdetails + "("
            + COLUMN_RID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_EMAIL + " TEXT,"
            + COLUMN_NAME + " TEXT," + COLUMN_PASSWORD + " TEXT);";

    private String CREATE_Dump_TABLE = "CREATE TABLE " + TABLE_Dump + "("
            + COLUMN_DID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_MAILID + " TEXT,"
            + COLUMN_EP1 + " TEXT," + COLUMN_EP2 + " TEXT);";

    private String DROP_Registerdetails_TABLE = "DROP TABLE IF EXISTS " + TABLE_Registerdetails;
    private String DROP_Dump_TABLE = "DROP TABLE IF EXISTS " + TABLE_Dump;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_Registerdetails_TABLE);
        db.execSQL(CREATE_Dump_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_Registerdetails_TABLE);
        db.execSQL(DROP_Dump_TABLE);

        onCreate(db);

    }

    public void addRegister(Registerdetails registerdetails) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, registerdetails.getEmailid());
        values.put(COLUMN_NAME, registerdetails.getName());
        values.put(COLUMN_PASSWORD, registerdetails.getPassword());

        // Inserting Row
        db.insert(TABLE_Registerdetails, null, values);
        db.close();
    }

    public void addDump(Dump dump) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MAILID, dump.getMailId());
        values.put(COLUMN_EP1, dump.getEp1());
        values.put(COLUMN_EP2, dump.getEp2());

        // Inserting Row
        db.insert(TABLE_Dump, null, values);
        db.close();
    }

public long createDump(String mailId, String ep1, String ep2){

        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(COLUMN_MAILID, mailId);
        values.put(COLUMN_EP1, ep1);
        values.put(COLUMN_EP2, ep2);

        return db.insert(TABLE_Dump,null,values);

}

public void insertIntoDump(){
        createDump("raj@gmail.com","10","20");
        createDump("ravi@gmail.com","30","40");
        createDump("remo@gmail.com","15","30");
}


public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_RID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_Registerdetails, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_RID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_Registerdetails, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
    public boolean deleteAllDump() {
        SQLiteDatabase mDb = this.getWritableDatabase();
        int doneDelete = 0;
        doneDelete = mDb.delete(TABLE_Dump, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }

    public Cursor fetchDumpByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        String[] columns = {
                COLUMN_DID,
                COLUMN_MAILID,
                COLUMN_EP1,
                COLUMN_EP2

        };
        // WHERE CLAUSE
        // selection criteria

        String selection = COLUMN_MAILID + " = ?";

        // selection argument
        String[] selectionArgs = {inputText};
        List<Dump> dumpList = new ArrayList<Dump>();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        SQLiteDatabase db = this.getReadableDatabase();
        mCursor = db.query(TABLE_Dump, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
          /*  Dump dump = new Dump();
            dump.setDid(Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(COLUMN_MAILID))));
            dump.setMailId(mCursor.getString(mCursor.getColumnIndex(COLUMN_MAILID)));
            dump.setEp1(mCursor.getString(mCursor.getColumnIndex(COLUMN_EP1)));
            dump.setEp2(mCursor.getString(mCursor.getColumnIndex(COLUMN_EP2)));
            dumpList.add(dump); */

        return mCursor;


    }






        public Cursor getAllDump() {

        SQLiteDatabase mDb = this.getReadableDatabase();

            Cursor mCursor = mDb.query(TABLE_Dump, new String[] {COLUMN_DID,
                            COLUMN_MAILID, COLUMN_EP1, COLUMN_EP2,},
                    null, null, null, null, null);

            if (mCursor != null) {
                mCursor.moveToFirst();
            }
            return mCursor;
        }

}
