package com.cayhualla.practicacalificado_6.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cayhualla.practicacalificado_6.Fragements.ChasngeNotifier;
import com.cayhualla.practicacalificado_6.R;
import com.cayhualla.practicacalificado_6.models.Producto;
import com.cayhualla.practicacalificado_6.repositories.ProductoRepository;

import java.util.List;
import java.util.Objects;

/**
 * Created by Alumno on 24/04/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    private List<Producto> productos;
    private ChasngeNotifier chasngeNotifier;

    public ProductAdapter(ChasngeNotifier chasngeNotifier ,List<Producto> productos){
        this.chasngeNotifier = chasngeNotifier;

        this.productos = productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public TextView descripcion;
        public TextView precio;
        public ImageView picture;
        public Button Favoritos;
        public Button Archivados;



        public ViewHolder(View itemView) {
            super(itemView);
            picture = (ImageView) itemView.findViewById(R.id.picture_image);

            nombre = (TextView) itemView.findViewById(R.id.tx_nombre);
            descripcion = (TextView) itemView.findViewById(R.id.tx_descripcion);
             precio = (TextView) itemView.findViewById(R.id.tx_precio);

            Favoritos = (Button) itemView.findViewById(R.id.btn_favoritos);
            Archivados = (Button) itemView.findViewById(R.id.btn_archivar);


        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProductAdapter.ViewHolder viewHolder, int position) {
        final Producto user = this.productos.get(position);
        viewHolder.nombre.setText(user.getNombre());
        viewHolder.descripcion.setText(user.getDescripcion());
        viewHolder.precio.setText(user.getPrecio());



        viewHolder.Favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.equals(user.getEstado(),"Favoritos")){
                Toast.makeText(viewHolder.Favoritos.getContext(),user.getNombre()+"eliminado de favoritos",Toast.LENGTH_SHORT).show();
                    ProductoRepository.actualizarEstados("",user.getId());
            }else{
                    Toast.makeText(viewHolder.Favoritos.getContext(),user.getNombre()+"agregado a favoritos",Toast.LENGTH_SHORT).show();
                    ProductoRepository.actualizarEstados("Favoritos",user.getId());
                }
                chasngeNotifier.notifyChanges();}
        });

        viewHolder.Archivados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.equals(user.getEstado(),"Archivar")){
                    Toast.makeText(viewHolder.Favoritos.getContext(),user.getNombre()+"eliminado de archivados",Toast.LENGTH_SHORT).show();
                    ProductoRepository.actualizarEstados("",user.getId());
                }else{
                    Toast.makeText(viewHolder.Favoritos.getContext(),user.getNombre()+"agregado a archivados",Toast.LENGTH_SHORT).show();
                    ProductoRepository.actualizarEstados("Archivar",user.getId());

                }
                chasngeNotifier.notifyChanges();
            }
        });



    }

    @Override
    public int getItemCount() {
        return this.productos.size();
    }




}
