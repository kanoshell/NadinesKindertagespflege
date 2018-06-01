package com.daheim.nadineskindertagespflege;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class actNeueVertragslaufzeit extends Activity
        implements DatePickerDialog.OnDateSetListener {

    long VertrLZID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_neuevlz);
    }

    protected void onStart() {
        super.onStart();

    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        if(getFragmentManager().findFragmentByTag("LZStartPicker")!=null){
            getFragmentManager().findFragmentByTag("LZStartPicker");
            GregorianCalendar datum = new GregorianCalendar(year,month,day);
            ((EditText)findViewById(R.id.lzstarttxt)).setText(formatDate(datum));
        }
        if(getFragmentManager().findFragmentByTag("LZEndePicker")!=null){
            getFragmentManager().findFragmentByTag("LZEndePicker");
            GregorianCalendar datum = new GregorianCalendar(year,month,day);
            ((EditText)findViewById(R.id.lzendetxt)).setText(formatDate(datum));
        }
    }

    private String formatDate(GregorianCalendar datum) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String datumstring=formatter.format(datum.getTime());
        return datumstring;
    }

    public void setLZStartDatum(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "LZStartPicker");
    }

    public void setLZEndeDatum(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "LZEndePicker");
    }
}
