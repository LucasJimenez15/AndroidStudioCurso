package josedlujan.articular.citic.ejemplo.app.com.ejemplorecyclerview;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Esta clase es un adaptador personalizado para RecyclerView.
 * Su función principal es vincular un conjunto de datos (en este caso, un arreglo de Strings)
 * con las vistas individuales (ítems) que se mostrarán en la lista.
 *
 * Este adaptador hereda de RecyclerView.Adapter y utiliza un ViewHolder interno
 * para mejorar el rendimiento mediante la reutilización de vistas.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    // Este arreglo almacena los datos que se van a mostrar en el RecyclerView.
    private String[] myData;

    /**
     * Constructor del adaptador.
     * Recibe el conjunto de datos (en este caso, un arreglo de Strings) al momento de crearse.
     * Esto permite que el adaptador se mantenga desacoplado y reutilizable con diferentes datos.*/
    public RecyclerAdapter(String[] Data) {
        myData = Data;
    }

    /**
     * Este método se llama cuando el RecyclerView necesita crear una nueva vista para mostrar un ítem.
     * Es decir, se ejecuta solo cuando no hay una vista reciclable disponible, por lo tanto,
     * se "infla" (convierte) un layout XML en un objeto View de Java.
     * ANTES: En ListView se usaba `getView()` y se inflaban las vistas todo el tiempo,
     * lo cual era costoso en rendimiento. Con ViewHolder + RecyclerView, esto se optimiza mucho.*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // LayoutInflater convierte el XML (item.xml) en una vista utilizable.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        // Se crea una nueva instancia del ViewHolder, pasándole la vista inflada.
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    /**
     * Este método se llama cada vez que se necesita mostrar datos en una posición específica de la lista.
     * Aquí se actualizan los datos del ViewHolder reutilizado con la información que le corresponde.
     * Esto reemplaza a `getView()` en ListView, pero de una forma mucho más ordenada y rápida.*/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Se asigna el texto correspondiente del arreglo al TextView del ViewHolder.
        holder.textView.setText(myData[position]);
    }

    /**
     * Este método le dice al RecyclerView cuántos elementos tiene la lista de datos.
     * Esto es fundamental porque permite al RecyclerView saber cuántas vistas necesita crear y manejar.*/
    @Override
    public int getItemCount() {
        return myData.length;
    }

    /**
     * Esta clase interna representa el ViewHolder.
     * El ViewHolder es una especie de "contenedor" para la vista individual de cada ítem.
     * Guarda las referencias a los componentes visuales del layout del ítem para que no haya
     * que buscarlos con `findViewById()` cada vez, lo cual mejora muchísimo el rendimiento.
     * ANTES: En ListView no había esta separación, y se buscaban las vistas todo el tiempo,
     * lo cual generaba lentitud, especialmente con muchas filas.*/
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Aquí se define una variable para el TextView que muestra el texto.
        public TextView textView;

        // Constructor del ViewHolder.
        // Se recibe la vista inflada del ítem y se vinculan sus componentes (en este caso, solo un TextView).
        public ViewHolder(View itemView) {
            super(itemView);
            // Se obtiene la referencia al TextView que está en el layout item.xml
            textView = (TextView) itemView.findViewById(R.id.title);
        }
    }
}

/*✅ ¿Qué hace cada parte?
Elemento	Descripción
RecyclerAdapter	Clase que conecta los datos con las vistas del RecyclerView.
myData	Arreglo de Strings que contiene los textos a mostrar.
onCreateViewHolder()	Crea una nueva vista (solo si no hay otra reciclable).
onBindViewHolder()	Asigna datos a una vista ya creada o reciclada.
getItemCount()	Dice cuántos ítems hay en total.
ViewHolder	Clase interna que guarda las vistas de un ítem para evitar búsquedas innecesarias.*/