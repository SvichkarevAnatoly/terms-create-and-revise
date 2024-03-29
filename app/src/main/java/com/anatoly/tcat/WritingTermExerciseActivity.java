package com.anatoly.tcat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anatoly.tcat.structure.DictEntry;
import com.anatoly.tcat.structure.Dictionary;

import java.util.Iterator;

public class WritingTermExerciseActivity extends Activity implements View.OnClickListener {
    private EditText etTerm;
    private TextView tvDefinition;
    private Button btnNext;

    private Iterator dictIterator;
    private String correctTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_term_exercise);

        etTerm = (EditText) findViewById(R.id.et_wte_term);
        tvDefinition = (TextView) findViewById(R.id.tv_wte_definition);

        btnNext = (Button) findViewById(R.id.btn_wte_next);
        btnNext.setOnClickListener(this);

        final Intent intent = getIntent();
        final Dictionary dictionary = intent.getParcelableExtra("terms");
        dictIterator = dictionary.iterator();

        if (dictIterator.hasNext()) {
            final DictEntry dictEntry = (DictEntry) dictIterator.next();

            correctTerm = dictEntry.getTerm();
            tvDefinition.setText(dictEntry.getDefinition());
        } else {
            Toast.makeText(
                    this, "Dictionary end",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        final String fieldText = etTerm.getText().toString().trim();
        if (!fieldText.equals(correctTerm)) {
            Toast.makeText(
                    this, "Not equals",
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
            etTerm.setVisibility(View.GONE);
            btnNext.setEnabled(false);
            tvDefinition.setText("");

            Toast.makeText(
                    this, "Dictionary end",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
