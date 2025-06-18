package josedlujan.compras.de.lista.app.com.ejemplocardview;

import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// Esta clase es el Adaptador del RecyclerView. Su función principal es:
// 1. Crear las vistas (cardview).
// 2. Asignar datos a cada vista (binding).
// 3. Manejar cuántos ítems hay.
// Es el puente entre la lista de datos (User) y lo que se muestra visualmente.
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<User> users;     // Lista con los datos que se van a mostrar (cada item es un User)
    Context context;      // Contexto de la aplicación, necesario para inflar vistas, cargar recursos, etc.
    int lastPosition = -1; // Guarda la última posición animada para evitar animaciones repetidas

    // Constructor: se le pasa la lista de usuarios y el contexto
    public RecyclerAdapter(List<User> users, Context context){
        this.users = users;
        this.context = context;
    }

    // Este método se llama CUANDO SE CREA UNA NUEVA VISTA (cardview)
    // Es decir, cuando el RecyclerView necesita una vista nueva, no una reciclada.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos (convertimos XML a vista real) el diseño llamado "cardview.xml"
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        // Creamos un nuevo ViewHolder pasando esa vista recién inflada
        ViewHolder viewHolder  = new ViewHolder(v);
        return viewHolder;
    }

    // Este método se llama cada vez que se va a mostrar un ítem en pantalla (o cuando se recicla uno)
    // Aquí se "rellena" cada vista con los datos correspondientes según la posición
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Asignamos el texto e imagen correspondientes al item de la posición actual
        holder.title.setText(users.get(position).title);
        holder.twitter.setText(users.get(position).twitter);
        holder.image.setImageResource(users.get(position).photoID);

        // Aplicamos una animación al cargar la vista
        setAnimation(holder.cardView, position);

        // 🔽 También se podrían aplicar animaciones personalizadas como las comentadas abajo
        // Animation animation = AnimationUtils.loadAnimation(context, R.anim.left);
        // animation.setDuration(500);
        // viewToAnimate.startAnimation(animation);
        // lastPosition = position;
    }

    // Devuelve el total de elementos en la lista de usuarios
    @Override
    public int getItemCount() {
        return users.size();
    }

    // Se llama una sola vez cuando el adaptador se conecta al RecyclerView
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Clase interna que representa cada elemento del RecyclerView
    // ViewHolder es un patrón que optimiza el rendimiento de listas al evitar llamadas repetidas a findViewById
    // Esto antes no existía en ListView (o había que hacerlo a mano con mucha lógica), por eso RecyclerView es más eficiente
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title, twitter; // Textos del CardView
        public ImageView image;         // Imagen del CardView
        public CardView cardView;       // Contenedor principal del CardView

        // Constructor que recibe una vista (el layout del ítem) y busca sus componentes internos
        public ViewHolder(View itemView){
            super(itemView);

            // Buscamos las vistas internas dentro del layout
            cardView = (CardView) itemView.findViewById(R.id.cardview);
            title = (TextView) itemView.findViewById(R.id.title);
            twitter = (TextView) itemView.findViewById(R.id.twitter);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    // Método para aplicar una animación al mostrar los ítems
    // Solo animamos si la posición actual es mayor a la última ya animada (evitamos repetir)
    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            // Cargamos la animación desde res/anim/left.xml (archivo que define cómo se mueve la vista)
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.left);

            // Aplicamos la animación a la vista
            viewToAnimate.startAnimation(animation);

            // Guardamos la posición animada más reciente
            lastPosition = position;
        }
    }
}
