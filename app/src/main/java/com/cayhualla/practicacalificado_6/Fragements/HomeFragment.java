package com.cayhualla.practicacalificado_6.Fragements;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cayhualla.practicacalificado_6.Activities.LoginActivity;
import com.cayhualla.practicacalificado_6.Activities.MainActivity;
import com.cayhualla.practicacalificado_6.Activities.ProductoRegister;
import com.cayhualla.practicacalificado_6.R;
import com.cayhualla.practicacalificado_6.adapter.ProductAdapter;
import com.cayhualla.practicacalificado_6.models.Producto;
import com.cayhualla.practicacalificado_6.models.User;
import com.cayhualla.practicacalificado_6.repositories.ProductoRepository;
import com.cayhualla.practicacalificado_6.repositories.UserRepository;

import java.util.List;

public class HomeFragment extends Fragment implements ChasngeNotifier {
    private static final int REGISTER_FORM_REQUEST=100;
    private static final String TAG=HomeFragment.class.getSimpleName();

    // SharedPreferences
    private RecyclerView usersList;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

// Configure ReciclerVie
        usersList = (RecyclerView)view.findViewById(R.id.user_list);
        usersList.setLayoutManager(new LinearLayoutManager(getActivity()));


        List<Producto> productos = ProductoRepository.list();
        usersList.setAdapter(new ProductAdapter(this,productos));
//Cambiar el nombre del menu superior

    return view;
    }


    // return from RegisterActivity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // refresh data
        ProductAdapter adapter = (ProductAdapter) usersList.getAdapter();

        List<Producto> productos = ProductoRepository.list();
        adapter.setProductos(productos);
        adapter.notifyDataSetChanged();



    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



    @Override
    public void notifyChanges() {
        ProductAdapter pa = (ProductAdapter) usersList.getAdapter();
        pa.setProductos(ProductoRepository.list());
        pa.notifyDataSetChanged();
    }
}
