package com.example.anatoly.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class WritingTermExerciseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_term_exercise);

        final TextView tvTerm = (TextView) findViewById(R.id.tv_wte_term);
        final TextView tvDefinition = (TextView) findViewById(R.id.tv_wte_definition);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String[] termDefinitions = extras.getStringArray("terms");
            final String termDefinition = termDefinitions[0];

            tvTerm.setText(termDefinition);
            tvDefinition.setText(termDefinition);
        }
    }
}
