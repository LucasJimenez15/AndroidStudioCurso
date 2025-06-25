package com.example.constraintlayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ListItemHolder>{
    private List<Producto> productos;
    private PaginaHome mMainActivity;

    /*3. Clase ProductoAdapter
        Es un adaptador personalizado que hereda de RecyclerView.Adapter. Este adaptador es el responsable de enlazar los datos de la lista
        de productos con las vistas en cada elemento del RecyclerView.

        Atributos:
        List<Producto> productos: Lista de productos a mostrar.
        MainActivity mMainActivity: Una referencia a la actividad principal para poder interactuar con ella.

        Métodos:
        onCreateViewHolder(ViewGroup parent, int viewType)
        Infla el layout para cada elemento de la lista (definido en elemento_lista.xml) y crea un nuevo ListItemHolder, que es el
        contenedor de las vistas.

        onBindViewHolder(ListItemHolder holder, int position) Vincula los datos del producto a las vistas correspondientes. Este método
        se ejecuta por cada elemento en la lista.

        holder.foto.setImageResource(producto.getFoto()); Asigna la imagen correspondiente al ImageView.

        holder.producto.setText(producto.getProducto()); Asigna el nombre del producto al TextView.

        holder.precio.setText(producto.getPrecio().toString()); Muestra el precio en el TextView.

        getItemCount() Devuelve el número de elementos en la lista de productos.

        Clase interna ListItemHolder: Esta clase representa un ViewHolder, que almacena las vistas individuales de cada
        elemento del RecyclerView.

        Atributos:
        Almacena referencias a los elementos visuales de cada item, como foto, producto, presentacion, y precio.

        Métodos:
        onClick(View view): Maneja los clics sobre los elementos de la lista. Muestra un Toast con el nombre del producto seleccionado.*/


    public ProductoAdapter(PaginaHome paginaHome, List<Producto> productos) {
        this.productos = productos;
        this.mMainActivity = paginaHome;
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mMainActivity).inflate(R.layout.elemento_lista, parent, false);
        return new ListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        Producto producto = productos.get(position);

        holder.foto.setImageResource(producto.getFoto());
        holder.producto.setText(producto.getProducto().toString());
        holder.precio.setText(producto.getPrecio().toString());
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView foto;
        TextView producto;
        TextView presentacion;
        TextView precio;

        public ListItemHolder(@NonNull View itemView) {
            super(itemView);

            foto = itemView.findViewById(R.id.foto);
            producto = itemView.findViewById(R.id.producto);
            presentacion = itemView.findViewById(R.id.producto);
            precio = itemView.findViewById(R.id.precio);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mMainActivity, productos.get(getAdapterPosition()).getProducto(), Toast.LENGTH_SHORT).show();
        }
    }
}
