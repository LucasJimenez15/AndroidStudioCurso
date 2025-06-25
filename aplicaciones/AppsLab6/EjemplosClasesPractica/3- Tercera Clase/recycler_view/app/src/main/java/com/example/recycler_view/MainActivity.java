package com.example.recycler_view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProductoAdapter adaptador;
    private List<Producto> listaProductos = new ArrayList<Producto>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        obtenerListaProductos();

        adaptador = new ProductoAdapter(this, listaProductos);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adaptador);

    }

    private void obtenerListaProductos() {
        listaProductos.add(new Producto(R.drawable.img01,"CREMOSO PUNTA DEL AGUA SIN TACC", "X 100GR",707.0f));
        listaProductos.add(new Producto(R.drawable.img02, "CREMOSO MEDIA PIEZA PUNTA DEL AGUA","MEDIA PIEZA ($ APROXIMADO)",13806.0f));
        listaProductos.add(new Producto(R.drawable.img03, "CREMOSO HORMA PUNTA DEL AGUA","HORMA ($APROXIMADO)",25445.0f));
        listaProductos.add(new Producto(R.drawable.img04, "POR SALUT PUNTA DEL AGUA SIN TACC","X 100GR",810.0f));
        listaProductos.add(new Producto(R.drawable.img05, "POR SALUT MEDIA PIEZA PUNTA DEL AGUA","MEDIA PIEZA ($ APROXIMADO)",15778.0f));
        listaProductos.add(new Producto(R.drawable.img06, "POR SALUT LIGHT PUNTA DELAGUA SIN TACC","X 100 GR",901.0f));
        listaProductos.add(new Producto(R.drawable.img07, "POR SALUT LIGHT MEDIA PIEZA PUNTA DEL AGUA","MEDIA PIEZA ($ APROXIMADO)",17548.0f));
        listaProductos.add(new Producto(R.drawable.img08, "TYBO PUNTA DEL AGUA SIN TACC \"OFERTA\"","X 100 GR",916.0f));

    }
}