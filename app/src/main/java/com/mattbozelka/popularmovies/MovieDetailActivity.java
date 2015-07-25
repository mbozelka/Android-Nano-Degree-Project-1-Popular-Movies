package com.mattbozelka.popularmovies;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/*
*
* Detail activity and displays the detail view of a movie
* Inflates MovieDetailFragment for the main UI
*
* */

public class MovieDetailActivity extends ActionBarActivity {

    private final String LOG_TAG = MovieDetailActivity.class.getSimpleName();
    private FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.container, new MovieDetailFragment())
                    .commit();
        }
        getSupportActionBar().setElevation(0f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
