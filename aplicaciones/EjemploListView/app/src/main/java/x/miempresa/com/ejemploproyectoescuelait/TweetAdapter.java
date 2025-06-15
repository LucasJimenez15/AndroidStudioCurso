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

// Esta clase adapta un arreglo de objetos Tweet para mostrarlos en un ListView.
// Hereda de ArrayAdapter, que sirve para adaptar datos a vistas como ListView
public class TweetAdapter extends ArrayAdapter<Tweet> {

    // El contexto actual, normalmente la Activity que usa el adapter
    Context context;

    // El layout que se va a usar para cada ítem de la lista (por ejemplo, fila personalizada)
    int layoutResourceID;

    // Arreglo de datos que queremos mostrar, en este caso un array de Tweets
    Tweet[] data = null;

    // Constructor del adapter, recibe el contexto, el layout y los datos
    public TweetAdapter(Context context, int layoutResourceID, Tweet[] data) {
        // Se llama al constructor del padre (ArrayAdapter)
        super(context, layoutResourceID, data);

        // Se guardan los parámetros en variables del adapter
        this.context = context;
        this.layoutResourceID = layoutResourceID;
        this.data = data;
    }

    // Este método es el que se encarga de construir y devolver la vista de cada ítem del ListView
    public View getView(int position, View convertView, ViewGroup parent) {
        // Reutiliza la vista existente, la que nos llego si es posible, si no es null
        View row = convertView;

        // Holder para mantener referencias a los elementos de la vista
        TweetHolder holder = null;

        // Si no hay vista para reciclar (es decir, si row es null), inflamos una nueva vista
        if (row == null) {
            // Inflamos el layout desde XML para la fila
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceID, parent, false);

            // Creamos el holder y referenciamos los elementos visuales
            holder = new TweetHolder();
            holder.imageView = (ImageView) row.findViewById(R.id.imageView);
            holder.textView = (TextView) row.findViewById(R.id.textView);

            // Asociamos el holder a la vista, para poder reutilizarlo después sin volver a buscar por ID
            row.setTag(holder);

        } else {
            // Si ya había una vista, recuperamos su holder guardado previamente
            holder = (TweetHolder) row.getTag();
        }

        // Obtenemos el tweet que se debe mostrar en esta posición
        Tweet tweet = data[position];

        // Asignamos los valores del tweet a los elementos visuales
        holder.textView.setText(tweet.title); // Establecemos el texto del tweet
        holder.imageView.setImageResource(tweet.image); // Establecemos la imagen del tweet

        // Devolvemos la vista configurada
        return row;
    }

    /* Antes en android surgia un problema y es que los elementos se eliminaban si estos no se mostraban en pantalla es decir solo se mustran los que estaban en ese momento por lo tanto
     * Esta clase estática es parte del patrón ViewHolder.
     * Su objetivo es evitar llamadas repetidas a findViewById, que son costosas.
     * En lugar de buscar los elementos cada vez que se dibuja la fila,
     * los "holdeamos" (almacenamos) en esta clase y los reutilizamos,
     * lo cual mejora mucho el rendimiento al hacer scroll en listas largas.
     */
    static class TweetHolder {
        ImageView imageView;  // Imagen del tweet
        TextView textView;    // Título o texto del tweet
    }
}
