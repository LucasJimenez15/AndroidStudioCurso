package com.example.sqlite;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlite.data.PaisContract;
import com.example.sqlite.data.PaisDbHelper;
import static com.example.sqlite.data.PaisContract.PaisEntry.*;

public class MainActivity extends ListActivity {

    private PaisDbHelper dbHelper;
    private List<Map<String, Object>> paises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] datos = {"pais", "capital", "poblacion"};
        int[] vistas = {R.id.pais, R.id.capital, R.id.poblacion};

        dbHelper = new PaisDbHelper(this);

        SimpleAdapter adaptador =
                new SimpleAdapter(this, listadoPaises(),
                        R.layout.item_pais, datos, vistas);

        setListAdapter(adaptador);
    }

    private List<Map<String, Object>> listadoPaises() {
        paises = new ArrayList<Map<String, Object>>();

        Map<String, Object> pais = new HashMap<String, Object>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);

        cursor.moveToFirst();

        for (int i=0; i < cursor.getCount(); i++){
            pais = new HashMap<String, Object>();
            pais.put(COLUMN_NAME_PAIS, cursor.getString(1));
            pais.put(COLUMN_NAME_CAPITAL, cursor.getString(2));
            pais.put(COLUMN_NAME_POBLACION,cursor.getLong(3));
            paises.add(pais);
            cursor.moveToNext();
        }

        cursor.close();
        return paises;
    }

    public void onListItemClick(ListView parent, View v, int posicion, long id) {
        Map<String, Object> item = paises.get(posicion);
        Toast.makeText(getBaseContext(), "Ha seleccionado " + item.get("pais").toString(), Toast.LENGTH_LONG).show();

    }
}