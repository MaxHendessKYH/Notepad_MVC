package com.example.notepad_mvc.models;

import java.io.Serializable;

public class Note implements Serializable {
    //Serializable -> Navigator can pass notes to noteActivity
    String filename;
    String text;

    public Note(String filename, String text) {
        this.filename = filename;
        this.text = text;
    }

    public String getFileName() {
        return filename;
    }

    public String getText() {
        return text;
    }

    public void setFileName(String fileName) {
        this.filename = fileName;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Note{" +
                "fileName='" + filename + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
