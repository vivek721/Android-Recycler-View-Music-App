package com.vivek.musicplayer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList allMusicDetails = new ArrayList<MusicDetails>();
    RVClickListener listener;
    RecyclerView musicView;
    MusicAdapter adapter;
    Boolean isLinearLayout = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // the listener to show toast and access the list of song names
        listener = (view, position) -> {
            TextView name = (TextView) view.findViewById(R.id.SongName);
            Toast.makeText(this, "Playing: " + name.getText(), Toast.LENGTH_SHORT).show();
        };
        adapter = new MusicAdapter(allMusicDetails, listener, isLinearLayout);
        musicView = findViewById(R.id.rv1);

        // Passing the music details as objects : 
        MusicDetails md1 = new MusicDetails("Blvd. Of Broken Dreams", "Green Day", "https://en.wikipedia.org/wiki/Green_Day", "https://en.wikipedia.org/wiki/Boulevard_of_Broken_Dreams_(Green_Day_song)", R.drawable.greenday_bulevard, "https://www.youtube.com/watch?v=Soa3gO7tL-c");
        MusicDetails md5 = new MusicDetails("We Are The Champions", "Queen", "https://en.wikipedia.org/wiki/Queen_(band)", "https://en.wikipedia.org/wiki/We_Are_the_Champions", R.drawable.queen_we_are_the_champion, "https://www.youtube.com/watch?v=KXw8CRapg7k");
        MusicDetails md3 = new MusicDetails("No Time To Die", "Billie Eilish", "https://en.wikipedia.org/wiki/Billie_Eilish", "https://en.wikipedia.org/wiki/No_Time_to_Die", R.drawable.billie_notime, "https://www.youtube.com/watch?v=BboMpayJomw");
        MusicDetails md4 = new MusicDetails("Lose Yourself", "Eminem", "https://en.wikipedia.org/wiki/Eminem", "https://en.wikipedia.org/wiki/Lose_Yourself", R.drawable.lose_yourself, "https://www.youtube.com/watch?v=_Yhyp-_hX2s");
        MusicDetails md2 = new MusicDetails("Stressed Out", "twenty one pilots", "https://en.wikipedia.org/wiki/Twenty_One_Pilots", "https://en.wikipedia.org/wiki/Stressed_Out", R.drawable.stressed_out, "https://www.youtube.com/watch?v=pXRviuL6vMY");
        MusicDetails md6 = new MusicDetails("Cradles", "Sub Urban", "https://en.wikipedia.org/wiki/Sub_Urban_(musician)", "https://en.wikipedia.org/wiki/Cradle_Song", R.drawable.cradles, "https://www.youtube.com/watch?v=KBtk5FUeJbk");
        MusicDetails md7 = new MusicDetails("Happy ", "Pharrell Williams", "https://en.wikipedia.org/wiki/Pharrell_Williams", "https://en.wikipedia.org/wiki/Happy_(Pharrell_Williams_song)", R.drawable.happy, "https://www.youtube.com/watch?app=desktop&feature=share&v=ZbZSe6N_BXs");
        MusicDetails md8 = new MusicDetails("Outrunning", "Alec Benjamin", "https://en.wikipedia.org/wiki/Alec_Benjamin", "https://genius.com/Alec-benjamin-outrunning-karma-lyrics", R.drawable.outrunning_karma, "https://www.youtube.com/watch?v=e4zT0dJnEVQ");
        allMusicDetails.add(md1);
        allMusicDetails.add(md2);
        allMusicDetails.add(md3);
        allMusicDetails.add(md4);
        allMusicDetails.add(md5);
        allMusicDetails.add(md6);
        allMusicDetails.add(md7);
        allMusicDetails.add(md8);

        musicView.setHasFixedSize(true);
        musicView.setAdapter(adapter);
        musicView.setLayoutManager(new LinearLayoutManager(this));

        // For creating action bar icon
        getSupportActionBar().setTitle("Music App"); // set the top title
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.music);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    // method for creating options menu and inflating from mainmenu layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    // method for handling options menu click operations
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.list:
                isLinearLayout = true;
                adapter = new MusicAdapter(allMusicDetails, listener, isLinearLayout);
                musicView.setAdapter(adapter);
                LinearLayoutManager llm = new LinearLayoutManager(this);
                musicView.setLayoutManager(llm);
                return true;
            case R.id.grid:
                isLinearLayout = false;
                adapter = new MusicAdapter(allMusicDetails, listener, isLinearLayout);
                musicView.setAdapter(adapter);
                GridLayoutManager glm = new GridLayoutManager(this, 2);
                musicView.setLayoutManager(glm);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}