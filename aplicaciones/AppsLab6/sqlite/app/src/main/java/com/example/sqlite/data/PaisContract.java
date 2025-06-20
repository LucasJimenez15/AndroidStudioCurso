package com.example.sqlite.data;

import android.provider.BaseColumns;

public class PaisContract {
    // Para prevenir que alguien accidentalmente instancia la clase contract,
    // hacemos que el constructor sea private.
    private PaisContract() {}

    /* Inner class que define el contenido de la tabla */
    public static class PaisEntry implements BaseColumns {
        public static final String TABLE_NAME = "pais";
        public static final String _ID = "_id";
        public static final String COLUMN_NAME_PAIS ="pais";
        public static final String COLUMN_NAME_CAPITAL = "capital";
        public static final String COLUMN_NAME_POBLACION = "poblacion";

    }
}
