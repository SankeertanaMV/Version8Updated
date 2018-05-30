package com.example.sankeerthana.version8;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


import java.util.ArrayList;
import java.util.List;


public class DumpList extends AppCompatActivity {

    private AppCompatActivity activity = DumpList.this;
    private TextView textViewName;
    private List<Dump> dumpList;
    private UserAdapter userAdapter;
    private DBHelper dbHelper;
     SimpleCursorAdapter dataAdapter;
private Button button;
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

    textViewName = (TextView) findViewById(R.id.textViewName);
    button = (Button) findViewById(R.id.button);
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
                dbHelper.COLUMN_BillEP1,
                dbHelper.COLUMN_BillEP2,
                dbHelper.COLUMN_BillEP3,
                dbHelper.COLUMN_BillFLAT,
                dbHelper.COLUMN_UsageEP1,
                dbHelper.COLUMN_UsageEP2,
                dbHelper.COLUMN_UsageEP3,
                dbHelper.COLUMN_UsageFLAT,

        };

        int[] to = new int[]{
                R.id.textViewMailId,
                R.id.textViewBillEP1,
                R.id.textViewBillEP2,
                R.id.textViewBillEP3,
                R.id.textViewBillFlat,
                R.id.textViewUsageEP1,
                R.id.textViewUsageEP2,
                R.id.textViewUsageEP3,
                R.id.textViewUsageFlat,
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

            // Get the users name from this row in the database.
            String userEmail =
                    cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.COLUMN_MAILID));
            Toast.makeText(getApplicationContext(),
                    userEmail, Toast.LENGTH_SHORT).show();
        }
    });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DumpList.this, LoginActivity.class));
            }
        });

    }


}