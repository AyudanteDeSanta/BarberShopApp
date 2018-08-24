package com.example.surfer.barbershopapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReserveActivity extends AppCompatActivity {
    private TextView tv;
    private Activity context;
    private TextView date;
    private String idLocation;
    private Long fecha;
    private Cursor cursor;
    private DatePickerFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        context = this;

        tv = (TextView) findViewById(R.id.fecha);
        date = (TextView) findViewById(R.id.date);

        Intent intent = getIntent();
        idLocation = intent.getStringExtra("idLocalizacion");

        final ImageButton btnPopUp = (ImageButton) findViewById(R.id.calendario);
        btnPopUp.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendar(view);
            }
        });

        Button aceptar = findViewById(R.id.accept);
        aceptar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                appointment(view);
            }
        });

        Button cancelar = findViewById(R.id.cancel);
        cancelar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel(view);
            }
        });
    }

    public void showCalendar(View view) {
        fragment = new DatePickerFragment(tv, date, fecha);
        fragment.show(context.getFragmentManager(), "datePicker");
    }

    public void appointment (View view){
        fecha = fragment.getFecha();
        BarberDbAdapter barberDbAdapter = new BarberDbAdapter(this);
        barberDbAdapter.open();
        Integer l = Integer.parseInt(idLocation);
        //Integer l = 1;

        cursor = barberDbAdapter.fetchAllAppoitment(fecha);
        boolean flag = false;
        while (cursor.moveToNext()){
            flag = true;
            break;
        }
        if (!flag)
        {
            barberDbAdapter.createAppointment(fecha, 1, l, 10000);
            barberDbAdapter.close();

            alerta("Cita Agendada", "Se ha creado una cita para la fecha seleccionada.");
        }
        else{
            barberDbAdapter.close();

            alerta("Error", "Ya existe una cita para esta fecha, por favor ingrese otra fecha");
        }

        cursor.close();
    }

    public void alerta (String titulo, String mensaje){
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setMessage(mensaje);
        alertbox.setTitle(titulo);
        alertbox.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });
        alertbox.show();

    }

    public void cancel(View view){
        finish();
    }
}
