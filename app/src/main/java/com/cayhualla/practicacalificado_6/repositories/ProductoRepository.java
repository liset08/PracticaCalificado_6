package com.cayhualla.practicacalificado_6.repositories;

import com.cayhualla.practicacalificado_6.models.Producto;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Alumno on 24/04/2018.
 */

public class ProductoRepository {
    public static List<Producto> list(){
        List<Producto> productos = SugarRecord.listAll(Producto.class);
        return productos;
    }

    public static Producto read(Long id){
        Producto user = SugarRecord.findById(Producto.class, id);
        return user;
    }
    public static void delete(Long id){
        Producto user = SugarRecord.findById(Producto.class, id);
        SugarRecord.delete(user);
    }

    public static void create(String nombre, String categoria, String descripcion, int precio){
        Producto user = new Producto(nombre, categoria, descripcion,precio);
        SugarRecord.save(user);
    }
}
