// Este archivo pertenece al paquete del proyecto. Define su ubicación dentro de la estructura de carpetas del proyecto Java.
package josedlujan.app.primera.mi.com.ejemplosonidomediaplayer;

// Importamos la clase base AppCompatActivity, que es una versión mejorada de Activity compatible con versiones antiguas de Android.
// Nos permite usar funcionalidades modernas (como Theme.Material, Toolbars, Fragmentos, etc.) incluso en dispositivos antiguos gracias a AndroidX.
import androidx.appcompat.app.AppCompatActivity;

// Importamos la clase MediaPlayer, que nos permite reproducir archivos de audio o video.
// Soporta formatos como .mp3, .ogg, .wav, etc., y ofrece métodos para controlar la reproducción: start, stop, pause, seek, etc.
import android.media.MediaPlayer;

// Importamos clases necesarias para crear la interfaz de usuario y manejar la interacción del usuario.
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// Importamos el archivo R.java que contiene las referencias a los recursos definidos en XML (layouts, strings, drawables, etc.)
// Por ejemplo, R.layout.activity_main hace referencia al archivo res/layout/activity_main.xml.
import josedlujan.compras.de.lista.app.com.EjemploSonidoMediaPlayer.R;

// Declaramos la clase MainActivity, que representa la pantalla principal de la app.
// Extiende AppCompatActivity para compatibilidad y funciones modernas.
// También implementa View.OnClickListener, lo que permite que esta clase maneje eventos de clic de botones desde un único método.
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Declaramos dos botones: uno para reproducir el audio y otro para detenerlo.
    private Button botonA, botonB;

    // Declaramos un objeto MediaPlayer que se usará para manejar la reproducción del archivo de audio.
    private MediaPlayer mp;

    // onCreate() es el método principal que se ejecuta al iniciar la pantalla (actividad).
    // Aquí se realiza toda la inicialización: se carga la interfaz y se configuran los eventos.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Llama a la versión base del método para asegurar la inicialización correcta.

        // Asigna la interfaz de usuario a esta actividad usando el layout XML correspondiente.
        // Este archivo define los botones y otros elementos visuales que se mostrarán en pantalla.
        setContentView(R.layout.activity_main);

        // Inicializamos el objeto MediaPlayer cargando un archivo de sonido ubicado en res/raw/main_theme.mp3 (o .ogg).
        // Esta es la forma más simple de reproducir audio en Android.
        mp = MediaPlayer.create(this, R.raw.main_theme);

        // Conectamos las variables de botón con los elementos del diseño usando sus ID definidos en el XML.
        // findViewById busca dentro del layout cargado y devuelve una referencia al botón correspondiente.
        botonA = findViewById(R.id.boton);    // Botón de "Play"
        botonB = findViewById(R.id.botonS);   // Botón de "Stop"

        // Registramos esta actividad como "escuchador" de los clics en los botones.
        // Al implementar View.OnClickListener, podemos manejar todos los clics con un solo método.
        botonA.setOnClickListener(this);
        botonB.setOnClickListener(this);
    }

    // Este método se ejecuta cuando el usuario hace clic en uno de los botones que tienen registrado un listener.
    // Se recibe un objeto View (v), que representa el botón clickeado. Se puede identificar por su ID.
    @Override
    public void onClick(View v) {
        int id = v.getId(); // Obtenemos el ID del botón presionado.

        // Usamos condicionales para saber qué botón fue presionado y ejecutar la acción correspondiente.
        if (id == R.id.boton) {
            play(); // Reproducir el audio si se presionó el botón de "Play"
        } else if (id == R.id.botonS) {
            stop(); // Detener el audio si se presionó el botón de "Stop"
        }
    }

    // Método que reproduce el audio desde el principio.
    private void play() {
        // Verificamos que el objeto MediaPlayer exista y que no esté ya reproduciendo para evitar errores.
        if (mp != null && !mp.isPlaying()) {
            mp.start(); // Inicia la reproducción del sonido.
        }
    }

    // Método que detiene la reproducción actual del audio.
    private void stop() {
        // Si el audio está en reproducción, lo detenemos.
        if (mp != null && mp.isPlaying()) {
            mp.stop(); // Detiene la reproducción inmediatamente.

            // Luego de llamar a stop(), el objeto MediaPlayer queda en un estado inválido para seguir usándolo.
            // Para volver a reproducir, debemos "prepararlo" nuevamente, lo cual reinicializa el archivo de audio.
            try {
                mp.prepare(); // Prepara el MediaPlayer para una nueva reproducción.
            } catch (Exception e) {
                // Si ocurre un error durante la preparación (por ejemplo, si el archivo no está disponible), lo mostramos en la consola.
                e.printStackTrace();
            }
        }
    }

    // Este método se llama automáticamente cuando la actividad se destruye (por ejemplo, cuando el usuario sale de la app).
    // Es importante liberar los recursos utilizados por el MediaPlayer para evitar pérdidas de memoria (memory leaks).
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Si el MediaPlayer fue usado, lo liberamos correctamente.
        if (mp != null) {
            mp.release(); // Libera los recursos del sistema asociados al MediaPlayer.
            mp = null;    // Eliminamos la referencia al objeto por seguridad.
        }
    }
}

/*🧠 BONUS:
AppCompatActivity forma parte de AndroidX y permite usar componentes modernos como Theme.Material3, fragmentos y compatibilidad con Dark Mode.
MediaPlayer es ideal para reproducir archivos largos como música o locuciones. Para efectos cortos y repetitivos, se recomienda usar SoundPool.
El archivo de sonido debe estar en res/raw/ con nombres en minúsculas y sin caracteres especiales.
Soporta formatos como .mp3, .ogg, y .wav. */

/*🧠 ¿Qué es un OnClickListener? Un OnClickListener es una interfaz en Android que permite escuchar los clics del usuario sobre elementos visuales como botones. En este código:

public class MainActivity extends AppCompatActivity implements View.OnClickListener
✔️ Estamos diciendo que MainActivity va a manejar los clics de vista (como botones), implementando el método:

@Override
public void onClick(View v)

📦 ¿Por qué usar implements View.OnClickListener? Usar esta forma tiene ventajas: Centraliza el manejo de eventos en un solo lugar. Evita tener que crear muchos listeners diferentes. Hace el código más organizado si hay varios botones.

🔁 ¿Qué hace setOnClickListener(this);? Esto:
botonA.setOnClickListener(this);
botonB.setOnClickListener(this);
👉 le dice a cada botón: "cuando te hagan clic, llamá al método onClick() de esta clase (MainActivity)".

🔎 ¿Cómo sabe cuál botón fue presionado? Dentro del onClick(View v):

int id = v.getId();
Esto toma el ID del botón clickeado (R.id.boton, por ejemplo) y lo compara:

if (id == R.id.boton) {
    play();
} else if (id == R.id.botonS) {
    stop();
}

🎯 ¿Por qué separar en métodos play() y stop()? Separar la lógica en métodos como play() y stop() ayuda a: Tener el código más limpio y legible. Reutilizar fácilmente esas funciones si se necesitan en otro momento. Evitar duplicar código dentro del onClick().

🧠 En resumen: implements View.OnClickListener: permite que la clase controle eventos de clic. setOnClickListener(this): vincula el botón con el método onClick() de la actividad. onClick(View v): recibe el botón que se presionó y ejecuta lo que le corresponde. play() y stop(): separan la lógica de reproducción para que sea más claro y reutilizable.*/