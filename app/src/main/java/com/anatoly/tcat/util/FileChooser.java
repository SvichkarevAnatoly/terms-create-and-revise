package com.anatoly.tcat.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

import com.anatoly.tcat.MainActivity;
import com.anatoly.tcat.structure.Dictionary;

import java.io.File;
import java.net.URISyntaxException;

public class FileChooser {
    private static final int FILE_SELECT_CODE = 0;

    private final MainActivity main;

    public FileChooser(MainActivity main) {
        this.main = main;
    }

    public void show() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            main.startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(
                    main, "Please install a File Manager.",
                    Toast.LENGTH_SHORT)
                 .show();
        }
    }

    public void parseResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();

                    File file = null;
                    try {
                        String filePath = getPath(main, uri);
                        file = new File(filePath);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                    final Dictionary dictionary = FileDictionaryParser.parse(
                            file);
                    main.setDictionary(dictionary);
                }
                break;
        }
    }

    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor;
            try {
                cursor = context.getContentResolver()
                                .query(
                                        uri,
                                        projection,
                                        null,
                                        null,
                                        null);
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
