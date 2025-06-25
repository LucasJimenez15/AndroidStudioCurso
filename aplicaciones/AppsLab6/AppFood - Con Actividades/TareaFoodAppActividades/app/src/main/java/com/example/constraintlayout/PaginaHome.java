package com.example.constraintlayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class PaginaHome extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProductoAdapter adaptador;
    private List<Producto> listaProductos = new ArrayList<Producto>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagina_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BottomNavigationView nav = findViewById(R.id.nav);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               if(item.getItemId() == R.id.profile){
                   Bundle b = getIntent().getExtras();
                   Intent intecion = new Intent(PaginaHome.this, Perfil.class);
                   intecion.putExtras(b);
                   startActivity(intecion);
               }
                return item.getItemId() == R.id.home;
            }

        });


        /*comentarios sobre ListView y RecyclerView
        El código  implementa una aplicación Android que utiliza un RecyclerView para mostrar una lista de productos en una
        interfaz personalizada. Aquí te explico detalladamente cada componente:

        1. Clase MainActivity: Esta clase es la actividad principal de la aplicación, donde se maneja la lógica de presentación de la
        lista de productos usando un RecyclerView.

        Atributos:
        RecyclerView recyclerView: Un componente visual que muestra listas de elementos.
        RecyclerView.LayoutManager layoutManager: Responsable de determinar cómo se presenta la lista (en este caso, con un diseño lineal).
        ProductoAdapter adaptador: El adaptador personalizado que maneja cómo se enlazan los datos con las vistas de la lista.
        List<Producto> listaProductos: Una lista de objetos Producto, que contendrá los elementos a mostrar en la interfaz.
        onCreate(Bundle savedInstanceState)
        Este es el método donde se inicializan los elementos de la actividad cuando se crea por primera vez.

        setContentView(R.layout.activity_main); Establece el layout principal de la actividad. El archivo XML activity_main.xml contiene
        la definición del RecyclerView.

        recyclerView = findViewById(R.id.recycler_view); Obtiene una referencia al RecyclerView definido en el XML.

        obtenerListaProductos(); Llama al método que llena la lista de productos (listaProductos) con los objetos Producto predefinidos.

        adaptador = new ProductoAdapter(this, listaProductos); Crea una instancia del adaptador personalizado (ProductoAdapter) que toma
        como parámetros la actividad y la lista de productos.

        layoutManager = new LinearLayoutManager(this); Define un LayoutManager lineal, que organiza los elementos del RecyclerView
        en una columna vertical.

        recyclerView.setLayoutManager(layoutManager); Asigna el LayoutManager al RecyclerView.

        recyclerView.setAdapter(adaptador); Asocia el adaptador personalizado con el RecyclerView para que se encargue de mostrar los datos
         de los productos en las vistas.

        Resumen
        Este código crea una lista de productos en una aplicación Android utilizando un RecyclerView. La clase principal MainActivity
        inicializa y configura el RecyclerView, un ProductoAdapter personalizado se encarga de enlazar los datos de los productos con
        las vistas, y la clase Producto representa el modelo de datos. Cada elemento de la lista tiene un diseño personalizado definido en
        elemento_lista.xml, que incluye una imagen del producto, su nombre, presentación y precio. La estructura es flexible y permite una
        fácil actualización de la interfaz según los datos proporcionados.*/



        recyclerView = findViewById(R.id.recycler_view);
        obtenerListaProductos();
        adaptador = new ProductoAdapter(this , listaProductos);
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