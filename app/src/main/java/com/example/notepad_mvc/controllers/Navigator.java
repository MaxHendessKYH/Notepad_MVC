package com.example.notepad_mvc.controllers;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.example.notepad_mvc.models.Note;
public class Navigator {

    private Context context;

    public Navigator(Context context) {
        this.context = context;
    }

    public void goToNoteActivity(Note note )
    {
        Intent intent = new Intent(context, NoteActivity.class);
        if(note != null) {
            intent.putExtra("id", note);
        }
        context.startActivity(intent);
    }
    public void finishActivity()
    {
        if (context instanceof NoteActivity) {
            ((Activity) context).finish();
        }
    }
}
