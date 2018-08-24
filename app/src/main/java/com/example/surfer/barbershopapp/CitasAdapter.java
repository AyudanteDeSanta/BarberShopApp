package com.example.surfer.barbershopapp;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CitasAdapter extends RecyclerView.Adapter<CitasAdapter.ViewHolder> {

    private Cursor mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public final BarberDbAdapter barberDbAdapter;
        public TextView mTextViewUsuario;
        public TextView mTextViewAdicional;
        public TextView mTextViewPrecio;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewUsuario = itemView.findViewById(R.id.text_cita_fecha);
            mTextViewAdicional = itemView.findViewById(R.id.text_nombre_barbebo);
            mTextViewPrecio = itemView.findViewById(R.id.text_precio);
            barberDbAdapter = new BarberDbAdapter(itemView.getContext());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.citas_item_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mDataSet.moveToPosition(position);

        long idFecha = mDataSet.getLong(mDataSet.getColumnIndex("fecha"));
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
        Date fechaAgenda = new Date(idFecha);

        //En este caso id localizacion es igual a id barbero, sólo en este caso
        int idLocalizacion = mDataSet.getInt(mDataSet.getColumnIndex("idLocalizacion"));



        String fechaAgendaStr = df2.format(fechaAgenda);
        String precioServicio = String.valueOf(mDataSet.getInt(mDataSet.getColumnIndex("precio")));

        holder.mTextViewUsuario.setText(fechaAgendaStr);
        holder.mTextViewPrecio.setText(precioServicio);

        holder.barberDbAdapter.open();
        Cursor cursorBarbero = holder.barberDbAdapter.fetchBarber(idLocalizacion);
        String nombreBarbero = "";

        while (!cursorBarbero.isAfterLast()){
            nombreBarbero = cursorBarbero.getString(cursorBarbero.getColumnIndex("nombres"));
            cursorBarbero.moveToNext();
        }
        holder.barberDbAdapter.close();

        holder.mTextViewAdicional.setText(nombreBarbero);

    }

    @Override
    public int getItemCount() {
        return mDataSet.getCount();
    }



    public CitasAdapter(Cursor mDataSet) {
        this.mDataSet = mDataSet;
    }

    public void setmDataSet(Cursor mDataSet) {
        this.mDataSet = mDataSet;
    }
}
