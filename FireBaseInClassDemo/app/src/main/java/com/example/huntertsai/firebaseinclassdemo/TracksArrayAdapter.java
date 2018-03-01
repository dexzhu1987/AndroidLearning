package com.example.huntertsai.firebaseinclassdemo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Dexter on 2018-02-26.
 */

public class TracksArrayAdapter extends ArrayAdapter<Track> {
    private Context context;
    private List<Track> tracks;
    private AlertDialog mAlertDialog;
    private String artistID;

    public  TracksArrayAdapter(@NonNull Context context, @NonNull List<Track> objects, String artistID) {
        super(context, R.layout.track_list_item, objects);
        this.context = context;
        this.tracks = objects;
        this.artistID = artistID;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Track track = tracks.get(position);

        LayoutInflater layoutInflater = ((Activity) this.context).getLayoutInflater();
        View listView = layoutInflater.inflate(R.layout.track_list_item, null);
        TextView tv_name = listView.findViewById(R.id.artist_track_list);
        TextView track_rating = listView.findViewById(R.id.track_rating);


        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText nameInput = new EditText(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                nameInput.setLayoutParams(params);
                nameInput.setText(track.getTitle());

                final Spinner ratingInput = new Spinner(getContext());
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                ratingInput.setLayoutParams(params2);
                List<String> list = Arrays.asList(getContext().getResources().getStringArray(R.array.rating));
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ratingInput.setAdapter(spinnerAdapter);
                for (int i=0; i<list.size(); i++){
                    if (track.getRating().equals(list.get(i))){
                       int position=i;
                       ratingInput.setSelection(position);
                    }
                }
                spinnerAdapter.notifyDataSetChanged();

                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setLayoutParams(params);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.addView(nameInput);
                linearLayout.addView(ratingInput);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Edit information");
                builder.setPositiveButton("Updated", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameInput.getText().toString();
                        String rating =  ratingInput.getSelectedItem().toString();
                        FirebaseDatabase.getInstance().getReference().child("tracks").child(artistID).child (track.getTrackId()).child("title").setValue(name);
                        FirebaseDatabase.getInstance().getReference().child("tracks").child(artistID).child (track.getTrackId()).child("rating").setValue(rating);
                        Toast.makeText(getContext(), name +  " is updated", Toast.LENGTH_LONG)
                                .show();
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameInput.getText().toString();
                        FirebaseDatabase.getInstance().getReference().child("tracks").child(artistID).child(track.getTrackId()).setValue(null);
                        Toast.makeText(getContext(), name +  " is deleted", Toast.LENGTH_LONG)
                                .show();
                    }
                });

                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                mAlertDialog = builder.create();
                mAlertDialog.setView(linearLayout);
                mAlertDialog.show();
            }
        });

        tv_name.setText(track.getTitle());
        track_rating.setText(track.getRating());

        return listView;

    }
}
