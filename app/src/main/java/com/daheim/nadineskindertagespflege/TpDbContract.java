package com.daheim.nadineskindertagespflege;

import android.provider.BaseColumns;
import android.content.ContentProvider;

public final class TpDbContract {

    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "TpDb";
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty(?) private(?) constructor.
    private TpDbContract() {}

    public static class TpDbKinder implements BaseColumns {
        public static final String TABLE_NAME = "TpDbKinder";
        public static final String Name = "Name";
        public static final String Vorname = "Vorname";
        public static final String Geburtstag = "Geburtstag";
        public static final String Allergien = "Allergien";
        public static final String Active = "Active";

        protected static final String SQL_CREATE_TABLE_KINDER =
                "CREATE TABLE " + TpDbKinder.TABLE_NAME + " (" +
                        TpDbKinder._ID + " INTEGER PRIMARY KEY," +
                        TpDbKinder.Name + " TEXT," +
                        TpDbKinder.Vorname + " TEXT," +
                        TpDbKinder.Geburtstag + " TEXT," +
                        TpDbKinder.Allergien + " TEXT," +
                        TpDbKinder.Active + " TEXT)";

        protected static final String SQL_DROP_TABLE_KINDER =
                "DROP TABLE IF EXISTS " + TpDbKinder.TABLE_NAME;
    }
}