package com.example.ejemplonavegationdrawer;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // usa el layout con DrawerLayout

// Referencias a las vistas
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);

// Configurar Toolbar como ActionBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mi App");

// Crear el toggle para el Drawer (icono "hamburguesa")
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.nav_open,  // "Abrir menú" (contentDescription para accesibilidad)
                R.string.nav_close  // "Cerrar menú"
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState(); // muestra el icono en el Toolbar

// Manejar selección de ítems en el NavigationView
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    // Este método se llama cuando se selecciona un ítem del menú lateral
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
// Identificar el ítem por su ID
        int id = item.getItemId();
        if (id == R.id.nav_account) {
// Navegar a la sección Cuenta
// Ejemplo: reemplazar fragmento
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new CuentaFragment())
                    .commit();
        } else if (id == R.id.nav_settings) {
// Navegar a Configuraciones
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SettingsFragment())
                    .commit();
        }
// ... manejar otros ítems ...

// Cerrar el Drawer tras la selección
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // Si presionan "atrás" con el drawer abierto, cerrarlo primero
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}


/*Este código ejemplifica lo siguiente:
        •	En onCreate, después de setContentView, obtenemos referencias a DrawerLayout, Toolbar y NavigationView. Luego llamamos setSupportActionBar(toolbar) para usar la toolbar como ActionBar.

        •	ActionBarDrawerToggle: creamos un objeto ActionBarDrawerToggle que enlaza el DrawerLayout con la toolbar. Le pasamos los recursos R.string.nav_open y R.string.nav_close para describir la acción (accesibilidad). Luego lo añadimos al Drawer con addDrawerListener y sincronizamos el estado con syncState(). Esto muestra automáticamente el icono de “hamburguesa” y lo convierte en flecha de retroceso cuando sea necesario.

        •	NavigationView.OnNavigationItemSelectedListener: implementamos esta interfaz para responder a los clics de menú. En el método onNavigationItemSelected, comprobamos item.getItemId() y realizamos la acción correspondiente (por ejemplo, reemplazar un fragmento en fragment_container). Al final cerramos el drawer con drawerLayout.closeDrawer(GravityCompat.START).

        •	onBackPressed: opcionalmente, sobrecargamos este método para cerrar el drawer cuando esté abierto antes de cerrar la actividad. Esto mejora la usabilidad.
El código comentado anterior está basado en ejemplos comunes. Notar que AndroidX recomienda ahora el uso de Navigation Component para una gestión más sencilla del menú, pero el enfoque tradicional con ActionBarDrawerToggle y fragmentos sigue siendo válido.



        Buenas prácticas
Para un Navigation Drawer robusto y accesible, considere lo siguiente:
        •	Accesibilidad: use cadenas descriptivas (nav_open y nav_close) para el toggle del drawer. Esto permite que TalkBack anuncie correctamente “Abrir menú” o “Cerrar menú” al interactuar con el icono. Asegúrese de que los ítems del menú tengan texto legible y, si es apropiado, texto alternativo para iconos. Los márgenes y tamaños recomendados (14 sp para textos, 48x48 dp para botones, etc.) ya favorecen la accesibilidad visual según Material Design.

        •	Uso de Fragments: es común que cada opción del menú lateral cargue un fragmento en el FrameLayout del contenido principal. Esto mantiene una sola actividad master y facilita el manejo del ciclo de vida. En el ejemplo anterior, los fragmentos CuentaFragment, SettingsFragment, etc., se reemplazan según la selección. Recuerde usar getSupportFragmentManager() (AndroidX) para gestionar estas transacciones.

        •	Compatibilidad con orientación y dispositivos grandes: en tablets o pantallas anchas se puede optar por un drawer permanente (siempre abierto) usando layouts en layout-sw600dp/ o layout-land/. Si no, al rotar el dispositivo preserve el estado del drawer (puede verificar en onCreate si estaba abierto con savedInstanceState). Android gestionará en gran medida la rotación, pero vale la pena probar que el fragmento actual y el estado del menú se mantengan. También asegúrese de usar android:layout_gravity="start", de modo que en idiomas RTL el drawer se muestre por el lado derecho automáticamente (Android lo maneja).

        •	Compatibilidad de versiones: use la librería Material Components (com.google.android.material:material) para el NavigationView, como recomienda Google. Ya no se usan las viejas Support Libraries para esto. Agregue en el build.gradle la dependencia de Material (por ejemplo implementation 'com.google.android.material:material:1.x.x').


        •	Otros consejos: si su app combina menú inferior y lateral, recuerde respetar las pautas de Material. Evite redundancias de navegación (no tenga el mismo destino accesible desde dos menús diferentes). Y cuide que los ítems de menú estén en res/menu y sean simples, sin lógica compleja; la lógica va en la Activity o ViewModel.
        En resumen, Google enfatiza el diseño universal y la coherencia visual. El Navigation Drawer debe implementarse siguiendo las especificaciones de espaciado, tipografía y colores de Material Design. Además, usar fragmentos para manejar el contenido garantiza una arquitectura modular y facilita la orientación de pantalla. Siguiendo estas pautas –y el código de ejemplo anterior– obtendrá un drawer funcional, accesible y bien integrado en Android Studio con Java.*/