package com.cayhualla.practicacalificado_6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cayhualla.practicacalificado_6.repositories.ProductoRepository;

public class ProductoRegister extends AppCompatActivity {

    private EditText nombreInput;
    private EditText descripcionInput;
    private EditText precioInput;
    private EditText categoriaInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_register);

        descripcionInput = (EditText)findViewById(R.id.descripcion_input);
        nombreInput = (EditText)findViewById(R.id.nombre_input);
        precioInput = (EditText)findViewById(R.id.precio_input);
        categoriaInput = (EditText)findViewById(R.id.categoria_input);


    }

    public void callRegisterProducto(View view){
        String descripcion = descripcionInput.getText().toString();
        String nombre = nombreInput.getText().toString();
        Integer precio = Integer.valueOf(precioInput.getText().toString());
        String categoria = categoriaInput.getText().toString();

        if(descripcion.isEmpty() || nombre.isEmpty() || categoria.isEmpty()){
            Toast.makeText(this, "You must complete these fields", Toast.LENGTH_SHORT).show();
            return;
        }

        ProductoRepository.create(nombre, categoria, descripcion,precio);

        finish();

    }
}
