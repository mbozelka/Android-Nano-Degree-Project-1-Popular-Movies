package com.mattbozelka.popularmovies;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/*
*
* the main UI of MainActivity and handles the logic for the view
*
* */

public class MovieListFragment extends Fragment{

    private final String LOG_TAG = MovieListFragment.class.getSimpleName();
    private ImageAdapter mMoviePosterAdapter;
    List<Movie> movies = new ArrayList<Movie>();

    public MovieListFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mMoviePosterAdapter = new ImageAdapter(
                getActivity(),
                R.layout.list_item_poster,
                R.id.list_item_poster_imageview,
                new ArrayList<String>());

        View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.main_movie_grid);
        gridView.setAdapter(mMoviePosterAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Movie details = movies.get(position);
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class)
                        .putExtra("movies_details", details);
                startActivity(intent);
            }

        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getMovies();
    }

    private void getMovies() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortOrder = prefs.getString(getString(R.string.display_preferences_sort_order_key),
                getString(R.string.display_preferences_sort_default_value));

        FetchMoviesTask fetchMoviesTask = new FetchMoviesTask(new AsyncResponse() {
            @Override
            public void onTaskCompleted(List<Movie> results) {
                movies.clear();
                movies.addAll(results);
                updatePosterAdapter();
            }
        });
        fetchMoviesTask.execute(sortOrder);
    }

    // updates the ArrayAdapter of poster images
    private void updatePosterAdapter(){
        mMoviePosterAdapter.clear();
        for(Movie movie : movies) {
            mMoviePosterAdapter.add(movie.getPoster());
        }
    }

}
