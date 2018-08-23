package com.example.surfer.barbershopapp;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CitasAdapter extends RecyclerView.Adapter<CitasAdapter.ViewHolder> {

    private Cursor mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextViewUsuario;
        public TextView mTextViewAdicional;


        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewUsuario = itemView.findViewById(R.id.text_cita_fecha);
            //mTextViewAdicional = itemView.findViewById(R.id.text_usuario_adicionales);
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

        String fechaAgenda = String.valueOf(mDataSet.getInt(mDataSet.getColumnIndex("_id")));
        //String datosBarbero = String.valueOf(mDataSet.getInt(mDataSet.getColumnIndex("precio")));

        holder.mTextViewUsuario.setText(fechaAgenda);
        //holder.mTextViewAdicional.setText(datosBarbero);

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
