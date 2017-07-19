package com.starproductions.starmotion.starmotion.ScoreSystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Arrays;

/**
 * Helper class to access the Database
 */
class ScoreDbHelper extends SQLiteOpenHelper {
    // Constants
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Starmotion.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StarmotionContract.ScoreEntry.TABLE_NAME + " (" +
            StarmotionContract.ScoreEntry._ID + " INTEGER PRIMARY KEY," +
            StarmotionContract.ScoreEntry.COLUMN_NAME_PLAYERNAME + " TEXT," +
            StarmotionContract.ScoreEntry.COLUMN_NAME_SCORE + " INT)";
    private static final String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " + StarmotionContract.ScoreEntry.TABLE_NAME;

    private SQLiteDatabase db = null;


    ScoreDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    void openDatabase(){
        if(db == null || !db.isOpen())
            db = getWritableDatabase();
    }

    void closeDatabase(){
        if(db != null && db.isOpen())
            db.close();
    }

    long insertScore(String playername, int score){
        ContentValues values = new ContentValues();
        values.put(StarmotionContract.ScoreEntry.COLUMN_NAME_PLAYERNAME, playername);
        values.put(StarmotionContract.ScoreEntry.COLUMN_NAME_SCORE, score);
        long id = db.insert(StarmotionContract.ScoreEntry.TABLE_NAME, null, values);
        return id;
    }

    Cursor readAllScores(){
        String[] projection = {
                StarmotionContract.ScoreEntry._ID,
                StarmotionContract.ScoreEntry.COLUMN_NAME_PLAYERNAME,
                StarmotionContract.ScoreEntry.COLUMN_NAME_SCORE
        };

        String sortOrder = StarmotionContract.ScoreEntry.COLUMN_NAME_SCORE + " DESC";

        Cursor cursor = db.query(StarmotionContract.ScoreEntry.TABLE_NAME, projection, null, null, null, null, sortOrder);
        return cursor;
    }

    void deleteScores(int... scoreIds){
        String selection = StarmotionContract.ScoreEntry._ID + " = ?";
        String[] selectionArgs = Arrays.toString(scoreIds).split("[\\[\\]]")[1].split(", ");
        db.delete(StarmotionContract.ScoreEntry.TABLE_NAME, selection, selectionArgs);
    }

}
