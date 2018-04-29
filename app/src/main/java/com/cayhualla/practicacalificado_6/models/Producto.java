package com.cayhualla.practicacalificado_6.models;

import com.orm.dsl.Table;

/**
 * Created by Alumno on 20/04/2018.
 */
//codenvy -- codeanywhere  Servicios RestFull

@Table
public class Producto {

    private Long id;
    private String nombre;
    private String categoria;
    private String descripcion;
    private String estado;
    private String precio;
    private Long id_user;



    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", precio='" + precio + '\'' +
                ", id_user=" + id_user +
                '}';
    }





    public Producto() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public Producto( String nombre, String categoria, String descripcion, String precio,String estado ,Long id_user) {


        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.precio = precio;
        this.id_user = id_user;

    }


}
