package com.example.sqlite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sqlite.data.PaisContract.PaisEntry;

public class PaisDbHelper extends SQLiteOpenHelper {
    // Si cambia el esquema de la database, debe incrementar la database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Paises.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PaisEntry.TABLE_NAME + " (" +
                    PaisEntry._ID + " TEXT PRIMARY KEY," +
                    PaisEntry.COLUMN_NAME_PAIS + " TEXT," +
                    PaisEntry.COLUMN_NAME_CAPITAL + " TEXT," +
                    PaisEntry.COLUMN_NAME_POBLACION + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PaisEntry.TABLE_NAME;

    public PaisDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

        mockData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        Log.w("PaisDbHelper", "Actualizando desde la version "+oldVersion+
                " a la version "+newVersion+". Se eliminaran todos los datos");
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockPais(sqLiteDatabase, new Pais("Argentina", "Ciudad Autonoma de BA","4800"));
        mockPais(sqLiteDatabase, new Pais("Brasil", "Brasilia","14000"));
        mockPais(sqLiteDatabase, new Pais("Chile", "Santiago","300"));
        mockPais(sqLiteDatabase, new Pais("Uruguay", "Montevideo","400"));

    }

    public long mockPais(SQLiteDatabase db, Pais pais) {
        return db.insert(
                PaisEntry.TABLE_NAME,
                null,
                pais.toContentValues());
    }
}
