// Paquete que indica la ubicación del archivo dentro del proyecto.
package com.cursoandroid.navigationdrawer.ui.home;

// Importa LiveData, que permite observar datos y reaccionar automáticamente cuando cambian.
import androidx.lifecycle.LiveData;

// MutableLiveData es una versión modificable de LiveData.
import androidx.lifecycle.MutableLiveData;

// ViewModel forma parte de la arquitectura de Android Jetpack.
// Su objetivo es almacenar y gestionar datos para la interfaz de usuario (UI) de manera segura y eficiente.
import androidx.lifecycle.ViewModel;

// Esta clase es el ViewModel del fragmento Home.
// El ViewModel mantiene los datos visibles de forma segura aunque se roten la pantalla o se cierre temporalmente la app.
public class HomeViewModel extends ViewModel {

    // Variable privada que contiene el texto observado desde la UI.
    // MutableLiveData significa que su valor puede cambiar internamente en esta clase.
    private MutableLiveData<String> mText;

    // Constructor del ViewModel
    public HomeViewModel() {
        // Se instancia el MutableLiveData.
        mText = new MutableLiveData<>();

        // Se establece un valor inicial al texto.
        // Este texto será el que se muestre en el TextView del fragmento cuando se abra.
        mText.setValue("This is home fragment");

        // 🟢 ¿Por qué usar ViewModel + LiveData?
        // Anteriormente, los datos se almacenaban directamente en la Activity o el Fragment,
        // pero al rotar la pantalla o si el sistema destruía la UI, esos datos se perdían.
        // ViewModel conserva los datos mientras la UI esté viva, y LiveData notifica automáticamente
        // a la interfaz si los datos cambian. Así, se separa la lógica de negocio de la vista.
    }

    // Este método permite que otras clases (como el fragmento) puedan observar el valor del texto.
    // Se devuelve LiveData, no MutableLiveData, para que desde afuera solo puedan observarlo, pero no modificarlo.
    public LiveData<String> getText() {
        return mText;
    }
}


/*📌 RESUMEN ACLARATORIO
🧠 ¿Qué se hacía antes de ViewModel y LiveData?
Antes, se usaban variables directamente dentro de la Activity o Fragment. Esto traía muchos problemas:
Al rotar la pantalla, la actividad se destruía y se creaba de nuevo, perdiendo toda la información.
Había que usar métodos como onSaveInstanceState() para guardar y recuperar manualmente los datos, lo cual era muy tedioso.
No había una forma simple de reactivar la UI automáticamente cuando los datos cambiaban.

✅ ¿Qué soluciona la arquitectura actual?
ViewModel mantiene los datos aunque la pantalla rote o el sistema destruya temporalmente la UI.
LiveData permite que los fragmentos o actividades escuchen los datos, y la interfaz se actualiza automáticamente si cambian, sin tener que escribir mucho código adicional.
Se mejora la separación de responsabilidades: la UI se encarga solo de mostrar, y el ViewModel de manejar los datos.*/