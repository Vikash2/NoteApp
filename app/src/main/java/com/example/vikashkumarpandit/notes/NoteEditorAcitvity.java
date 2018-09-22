package com.example.vikashkumarpandit.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteEditorAcitvity extends AppCompatActivity {

    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor_acitvity);

        final EditText noteText = findViewById(R.id.noteEditorText);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId",-1);

        if(noteId != -1)
        {
            noteText.setText(MainActivity.notes.get(noteId));
        }
        else
        {
            MainActivity.notes.add(" ");
            noteId = MainActivity.notes.size()-1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }

        noteText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                MainActivity.notes.set(noteId,charSequence.toString());
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.vikashkumarpandit.notesapp", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(MainActivity.notes);
                sharedPreferences.edit().putStringSet("Notes",set).apply();


                Snackbar.make(findViewById(R.id.noteEditorText), "Note added.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
