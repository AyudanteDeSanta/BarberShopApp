package com.example.surfer.barbershopapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {


    private TextView tv;
    private TextView date;
    private Long fecha;

    public DatePickerFragment(){

    }

    public DatePickerFragment(TextView tv, TextView date, Long fecha) {
        this.tv = tv;
        this.date = date;
        this.fecha = fecha;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this, y, m,d);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, y);
        c.set(Calendar.MONTH, m);
        c.set(Calendar.DAY_OF_MONTH, d);

        long TimeinMilliSeccond = c.getTimeInMillis();

        String dateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(TimeinMilliSeccond));



        tv.setText(dateString);
        date.setText(dateString);
        fecha = TimeinMilliSeccond;
    }

    public Long getFecha() {
        return fecha;
    }
}
