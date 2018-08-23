package com.example.surfer.barbershopapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UsuarioTabFragment extends Fragment {
    private View view;
    private CitasAdapter citasAdapter;
    private BarberDbAdapter barberDbAdapter;
    private Cursor cursorCitas;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerViewCitas;

    public UsuarioTabFragment() {
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
        view = inflater.inflate(R.layout.usuario_fragment, container, false);
        initInstance();
        return view;
    }

    private void initInstance() {

        barberDbAdapter = new BarberDbAdapter(getActivity());
        barberDbAdapter.open();
        cursorCitas = barberDbAdapter.fetchCitas(1);

        mRecyclerViewCitas = (RecyclerView)view.findViewById(R.id.recycler_citas);
        mRecyclerViewCitas.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewCitas.setLayoutManager(mLayoutManager);

        citasAdapter = new CitasAdapter(cursorCitas);
        mRecyclerViewCitas.setAdapter(citasAdapter);
    }
}
