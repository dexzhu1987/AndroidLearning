package com.example.huntertsai.firebaseinclassdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArtistDetailsActivity extends AppCompatActivity {
    private static String ARTISTID = "artistID";

    private Artist artist;
    private DatabaseReference mFirebaseDataBase;
    private TextView mArtistName, mGenre;
    private EditText mTrack;
    private ListView mListTracks;
    private List<Track> mTracks;
    private String artistID;
    private Spinner mSpinner;


    public static Intent newIntent(Context context, String artistId){
        Intent intent = new Intent(context, ArtistDetailsActivity.class);
        intent.putExtra(ARTISTID, artistId);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_details);

       artistID = getIntent().getStringExtra(ARTISTID);

       mFirebaseDataBase = FirebaseDatabase.getInstance().getReference("artists");
       mArtistName = findViewById(R.id.artist_name_detail);
       mGenre = findViewById(R.id.genre_details);
       mTrack = findViewById(R.id.track_details);
       mListTracks = findViewById(R.id.list_view_track);
       mSpinner = findViewById(R.id.spinner_details);
       mTracks = new ArrayList<>();

       mFirebaseDataBase.child(artistID).addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               artist = dataSnapshot.getValue(Artist.class);
               mArtistName.setText(artist.getName());
               mGenre.setText(artist.getGenre());
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseDatabase.getInstance().getReference().child("tracks").child(artistID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTracks.clear();
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Track track = artistSnapshot.getValue(Track.class);
                    mTracks.add(track);
                }
                TracksArrayAdapter tracksArrayAdapter = new TracksArrayAdapter(ArtistDetailsActivity.this, mTracks,artistID);
                mListTracks.setAdapter(tracksArrayAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //error happens
                if (databaseError != null){
                    Toast(databaseError.getMessage());
                }
            }
        });
    }


    public void add_Track(View view) {
        String rating = mSpinner.getSelectedItem().toString();
        String trackName = mTrack.getText().toString().trim();
        String id = mFirebaseDataBase.push().getKey(); //push()->auto gen key and get key
        Track track = new Track(trackName,rating,id);
        Toast(trackName + " Added");

        FirebaseDatabase.getInstance().getReference().child("tracks").child(artistID).child(id).setValue(track);

        mTrack.setText("");
        mSpinner.setSelection(0);

    }

    private void Toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
