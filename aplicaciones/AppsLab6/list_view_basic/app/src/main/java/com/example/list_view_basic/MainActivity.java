package com.example.list_view_basic;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.list_view_basic.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private final String [] productos = {
            "CREMOSO PUNTA DEL AGUA SIN TACC","CREMOSO MEDIA PIEZA PUNTA DEL AGUA","CREMOSO HORMA PUNTA DEL AGUA",
            "POR SALUT PUNTA DEL AGUA SIN TACC","POR SALUT MEDIA PIEZA PUNTA DEL AGUA","POR SALUT LIGHT PUNTA DELAGUA SIN TACC",
            "POR SALUT LIGHT MEDIA PIEZA PUNTA DEL AGUA","TYBO PUNTA DEL AGUA SIN TACC","DANBO PUNTA DEL AGUA SIN TACC",
            "MOZZARELLA PUNTA DEL AGUA SIN TACC","SARDO FRESCO PUNTA DEL AGUA","SARDO ESTACIONADO PUNTA DEL AGUA SIN TACC",
            "REGIANITTO PUNTA DEL AGUA SIN TACC","BARRA TILSIT SIN TACC","HOLANDA LACTEAR SIN TACC","PATEGRAS LACTEAR SIN TACC",
            "FONTINA LACTEAR SIN TACC","BARRA CHEEDAR LACTEAR SIN TACC","BARRA CHEEDAR LA QUESERA","POR SALUT LACTEAR SIN TACC",
            "POR SALUT SIN SAL LACTEAR SIN TACC","POR SALUT MAGRO LACTEAR SIN TACC","ROQUEFORT LA QUESERA SIN TACC",
            "CANESTRATO CON PIMIENTA LA QUESERA","PROVOLETA LA QUESERA SIN TACC","PARMESANO LA QUESERA","GRUYERE LA QUESERA",
            "RALLADITO LA QUESERA X40GRS"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityMainBinding binding;

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //1.- Creacion del ListView
        ListView listaProductos;
        listaProductos = binding.listaProductos;

        //2.- Establecimiento del Adaptador para los Datos
        ArrayAdapter adaptadorListaProductos = new ArrayAdapter(
                this, android.R.layout.simple_list_item_1, productos);

        //3.- Vinculacion del Adaptador con el ListView
        listaProductos.setAdapter(adaptadorListaProductos);

        //4.-Capturar los eventos de la lista
        listaProductos.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String productoSeleccionado = productos[i];
        Toast.makeText(this, productoSeleccionado, Toast.LENGTH_LONG).show();
    }
}