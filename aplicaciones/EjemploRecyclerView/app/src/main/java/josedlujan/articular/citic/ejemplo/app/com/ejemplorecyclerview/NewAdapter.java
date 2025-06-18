// Este paquete indica en qué ubicación se encuentra la clase dentro del proyecto.
package josedlujan.articular.citic.ejemplo.app.com.ejemplorecyclerview;

// Importaciones necesarias para trabajar con RecyclerView.
// `RecyclerView` es un componente moderno que reemplaza al antiguo `ListView` porque
// permite una gestión mucho más eficiente y flexible de listas o grillas de datos.
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Esta clase define un adaptador personalizado llamado NewAdapter para un RecyclerView.
 * Un adaptador es el "puente" entre los datos y las vistas que se muestran en pantalla.
 * En este caso, hereda de RecyclerView.Adapter y utiliza una clase interna ViewHolder.
 *
 * IMPORTANTE: Esta clase aún está incompleta y solo es una plantilla básica (esqueleto).
 */
public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {

    /**
     * Método que se encarga de crear una nueva vista (ViewHolder) para un ítem de la lista.
     * Es como "inflar" el diseño XML que representa un solo elemento.
     * Este método se llama solo cuando se necesita una nueva vista (no reciclada).
     * ANTES: en ListView, se usaba `getView()`, pero no era tan eficiente ni modular como esto.*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Aquí debería inflarse el layout XML de un ítem y crear el ViewHolder.
        // Por ahora retorna null porque no se ha implementado aún.
        return null;
    }

    /**
     * Este método une (bind) los datos con la vista del ViewHolder.
     * Es decir, aquí se toman los datos de la posición actual y se muestran en los componentes visuales (TextViews, ImageViews, etc).
     *
     * Este método se llama cada vez que una vista se va a mostrar en pantalla.
     *
     * ANTES: en ListView, este proceso estaba todo mezclado en `getView()` y se debía hacer el reciclado manual.
     * En RecyclerView es mucho más ordenado y eficiente.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Aquí se deberían cargar los datos en los elementos del ViewHolder.
        // Aún no está implementado.
    }

    /**
     * Este método devuelve la cantidad total de elementos que hay en la lista.
     *
     * Esto es necesario para que el RecyclerView sepa cuántas veces debe llamar a onCreateViewHolder y onBindViewHolder.
     */
    @Override
    public int getItemCount() {
        // Por ahora devuelve 0 porque no hay datos definidos aún.
        return 0;
    }

    /**
     * Esta clase interna representa el ViewHolder.
     * Un ViewHolder es un objeto que "guarda" las vistas de un ítem (por ejemplo, un TextView, ImageView, etc.)
     * para que no sea necesario buscarlas cada vez que se recicla una vista, lo cual mejora muchísimo el rendimiento.
     *
     * ANTES: en ListView no existía esta clase de forma tan clara, y era común ver problemas de rendimiento
     * por usar `findViewById()` en cada fila repetidamente.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Constructor del ViewHolder.
         * Recibe una vista (el layout inflado del ítem) y se encarga de enlazar sus componentes visuales.
         */
        public ViewHolder(View itemView) {
            super(itemView);
            // Aquí es donde normalmente se hace:
            // miTextView = itemView.findViewById(R.id.mi_textview);
            // Pero aún no se ha hecho porque es un esqueleto.
        }
    }
}

/*🧠 Explicación adicional:
Parte	Explicación
onCreateViewHolder()	Se llama cuando se necesita crear una nueva vista. Aquí se infla el layout del ítem.
onBindViewHolder()	Se llama para mostrar los datos en la vista. Se enlazan los datos con la vista reciclada.
getItemCount()	Informa al RecyclerView cuántos elementos hay. Si devuelve 0, no se muestra nada.
ViewHolder	Clase que guarda las vistas internas de cada ítem. Mejora el rendimiento al evitar llamadas innecesarias a findViewById().*/
