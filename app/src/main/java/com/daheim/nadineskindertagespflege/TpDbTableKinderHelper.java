package com.daheim.nadineskindertagespflege;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class TpDbTableKinderHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = TpDbContract.DATABASE_VERSION;
    public static final String DATABASE_NAME = TpDbContract.DATABASE_NAME;

    public TpDbTableKinderHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TpDbContract.TpDbKinder.SQL_CREATE_TABLE_KINDER);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(TpDbContract.TpDbKinder.SQL_DROP_TABLE_KINDER);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void neueskind(ContentValues values) {
    // Instantiierung des helpers für die Kindertabelle
    //TpDbTableKinderHelper kindhelper = new TpDbTableKinderHelper();
    SQLiteDatabase db = null;
    // Gets the data repository in write mode
        try {
        db = this.getWritableDatabase();
    } catch (Exception e) {
        Log.e("dbcreate", "onCreate: ", e);
        e.printStackTrace();
    }

    // Insert the new row, returning the primary key value of the new row
    long newRowId = db.insert(TpDbContract.TpDbKinder.TABLE_NAME, null, values);
    //System.out.println(newRowId);
    }

    public Cursor anzeigeKinder() {
        SQLiteDatabase db = null;
        // Gets the data repository in read mode
        try {
            db = this.getReadableDatabase();
        } catch (Exception e) {
            Log.e("dbread", "readable: ", e);
            e.printStackTrace();
        }
    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
        String[] projection = {
                TpDbContract.TpDbKinder.Vorname,
                TpDbContract.TpDbKinder.Name,
                TpDbContract.TpDbKinder._ID
        };

    // Filter results WHERE "Active" = 'ja'
        String selection = TpDbContract.TpDbKinder.Active + " = ?";
        String[] selectionArgs = { "ja" };

    // How you want the results sorted in the resulting Cursor
        String sortOrder =
                TpDbContract.TpDbKinder.Vorname + " DESC";

        Cursor cursor = db.query(
                TpDbContract.TpDbKinder.TABLE_NAME,       // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        return cursor;
    }

    public Cursor anzeigeGewaeltesKind(String _ID) {
        String eindeutid = _ID;
        SQLiteDatabase db = null;
        // Gets the data repository in read mode
        try {
            db = this.getReadableDatabase();
        } catch (Exception e) {
            Log.e("dbread", "readable: ", e);
            e.printStackTrace();
        }
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {"*"};

        // Filter results WHERE "_ID"=<mitgegebene nummer>
        String selection = TpDbContract.TpDbKinder._ID + " = ?";
        String[] selectionArgs = { eindeutid };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = "";

        Cursor cursor = db.query(
                TpDbContract.TpDbKinder.TABLE_NAME,       // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        return cursor;
    }
}
