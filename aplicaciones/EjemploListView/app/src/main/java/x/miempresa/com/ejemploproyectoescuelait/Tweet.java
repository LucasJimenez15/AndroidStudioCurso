package x.miempresa.com.ejemploproyectoescuelait; // Define el paquete en el que está la clase (como una carpeta lógica).

/**
 * Esta clase representa un modelo de datos llamado Tweet. Es un objeto por asi decirlo
 *
 * Un "Tweet" en este caso es un objeto que tiene dos propiedades:
 *  - una imagen (image) representada por un entero (el ID del recurso de imagen),
 *  - y un título o texto (title) que se muestra.
 *
 * Esta clase es muy útil cuando queremos mostrar una lista de tweets en la interfaz gráfica,
 * por ejemplo usando un ListView. Cada ítem de la lista usará un objeto Tweet para obtener la información.
 */
public class Tweet {

    // Campo para guardar el ID del recurso de imagen.
    // En Android, las imágenes se almacenan como recursos (en la carpeta res/drawable),
    // y se accede a ellas mediante IDs enteros generados automáticamente (R.drawable.imagen).
    public int image;

    // Campo para guardar el texto o título del tweet.
    public String title;

    // Getter para la imagen. Nos permite obtener el valor del campo 'image' desde otra clase.
    public int getImage() {
        return image;
    }

    // Setter para la imagen. Nos permite cambiar el valor del campo 'image'.
    public void setImage(int image) {
        this.image = image;
    }

    // Getter para el título. Nos permite obtener el valor de 'title' desde fuera de la clase.
    public String getTitle() {
        return title;
    }

    // Setter para el título. Permite modificar el valor de 'title'.
    public void setTitle(String title) {
        this.title = title;
    }

    // Constructor sin parámetros (vacío).
    // Es necesario si queremos crear un objeto Tweet y luego asignar sus valores más adelante usando los setters.
    public Tweet() {
        super(); // Llama al constructor de la clase padre (Object). Es redundante aquí, pero a veces se incluye por claridad.
    }

    // Constructor que recibe todos los datos necesarios para crear un Tweet directamente.
    // Nos permite crear un objeto con todos sus valores ya asignados desde el comienzo.
    public Tweet(int image, String title) {
        super(); // Llama al constructor de la clase Object.
        this.image = image;
        this.title = title;
    }
}
