package com.anatoly.tcat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anatoly.tcat.structure.Dictionary;
import com.anatoly.tcat.util.FileChooser;

public class MainActivity extends Activity implements View.OnClickListener {
    private FileChooser fileChooser = new FileChooser(this);

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
                fileChooser.show();
                isOpenDict = true;
                break;
            case R.id.btn_dict_list_view:
                if (isOpenDict) {
                    intent = new Intent(this, DictionaryViewActivity.class);
                    intent.putExtra("terms", dictionary.toStringArray());
                    startActivity(intent);
                } else {
                    Toast.makeText(
                            this, "Please select dictionary file.",
                            Toast.LENGTH_SHORT)
                         .show();
                }
                break;
            case R.id.btn_writing_term_exercise:
                if (isOpenDict) {
                    intent = new Intent(
                            this,
                            WritingTermExerciseActivity.class);
                    intent.putExtra("terms", dictionary.toStringArray());
                    startActivity(intent);
                } else {
                    Toast.makeText(
                            this, "Please select dictionary file.",
                            Toast.LENGTH_SHORT)
                         .show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        fileChooser.parseResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}