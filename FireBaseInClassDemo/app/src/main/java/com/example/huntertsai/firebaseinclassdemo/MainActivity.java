package com.example.huntertsai.firebaseinclassdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText meditText;
    private DatabaseReference mdatabaseReference;
    private Spinner mSpinner;
    private ListView mListView;
    private ArrayList<Artist> mArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meditText = findViewById(R.id.artist_name_input);
        mSpinner = findViewById(R.id.spinner_genre);
        mListView = findViewById(R.id.list_view);
        mArtist = new ArrayList<>();

        mdatabaseReference = FirebaseDatabase.getInstance().getReference("artists");//root

    }

    @Override
    protected void onStart() {
        super.onStart();

        mdatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mArtist.clear();

                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Artist artist = artistSnapshot.getValue(Artist.class);
                    mArtist.add(artist);
                }

                ArtistArrayAdapter mArtistArrayAdapter = new ArtistArrayAdapter(MainActivity.this, mArtist);
                mListView.setAdapter(mArtistArrayAdapter);
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

    public void add_Artist(View view) {
        String artist_name = meditText.getText().toString().trim();
        String genres = mSpinner.getSelectedItem().toString();
        String id = mdatabaseReference.push().getKey(); //push()->auto gen key and get key
        Toast(artist_name + " Added");
        //get ref to database

        Artist mArtist = new Artist(id, artist_name, genres);

        //store key value as a child of the root
        mdatabaseReference.child(id).setValue(mArtist);

        meditText.setText("");
        mSpinner.setSelection(0);

    }

    private void Toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
