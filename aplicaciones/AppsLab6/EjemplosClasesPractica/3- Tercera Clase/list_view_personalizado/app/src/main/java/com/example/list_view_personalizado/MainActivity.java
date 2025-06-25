package com.example.list_view_personalizado;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {
    private List<Map<String, Object>> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] datos = {"foto", "producto", "presentacion", "precio"};
        int[] vistas = {R.id.foto, R.id.producto, R.id.presentacion, R.id.precio};

        SimpleAdapter adaptador =
                new SimpleAdapter(this, listadoProductos(),
                        R.layout.item_producto, datos, vistas);

        setListAdapter(adaptador);


}

private List<Map<String, Object>> listadoProductos() {
    productos = new ArrayList<Map<String, Object>>();

    Map<String, Object> item = new HashMap<String, Object>();
    item.put("producto", "CREMOSO PUNTA DEL AGUA SIN TACC");
    item.put("precio", "$707.00");
    item.put("presentacion", "X 100GR");
    item.put("foto", R.drawable.img01);
    productos.add(item);

    item = new HashMap<String, Object>();
    item.put("producto", "CREMOSO MEDIA PIEZA PUNTA DEL AGUA");
    item.put("precio", "$13,806.00");
    item.put("presentacion", "MEDIA PIEZA ($ APROXIMADO)");
    item.put("foto", R.drawable.img02);
    productos.add(item);

    item = new HashMap<String, Object>();
    item.put("producto", "CREMOSO HORMA PUNTA DEL AGUA");
    item.put("precio", "$25445.00");
    item.put("presentacion", "HORMA ($APROXIMADO)");
    item.put("foto", R.drawable.img03);
    productos.add(item);

    item = new HashMap<String, Object>();
    item.put("producto", "POR SALUT PUNTA DEL AGUA SIN TACC");
    item.put("precio", "$810.00");
    item.put("presentacion", "X 100GR");
    item.put("foto", R.drawable.img04);
    productos.add(item);

    item = new HashMap<String, Object>();
    item.put("producto", "POR SALUT MEDIA PIEZA PUNTA DEL AGUA");
    item.put("precio", "$15778.00");
    item.put("presentacion", "MEDIA PIEZA ($ APROXIMADO)");
    item.put("foto", R.drawable.img05);
    productos.add(item);

    item = new HashMap<String, Object>();
    item.put("producto", "POR SALUT LIGHT PUNTA DELAGUA SIN TACC");
    item.put("precio", "$901.00");
    item.put("presentacion", "X 100 GR");
    item.put("foto", R.drawable.img06);
    productos.add(item);

    item = new HashMap<String, Object>();
    item.put("producto", "POR SALUT LIGHT MEDIA PIEZA PUNTA DEL AGUA");
    item.put("precio", "$17,548.00");
    item.put("presentacion", "MEDIA PIEZA ($ APROXIMADO)");
    item.put("foto", R.drawable.img07);
    productos.add(item);

    item = new HashMap<String, Object>();
    item.put("producto", "TYBO PUNTA DEL AGUA SIN TACC \"OFERTA\"");
    item.put("precio", "$916.00");
    item.put("presentacion", "X 100 GR");
    item.put("foto", R.drawable.img08);
    productos.add(item);

    return productos;
}

}