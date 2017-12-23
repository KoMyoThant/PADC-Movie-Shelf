package com.ps.movieshelf.data.vo;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.ps.movieshelf.persistence.MovieShelfContract;

import java.util.ArrayList;


/**
 * Created by Dell on 12/6/2017.
 */

public class MovieVO {
    @SerializedName("vote_count")
    private int voteCount;
    @SerializedName("id")
    private String movieId;
    @SerializedName("video")
    private boolean video;
    @SerializedName("vote_average")
    private float voteAverage;
    @SerializedName("title")
    private String title;
    @SerializedName("popularity")
    private float popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("genre_ids")
    private ArrayList<Integer> genreIds;
    @SerializedName("backdrop_path")
    private String backDropPath;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releasedDate;

    public int getVoteCount() {
        return voteCount;
    }

    public String getMovieId() {
        return movieId;
    }

    public boolean isVideo() {
        return video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public ArrayList<Integer> getGenreIds() {
        return genreIds;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleasedDate() {
        return releasedDate;
    }

    public ContentValues parseToContentValues() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieShelfContract.MovieEntry.COLUMN_MOVIE_ID, movieId);
        contentValues.put(MovieShelfContract.MovieEntry.COLUMN_VOTE_COUNT, voteCount);
        contentValues.put(MovieShelfContract.MovieEntry.COLUMN_VOTE_AVERAGE, voteAverage);
        contentValues.put(MovieShelfContract.MovieEntry.COLUMN_VIDEO, video);
        contentValues.put(MovieShelfContract.MovieEntry.COLUMN_ORIGINAL_TITLE, originalTitle);
        contentValues.put(MovieShelfContract.MovieEntry.COLUMN_TITLE, title);
        contentValues.put(MovieShelfContract.MovieEntry.COLUMN_POPULARITY, popularity);
        contentValues.put(MovieShelfContract.MovieEntry.COLUMN_POSTER_PATH, posterPath);
        contentValues.put(MovieShelfContract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE, originalLanguage);
        contentValues.put(MovieShelfContract.MovieEntry.COLUMN_BACKDROP_PATH, backDropPath);
        contentValues.put(MovieShelfContract.MovieEntry.COLUMN_OVERVIEW, overview);
        contentValues.put(MovieShelfContract.MovieEntry.COLUMN_RELEASE_DATE, releasedDate);
        contentValues.put(MovieShelfContract.MovieEntry.COLUMN_ADULT, adult);
        return contentValues;
    }

    public static MovieVO parseFromCursor(Cursor cursor) {
        MovieVO movie = new MovieVO();
        movie.movieId = cursor.getString(cursor.getColumnIndex(MovieShelfContract.MovieEntry.COLUMN_MOVIE_ID));
        movie.voteCount = cursor.getInt(cursor.getColumnIndex(MovieShelfContract.MovieEntry.COLUMN_VOTE_COUNT));
        movie.video=cursor.getInt(cursor.getColumnIndex(MovieShelfContract.MovieEntry.COLUMN_VIDEO))>0;
        movie.voteAverage = cursor.getInt(cursor.getColumnIndex(MovieShelfContract.MovieEntry.COLUMN_VOTE_AVERAGE));
        movie.title = cursor.getString(cursor.getColumnIndex(MovieShelfContract.MovieEntry.COLUMN_TITLE));
        movie.popularity = cursor.getInt(cursor.getColumnIndex(MovieShelfContract.MovieEntry.COLUMN_POPULARITY));
        movie.posterPath = cursor.getString(cursor.getColumnIndex(MovieShelfContract.MovieEntry.COLUMN_POSTER_PATH));
        movie.originalLanguage = cursor.getString(cursor.getColumnIndex(MovieShelfContract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE));
        movie.originalTitle = cursor.getString(cursor.getColumnIndex(MovieShelfContract.MovieEntry.COLUMN_ORIGINAL_TITLE));
        movie.backDropPath = cursor.getString(cursor.getColumnIndex(MovieShelfContract.MovieEntry.COLUMN_BACKDROP_PATH));
        movie.adult=cursor.getInt(cursor.getColumnIndex(MovieShelfContract.MovieEntry.COLUMN_ADULT))>0;
        movie.overview = cursor.getString(cursor.getColumnIndex(MovieShelfContract.MovieEntry.COLUMN_OVERVIEW));
        movie.releasedDate = cursor.getString(cursor.getColumnIndex(MovieShelfContract.MovieEntry.COLUMN_RELEASE_DATE));
        return movie;
    }
}
