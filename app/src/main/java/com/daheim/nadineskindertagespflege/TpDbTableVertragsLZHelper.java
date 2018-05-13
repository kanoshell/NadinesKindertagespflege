package com.daheim.nadineskindertagespflege;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class TpDbTableVertragsLZHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = TpDbContract.DATABASE_VERSION;
    public static final String DATABASE_NAME = TpDbContract.DATABASE_NAME;

    public TpDbTableVertragsLZHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TpDbContract.TpDbVertragslaufzeit.SQL_CREATE_TABLE_VERTRAGSLAUFZEIT);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(TpDbContract.TpDbVertragslaufzeit.SQL_DROP_TABLE_VERTRAGSLAUFZEIT);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void neuevertragslz(ContentValues values) {
    SQLiteDatabase db= null;
    // Gets the data repository in write mode
        try {
        db = this.getWritableDatabase();
    } catch (Exception e) {
        Log.e("dbcreate", "onCreate: ", e);
        e.printStackTrace();
    }

    // Insert the new row, returning the primary key value of the new row
    long newRowId = db.insert(TpDbContract.TpDbVertragslaufzeit.TABLE_NAME, null, values);
    //System.out.println(newRowId);
    }

    public Cursor anzeigeVertragsLZ() {
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
                TpDbContract.TpDbVertragslaufzeit.Datumvon,
                TpDbContract.TpDbVertragslaufzeit.Datumbis,
                TpDbContract.TpDbVertragslaufzeit._ID
        };

    // Filter results alle
        String selection = "*";
        String[] selectionArgs = {};

    // How you want the results sorted in the resulting Cursor
        String sortOrder =
                TpDbContract.TpDbVertragslaufzeit.Datumvon + " DESC";

        Cursor cursor = db.query(
                TpDbContract.TpDbVertragslaufzeit.TABLE_NAME,       // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        return cursor;
    }

    /*public Cursor anzeigeGewaeltesKind(String _ID) {
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
    }*/
}
