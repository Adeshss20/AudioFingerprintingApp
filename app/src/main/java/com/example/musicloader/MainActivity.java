package com.example.musicloader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private String[] songNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        ArrayList<File> songs=readSongs((Environment.getExternalStorageDirectory()));
        songNames=new String[songs.size()];
        for(int i=0;i<songs.size();i++)
        {
            songNames[i]=songs.get(i).getName().toString().replace(".mp3","");
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.song_layout,R.id.textView,songNames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });
    }
    private ArrayList<File> readSongs(File root)
    {
        ArrayList<File> l=new ArrayList<File>();
        File[] files=root.listFiles();
        for(File file:files)
        {
            if(file.isDirectory())
            {
                l.addAll(readSongs(file));
            }
            else
            {
                if(file.getName().endsWith(".mp3"))
                {
                    l.add(file);
                }
            }
        }
        return l;
    }
}