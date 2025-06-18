package datos.preferencias.plataforma.cursos.escuela.it.ejemplopreferenciasdatos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import josedlujan.compras.de.lista.app.com.ejemplocardview.databinding.ActivityMainBinding;

// Esta clase es la actividad principal de la aplicación,
// donde se muestra y se guarda un texto usando SharedPreferences para que persista entre sesiones.
public class MainActivity extends AppCompatActivity {

    // Variable para manejar el ViewBinding, que es una forma moderna y segura para acceder a las vistas definidas en el XML sin usar findViewById. Antes, usar findViewById era necesario, pero era más propenso a errores (como referencias nulas) y más repetitivo.
    private ActivityMainBinding binding;

    // Variable para almacenar la referencia a SharedPreferences, que es el mecanismo simple de Android para guardar datos pequeños (clave-valor). Se usa para guardar configuraciones o información que debe persistir entre sesiones.
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializamos ViewBinding. Esto crea un objeto que enlaza las vistas declaradas en el XML (activity_main.xml) con variables en este código, evitando la necesidad de usar findViewById. Esto ayuda a prevenir errores, mejora la legibilidad y el mantenimiento.
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        // Establecemos el layout que usará la actividad, usando la raíz de la jerarquía de vistas creada por ViewBinding.
        setContentView(binding.getRoot());

        // Obtenemos la instancia de SharedPreferences con el nombre "preferencias". El modo Context.MODE_PRIVATE indica que estas preferencias solo pueden ser accedidas por esta aplicación, y hace que el archivo que guarda esta informacion no pueda ser accedido por otra aplicacion para modificarla (cosa que antes sucedia). Antes, guardar datos se hacía con archivos o bases de datos más complejas, pero SharedPreferences es sencillo y rápido para datos pequeños.
        preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);

        // Leemos el valor guardado con la clave "pepito" de SharedPreferences. Si no existe ningún valor guardado, retorna "valor por defecto". Esto es útil para evitar errores si aún no se ha guardado nada.
        String valorGuardado = preferences.getString("pepito", "valor por defecto");

        // Mostramos el valor recuperado en el TextView correspondiente. Así el usuario puede ver la información que fue guardada anteriormente.
        binding.textView.setText(valorGuardado);

        // Configuramos el evento click para el botón usando una expresión lambda. Antes se usaban clases anónimas con mucha más sintaxis, lo que hacía el código más extenso y menos claro.
        /*Un listener (escuchador) en Android es un objeto que "escucha" eventos, como toques en botones, cambios de texto, clics, etc. Cuando ocurre ese evento, el listener ejecuta un bloque de código (una acción que tú defines).
        Por ejemplo, un botón usa un setOnClickListener() para saber qué hacer cuando se lo toca.
        Resumiendo: 👉 Un listener espera un evento y responde cuando sucede.*/

        binding.boton.setOnClickListener(v -> {
            // Obtenemos el texto ingresado en el EditText y le quitamos espacios al inicio y final con trim() para guardar un dato limpio.
            String dato = binding.editText.getText().toString().trim();

            // Creamos un editor para SharedPreferences que una interfaz como podemos ver para poder modificar los datos. Luego guardamos la cadena 'dato' bajo la clave "pepito". Finalmente, usamos apply() para guardar de forma asíncrona. Antes se usaba commit(), que bloqueaba el hilo principal y podía causar problemas de rendimiento o que la interfaz se congelara.
            preferences.edit().putString("pepito", dato).apply();
        });
    }
}

/*Resumen de los puntos importantes en la explicación:
ViewBinding: Evita el findViewById que era tedioso, propenso a errores y poco legible.
SharedPreferences: Método sencillo para guardar datos pequeños (texto, números).
apply() vs commit(): apply() guarda en segundo plano sin bloquear la interfaz, mientras que commit() es síncrono y puede causar lentitud.
Lambdas: Reducen mucho la cantidad de código para eventos como clics, haciendo el código más limpio.*/