package com.anatoly.tcat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.anatoly.tcat.structure.Dictionary;

public class DictionaryViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dict_list_view);

        ListView listView = (ListView) findViewById(R.id.lvMain);

        final Intent intent = getIntent();
        Dictionary dictionary = intent.getParcelableExtra("terms");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dictionary.toStringArray());
        listView.setAdapter(adapter);
    }
}
