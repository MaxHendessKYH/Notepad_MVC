package com.example.notepad_mvc.models;

import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class DataManager extends AppCompatActivity {
    private static ArrayList<Note> notes = new ArrayList<Note>();
public static ArrayList<Note> getAllSharedPreferences(SharedPreferences prefs )
{
    DataManager.notes.clear();
    java.util.Map<String, ?> allPrefs = prefs.getAll();
    for (String key : allPrefs.keySet()) {
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        Note newNote = gson.fromJson(json, Note.class);
        DataManager.notes.add(newNote);
    }
    return notes;
}
    public static void savePreference( String fileName, String text, SharedPreferences prefs)
    {
        Note note = new Note(fileName, text);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(note);
        editor.putString(fileName, json);
        editor.apply();
    }
    public static void deletePreference( Note note , SharedPreferences prefs)
    {
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.remove(note.getFileName());
        prefsEditor.commit();
    }
}