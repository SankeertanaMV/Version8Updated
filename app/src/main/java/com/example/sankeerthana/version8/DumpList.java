package com.example.sankeerthana.version8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


import java.util.ArrayList;
import java.util.List;


public class DumpList extends AppCompatActivity {

    private AppCompatActivity activity = DumpList.this;
    private AppCompatTextView textViewName;
    private List<Dump> dumpList;
    private UserAdapter userAdapter;
    private DBHelper dbHelper;
     SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dump_list);
        getSupportActionBar().setTitle("");
        dbHelper = new DBHelper(this);
        initViews();
        initObjects();
    }

private void initViews(){
    textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
}
    private void initObjects() {

        dumpList = new ArrayList<>();
        userAdapter = new UserAdapter(dumpList);
        //myFilter.setText(emailFromIntent);
        dbHelper.deleteAllDump();
        dbHelper.insertIntoDump();

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText(emailFromIntent);
        displayListView();
    }


    private void displayListView() {



        Cursor mCursor = dbHelper.fetchDumpByName(textViewName.getText().toString());

        // The desired columns to be bound
        String[] columns = new String[]{

                dbHelper.COLUMN_MAILID,
                dbHelper.COLUMN_EP1,
                dbHelper.COLUMN_EP2

        };

        int[] to = new int[]{
                R.id.textViewMailId,
                R.id.textViewEP1,
                R.id.textViewEP2,
        };

        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.item_user_recycler,
                mCursor,
                columns,
                to,
                0);


    // Assign adapter to ListView
  ListView  listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {

        @Override

        public void onItemClick(AdapterView<?> listView, View view,
        int position, long id) {
            // Get the cursor, positioned to the corresponding row in the result set
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);

            // Get the state's capital from this row in the database.
            String userEmail =
                    cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.COLUMN_MAILID));
            Toast.makeText(getApplicationContext(),
                    userEmail, Toast.LENGTH_SHORT).show();
        }
    });
        EditText myFilter = (EditText) findViewById(R.id.myFilter);
        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }
        });


    }
}