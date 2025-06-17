// Este paquete define la ubicación del proyecto dentro de la estructura de tu app
package x.miempresa.com.ejemploproyectoescuelait;

// Importaciones necesarias para el uso de componentes de Android
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*Un Adapter en Android es un intermediario que convierte datos (como listas o arreglos en este caso el arreglo de tweets) en vistas visuales que pueden mostrarse en componentes como ListView o RecyclerView.
Sirve para conectar los datos con la interfaz, creando y reutilizando vistas para cada elemento de forma eficiente.*/

// Esta clase es un Adapter personalizado que adapta un arreglo de objetos Tweet para mostrarlos en un ListView.
// Hereda de ArrayAdapter<Tweet>, que permite convertir cada objeto Tweet en una vista que se puede mostrar en una lista.
public class TweetAdapter extends ArrayAdapter<Tweet> {

    // Contexto actual (por ejemplo, una Activity). Se usa para acceder a recursos, inflar layouts, etc.
    Context context;

    // ID del layout XML que define cómo se ve cada elemento (item) de la lista. Este es como se ve visualmente cada elemento (podemos ver eso cuando lo instanciamos en el mainActivity por ejemplo).
    int layoutResourceID;

    // Arreglo de objetos Tweet que vamos a mostrar en el ListView.
    Tweet[] data = null;

    // Constructor del Adapter. Recibe el contexto, el layout a usar y los datos a mostrar.
    public TweetAdapter(Context context, int layoutResourceID, Tweet[] data) {
        // Llamamos al constructor de la clase padre (ArrayAdapter) con los mismos parámetros.
        super(context, layoutResourceID, data);

        // Guardamos los valores recibidos para usarlos dentro del Adapter.
        this.context = context;
        this.layoutResourceID = layoutResourceID;
        this.data = data;
    }

    /** Este método es llamado por el ListView cada vez que necesita mostrar un elemento en una posición específica. Su función es construir (o reutilizar) la vista de ese elemento y asignarle los datos correspondientes.
     * Es el corazón de cualquier Adapter.
     * @param position     posición del ítem actual dentro del arreglo 'data'
     * @param convertView  vista reciclada, si existe. Puede ser null si no hay vistas disponibles para reciclar. Una vista reciclada es una vista que ya se usó en el ListView y que ya no se ve en pantalla. En lugar de crear una nueva, Android la reutiliza (te la da en convertView) para mostrar otro elemento, lo que hace que la app sea más rápida y consuma menos memoria.
     * @param parent       Es el ListView padre al que esta fila (vista) va a pertenecer. No suele usarse directamente, pero es necesario para el inflado del layout.
     * @return             la vista que se va a mostrar en la posición 'position' del ListView*/
    public View getView(int position, View convertView, ViewGroup parent) {

        // Reutilizamos la vista recibida si es posible. Esto mejora el rendimiento y ahorra memoria.
        // Si 'convertView' es null, significa que no hay vista reciclada disponible, y debemos inflar una nueva.
        View row = convertView;

        // Creamos una variable del tipo 'TweetHolder', que nos ayudará a mantener referencias a los elementos visuales ,dentro del layout de cada fila. Esto evita múltiples llamadas a findViewById(), que son costosas.
        TweetHolder holder = null;

        // Si 'row' es null, necesitamos inflar una nueva vista desde el archivo XML
        if (row == null) {

            // Obtenemos un LayoutInflater desde el contexto (normalmente la Activity)
            // El LayoutInflater se usa para "inflar" (convertir) un archivo XML en una vista real de Java.
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();

            // Inflamos el layout del recurso layoutResourceID y lo asociamos al objeto 'row'
            // Este layout define cómo se verá cada fila del ListView.
            row = inflater.inflate(layoutResourceID, parent, false);

            // Creamos una nueva instancia del holder, que almacenará referencias a los elementos visuales
            holder = new TweetHolder();

            // Asociamos los elementos visuales del layout inflado al holder
            // Estos elementos deben existir en el layout especificado por 'layoutResourceID'
            holder.imageView = (ImageView) row.findViewById(R.id.imageView);
            holder.textView = (TextView) row.findViewById(R.id.textView);

            // Guardamos el holder dentro de la vista 'row' usando setTag(). sirve para guardar lo que ya veniamos haciendo, Esto permite que en próximas reutilizaciones podamos recuperar el mismo holder y no buscar todo otra vez.
            row.setTag(holder);

        } else {
            // Si 'row' no es null, significa que estamos reutilizando una vista vieja.
            // Recuperamos el holder previamente guardado usando getTag().
            // Así accedemos directamente a los elementos visuales sin hacer findViewById().
            holder = (TweetHolder) row.getTag();
        }

        // Obtenemos el objeto Tweet correspondiente a esta posición del ListView
        Tweet tweet = data[position];
        // Asignamos los valores del objeto Tweet a los componentes visuales y Mostramos el título del tweet en el TextView
        holder.textView.setText(tweet.title);
        // Mostramos la imagen asociada al tweet en el ImageView, usando su ID de recurso (ej. R.drawable.img1)
        holder.imageView.setImageResource(tweet.image);
        // Finalmente, devolvemos la vista completamente configurada, lista para mostrarse en el ListView
        return row;
    }

    /* Antes en android surgia un problema y es que los elementos se eliminaban si estos no se mostraban en pantalla es decir solo se mustran los que estaban en ese momento por lo tanto
     * Esta clase estática es parte del patrón ViewHolder.
     * Su objetivo es evitar llamadas repetidas a findViewById, que son costosas.
     * En lugar de buscar los elementos cada vez que se dibuja la fila,
     * los "holdeamos" (almacenamos) en esta clase y los reutilizamos, lo que hace es guardar una referencia a estos objetos lo cual mejora mucho el rendimiento al hacer scroll en listas largas.*/
    static class TweetHolder {
        ImageView imageView;  // Imagen del tweet
        TextView textView;    // Título o texto del tweet
    }

    /*Cuando usás getView(), el ViewHolder guarda las referencias a los elementos visuales de esa fila (como TextView, ImageView, etc.) una sola vez usando findViewById(). Después, si esa vista se vuelve a mostrar (por ejemplo, al hacer scroll hacia atrás), no se crea de nuevo ni se buscan otra vez los elementos, sino que se reutiliza lo que ya estaba guardado en el holder.*/

}
