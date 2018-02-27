package com.example.huntertsai.firebaseinclassdemo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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

public class TracksArrayAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> tracks;
    private List<String> tracksId;
    private AlertDialog mAlertDialog;
    private String artistID;

    public TracksArrayAdapter(@NonNull Context context, @NonNull List<String> objects, List<String> tracksId, String artistID) {
        super(context, R.layout.track_list_item, objects);
        this.context = context;
        this.tracks = objects;
        this.tracksId = tracksId;
        this.artistID = artistID;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final String trackname = tracks.get(position);
        Log.i("TracksAdapter", trackname);
        final String trackID = tracksId.get(position);
        Log.i("TracksAdapter", trackID);
        String message = "starts: ";
        for (int i=0; i<tracks.size(); i++){
            message += " : " + tracks.get(i);
        }

        Log.i("TracksAdapter", message);
        String message2 = "starts: ";
        for (int i=0; i<tracksId.size(); i++){
            message2 += " : " + tracksId.get(i);
        }
        Log.i("TracksAdapter", message2);

        LayoutInflater layoutInflater = ((Activity) this.context).getLayoutInflater();
        View listView = layoutInflater.inflate(R.layout.track_list_item, null);
        TextView tv_name = listView.findViewById(R.id.artist_track_list);

        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText nameInput = new EditText(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                nameInput.setLayoutParams(params);
                nameInput.setText(trackname);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Edit information");
                builder.setPositiveButton("Updated", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameInput.getText().toString();
                        FirebaseDatabase.getInstance().getReference().child("artists").child(artistID).child ("track").child(trackID).setValue(name);
                        Toast.makeText(getContext(), name +  " is updated", Toast.LENGTH_LONG)
                                .show();
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameInput.getText().toString();
                        FirebaseDatabase.getInstance().getReference().child("artists").child(artistID).child("track").child(trackID).setValue(null);
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
                mAlertDialog.setView(nameInput);
                mAlertDialog.show();
            }
        });

        tv_name.setText(trackname);

        return listView;

    }
}
