package com.example.ejemplosensores;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

// La clase MainActivity extiende AppCompatActivity para poder usar las características modernas de Android
// e implementa SensorEventListener para escuchar los eventos del sensor (en este caso el acelerómetro) y agrega o debemos implementar 2 metodos el onSensorChanged y el onAcurracyChanged.
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // Variables de tipo TextView que mostrarán en pantalla los valores del acelerómetro en los ejes X, Y y Z.
    TextView x, y, z;

    // Variable para referenciar el sensor acelerómetro que usaremos para obtener datos.
    Sensor acelerometro;

    // onCreate es el primer método que se ejecuta cuando la actividad se crea por primera vez.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Indicamos que el layout que usaremos es activity_main.xml
        setContentView(R.layout.activity_main);

        // Referenciamos los TextViews del layout para poder actualizar su texto con datos reales.
        // findViewById busca la vista en el layout por su id.
        x = findViewById(R.id.x);
        y = findViewById(R.id.y);
        z = findViewById(R.id.z);

        // Bloqueamos la orientación de la pantalla para que siempre esté en modo retrato (vertical).
        // Esto es importante porque los sensores pueden variar sus valores con el cambio de orientación
        // y se evita que la interfaz se desorganice al girar la pantalla.
        // Antes, la orientación podía cambiar libremente y eso complicaba el manejo de sensores y la UI.
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    // onResume es llamado cuando la actividad pasa a primer plano y está lista para interactuar con el usuario.
    @Override
    protected void onResume() {
        super.onResume();

        // Obtenemos el servicio de sensores del sistema Android, que nos permitirá acceder a los sensores disponibles. y que lo queremos utilizar
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        //luego deberemos preguntar por los sensores del telefono

        // Obtenemos una lista de sensores del tipo acelerómetro. aqui en el TYPE podremos elegir cual es el tipo de sensor del que queremos obtener informacion para realizar determinadas cosas
        // Antes, para usar sensores se tenía que hacer mucha gestión manual y era más propenso a errores.
        // Ahora Android maneja todo eso con SensorManager y permite acceso fácil y seguro.
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (sensors.size() > 0) {
            // Si existe al menos un acelerómetro, nos registramos como escuchas de sus eventos.
            // Esto quiere decir que la clase MainActivity (this) recibirá actualizaciones del sensor.
            // SENSOR_DELAY_GAME es una constante que indica la frecuencia con la que queremos recibir datos.
            // Esta frecuencia es un buen balance para apps que necesitan datos en tiempo casi real sin saturar la CPU.
            sm.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_GAME);

            // Nota importante: En versiones antiguas, la gestión de sensores podía causar fugas de memoria
            // si no se desregistraba el listener apropiadamente (lo que hacemos en onPause y onStop).
        }
    }

    // onPause es llamado cuando la actividad deja de estar en primer plano pero puede volver a ser visible.
    @Override
    protected void onPause() {
        // Aquí es importante liberar los recursos del sensor para evitar que siga consumiendo batería
        // y para evitar fugas de memoria, ya que la actividad ya no está activa para procesar los datos.
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.unregisterListener(this, acelerometro);
        super.onPause();
    }

    // onStop es llamado cuando la actividad deja de ser visible.
    @Override
    protected void onStop() {
        // También cancelamos la escucha del sensor aquí, para asegurarnos que cuando la app no se vea
        // no siga consumiendo recursos. Esto es una buena práctica para optimizar la batería y rendimiento.
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.unregisterListener(this, acelerometro);
        super.onStop();
    }

    // Este método crea el menú de opciones que se muestra en la interfaz de la app.
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflamos el menú con el recurso menu_main.xml que contiene los ítems del menú.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Aquí manejamos las acciones cuando se selecciona un ítem del menú.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Por ejemplo, si el ítem con id 'main' es seleccionado, simplemente retornamos true para indicar que el evento se consumió.
        if (id == R.id.main) {
            return true;
        }

        // Para otros ítems, dejamos que la superclase maneje la acción por defecto.
        return super.onOptionsItemSelected(item);
    }

    // Método obligatorio para la interfaz SensorEventListener.
    // Se llama cada vez que hay un cambio en el sensor que estamos escuchando. ya que nosotros cada vez que usemos sensores queremos saber sus valores y saber cuando cambian
    @Override
    public void onSensorChanged(SensorEvent event) { //en este metodo recibimos un evento
        // Actualizamos los TextViews con los valores actuales del acelerómetro en los ejes X, Y y Z.
        // event.values es un array donde se guardan los valores del sensor.
        // SensorManager.DATA_X, DATA_Y y DATA_Z son índices predefinidos para acceder a esos valores específicos.
        // Antes, los valores de sensores se accedían de forma menos estándar, causando confusión.
        this.x.setText("X = " + event.values[SensorManager.DATA_X]);
        this.y.setText("Y = " + event.values[SensorManager.DATA_Y]);
        this.z.setText("Z = " + event.values[SensorManager.DATA_Z]);
    }

    // Otro método obligatorio para SensorEventListener, se llama cuando cambia la precisión del sensor.
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { //recibimos un sensor y una medida
        // No se necesita hacer nada aquí para este ejemplo,
        // pero se implementa porque es parte de la interfaz SensorEventListener.
    }
}


/*Explicación adicional y contexto histórico:
Uso del SensorManager y SensorEventListener:
Antes de Android 3.0 (Honeycomb), trabajar con sensores era más complicado y menos optimizado. Los desarrolladores tenían que gestionar manualmente la frecuencia de muestreo y la liberación de recursos, lo que a menudo causaba fugas de memoria o un gasto excesivo de batería.
Hoy en día, Android ofrece SensorManager para manejar los sensores de forma centralizada y segura, y permite registrar y desregistrar listeners con diferentes niveles de frecuencia para equilibrar precisión y rendimiento.

Registro y desregistro de sensores:
Es fundamental desregistrar el listener en onPause() y onStop() para evitar que el sensor siga enviando datos cuando la app no está visible. Esto previene fugas de memoria y reduce consumo de batería, algo que no siempre se hacía correctamente en apps antiguas.

Bloqueo de orientación:
Antes, si no se bloqueaba la orientación, la app podía rotar y reconstruirse cuando el usuario giraba el dispositivo. Esto podía interrumpir la experiencia del usuario y complicar la gestión del sensor, porque cada rotación podía reiniciar la actividad y reiniciar el sensor, causando latencia o inconsistencias.

Manejo de los valores del sensor:
Los valores del acelerómetro se entregan en un array con índices fijos (DATA_X, DATA_Y, DATA_Z), lo cual facilita el acceso y la claridad del código. Antes, se usaban constantes menos intuitivas o se manejaban datos sin estandarizar, generando confusión y errores.*/