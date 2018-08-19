package com.example.surfer.barbershopapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeTabFragment extends Fragment {
    private BarberDbAdapter barberDb;
    private View view;

    public HomeTabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        barberDb = new BarberDbAdapter(getActivity());
        barberDb.open();
        barberDb.close();

        view = inflater.inflate(R.layout.home_fragment, container, false);
        // Inflate the layout for this fragment
        return view;
    }
}
