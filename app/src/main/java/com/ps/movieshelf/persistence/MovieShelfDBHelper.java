package com.ps.movieshelf.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pyaesone on 12/15/17.
 */

public class MovieShelfDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "movies.db";


    private static final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieShelfContract.MovieEntry.TABLE_NAME + " (" +
            MovieShelfContract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieShelfContract.MovieEntry.COLUMN_MOVIE_ID + " VARCHAR(256), " +
            MovieShelfContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            MovieShelfContract.MovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL, " +
            MovieShelfContract.MovieEntry.COLUMN_VOTE_COUNT + " INTEGER, " +
            MovieShelfContract.MovieEntry.COLUMN_VIDEO + " INTEGER DEFAULT 0, " +
            MovieShelfContract.MovieEntry.COLUMN_VOTE_AVERAGE + " REAL, " +
            MovieShelfContract.MovieEntry.COLUMN_POPULARITY + " REAL, " +
            MovieShelfContract.MovieEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
            MovieShelfContract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT NOT NULL, " +
            MovieShelfContract.MovieEntry.COLUMN_BACKDROP_PATH + " TEXT NOT NULL, " +
            MovieShelfContract.MovieEntry.COLUMN_ADULT + " INTEGER DEFAULT 0, " +
            MovieShelfContract.MovieEntry.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
            MovieShelfContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
            " UNIQUE (" + MovieShelfContract.MovieEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            " );";
    private static final String SQL_CREATE_GENRE_TABLE = "CREATE TABLE " + MovieShelfContract.GenreEntry.TABLE_NAME + " (" +
            MovieShelfContract.GenreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieShelfContract.GenreEntry.COLUMN_GENRE_ID + " VARCHAR(256), " +
            " UNIQUE (" + MovieShelfContract.GenreEntry.COLUMN_GENRE_ID + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_MOVIE_GENRE_TABLE = "CREATE TABLE " + MovieShelfContract.MovieGenreEntry.TABLE_NAME + " (" +
            MovieShelfContract.MovieGenreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieShelfContract.MovieGenreEntry.COLUMN_MOVIE_ID + " VARCHAR(256), " +
            MovieShelfContract.MovieGenreEntry.COLUMN_GENRE_ID + " VARCHAR(256), " +
            " UNIQUE (" + MovieShelfContract.MovieGenreEntry.COLUMN_MOVIE_ID + ", " +
            MovieShelfContract.MovieGenreEntry.COLUMN_GENRE_ID
            + ") ON CONFLICT REPLACE" +
            ");";

    public MovieShelfDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_GENRE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_GENRE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieShelfContract.MovieGenreEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieShelfContract.GenreEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieShelfContract.MovieEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
