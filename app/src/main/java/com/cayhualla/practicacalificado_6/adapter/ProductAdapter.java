package com.cayhualla.practicacalificado_6.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cayhualla.practicacalificado_6.R;
import com.cayhualla.practicacalificado_6.models.Producto;

import java.util.List;

/**
 * Created by Alumno on 24/04/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    private List<Producto> productos;

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public ProductAdapter(List<Producto> productos){
        this.productos = productos;
    }




    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder viewHolder, int position) {
        Producto user = this.productos.get(position);
        viewHolder.nombre.setText(user.getNombre());
        viewHolder.descripcion.setText(user.getDescripcion());
        viewHolder.precio.setText(user.getPrecio());

    }

    @Override
    public int getItemCount() {
        return this.productos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public TextView descripcion;
        public TextView precio;


        public ViewHolder(View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.nombre);
            descripcion = (TextView) itemView.findViewById(R.id.descripcion);
            precio = (TextView) itemView.findViewById(R.id.precio);
        }
    }



}
