package com.cayhualla.practicacalificado_6.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cayhualla.practicacalificado_6.Fragements.DeleteFragment;
import com.cayhualla.practicacalificado_6.Fragements.FavoriteFragment;
import com.cayhualla.practicacalificado_6.Fragements.HomeFragment;
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
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Recycler View

        getSupportActionBar().setTitle("Catalogo");
//
        //menu bottom
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

              //La accion del menu del fragment
                FragmentManager fragmentManager = getSupportFragmentManager();


                switch (item.getItemId()) {
                    case R.id.menu_home:
                        Toast.makeText(MainActivity.this, "Go home section...", Toast.LENGTH_SHORT).show();
                        HomeFragment fragment = new HomeFragment();
                        fragmentManager.beginTransaction().replace(R.id.drawer_layout, fragment).addToBackStack("tag").commit();

                        break;
                    case R.id.menu_favorite:
                        Toast.makeText(MainActivity.this, "Go camera section...", Toast.LENGTH_SHORT).show();
                        FavoriteFragment favoriteFragment=new FavoriteFragment();
                        fragmentManager.beginTransaction().replace(R.id.drawer_layout,favoriteFragment).addToBackStack("tag").commit();

                        break;
                    case R.id.menu_delete:
                        Toast.makeText(MainActivity.this, "Go share section...", Toast.LENGTH_SHORT).show();
                        DeleteFragment deleteFragment=new DeleteFragment();
                        fragmentManager.beginTransaction().replace(R.id.drawer_layout,deleteFragment).addToBackStack("tag").commit();
                        break;

                }
                return true;
            }
        });

// init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // get username from SharedPreferences
        String username = sharedPreferences.getString("username", null);
HomeFragment hf=new HomeFragment();
        Bundle args = new Bundle();

        args.putString("email", username);
        hf.setArguments(args);
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


    //icono de agregar nuevo producto
    public void callRegisterFormProducto(View view){
        startActivityForResult(new Intent(MainActivity.this, ProductoRegister.class), REGISTER_FORM_REQUEST);
    }


    public void callLogout(){

        // remove from SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor.putBoolean("islogged", false).commit();
//        boolean success = editor.clear().commit(); // not recommended


        goDashboard();
    }
    private void goDashboard() {

        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);

    }


}
