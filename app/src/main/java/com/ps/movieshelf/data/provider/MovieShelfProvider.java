package com.ps.movieshelf.data.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ps.movieshelf.data.persistence.MovieShelfContract;
import com.ps.movieshelf.data.persistence.MovieShelfDBHelper;

/**
 * Created by pyaesone on 12/21/17.
 */

public class MovieShelfProvider extends ContentProvider {

    public static final int GENRE = 100;
    public static final int MOVIE_SHELF = 200;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private MovieShelfDBHelper sMovieShelfDBHelper;

    private static UriMatcher buildUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(MovieShelfContract.CONTENT_AUTHORITY,MovieShelfContract.PATH_GENRE,GENRE);
        uriMatcher.addURI(MovieShelfContract.CONTENT_AUTHORITY,MovieShelfContract.PATH_MOVIE,MOVIE_SHELF);

        return uriMatcher;
    }

    private String getTableName(Uri uri){
        switch (sUriMatcher.match(uri)){
            case GENRE:
                return MovieShelfContract.GenreEntry.TABLE_NAME;
            case MOVIE_SHELF:
                return MovieShelfContract.MovieEntry.TABLE_NAME;
        }
        return null;
    }

    private Uri getContentUri(Uri uri){
        switch (sUriMatcher.match(uri)){
            case GENRE:
                return MovieShelfContract.GenreEntry.CONTENT_URI;
            case MOVIE_SHELF:
                return MovieShelfContract.MovieEntry.CONTENT_URI;
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        sMovieShelfDBHelper = new MovieShelfDBHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor queryCursor = sMovieShelfDBHelper.getReadableDatabase().query(getTableName(uri),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        if (getContext() != null) {
            queryCursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)){
            case GENRE:
                return MovieShelfContract.GenreEntry.DIR_TYPE;
            case MOVIE_SHELF:
                return MovieShelfContract.MovieEntry.DIR_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = sMovieShelfDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        long _id = db.insert(tableName, null, values);
        if (_id > 0) {
            Uri tableContentUri = getContentUri(uri);
            Uri insertedUri = ContentUris.withAppendedId(tableContentUri, _id);

            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(uri, null);
            }

            return insertedUri;
        }

        return null;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = sMovieShelfDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = sMovieShelfDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);

        rowDeleted = db.delete(tableName, selection, selectionArgs);
        if (getContext() != null && rowDeleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = sMovieShelfDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, values, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }
}
