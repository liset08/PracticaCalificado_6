package com.cayhualla.practicacalificado_6.repositories;

import com.cayhualla.practicacalificado_6.models.Producto;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Alumno on 24/04/2018.
 */

public class ProductoRepository {
    public static List<Producto> list(){
        List<Producto> productos = SugarRecord.find(Producto.class, "estado NOT IN(?)", "Archivar");
        return productos;
    }

    //Lista del favorito

    public static List<Producto> FavoriteList(){
        List<Producto> producto = SugarRecord.find(Producto.class, "estado = ?", "Favoritos");
        return producto;

    }
    //Lista del favorito

    public static List<Producto> ArchivarList(){
        List<Producto> producto = SugarRecord.find(Producto.class, "estado = ?", "Archivar");
        return producto;

    }


    public static void create(String nombre, String categoria, String descripcion, String precio,String estado, Long user_id){
        Producto user = new Producto(nombre, categoria, descripcion,precio,estado,user_id);
        SugarRecord.save(user);
    }

    public  static void actualizarEstados(String estado, Long id){
        Producto producto=SugarRecord.findById(Producto.class, id);
        producto.setEstado(estado);
        SugarRecord.save(producto);
    }

}
