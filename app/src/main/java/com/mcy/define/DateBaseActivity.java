package com.mcy.define;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class DateBaseActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnInsert, btnDelete, btnUpdate, btnQuery;
    TextView textView;
    DataBaseUtil dataBaseUtil;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_base);
        btnInsert = findViewById(R.id.insert);
        btnDelete = findViewById(R.id.delete);
        btnUpdate = findViewById(R.id.update);
        btnQuery = findViewById(R.id.query);

        btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        textView = findViewById(R.id.tvHome);
        dataBaseUtil = DataBaseUtil.getInstance(this);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert:
                count++;
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "macy" + count);
                contentValues.put("age", count);
                long insertDB = dataBaseUtil.insertDB(contentValues);
                textView.setText("insert " + insertDB);
                break;
            case R.id.delete:
                String[] conditions = new String[]{"macy2", "2"};
                int deleteDB = dataBaseUtil.deleteDB(conditions);
                textView.setText("delete " + deleteDB);
                break;
            case R.id.update:
                ContentValues contentValues1 = new ContentValues();
                contentValues1.put("name", "macy999");
                contentValues1.put("age", 999);
                String[] conditions1 = new String[]{"macy1", "1"};
                int updateDB = dataBaseUtil.updateDB(contentValues1, conditions1);
                textView.setText("update " + updateDB);
                break;
            case R.id.query:
                List<Student> list = dataBaseUtil.queryDB();
                textView.setText("update " + list.toString());
                break;
        }
    }
}
