package com.cayhualla.practicacalificado_6.Activities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cayhualla.practicacalificado_6.R;
import com.cayhualla.practicacalificado_6.models.User;
import com.cayhualla.practicacalificado_6.repositories.ProductoRepository;
import com.cayhualla.practicacalificado_6.repositories.UserRepository;

public class ProductoRegister extends AppCompatActivity {

    private EditText nombreInput;
    private EditText descripcionInput;
    private EditText precioInput;
    private EditText categoriaInput;

    private SharedPreferences sharedPreferences;

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

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // get username from SharedPreferences
        String username = sharedPreferences.getString("username", null);

        //Get Parameters from Current USER
        User user = UserRepository.getUser(username);
        assert user != null;

        String descripcion = descripcionInput.getText().toString();
        String nombre = nombreInput.getText().toString();
        String precio = precioInput.getText().toString();
        String categoria = categoriaInput.getText().toString();
        String estado="";
        Long user_id=user.getId();


        if(descripcion.isEmpty() || nombre.isEmpty() || categoria.isEmpty()){
            Toast.makeText(this, "You must complete these fields", Toast.LENGTH_SHORT).show();
            return;
        }

        ProductoRepository.create(nombre, categoria, descripcion,precio,estado,user_id);

        finish();

    }
}


