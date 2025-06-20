package com.example.recycler_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ListItemHolder>{
    private List<Producto> productos;
    private MainActivity mMainActivity;
    public ProductoAdapter(MainActivity mainActivity, List<Producto> productos) {
        this.productos = productos;
        this.mMainActivity = mainActivity;
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
