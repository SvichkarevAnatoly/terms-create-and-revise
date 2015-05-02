package com.example.anatoly.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends Activity {
    String[] values = {
            "Android - an open-source operating system used for smartphones and tablet computers.",
            "Google - search for information about (someone or something) on the Internet using the search engine Google."
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String text = getSdcardText();

        TextView tv = (TextView) findViewById(R.id.text_view);
        tv.setText(text);

        ListView lvMain = (ListView) findViewById(R.id.lvMain);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, values);

        lvMain.setAdapter(adapter);
    }

    private String getSdcardText() {
        final String filePath = "fastnote/2013-06-19-20-43-32.txt";

        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, filePath);

        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text.toString();
    }
}