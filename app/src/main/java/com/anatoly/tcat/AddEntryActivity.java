package com.anatoly.tcat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anatoly.tcat.structure.DictEntry;
import com.anatoly.tcat.structure.Dictionary;

public class AddEntryActivity extends Activity implements View.OnClickListener {
    private EditText etTerm;
    private EditText etDefinition;

    private Dictionary dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        etTerm = (EditText) findViewById(R.id.et_add_term);
        etDefinition = (EditText) findViewById(R.id.et_add_definition);

        final Button btnAddEntry = (Button) findViewById(R.id.btn_add_entry_to_dict);
        btnAddEntry.setOnClickListener(this);

        final Intent intent = getIntent();
        dictionary = intent.getParcelableExtra("terms");
    }

    @Override
    public void onClick(View v) {
        final String term = etTerm.getText().toString();
        final String definition = etDefinition.getText().toString();

        final DictEntry dictEntry = new DictEntry(term, definition);

        // TODO: check
        if(! dictionary.contains(dictEntry)) {
            dictionary.add(dictEntry);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("newDictionary", dictionary);
            setResult(RESULT_OK, resultIntent);
            finish();
        } else {
            Toast.makeText(
                    this, "This entry already in dictionary",
                    Toast.LENGTH_SHORT)
                 .show();
        }
    }
}
