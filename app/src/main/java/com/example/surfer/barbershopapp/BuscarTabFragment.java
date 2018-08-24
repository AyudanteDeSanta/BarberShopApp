package com.example.surfer.barbershopapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

//esto tenia extends Fragment
public class BuscarTabFragment extends SupportMapFragment  implements OnMapReadyCallback {
    private GoogleMap mMap;
    private MarkerOptions marker;
    private BarberDbAdapter barberDbAdapter;
    private Cursor cursor;

    public BuscarTabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        getMapAsync(this);

        initInstance();

        return rootView;
    }

    private void initInstance() {
        barberDbAdapter = new BarberDbAdapter(getActivity());
        barberDbAdapter.open();
        cursor = barberDbAdapter.fetchAllLocalizations();


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ubicacion = null;
        double latitud, longitud;
        String nombreBarbero = "";
        int idBarbero = 0;
        Cursor cursorBarbero;
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            latitud = cursor.getDouble(cursor.getColumnIndex("latitud"));
            longitud = cursor.getDouble(cursor.getColumnIndex("longitud"));
            ubicacion = new LatLng(latitud, longitud);

            //Log.v("UBICACION","====> Latitud: "+latitud+ " -- Longitud: "+longitud);

            //En este contexto id localizacion es igual al id barbero, sólo para este ejemplo
            idBarbero = cursor.getInt(cursor.getColumnIndex("_id"));
            cursorBarbero = barberDbAdapter.fetchBarber(idBarbero);
            while (!cursorBarbero.isAfterLast()){
                nombreBarbero = cursorBarbero.getString(cursorBarbero.getColumnIndex("nombres"));
                cursorBarbero.moveToNext();
            }

            marker = new MarkerOptions().position(ubicacion);

            marker.title(nombreBarbero);
            marker.snippet("Selecciona para un servicio");
            mMap.addMarker(marker).setTag(String.valueOf(idBarbero));
            cursor.moveToNext();
        }

        if (ubicacion!=null){
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,17));
        }

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){

            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(getActivity(),ReserveActivity.class);
                String idBarbero = String.valueOf(marker.getTag());

                intent.putExtra("idLocalizacion", idBarbero);
                startActivity(intent);

                //Toast.makeText(getActivity(),idBarbero,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        barberDbAdapter.close();

    }
}
