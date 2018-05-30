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
     static final String COLUMN_BillEP1 = "bill_ep1";
     static final String COLUMN_BillEP2 = "bill_ep2";
    static final String COLUMN_BillEP3 = "bill_ep3";
    static final String COLUMN_BillFLAT = "bill_flat";
    static final String COLUMN_UsageEP1 = "usage_ep1";
    static final String COLUMN_UsageEP2 = "usage_ep2";
    static final String COLUMN_UsageEP3 = "usage_ep3";
    static final String COLUMN_UsageFLAT = "usage_flat";

    private static final String COLUMN_RID = "r_id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_Flatno = "flatno";
    private static final String COLUMN_Address = "address";


    private String CREATE_Registerdetails_TABLE = "CREATE TABLE " + TABLE_Registerdetails + "("
            + COLUMN_RID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_PASSWORD + " TEXT,"
            +COLUMN_Flatno + " TEXT,"
            + COLUMN_Address + " TEXT);";

    private String CREATE_Dump_TABLE = "CREATE TABLE " + TABLE_Dump + "("
            + COLUMN_DID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_MAILID + " TEXT,"
            + COLUMN_BillEP1 + " TEXT,"
            + COLUMN_BillEP2 + " TEXT,"
            + COLUMN_BillEP3 + " TEXT,"
            + COLUMN_BillFLAT + " TEXT,"
            + COLUMN_UsageEP1 + " TEXT,"
            + COLUMN_UsageEP2 + " TEXT,"
            + COLUMN_UsageEP3 + " TEXT,"
            +COLUMN_UsageFLAT + " TEXT);";

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
        values.put(COLUMN_NAME, registerdetails.getName());
        values.put(COLUMN_EMAIL, registerdetails.getEmailid());
        values.put(COLUMN_PASSWORD, registerdetails.getPassword());
        values.put(COLUMN_Flatno, registerdetails.getFlatno());
        values.put(COLUMN_Address, registerdetails.getAddress());

        // Inserting Row
        db.insert(TABLE_Registerdetails, null, values);
        db.close();
    }

    public void addDump(Dump dump) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MAILID, dump.getMailId());
        values.put(COLUMN_BillEP1,dump.getBill_ep1() );
        values.put(COLUMN_BillEP2,dump.getBill_ep2());
        values.put(COLUMN_BillEP3, dump.getBill_ep3());
        values.put(COLUMN_BillFLAT, dump.getBill_flat());
        values.put(COLUMN_UsageEP1, dump.getUsage_ep1());
        values.put(COLUMN_UsageEP2, dump.getUsage_ep2());
        values.put(COLUMN_UsageEP3, dump.getUsage_ep3());
        values.put(COLUMN_UsageFLAT, dump.getUsage_flat());

        // Inserting Row
        db.insert(TABLE_Dump, null, values);
        db.close();
    }

public long createDump(String mailId, String billep1, String billep2, String billep3, String billflat,  String usageep1, String usageep2, String usageep3, String usageflat){

        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(COLUMN_MAILID, mailId);
    values.put(COLUMN_BillEP1, billep1 );
    values.put(COLUMN_BillEP2,billep2);
    values.put(COLUMN_BillEP3, billep3);
    values.put(COLUMN_BillFLAT, billflat);
    values.put(COLUMN_UsageEP1, usageep1);
    values.put(COLUMN_UsageEP2,usageep2);
    values.put(COLUMN_UsageEP3, usageep3);
    values.put(COLUMN_UsageFLAT, usageflat);


    return db.insert(TABLE_Dump,null,values);

}

public void insertIntoDump(){
        createDump("raj@gmail.com","10","20","30","60","5","10","15","30");
        createDump("ravi@gmail.com","5","5","5","15","10","10","10","30");
        createDump("keerthi.mvsb@gmail.com","15","5","5","5","5","5","5","5");

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
                COLUMN_BillEP1,
                COLUMN_BillEP2,
                COLUMN_BillEP3,
                COLUMN_BillFLAT,
                COLUMN_UsageEP1,
                COLUMN_UsageEP2,
                COLUMN_UsageEP3,
                COLUMN_UsageFLAT,

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

                            COLUMN_MAILID,
                            COLUMN_BillEP1,
                            COLUMN_BillEP2,
                            COLUMN_BillEP3,
                            COLUMN_BillFLAT,
                            COLUMN_UsageEP1,
                            COLUMN_UsageEP2,
                            COLUMN_UsageEP3,
                            COLUMN_UsageFLAT,
                            },
                    null, null, null, null, null);

            if (mCursor != null) {
                mCursor.moveToFirst();
            }
            return mCursor;
        }

}
