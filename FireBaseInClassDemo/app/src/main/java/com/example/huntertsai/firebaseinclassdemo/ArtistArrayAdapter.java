package com.example.huntertsai.firebaseinclassdemo;

import android.app.Activity;
import android.app.Dialog;
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
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

/**
 * Created by huntertsai on 2018-02-26.
 */

public class ArtistArrayAdapter extends ArrayAdapter<Artist>{

    private Context context;
    private List<Artist> martists;
    private AlertDialog mAlertDialog;

    public ArtistArrayAdapter(@NonNull Context context, @NonNull List<Artist> objects) {
        super(context, R.layout.artist_list_layout, objects);
        this.context = context;
        this.martists = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Artist artist = martists.get(position);
        final String artist_name = artist.getName();
        final String artist_genre = artist.getGenre();

        LayoutInflater layoutInflater = ((Activity) this.context).getLayoutInflater();
        View listView = layoutInflater.inflate(R.layout.artist_list_layout, null);
        TextView tv_name = listView.findViewById(R.id.artist_name_list);
        TextView tv_genre = listView.findViewById(R.id.artist_genre_list);
        final LinearLayout linearLayout = listView.findViewById(R.id.item_whole);



        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ArtistDetailsActivity.newIntent(getContext(),artist.getId());
                getContext().startActivity(intent);
            }
        });

        linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final EditText nameInput = new EditText(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                nameInput.setLayoutParams(params);
                nameInput.setText(artist_name);

                final Spinner genreInput = new Spinner(getContext());
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                genreInput.setLayoutParams(params2);
                List<String> list = Arrays.asList(getContext().getResources().getStringArray(R.array.genres));
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                genreInput.setAdapter(spinnerAdapter);
                for (int i=0; i<list.size(); i++){
                    if (artist.getGenre().equals(list.get(i))){
                        int position=i;
                        genreInput.setSelection(position);
                    }
                }
                spinnerAdapter.notifyDataSetChanged();

                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setLayoutParams(params);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.addView(nameInput);
                linearLayout.addView(genreInput);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Edit information");
                builder.setPositiveButton("Updated", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameInput.getText().toString();
                        String genre = genreInput.getSelectedItem().toString();
                        FirebaseDatabase.getInstance().getReference().child("artists").child(artist.getId()).child("name").setValue(name);
                        FirebaseDatabase.getInstance().getReference().child("artists").child(artist.getId()).child("genre").setValue(genre);
                        Toast.makeText(getContext(), name +  " is updated", Toast.LENGTH_LONG)
                                .show();
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameInput.getText().toString();
                        FirebaseDatabase.getInstance().getReference().child("artists").  child(artist.getId()).setValue(null);
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
                return true;
            }
        });



        tv_name.setText(artist_name);
        tv_genre.setText(artist_genre);

        return listView;

    }
}
