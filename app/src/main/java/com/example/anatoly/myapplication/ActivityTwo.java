package com.example.anatoly.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ActivityTwo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dict_list_view);

        ListView listView = (ListView) findViewById(R.id.lvMain);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String[] terms = extras.getStringArray("terms");
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, terms);
            listView.setAdapter(adapter);
        }
    }
}
