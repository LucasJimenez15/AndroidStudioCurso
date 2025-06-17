package x.miempresa.com.ejemploproyectoescuelait;
// Esta línea indica el "paquete" al que pertenece esta clase.
// En Android, los paquetes funcionan como carpetas organizadoras de archivos de código.

/**
 * Esta clase representa la pantalla principal de la aplicación.
 * Extiende de AppCompatActivity, que es una versión moderna de Activity con compatibilidad hacia atrás. Antes se usaba la clase `Activity` directamente, pero eso traía problemas al trabajar con funciones más modernas
 * o dispositivos con versiones viejas de Android. `AppCompatActivity` permite mantener compatibilidad con versiones antiguas.*/

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;                       // Necesario para el ciclo de vida de la actividad.
import android.view.View;                       // Permite manejar vistas.
import android.widget.AdapterView;              // Para manejar clics en elementos de la lista.
import android.widget.ListView;                 // Vista que permite mostrar una lista de elementos desplazables.
import android.widget.TextView;                 // Para mostrar texto.
import android.widget.Toast;                    // Para mostrar mensajes temporales (notificaciones pequeñas).

public class MainActivity extends AppCompatActivity {

    // Declaramos una referencia al ListView (donde se mostrarán los tweets)
    ListView listView;

    // Declaramos el adaptador que convertirá los objetos Tweet en vistas visuales para el ListView
    TweetAdapter adapter;

    // Este método se llama automáticamente cuando se crea la actividad (pantalla) es un metodo del ciclo de vida
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Llama al método del padre, necesario para iniciar la actividad correctamente.
        setContentView(R.layout.activity_main); // Carga el diseño XML asociado a esta actividad (activity_main.xml).

        // Creamos un arreglo de objetos Tweet que vamos a mostrar en la lista.
        // Cada tweet tiene una imagen (el mismo ícono en este caso) y un texto.
        Tweet[] tweets = new Tweet[]{
                new Tweet(R.drawable.ic_launcher, "El primer tweet"),
                new Tweet(R.drawable.ic_launcher, "El segundo tweet"),
                new Tweet(R.drawable.ic_launcher, "El tercero tweet"),
                new Tweet(R.drawable.ic_launcher, "El cuarto tweet"),
                new Tweet(R.drawable.ic_launcher, "El quinto tweet"),
                new Tweet(R.drawable.ic_launcher, "El sexto tweet"),
                new Tweet(R.drawable.ic_launcher, "El séptimo tweet"),
                new Tweet(R.drawable.ic_launcher, "El octavo tweet"),
                new Tweet(R.drawable.ic_launcher, "El noveno tweet"),
                new Tweet(R.drawable.ic_launcher, "El décimo tweet")
        };

        // Creamos el adaptador personalizado (TweetAdapter), que toma:
        // - el contexto (this → la actividad actual),
        // - el diseño de cada fila (listiview_item_row.xml),
        // - y el arreglo de datos (tweets)
        adapter = new TweetAdapter(this, R.layout.listiview_item_row, tweets);

        // Obtenemos el ListView desde el archivo de diseño mediante su ID
        listView = findViewById(R.id.listview);

        // Inflamos (creamos visualmente) una vista de encabezado (header) que aparecerá arriba de todos los elementos de la lista
        View header = getLayoutInflater().inflate(R.layout.list_header_row, null);

        // Agregamos esa vista como encabezado del ListView. Esto es útil para mostrar títulos o descripciones.
        listView.addHeaderView(header);

        // Asignamos el adaptador que contiene los datos y la lógica de visualización al ListView
        listView.setAdapter(adapter);

        // Definimos qué hacer cuando el usuario toca un ítem de la lista:
        // Se captura la vista seleccionada, se busca su TextView interno y se muestra un Toast con ese texto.
        listView.setOnItemClickListener((parent, view, position, id) -> {
            TextView texto = view.findViewById(R.id.textView); // Obtenemos el texto del ítem tocado
            Toast.makeText(getApplicationContext(), texto.getText(), Toast.LENGTH_SHORT).show(); // Mostramos un mensaje
        });
    }
}
