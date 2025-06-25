package com.example.sqlite.data;

import android.content.ContentValues;
import com.example.sqlite.data.PaisContract.PaisEntry;
import java.util.UUID;

public class Pais {
    private String id;
    private String pais;
    private String capital;
    private String poblacion;

    public Pais(String pais, String capital, String poblacion) {
        this.id = UUID.randomUUID().toString();
        this.pais = pais;
        this.capital = capital;
        this.poblacion = poblacion;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(PaisEntry._ID, id);
        values.put(PaisEntry.COLUMN_NAME_PAIS, pais);
        values.put(PaisEntry.COLUMN_NAME_CAPITAL, capital);
        values.put(PaisEntry.COLUMN_NAME_POBLACION, poblacion);
        return values;
    }

    public String getId() {
        return id;
    }

    public String getPais() {
        return pais;
    }

    public String getCapital() {
        return capital;
    }

    public String getPoblacion() {
        return poblacion;
    }
}
