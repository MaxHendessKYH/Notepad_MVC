package com.example.notepad_mvc.Views;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.notepad_mvc.R;
import com.example.notepad_mvc.controllers.MainActivity;
import com.example.notepad_mvc.controllers.Navigator;
import com.example.notepad_mvc.models.DataManager;
import com.example.notepad_mvc.models.Note;

import java.util.List;

public class NoteAdapter extends ArrayAdapter {
    private MainActivity mainActivity;

    public  NoteAdapter(Context context, List<Note> notes, MainActivity mainActivity){
        super(context,0,notes);
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertview, @NonNull ViewGroup parent){
        View view = convertview;
        if( view == null)
        {
            view = LayoutInflater.from(getContext()).inflate(R.layout.listitem_notes, parent, false);
        }

        Note note = (Note) getItem(position);
        TextView fileName = view.findViewById(R.id.tv_file_name);
        Button edit_file_btn = view.findViewById(R.id.btn_edit_file);
        Button delete_file_btn = view.findViewById(R.id.btn_delete_file);
        fileName.setText(note.getFileName());

        edit_file_btn.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                Navigator nav = new Navigator(getContext());
                nav.goToNoteActivity(note);
            }
        });

        delete_file_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               confirmDelete(note);
            }
        });
        return view;
    }
    private void confirmDelete(Note note)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder( mainActivity );
        builder.setTitle("Are you sure you want to delete\n" + note.getFileName() + "?" )
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences prefs = getContext().getSharedPreferences("com.example.com.example.notepad_mvc.prefs", MODE_PRIVATE);
                        DataManager.deletePreference( note , prefs);
                        mainActivity.setAdapter();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }
}
