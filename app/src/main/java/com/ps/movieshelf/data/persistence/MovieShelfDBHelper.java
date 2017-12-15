package com.ps.movieshelf.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pyaesone on 12/15/17.
 */

public class MovieShelfDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "attractions.db";

    private static final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieShelfContract.MovieEntry.TABLE_NAME + " (" +
            MovieShelfContract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieShelfContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            MovieShelfContract.MovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL" +
            MovieShelfContract.MovieEntry.COLUMN_VIDEO + " TEXT NOT NULL, " +
            MovieShelfContract.MovieEntry.COLUMN_VOTE_COUNT + " INTEGER, " +
            MovieShelfContract.MovieEntry.COLUMN_VOTE_AVERAGE + " REAL, " +
            MovieShelfContract.MovieEntry.COLUMN_POPULARITY + " REAL, " +
            MovieShelfContract.MovieEntry.COLUMN_OVERVIEW + " TEXT, " +
            MovieShelfContract.MovieEntry.COLUMN_ADULT + " INTEGER DEFAULT 0, " +
            MovieShelfContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
            MovieShelfContract.MovieEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
            MovieShelfContract.MovieEntry.COLUMN_BACKDROP_PATH + " TEXT NOT NULL, " +
            MovieShelfContract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT NOT NULL, " +
            " UNIQUE (" + MovieShelfContract.MovieEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE " +
            " );";


    public MovieShelfDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieShelfContract.MovieEntry.TABLE_NAME);

        onCreate(db);
    }
}
