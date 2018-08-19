package com.example.surfer.barbershopapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.buscar_fragment);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }*/

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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        marker = new MarkerOptions().position(sydney);
        marker.title("En caucasia");
        mMap.addMarker(marker);

        LatLng titiribi = new LatLng(-34, 90);
        marker = new MarkerOptions().position(titiribi);
        marker.title("En titi");
        mMap.addMarker(marker);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){

            @Override
            public void onInfoWindowClick(Marker marker) {
                /*Intent intent = new Intent(MyMapsActivity.this,DisplayMessageActivity.class);
                intent.putExtra("Mensaje1", marker.getTitle());
                startActivity(intent);*/
                //Toast.makeText(getApplicationContext(),"alerta",Toast.LENGTH_LONG).show();
            }
        });
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.buscar_fragment, container, false);
    }*/
}
