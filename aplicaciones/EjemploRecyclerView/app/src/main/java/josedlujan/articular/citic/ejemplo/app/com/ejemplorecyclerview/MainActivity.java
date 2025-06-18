// Paquete del proyecto que define la ubicación de esta clase dentro de la estructura general del proyecto.
package josedlujan.articular.citic.ejemplo.app.com.ejemplorecyclerview;

// Importación de clases necesarias para la funcionalidad de la app.
// Estas clases forman parte de AndroidX y otras bibliotecas modernas.
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

// Importaciones modernas de AndroidX. Antes estas clases pertenecían a android.support.v7,
// lo cual traía problemas de compatibilidad con nuevas librerías. AndroidX es modular, moderno
// y con mejor mantenimiento.
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// Importaciones de Material Design (botones flotantes, snackbar, etc.)
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/**
 * Esta clase representa la actividad principal (MainActivity) de la aplicación.
 * Extiende de AppCompatActivity, que es una clase base para actividades compatibles
 * con versiones anteriores de Android, brindando soporte moderno gracias a AndroidX.
 */
public class MainActivity extends AppCompatActivity {

    // Declaramos el RecyclerView, que es un componente moderno para mostrar listas de elementos
    // de forma eficiente y flexible. Reemplaza al antiguo ListView, el cual era menos eficiente
    // y difícil de personalizar.
    RecyclerView recyclerView;

    // Adapter: El intermediario entre los datos y el RecyclerView. Es quien crea y enlaza los elementos visuales.
    RecyclerView.Adapter adapter;

    // LayoutManager: Define cómo se colocan los ítems dentro del RecyclerView (por ejemplo, en forma vertical).
    RecyclerView.LayoutManager layoutManager;

    // Arreglo de Strings que contiene los datos que se mostrarán en la lista.
    // En una app real, esto sería una lista de objetos más complejos (por ejemplo, usuarios, productos, etc.) Y podria ser recuperada desde un web service o un json y luego parseralo a un tipo de dato mediante una clase para que lo podamos manejar, o atravez de base de datos, etc...
    String[] Data = new String[]{
            "Elemento 1", "Elemento 2", "Elemento 3", "Elemento 4",
            "Elemento 5", "Elemento 6", "Elemento 7", "Elemento 8",
            "Elemento 9", "Elemento 10", "Elemento 11", "Elemento 12",
            "Elemento 13", "Elemento 14", "Elemento 15", "Elemento 16"
    };

    /*Método que se ejecuta cuando la actividad es creada, Aquí se inicializan todos los componentes
      visuales y funcionales.*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Establece el diseño de la actividad principal utilizando el archivo XML activity_main.xml.
        setContentView(R.layout.activity_main);

        // Obtenemos la referencia del Toolbar (barra superior de navegación) desde el layout.
        // El Toolbar es una mejora moderna sobre la antigua ActionBar, permitiendo mayor personalización. el toolbar sirve para dar ciertos efectos
        Toolbar toolbar = findViewById(R.id.toolbar);

        // Establece el toolbar como la barra de acción (action bar) de la actividad.
        // Esto permite mostrar íconos, menús y botones arriba.
        setSupportActionBar(toolbar);

        // Obtenemos una referencia al RecyclerView definido en el layout (activity_main.xml).
        recyclerView = findViewById(R.id.rv);

        // Definimos el LayoutManager como uno lineal vertical (como una lista).
        // Podría ser también horizontal o un grid. El LayoutManager es obligatorio. y para lo que sirve es que es como el administrador del layout y  le dice como se ubicara horizontal o vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Creamos el adaptador, que toma el arreglo de Strings y lo usa para llenar el RecyclerView.
        // Este adaptador debe estar definido en otra clase llamada RecyclerAdapter.
        adapter = new RecyclerAdapter(Data);

        // Asociamos el adaptador al RecyclerView. Ahora el RecyclerView sabrá cómo mostrar los datos.
        recyclerView.setAdapter(adapter);

        // Establece una animación por defecto para cuando los elementos cambian, se agregan o eliminan.
        // Es opcional, pero ayuda a que la interfaz se vea más fluida. sirve para crear una animacion
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Obtenemos una referencia al botón flotante (FAB) definido en el layout.
        // Este es el botón circular que suele usarse para acciones rápidas.
        FloatingActionButton fab = findViewById(R.id.fab);

        // Le asignamos un evento al FAB: cuando se hace clic, se muestra un mensaje tipo "snackbar"
        // en la parte inferior de la pantalla. Es una alternativa moderna al clásico Toast.
        fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
        );
    }

    /**
     * Este método se ejecuta automáticamente cuando se crea el menú de opciones (los tres puntos).
     * Infla el menú definido en res/menu/menu_main.xml para mostrarlo en el Toolbar.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Este método se ejecuta cuando el usuario selecciona un ítem del menú.
     * Aquí se puede definir la acción que se realiza cuando se selecciona, por ejemplo, "Ajustes".
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Si el ítem seleccionado es "Ajustes", devolvemos true para indicar que fue manejado.
        if (id == R.id.action_settings) {
            return true;
        }

        // Para otros ítems no manejados, llamamos al comportamiento por defecto.
        return super.onOptionsItemSelected(item);
    }
}

/*🧠 Resumen del Por qué de cada cambio moderno:
Componente	¿Cómo se hacía antes?	¿Por qué se cambió?
AppCompatActivity	Se usaba Activity o android.support.v7.app.AppCompatActivity	AndroidX lo reemplaza, es más compatible y modular
Toolbar	Se usaba ActionBar o android.support.v7.widget.Toolbar	Toolbar permite más personalización y mejor diseño
RecyclerView	Se usaba ListView	RecyclerView es más flexible, eficiente y permite mejores animaciones
FloatingActionButton y Snackbar	Se usaban botones simples y Toast	Material Design mejora la experiencia visual del usuario
LinearLayoutManager	Se colocaban los ítems manualmente en un LinearLayout	Mucho más trabajo, y sin reutilización de vistas*/