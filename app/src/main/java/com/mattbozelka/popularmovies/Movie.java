package com.mattbozelka.popularmovies;

/*
*
* simple object to store all the information
* for a movie being used in the UI
*
* Takes a string for the movie title, poster, overview, voteAverage
* and releaseDate
*
* Implements parcelable in order to easily pass between intents
*
* */

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String title;
    private String poster;
    private String overview;
    private String voteAverage;
    private String releaseDate;

    public Movie(String title, String poster, String overview,
                 String voteAverage, String releaseDate){
        this.title = title;
        this.poster = poster;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getOverview() {
        return overview;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
        out.writeString(poster);
        out.writeString(overview);
        out.writeString(voteAverage);
        out.writeString(releaseDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private Movie(Parcel in) {
        title = in.readString();
        poster = in.readString();
        overview = in.readString();
        voteAverage = in.readString();
        releaseDate = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}