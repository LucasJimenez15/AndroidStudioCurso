package josedlujan.compras.de.lista.app.com.ejemplocardview;

import java.util.ArrayList;
import java.util.List;

// Esta clase representa un modelo de datos o "POJO" (Plain Old Java Object).
// Es decir, simplemente guarda información sin lógica compleja ni comportamiento especial.

// Este tipo de clases se usa en la arquitectura moderna para separar los datos de la lógica de presentación.
// Antes, era común mezclar datos con lógica de interfaz (por ejemplo, armar strings directamente en un Adapter),
// lo cual hacía que el código fuera muy difícil de mantener o escalar.

// Con este enfoque actual, cada item de la lista va a estar representado por un objeto User.
public class User {

    // Atributo que representa el título de la tarjeta (por ejemplo, "Canadá - Lago")
    String title;

    // Atributo que representa una descripción o texto adicional (acá se llama "twitter" pero puede ser cualquier texto)
    String twitter;

    // ID del recurso de imagen (imagen guardada en la carpeta drawable, como paisaje1, paisaje2, etc.)
    int photoID;

    // Constructor: se utiliza para crear objetos User con los tres datos necesarios.
    // Se llama automáticamente cuando se hace: new User("titulo", "texto", R.drawable.imagen)
    User(String title, String twitter, int photoID){
        // Se asignan los parámetros recibidos a las variables internas de la clase
        this.title = title;
        this.twitter = twitter;
        this.photoID = photoID;
    }

    // En esta versión del código no se incluyen métodos getters y setters.
    // Pero en prácticas modernas (por ejemplo con arquitecturas como MVVM),
    // se suelen usar para encapsular el acceso a las propiedades (por ejemplo, con data binding).

    // También se podría implementar la interfaz Serializable o Parcelable si se quisieran
    // enviar estos objetos entre Activities o Fragments.
}

/*📌 ¿Por qué usar una clase modelo como User?
ANTES:Los datos se representaban con estructuras desordenadas como ArrayList<HashMap<String, Object>> o arreglos sueltos. Esto causaba problemas: errores al escribir claves, código difícil de leer, y poca escalabilidad.

AHORA: Usamos clases modelo (User, Producto, Cliente, etc.) que encapsulan los datos de forma clara.
Se mejora la mantenibilidad, legibilidad y posibilidad de reusar objetos con adaptadores, bases de datos, APIs, etc.*/