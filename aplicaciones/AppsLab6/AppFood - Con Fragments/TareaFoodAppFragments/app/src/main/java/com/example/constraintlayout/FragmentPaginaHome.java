package com.example.constraintlayout;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FragmentPaginaHome extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProductoAdapter adaptador;
    private List<Producto> listaProductos = new ArrayList<Producto>();


    public static FragmentPaginaHome newInstance(String param1, String param2) {
        FragmentPaginaHome fragment = new FragmentPaginaHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar la vista del fragmento
        View view = inflater.inflate(R.layout.fragment_pagina_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //RECUPERAMOS LOS DATOS ENVIADOS DEL FRAGMENTO ANTERIOR
        Bundle bundle = getArguments();

        BottomNavigationView nav = view.findViewById(R.id.nav);

        // Obtener NavController desde la vista raíz del fragmento
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);

        // Conectar el BottomNavigationView con el NavController
        NavigationUI.setupWithNavController(nav, navController);

        // Escuchar los cambios en la selección del menú
        nav.setOnNavigationItemSelectedListener(item -> {
            Fragment currentFragment = getParentFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);

            if (item.getItemId() == R.id.profile) {
                if (!(currentFragment instanceof FragmentPerfil)) {
                    // Obtener el email desde los argumentos del fragmento y tambien lo enviamos a la vista de perfil

                    navController.navigate(R.id.action_fragmentPaginaHome_to_fragmentPerfil,bundle);
                }
                return true;
            } else if (item.getItemId() == R.id.shop) {
                // Navegar a otro fragmento, si es necesario
                return true;
            } else if (item.getItemId() == R.id.home) {
                if (!(currentFragment instanceof FragmentPaginaHome)) {
                    // Obtener el email desde los argumentos del fragmento y tambien lo enviamos a la vista de home
                    navController.navigate(R.id.action_fragmentPerfil_to_fragmentPaginaHome,bundle);
                }
                return true;
            }
            return false;
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



        // Cambiar el acceso a la vista
        recyclerView = view.findViewById(R.id.recycler_view); // Cambia findViewById por view.findViewById

        // Llenar la lista de productos
        obtenerListaProductos();

        // Usar el contexto del fragmento
        adaptador = new ProductoAdapter(this, listaProductos); // Cambia "this" por "requireContext()"

        layoutManager = new LinearLayoutManager(getContext()); // Cambia "this" por "getContext()"
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


