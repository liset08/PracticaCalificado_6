package com.cayhualla.practicacalificado_6.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cayhualla.practicacalificado_6.ProductoRegister;
import com.cayhualla.practicacalificado_6.R;
import com.cayhualla.practicacalificado_6.adapter.ProductAdapter;
import com.cayhualla.practicacalificado_6.models.Producto;
import com.cayhualla.practicacalificado_6.models.User;
import com.cayhualla.practicacalificado_6.repositories.ProductoRepository;
import com.cayhualla.practicacalificado_6.repositories.UserRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REGISTER_FORM_REQUEST=100;

    // SharedPreferences
    private SharedPreferences sharedPreferences;
    private RecyclerView usersList;
    private TextView usernameText, usernameText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Recycler View
        // Configure ReciclerView
        usersList = (RecyclerView) findViewById(R.id.user_list);
        usersList.setLayoutManager(new LinearLayoutManager(this));

        //

        // Set Data Adapter to ReciclerView
        List<Producto> productos = ProductoRepository.list();
        usersList.setAdapter(new ProductAdapter(productos));

        getSupportActionBar().setTitle("Catalogo");

        //menu bottom
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        Toast.makeText(MainActivity.this, "Go home section...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_camera:
                        Toast.makeText(MainActivity.this, "Go camera section...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_search:
                        Toast.makeText(MainActivity.this, "Go share section...", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }
        });

        usernameText1 = (TextView)findViewById(R.id.welcome_text);

        usernameText = (TextView)findViewById(R.id.fullname_text);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // get username from SharedPreferences
        String username = sharedPreferences.getString("username", null);


        Log.d(TAG, "username: " + username);

        User user = UserRepository.getUser(username);

        usernameText.setText(user.getFullname());
    }




    //menu superior

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){

            case R.id.action_takepicture:
                Toast.makeText(this, "Tomando una foto...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_logout:

               callLogout();
                Toast.makeText(this, "Cerrando Sesion...", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_toggle:
                if(item.isChecked()){
                    item.setChecked(false);
                    Toast.makeText(this, "Modo offline desactivado...", Toast.LENGTH_SHORT).show();
                }else{
                    item.setChecked(true);
                    Toast.makeText(this, "Modo offline activado...", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.action_about:
                Toast.makeText(this, "Acerca de...", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void callLogout(){
        // remove from SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor.putBoolean("islogged", false).commit();
//        boolean success = editor.clear().commit(); // not recommended

        goDashboard();
    }
    private void goDashboard() {

        startActivity(new Intent(this, LoginActivity.class));


        finish();

    }

    //icono de agregar nuevo producto
    public void callRegisterForm(View view){
        startActivityForResult(new Intent(this, ProductoRegister.class), REGISTER_FORM_REQUEST);
    }

}
