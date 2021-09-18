package com.example.kitchen;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static java.security.AccessController.getContext;

public class IngredientProvider extends ContentProvider {

    private static final int INGREDIENTS = 100;
    private static final int INGREDIENT_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static{
        sUriMatcher.addURI(IngredientContract.CONTENT_AUTHORITY, IngredientContract.PATH_INGREDIENTS, INGREDIENTS);
        sUriMatcher.addURI(IngredientContract.CONTENT_AUTHORITY, IngredientContract.PATH_INGREDIENTS + "/#", INGREDIENT_ID);
    }

    private ingredientDbHelper mDbHelper;

    @Override
    public boolean onCreate(){
        mDbHelper = new ingredientDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor = null;

        int match = sUriMatcher.match(uri);
        switch(match){
            case INGREDIENTS:
                cursor = database.query(IngredientContract.IngredientEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case INGREDIENT_ID:
                selection = IngredientContract.IngredientEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(IngredientContract.IngredientEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

}



















