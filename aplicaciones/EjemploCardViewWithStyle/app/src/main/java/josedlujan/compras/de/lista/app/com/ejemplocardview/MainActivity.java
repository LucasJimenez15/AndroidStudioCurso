package josedlujan.compras.de.lista.app.com.ejemplocardview;
// El package indica la ruta del proyecto dentro de la estructura de carpetas.
// En Android se usa para organizar el código y evitar conflictos con otras clases con el mismo nombre en otros paquetes.

import android.os.Bundle;
// Bundle permite guardar y restaurar el estado de la actividad (por ejemplo, cuando se rota la pantalla)

import com.google.android.material.floatingactionbutton.FloatingActionButton;
// FloatingActionButton es un botón circular que flota sobre la interfaz, introducido con Material Design para destacar acciones principales

import com.google.android.material.snackbar.Snackbar;
// Snackbar es una alternativa moderna a los Toasts: aparece en la parte inferior y permite una acción opcional

import androidx.appcompat.app.AppCompatActivity;
// AppCompatActivity es una clase base que proporciona compatibilidad con versiones antiguas de Android
// Antes se usaba Activity directamente, pero era más limitado y no funcionaba bien con los nuevos componentes visuales

import androidx.appcompat.widget.Toolbar;
// Toolbar es una versión moderna de ActionBar, más personalizable. Con ella podemos incluir botones, títulos, íconos, etc.

import androidx.recyclerview.widget.GridLayoutManager;
// GridLayoutManager permite mostrar los ítems en forma de cuadrícula (varias columnas)

import androidx.recyclerview.widget.LinearLayoutManager;
// LinearLayoutManager organiza los ítems en una lista vertical u horizontal

import androidx.recyclerview.widget.RecyclerView;
// RecyclerView es el sucesor de ListView y GridView. Es más eficiente, flexible y permite personalizaciones avanzadas.
// Antes con ListView era muy complicado hacer animaciones o diseños distintos. RecyclerView soluciona eso.

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // RecyclerView será el componente visual donde se mostrarán los ítems en forma de lista o grilla
    RecyclerView recyclerView;

    // El adaptador conecta los datos (userList) con el RecyclerView y se encarga de crear las vistas (ViewHolder)
    RecyclerAdapter adapter;

    // Lista que contendrá los objetos User que queremos mostrar (cada uno con título, descripción e imagen)
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Llama al método onCreate de la clase base
        setContentView(R.layout.activity_main); // Carga la interfaz visual definida en activity_main.xml

        // Buscamos el Toolbar en el layout y lo configuramos como la barra principal de navegación
        // Antes se usaba "ActionBar", pero era poco flexible y no funcionaba igual en todos los dispositivos
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Buscamos el RecyclerView en el layout (tiene el ID rv)
        recyclerView = (RecyclerView)findViewById(R.id.rv);

        // Creamos un LinearLayoutManager que organiza los ítems en forma vertical, uno debajo del otro
        // El tercer parámetro "false" indica que no queremos invertir el orden (de abajo hacia arriba)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // Asignamos ese layout manager al RecyclerView
        recyclerView.setLayoutManager(linearLayoutManager);

        // Cargamos la lista con datos simulados para mostrar
        initializeData();

        // Creamos el adaptador, pasándole la lista de datos y el contexto de la actividad
        adapter = new RecyclerAdapter(userList, this);

        // Asignamos el adaptador al RecyclerView para que comience a mostrar los datos
        recyclerView.setAdapter(adapter);

        /*
        CÓDIGO OPCIONAL PARA MOSTRAR EN FORMA DE GRILLA (2 columnas):

        recyclerView = (RecyclerView)findViewById(R.id.rv); // Referenciamos el RecyclerView
        recyclerView.setHasFixedSize(true); // Indica que el tamaño del RecyclerView no cambia (mejora el rendimiento)

        GridLayoutManager glm = new GridLayoutManager(this, 2); // Queremos 2 columnas
        glm.setOrientation(LinearLayoutManager.VERTICAL); // Mostramos las columnas en forma vertical

        recyclerView.setLayoutManager(glm); // Asignamos el layout manager de tipo grilla

        initializeData(); // Cargamos los datos
        adapter = new RecyclerAdapter(userList, this); // Creamos el adaptador
        recyclerView.setAdapter(adapter); // Lo conectamos al RecyclerView
        */

        // Obtenemos el botón flotante del layout
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        // Asignamos una acción al hacer clic: mostrar un mensaje breve (Snackbar)
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar muestra un mensaje con duración larga y permite agregar una acción (botón)
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); // "Action" está deshabilitada aquí (null)
            }
        });
    }

    // Este método se llama automáticamente al crear el menú en la barra superior (Toolbar)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); // Carga el menú desde el archivo XML
        return true;
    }

    // Este método maneja lo que pasa cuando se selecciona una opción del menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId(); // Obtenemos el ID del ítem seleccionado

        // Si el usuario toca "settings", podríamos abrir otra pantalla o mostrar opciones
        if (id == R.id.action_settings) {
            return true;
        }

        // Si no era la opción de configuración, dejamos que lo maneje el sistema
        return super.onOptionsItemSelected(item);
    }

    // Método privado que crea y llena la lista con datos ficticios
    private void initializeData() {
        // Creamos una lista nueva de tipo ArrayList
        userList = new ArrayList<>();

        // Agregamos objetos "User" con título, texto largo y una imagen que está en la carpeta drawable
        userList.add(new User("Canada - Lago", "Este Texto es para probar solamente...", R.drawable.paisaje1));
        userList.add(new User("Cancún - Playa", "Este Texto es para probar solamente...", R.drawable.paisaje2));
        userList.add(new User("Europa - Montaña", "Este Texto es para probar solamente...", R.drawable.paisaje3));
        userList.add(new User("Canada - Lago", "Este Texto es para probar solamente...", R.drawable.paisaje1));
        userList.add(new User("Cancún - Playa", "Este Texto es para probar solamente...", R.drawable.paisaje2));
        userList.add(new User("Europa - Montaña", "Este Texto es para probar solamente...", R.drawable.paisaje3));
    }
}

/*👀 ¿Qué resolvió RecyclerView que antes no podíamos con ListView?
Antiguo ListView	Nuevo RecyclerView
Renderizaba todas las vistas sin reciclar	Usa patrón ViewHolder para reciclar vistas
Difícil personalización (solo texto/imágenes)	Permite cualquier tipo de diseño complejo
No soportaba animaciones fácilmente	Soporta animaciones y transiciones fluidas
No soportaba listas en grilla	Se adapta fácilmente con GridLayoutManager
Lento con muchos ítems	Mucho más eficiente y escalable*/