// Este paquete representa la ubicación del archivo dentro del proyecto.
package com.cursoandroid.navigationdrawer;

// Importa la clase Bundle, que permite pasar datos entre actividades.
import android.os.Bundle;

// Importa el botón flotante de acción y la barra emergente (Snackbar)
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

// Importaciones necesarias para la navegación con componentes de Jetpack
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

// Importa la vista de navegación lateral
import com.google.android.material.navigation.NavigationView;

// Importa el contenedor principal del Navigation Drawer
import androidx.drawerlayout.widget.DrawerLayout;

// Importa compatibilidad con componentes modernos en versiones antiguas
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

// Importa la clase que representa el menú
import android.view.Menu;

// Esta es la clase principal que representa la actividad del Navigation Drawer.
public class MainActivity extends AppCompatActivity {

    // Esta clase contiene la configuración de la barra de acciones (AppBar)
    // y los destinos principales del Navigation Drawer.
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Método llamado al crear la actividad.
        super.onCreate(savedInstanceState);

        // Asocia esta actividad al archivo XML activity_main.xml que define la UI.
        setContentView(R.layout.activity_main);

        // 🟡 CONFIGURACIÓN DE LA TOOLBAR 🟡
        // Encuentra la Toolbar en el layout y la usa como barra de acción de la app.
        // Antes de esto, se usaba la ActionBar por defecto, pero era difícil de personalizar.
        // La Toolbar permite personalizaciones más flexibles y se integra mejor con Material Design.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 🟢 CONFIGURACIÓN DEL DRAWER 🟢
        // DrawerLayout es el contenedor principal que permite deslizar el menú desde la izquierda.
        // Antes, los Navigation Drawers se implementaban manualmente con RelativeLayout y animaciones,
        // lo cual era complicado y daba muchos errores de diseño.
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        // NavigationView representa la parte del menú lateral con sus opciones (los ítems).
        // Es donde se cargan las opciones del menú desde el archivo menu/nav_menu.xml
        NavigationView navigationView = findViewById(R.id.nav_view);

        // 🔵 CONFIGURACIÓN DE LAS OPCIONES DEL MENÚ Y DESTINOS 🔵
        // Aquí se indican qué fragmentos están disponibles desde el menú.
        // Esta configuración permite que el botón "hamburguesa" funcione bien
        // y que los destinos sean considerados como principales (top-level).
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,       // Fragmento de inicio
                R.id.nav_gallery,    // Fragmento de galería
                R.id.nav_slideshow,  // Fragmento de presentación
                R.id.nav_share,      // Fragmento de compartir
                R.id.nav_send,       // Fragmento de envío
                R.id.nav_contato     // Fragmento de contacto
        )
                .setDrawerLayout(drawer) // Asocia esta configuración al DrawerLayout creado arriba.
                .build();

        // 🔴 CONTROLADOR DE NAVEGACIÓN 🔴
        // NavController es el encargado de gestionar la navegación entre fragmentos.
        // Antes esto se hacía manualmente con FragmentManager, lo cual era tedioso y propenso a errores.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Vincula la Toolbar con el NavController para que el título y el botón hamburguesa
        // se sincronicen automáticamente según el fragmento que esté activo.
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        // Vincula el menú lateral (NavigationView) con el NavController,
        // de modo que al seleccionar una opción del menú se cambie de fragmento automáticamente.
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    // Este método se encarga de manejar la acción de "volver" (flecha hacia atrás) en la barra de acción.
    // Si hay un fragmento al que se puede volver, lo hace. Si no, usa el comportamiento por defecto.
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}

/*🧠 ¿Por qué se usan estas nuevas formas?
Antes, la navegación en Android era manual y compleja:

Había que gestionar los FragmentTransaction manualmente. No había una integración clara entre la barra superior y los fragmentos. El botón de retroceso o el menú hamburguesa eran difíciles de sincronizar. Los errores en la navegación eran comunes.

Ahora, con Android Jetpack Navigation Component: Todo se maneja de forma automática y segura. Las transiciones entre pantallas se definen en un archivo XML (nav_graph.xml) El NavController y NavigationUI conectan los componentes visuales con el flujo de navegación. La Toolbar y el Drawer se integran de forma fluida.*/