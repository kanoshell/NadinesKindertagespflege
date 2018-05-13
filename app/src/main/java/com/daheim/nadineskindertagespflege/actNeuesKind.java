package com.daheim.nadineskindertagespflege;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class actNeuesKind extends Activity implements DatePickerDialog.OnDateSetListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neues_kind);
    }

    protected void onStart() {
        super.onStart();

    }

    public void kindInDb(View view) {
        String name = ((EditText)findViewById(R.id.Name)).getText().toString();
        String vorname = ((EditText)findViewById(R.id.Vorname)).getText().toString();
        String geburtstag = ((EditText)findViewById(R.id.Geburtstag)).getText().toString();
        String allergien = ((EditText)findViewById(R.id.Allergien)).getText().toString();
        String laufzeitanfang = ((EditText)findViewById(R.id.lzstarttxt)).getText().toString();
        String laufzeitende = ((EditText)findViewById(R.id.lzendetxt)).getText().toString();
        String active = null;
            if (((CheckBox) findViewById(R.id.Aktiv)).isChecked()) {
                active = "ja";
            }
            else {
                active = "nein";
            }

        // ContentValues zur Übergabe erstellen
        ContentValues kindvalues = new ContentValues();
        kindvalues.put(TpDbContract.TpDbKinder.Name, name);
        kindvalues.put(TpDbContract.TpDbKinder.Vorname, vorname);
        kindvalues.put(TpDbContract.TpDbKinder.Geburtstag, geburtstag);
        kindvalues.put(TpDbContract.TpDbKinder.Allergien, allergien);
        kindvalues.put(TpDbContract.TpDbKinder.Active, active);

        // ContentValues zur Übergabe erstellen
        ContentValues vertragslaufzeitvalues = new ContentValues();
        vertragslaufzeitvalues.put(TpDbContract.TpDbVertragslaufzeit.Datumvon,laufzeitanfang);
        vertragslaufzeitvalues.put(TpDbContract.TpDbVertragslaufzeit.Datumbis,laufzeitende);

        // Instantiierung des helpers für die Kindertabelle
        //TpDbTableKinderHelper kindhelper = new TpDbTableKinderHelper(getApplicationContext());
        //kindhelper.neueskind(kindvalues);

        // Instantiierung des helpers für die Vertragslaufzeittabelle
        TpDbTableVertragsLZHelper vertragsLZHelper = new TpDbTableVertragsLZHelper(getApplicationContext());
        vertragsLZHelper.neuevertragslz(vertragslaufzeitvalues);

        // zurück zur View actKinder
        startActivity(new Intent(this,actKinder.class));
    }

    //public void showDatePickerDialog(View view) {
    //    DialogFragment newFragment = new DatePickerFragment();
    //    newFragment.show(getFragmentManager(), "datePicker");
    //}

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
        if(getFragmentManager().findFragmentByTag("GebTagPicker")!=null){
            getFragmentManager().findFragmentByTag("GebTagPicker");
            GregorianCalendar datum = new GregorianCalendar(year,month,day);
            ((EditText)findViewById(R.id.Geburtstag)).setText(formatDate(datum));
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

    public void setGebTagDatum(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "GebTagPicker");
    }
}

