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


public class DeleteFragment extends Fragment implements ChasngeNotifier {
    private static final String TAG = DeleteFragment.class.getSimpleName();
    private RecyclerView archivarList;

    public DeleteFragment() {
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
        View view= inflater.inflate(R.layout.fragment_delete, container, false);

        archivarList=view.findViewById(R.id.delete_list);
        archivarList.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Producto> produc= ProductoRepository.ArchivarList();
        archivarList.setAdapter(new ProductAdapter(this,produc));
        return view;
    }

    public void callDeleteProdu(View view){




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
        ProductAdapter pa = (ProductAdapter) archivarList.getAdapter();
        pa.setProductos(ProductoRepository.ArchivarList());
        pa.notifyDataSetChanged();
    }



}
