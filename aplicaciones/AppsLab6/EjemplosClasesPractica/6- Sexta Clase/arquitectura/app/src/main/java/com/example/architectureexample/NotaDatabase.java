package com.example.architectureexample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Nota.class}, version = 1)
public abstract class NotaDatabase extends RoomDatabase {
    private static NotaDatabase instancia;
    public abstract NotaDao notaDao();

    public static synchronized NotaDatabase getInstance(Context context){
        if (instancia == null){
            instancia = Room.databaseBuilder(context.getApplicationContext(), NotaDatabase.class,
                    "notas_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instancia;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDbAsyncTask(instancia).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NotaDao notaDao;

        private PopulateDbAsyncTask(NotaDatabase db) {
            notaDao = db.notaDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notaDao.insert(new Nota("Titulo 1", "Descripcion 1", 1));
            notaDao.insert(new Nota("Titulo 2", "Descripcion 2", 2));
            notaDao.insert(new Nota("Titulo 3", "Descripcion 3", 3));
            return null;
        }
    }
}
