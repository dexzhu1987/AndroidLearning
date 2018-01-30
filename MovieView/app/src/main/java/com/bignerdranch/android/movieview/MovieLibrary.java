package com.bignerdranch.android.movieview;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by dexunzhu on 2018-01-29.
 */

public class MovieLibrary {
    private static MovieLibrary sMovieLibrary;

    private List<Movie> mMovies;

    public static MovieLibrary get(Context context){
        if (sMovieLibrary == null){
            sMovieLibrary = new MovieLibrary(context);
        }
        return sMovieLibrary;
    }

    private MovieLibrary(Context context){
        mMovies = new ArrayList<>();

        List<Integer> drawableId = new ArrayList();
        drawableId.add(R.drawable.catch_me_if_you_can);
        drawableId.add(R.drawable.flight_club);
        drawableId.add(R.drawable.forrest_gump);
        drawableId.add(R.drawable.good_will_hunting);
        drawableId.add(R.drawable.pulp_fiction);
        drawableId.add(R.drawable.the_godfather);
        drawableId.add(R.drawable.the_hangover);
        drawableId.add(R.drawable.the_shawshank_redemption);
        drawableId.add(R.drawable.titanic);

//        Field[] ID_Fields = R.drawable.class.getFields();
//        int[] resArray = new int[ID_Fields.length];
//        for(int i = 0; i < ID_Fields.length; i++) {
//            try {
//                resArray[i] = ID_Fields[i].getInt(null);
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//        }
//
//        for (int i=0; i<resArray.length; i++){
//            drawableId.add(resArray[i]);
//        }

        for (int n = 0; n < drawableId.size(); n++) {
            Movie movie = new Movie();
            movie.setImage(drawableId.get(n));
            String name = context.getResources().getResourceEntryName(drawableId.get(n));
            int fileid = context.getResources().getIdentifier(name, "raw", context.getPackageName());
            Scanner scanner = new Scanner(context.getResources().openRawResource(fileid));
            String desprition = "";
            while (scanner.hasNext()){
                desprition += scanner.next() + " ";
            }
            movie.setDescription(desprition);
            mMovies.add(movie);
        }

        System.out.println(mMovies.toString());

    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public Movie getMovie(UUID id){
        for (Movie movie: mMovies){
            if (movie.getUUID().equals(id)){
                return movie;
            }
        }
        return null;
    }
}
