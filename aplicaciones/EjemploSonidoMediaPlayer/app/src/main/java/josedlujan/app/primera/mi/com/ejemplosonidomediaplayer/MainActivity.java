// Este archivo pertenece al paquete del proyecto. Define su ubicación dentro de la estructura de carpetas.
package josedlujan.app.primera.mi.com.ejemplosonidomediaplayer;

// Importamos la clase base AppCompatActivity, que es una versión mejorada de Activity compatible con versiones antiguas de Android.
import androidx.appcompat.app.AppCompatActivity;

// Importamos la clase MediaPlayer para reproducir archivos de audio.
import android.media.MediaPlayer;

// Importamos componentes de la interfaz gráfica.
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// Importamos el archivo generado automáticamente con referencias a los recursos del proyecto, como layouts, botones, etc.
import josedlujan.compras.de.lista.app.com.EjemploSonidoMediaPlayer.R;

// Esta clase representa la pantalla principal de la app.
// Extiende AppCompatActivity, lo cual nos permite usar temas modernos y compatibilidad con versiones anteriores de Android.
// También implementa View.OnClickListener para poder manejar eventos de clic en botones desde un solo método.
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Declaramos dos botones que usaremos para iniciar y detener el audio.
    private Button botonA, botonB;

    // Declaramos el objeto MediaPlayer, que manejará la reproducción del archivo de sonido.
    private MediaPlayer mp;

    // Este método se llama automáticamente cuando se crea la actividad (pantalla).
    // Aquí es donde se inicializa todo lo necesario para que funcione la app.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Llama al método original de la clase padre.

        // Cargamos la interfaz definida en el archivo XML "activity_main.xml"
        setContentView(R.layout.activity_main);

        // Creamos el MediaPlayer usando un archivo de sonido ubicado en la carpeta res/raw.
        // Antes, para reproducir sonido, se usaban APIs más complejas como SoundPool o clases externas.
        // MediaPlayer es más sencillo y permite reproducir archivos completos fácilmente.
        mp = MediaPlayer.create(this, R.raw.main_theme);

        // Asociamos los botones definidos en el XML con sus respectivas variables Java.
        // Esto se conoce como "referenciar vistas". Nos permite manipularlas desde el código.
        botonA = findViewById(R.id.boton);    // Este botón servirá para iniciar la música.
        botonB = findViewById(R.id.botonS);   // Este otro botón será para detenerla.

        // Registramos esta clase como listener (escuchador) de los clics en ambos botones.
        // Esto quiere decir que cuando se haga clic, se ejecutará el método "onClick".
        botonA.setOnClickListener(this);
        botonB.setOnClickListener(this);
    }

    // Este método se ejecuta automáticamente cada vez que se hace clic en alguno de los botones registrados.
    // Se usa el parámetro View "v" para saber qué botón fue presionado.
    @Override
    public void onClick(View v) {
        int id = v.getId(); // Obtenemos el ID del botón que se presionó.

        // Usamos un condicional para identificar cuál fue el botón y actuar en consecuencia.
        if (id == R.id.boton) {
            play(); // Si fue el botón "boton", llamamos al método que reproduce el sonido.
        } else if (id == R.id.botonS) {
            stop(); // Si fue el botón "botonS", detenemos la reproducción.
        }
    }

    // Método para reproducir el audio.
    private void play() {
        // Verificamos que el MediaPlayer no sea nulo y que no esté ya reproduciendo.
        // Esto evita errores como "IllegalStateException".
        if (mp != null && !mp.isPlaying()) {
            mp.start(); // Inicia la reproducción del sonido.
        }
    }

    // Método para detener el audio.
    private void stop() {
        // Si el sonido se está reproduciendo, lo detenemos.
        if (mp != null && mp.isPlaying()) {
            mp.stop(); // Detiene el sonido actual.

            // Luego de llamar a stop(), el MediaPlayer necesita ser "preparado" nuevamente.
            // Si no se llama a prepare(), el MediaPlayer no podrá reproducir de nuevo.
            try {
                mp.prepare(); // Lo preparamos para poder reproducir de nuevo.
            } catch (Exception e) {
                // En caso de error al preparar, mostramos la excepción en consola.
                e.printStackTrace();
            }
        }
    }

    // Este método se llama cuando la actividad se está cerrando o destruyendo (por ejemplo, al salir de la app).
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Es muy importante liberar los recursos del MediaPlayer cuando ya no se necesita.
        // Si no se hace esto, la app puede consumir memoria innecesariamente o causar errores.
        if (mp != null) {
            mp.release(); // Libera los recursos asociados al audio.
            mp = null;    // Por seguridad, limpiamos la referencia.
        }
    }
}

/*🧠 Bonus: ¿Por qué se usaba diferente antes?
Antes de AppCompatActivity, se usaba directamente Activity, pero traía muchos problemas:
No era compatible con todas las versiones de Android.
No incluía muchas mejoras de interfaz modernas.
No permitía usar fácilmente componentes como Toolbar, Dark Mode, etc.
AppCompatActivity vino con las Support Libraries (hoy AndroidX), que permiten mantener una app moderna compatible con versiones antiguas de Android (desde Android 5.0+ normalmente).*/