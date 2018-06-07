package com.example.oluwatobig.shelter.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.oluwatobig.shelter.data.ShelterContract.ShelterEntry;

import static android.os.FileObserver.CREATE;
import static android.transition.Fade.IN;

/**
 * Created by oluwatobig on 21-May-18.
 */

public class ShelterDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="shelter.db";
    public static final int DATABASE_VERSION=1;

    public static final String COMMAND="CREATE TABLE";

    public ShelterDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLE shelter(_id INTEGER PRIMARY KEY AUTO INCREMENT,name TEXT,breed TEXT,gender INTEGER,weight INTEGER);
        String SQL_CREATE_TABLE="CREATE TABLE "+ShelterEntry.TABLE_NAME+"("
                +ShelterEntry.PET_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ShelterEntry.PET_NAME+" TEXT NOT NULL,"
                +ShelterEntry.BREED+" TEXT,"
                +ShelterEntry.GENDER+" INTEGER NOT NULL,"
                +ShelterEntry.WEIGHT+" INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
