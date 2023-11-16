// Mvc
package com.example.notepad_mvc.controllers;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.example.notepad_mvc.R;
import com.example.notepad_mvc.models.DataManager;
import com.example.notepad_mvc.models.Note;
import com.example.notepad_mvc.Views.NoteAdapter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Button newNoteBtn;
ListView listView;
SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.notes_list);
        newNoteBtn = findViewById(R.id.new_note_button);
        setAdapter();

        newNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Navigator nav = new Navigator( MainActivity.this);
              Note note = new Note ("", "");
              nav.goToNoteActivity(note);
            }
        });
    }

    protected void onResume() {
        super.onResume();
        setAdapter();
    }

    public void setAdapter()
    {
        prefs = getSharedPreferences("com.example.com.example.notepad_mvc.prefs", MODE_PRIVATE);
        ArrayList<Note> notes = DataManager.getAllSharedPreferences(prefs);
        NoteAdapter adapter = new NoteAdapter(this, notes, this);
        listView.setAdapter(adapter);
    }
}