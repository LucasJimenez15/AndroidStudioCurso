// Paquete donde se encuentra este archivo. Esto indica su ubicación lógica en el proyecto.
package com.cursoandroid.navigationdrawer.ui.home;

// Importación de clases necesarias para trabajar con vistas y fragmentos.
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

// Anotaciones que ayudan a evitar errores y mejorar la compatibilidad.
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

// Fragment es una parte reutilizable de la interfaz de usuario.
// Antes todo se hacía con Activities, lo que generaba interfaces menos modulares.
import androidx.fragment.app.Fragment;

// ViewModel es parte de la arquitectura Jetpack y permite separar la lógica de UI de los datos.
// Esto evita que se pierdan datos al rotar la pantalla, algo que pasaba antes con Activity y Fragment.
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cursoandroid.navigationdrawer.R;

// Esta clase representa el Fragmento de "Home" que se muestra al abrir la app o seleccionar "Inicio" en el menú.
public class HomeFragment extends Fragment {

    // Se declara una variable para acceder al ViewModel, que contiene los datos que usará este fragmento.
    private HomeViewModel homeViewModel;

    // Este método se llama cuando se va a crear la vista del fragmento.
    // Aquí se configura la interfaz de usuario y la lógica del fragmento.
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // 🔷 SE OBTIENE EL VIEWMODEL 🔷
        // ViewModelProviders.of(this).get(...) obtiene el ViewModel asociado a este Fragment.
        // Antes se usaban variables normales en la Activity, pero al rotar la pantalla se perdían los datos.
        // El ViewModel permite que los datos sobrevivan a cambios como rotación de pantalla.
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        // 🔷 SE INFLA EL LAYOUT 🔷
        // Se carga el archivo XML (fragment_home.xml) y se convierte en una vista de Java.
        // 'inflater' se encarga de traducir el XML a código visual en tiempo de ejecución.
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // 🔷 SE OBTIENE REFERENCIA AL TextView 🔷
        // Busca el componente TextView dentro del layout cargado, para luego poder modificar su contenido.
        final TextView textView = root.findViewById(R.id.text_home);

        // 🔷 OBSERVACIÓN DE DATOS CON LIVEDATA 🔷
        // getText() devuelve un LiveData<String>. LiveData permite observar datos en tiempo real.
        // Aquí se está observando ese texto, y cuando cambie, automáticamente se actualizará el TextView.
        // Esto es muy útil porque hace que la interfaz se mantenga sincronizada con los datos sin escribir mucho código.
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                // Cuando el texto cambie, se actualiza el contenido del TextView.
                textView.setText(s);
            }
        });

        // Se devuelve la vista principal del fragmento, ya con todos los componentes cargados.
        return root;
    }
}

/*📌 RESUMEN EXPLICATIVO
Antes, toda la lógica y datos estaban en la Activity. Cuando girabas la pantalla o el sistema mataba la app, se perdía todo. Esto complicaba mucho el manejo de estado.

Ahora, con Fragment + ViewModel + LiveData, los datos se mantienen vivos, la lógica se separa de la interfaz, y la UI se actualiza automáticamente cuando los datos cambian. Esto mejora el rendimiento, reduce errores, y hace que tu código sea más limpio, modular y fácil de mantener.*/