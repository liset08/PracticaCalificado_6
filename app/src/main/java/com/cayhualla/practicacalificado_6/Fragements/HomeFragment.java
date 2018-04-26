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

public class HomeFragment extends Fragment {
    private static final int REGISTER_FORM_REQUEST=100;
    private static final String TAG=HomeFragment.class.getSimpleName();

    // SharedPreferences
    private RecyclerView usersList;
    private TextView usernameText, usernameText1;


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

// Configure ReciclerView
        usersList = (RecyclerView)view.findViewById(R.id.user_list);
        usersList.setLayoutManager(new LinearLayoutManager(getActivity()));

        //

        // Set Data Adapter to ReciclerView
        List<Producto> productos = ProductoRepository.list();
        usersList.setAdapter(new ProductAdapter(productos));
//Cambiar el nombre del menu superior
        usernameText1 = (TextView)view.findViewById(R.id.welcome_text);

        usernameText = (TextView)view.findViewById(R.id.fullname_text);

       // Bundle args = getArguments();
        // String username = args.getString("email");


      //  Log.d(TAG, "username: " + username);

       // User user = UserRepository.getUser(username);
        //usernameText.setText(user.getFullname());
        // Inflate the layout for this fragment
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
