package com.anatoly.tcat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anatoly.tcat.structure.Dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final int FILE_SELECT_CODE = 0;

    private String filePath;
    private boolean isOpenDict = false;

    private Dictionary dictionary = new Dictionary();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSelectDict = (Button) findViewById(R.id.btn_select_dict_file);
        btnSelectDict.setOnClickListener(this);

        Button btnShowDict = (Button) findViewById(R.id.btn_dict_list_view);
        btnShowDict.setOnClickListener(this);

        Button btnWTExercise = (Button) findViewById(R.id.btn_writing_term_exercise);
        btnWTExercise.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_select_dict_file:
                showFileChooser();
                isOpenDict = true;
                break;
            case R.id.btn_dict_list_view:
                if (isOpenDict) {
                    intent = new Intent(this, DictionaryViewActivity.class);
                    intent.putExtra("terms", dictionary.toStringArray());
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Please select dictionary file.",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_writing_term_exercise:
                if (isOpenDict) {
                    intent = new Intent(this, WritingTermExerciseActivity.class);
                    intent.putExtra("terms", dictionary.toStringArray());
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Please select dictionary file.",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();

                    // Get the path
                    try {
                        filePath = getPath(this, uri);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                    fillDictionaryFromFile();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void fillDictionaryFromFile() {
        File file = new File(filePath);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                dictionary.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor;
            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }
}