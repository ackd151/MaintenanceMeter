package com.ackd151.maintenancemeter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class NotesSpecs extends AppCompatActivity {

    EditText note;
    int profileIndex;
    Machine profile;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_specs);

        note = findViewById(R.id.notes);

        profileIndex = getIntent().getIntExtra("profileIndex", -1);
        profile = PreferencesMgr.getProfile(this, profileIndex);

        filename = profileIndex + ".txt";
        loadNotes();
    }

    public void saveNotes(View v) {
        String content = note.getText().toString();
        try {
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(this.openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(content);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
        finish();
    }

    public void loadNotes() {
        String content = "";

        try {
            InputStream in = this.openFileInput(filename);
            if ( in != null ) {
                InputStreamReader isr = new InputStreamReader(in);
                BufferedReader br = new BufferedReader(isr);
                String str;
                StringBuilder sb = new StringBuilder();
                while ( (str = br.readLine()) != null ) {
                    sb.append(str + "\n");
                }
                in.close();
                content = sb.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        note.setText(content);
    }
}
