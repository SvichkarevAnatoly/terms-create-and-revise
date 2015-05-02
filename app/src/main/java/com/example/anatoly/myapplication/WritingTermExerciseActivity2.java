package com.example.anatoly.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anatoly.myapplication.structure.DictEntry;
import com.example.anatoly.myapplication.structure.Dictionary;

import java.util.Iterator;

public class WritingTermExerciseActivity2 extends Activity implements View.OnClickListener {
    private EditText etTerm;
    private TextView tvDefinition;

    private Iterator dictIterator;
    private String correctTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_term_exercise2);

        etTerm = (EditText) findViewById(R.id.et_wte_term);
        tvDefinition = (TextView) findViewById(R.id.tv_wte_definition2);

        final Button btnNext = (Button) findViewById(R.id.btn_wte_next2);
        btnNext.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String[] termDefinitions = extras.getStringArray("terms");
            final Dictionary dictionary = new Dictionary(termDefinitions);
            dictIterator = dictionary.iterator();

            if (dictIterator.hasNext()) {
                final DictEntry dictEntry = (DictEntry) dictIterator.next();

                correctTerm = dictEntry.getTerm();
                tvDefinition.setText(dictEntry.getDefinition());
            } else {
                Toast.makeText(this, "Dictionary end",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        final String fieldText = etTerm.getText().toString().trim();
        if (!fieldText.equals(correctTerm)) {
            Toast.makeText(this, "Not equals",
                    Toast.LENGTH_SHORT).show();
            return;
        } else {
            etTerm.getText().clear();
        }

        if (dictIterator.hasNext()) {
            final DictEntry dictEntry = (DictEntry) dictIterator.next();

            correctTerm = dictEntry.getTerm();
            tvDefinition.setText(dictEntry.getDefinition());
        } else {
            Toast.makeText(this, "Dictionary end",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
