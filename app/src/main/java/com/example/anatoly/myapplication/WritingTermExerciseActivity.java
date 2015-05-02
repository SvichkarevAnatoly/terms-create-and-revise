package com.example.anatoly.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anatoly.myapplication.structure.DictEntry;
import com.example.anatoly.myapplication.structure.Dictionary;

import java.util.Iterator;

public class WritingTermExerciseActivity extends Activity implements View.OnClickListener {
    private TextView tvTerm;
    private TextView tvDefinition;

    private Iterator dictIterator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_term_exercise);

        tvTerm = (TextView) findViewById(R.id.tv_wte_term);
        tvDefinition = (TextView) findViewById(R.id.tv_wte_definition);

        final Button btnNext = (Button) findViewById(R.id.btn_wte_next);
        btnNext.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String[] termDefinitions = extras.getStringArray("terms");
            final Dictionary dictionary = new Dictionary(termDefinitions);
            dictIterator = dictionary.iterator();
            btnNext.callOnClick();
        }
    }

    @Override
    public void onClick(View v) {
        if (dictIterator.hasNext()) {
            final DictEntry dictEntry = (DictEntry) dictIterator.next();

            tvTerm.setText(dictEntry.getTerm());
            tvDefinition.setText(dictEntry.getDefinition());
        } else {
            Toast.makeText(this, "Dictionary end",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
