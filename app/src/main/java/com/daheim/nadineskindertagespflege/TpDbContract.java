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

    public static class TpDbStundenplan implements BaseColumns {
        public static final String TABLE_NAME = "TpDbStundenplan";
        public static final String KindID = "KindID";
        public static final String Tag = "Tag";
        public static final String Uhrzeitvon = "Uhrzeitvon";
        public static final String Uhrzeitbis = "Uhrzeitbis";

        protected static final String SQL_CREATE_TABLE_STUNDENPLAN =
                "CREATE TABLE " + TpDbStundenplan.TABLE_NAME + " (" +
                        TpDbStundenplan._ID + " INTEGER PRIMARY KEY," +
                        TpDbStundenplan.KindID + " TEXT," +
                        TpDbStundenplan.Tag + " TEXT," +
                        TpDbStundenplan.Uhrzeitvon + " TEXT," +
                        TpDbStundenplan.Uhrzeitbis + " TEXT)";

        protected static final String SQL_DROP_TABLE_STUNDENPLAN =
                "DROP TABLE IF EXISTS " + TpDbStundenplan.TABLE_NAME;
    }

    public static class TpDbVertragslaufzeit implements BaseColumns {
        public static final String TABLE_NAME = "TpDbVertragslaufzeit";
        //public static final String KindID = "KindID";
        public static final String Datumvon = "Datumvon";
        public static final String Datumbis = "Datumbis";

        protected static final String SQL_CREATE_TABLE_VERTRAGSLAUFZEIT =
                "CREATE TABLE " + TpDbVertragslaufzeit.TABLE_NAME + " (" +
                        TpDbVertragslaufzeit._ID + " INTEGER PRIMARY KEY," +
                        //TpDbVertragslaufzeit.KindID + " TEXT," +
                        TpDbVertragslaufzeit.Datumvon + " TEXT," +
                        TpDbVertragslaufzeit.Datumbis + " TEXT)";

        protected static final String SQL_DROP_TABLE_VERTRAGSLAUFZEIT =
                "DROP TABLE IF EXISTS " + TpDbVertragslaufzeit.TABLE_NAME;
    }
}