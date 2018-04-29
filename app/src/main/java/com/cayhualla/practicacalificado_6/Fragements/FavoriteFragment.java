package com.cayhualla.practicacalificado_6.Fragements;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cayhualla.practicacalificado_6.R;
import com.cayhualla.practicacalificado_6.adapter.ProductAdapter;
import com.cayhualla.practicacalificado_6.models.Producto;
import com.cayhualla.practicacalificado_6.repositories.ProductoRepository;

import java.util.List;


public class FavoriteFragment extends Fragment implements ChasngeNotifier {
    private static final String TAG = FavoriteFragment.class.getSimpleName();

    private RecyclerView favoritoList;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favorite, container, false);
        favoritoList=view.findViewById(R.id.favorite_list);
        favoritoList.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Producto> produc= ProductoRepository.FavoriteList();
        favoritoList.setAdapter(new ProductAdapter(this,produc));
        return view;
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
        ProductAdapter pa = (ProductAdapter) favoritoList.getAdapter();
        pa.setProductos(ProductoRepository.FavoriteList());
        pa.notifyDataSetChanged();
    }


}
