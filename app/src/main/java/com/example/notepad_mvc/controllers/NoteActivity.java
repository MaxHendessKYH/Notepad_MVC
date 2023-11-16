package com.example.notepad_mvc.controllers;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.notepad_mvc.models.DataManager;
import com.example.notepad_mvc.models.Note;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.notepad_mvc.databinding.ActivityNoteBinding;

import com.example.notepad_mvc.R;
import com.google.gson.Gson;

public class NoteActivity extends AppCompatActivity {
Button saveBtn;
Button saveAsBtn;
Button homeBtn;
EditText editText;
TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        
        header = findViewById(R.id.tv_header);
        editText = findViewById(R.id.editText);
        saveBtn = findViewById(R.id.save_button);
        homeBtn = findViewById(R.id.home_button);
        saveAsBtn = findViewById(R.id.save_as_button);
        Navigator navigator = new Navigator(this);
        Note note = (Note) getIntent().getSerializableExtra("id");
        if(!note.getText().isEmpty() && note.getText() != null) {
            header.setText(note.getFileName());
            editText.setText(note.getText());
        }
        else
        {
            header.setText("New File");
        }
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(note.getFileName().isEmpty()) {
                    saveNewFile();
                }else
                {
                    saveFile(note.getFileName(), editText.getText().toString());
                    Toast.makeText(NoteActivity.this, "File Saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        saveAsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewFile();
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // finish();
                navigator.finishActivity();
            }
        });
    }
    private void saveNewFile()
    {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final EditText input = new EditText(this);
            builder.setView(input);
            builder.setTitle("Enter file name")
                    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           saveFile(input.getText().toString(), editText.getText().toString());
                            header.setText(input.getText().toString());
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Kill dialog
                        }
                    }).show();
    }
    private void saveFile(String fileName, String text)
    {
        SharedPreferences prefs = getSharedPreferences("com.example.com.example.notepad_mvc.prefs", MODE_PRIVATE);
        DataManager.savePreference( fileName, text , prefs);
    }
       /* give user option to save if changes has been made when he presses home btn
                TODO fix this spagett if I have time
                SharedPreferences prefs  = getSharedPreferences("com.example.com.example.notepad_mvc.prefs", MODE_PRIVATE);
                Gson gson = new Gson();
                // if file exsists in Prefs
                if(prefs.contains(header.getText().toString())) {
                  String json = prefs.getString(header.getText().toString(), "");
                    Note obj = gson.fromJson(json, Note.class);

                    // if changes has been madde dialog else no dialog
                    if (!editText.getText().toString().equals(obj.getText())) {
                        confirmLeaveDialog();
                    }else {
                        finish();
                    }
                }else // if file doesnt exist in prefs
                {
                    // if changes hase been made
                    if (!editText.getText().toString().isEmpty()) {
                        confirmLeaveDialog();
                    }else {
                        finish();
                    }
                }
             */
  /*  private void confirmLeaveDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to discard your unsaved changes?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Kill dialog
                    }
                }).show();
    }*/
}