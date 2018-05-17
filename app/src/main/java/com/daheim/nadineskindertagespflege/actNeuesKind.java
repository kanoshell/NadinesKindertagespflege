package com.daheim.nadineskindertagespflege;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class actNeuesKind extends Activity
        implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{

    long KindID;
    long VertrLZID;
    long StdPlanID;

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
        String uhrzeitvon = ((EditText)findViewById(R.id.uhrzeitstart)).getText().toString();
        String uhrzeitbis = ((EditText)findViewById(R.id.uhrzeitende)).getText().toString();
        String active = null;
            if (((CheckBox) findViewById(R.id.Aktiv)).isChecked()) {
                active = "ja";
            }
            else {
                active = "nein";
            }
        String tage = null;
        if (((CheckBox) findViewById(R.id.mon)).isChecked()) {
            tage = "mo";
        }
        if (((CheckBox) findViewById(R.id.die)).isChecked()) {
            if(tage==null){
                tage="di";
            }else{
                tage=tage+";di";
            }
        }
        if (((CheckBox) findViewById(R.id.mit)).isChecked()) {
            if(tage==null){
                tage="mi";
            }else{
                tage=tage+";mi";
            }
        }
        if (((CheckBox) findViewById(R.id.don)).isChecked()) {
            if (tage == null) {
                tage = "do";
            } else {
                tage = tage + ";do";
            }
        }
        if (((CheckBox) findViewById(R.id.fre)).isChecked()) {
            if(tage==null){
                tage="fr";
            }else{
                tage=tage+";fr";
            }
        }
        if (((CheckBox) findViewById(R.id.sam)).isChecked()) {
            if(tage==null){
                tage="sa";
            }else{
                tage=tage+";sa";
            }
        }
        if (((CheckBox) findViewById(R.id.son)).isChecked()) {
            if(tage==null){
                tage="so";
            }else{
                tage=tage+";so";
            }
        }

        // ContentValues zur Übergabe erstellen
        ContentValues kindvalues = new ContentValues();
        kindvalues.put(TpDbContract.TpDbKinder.Name, name);
        kindvalues.put(TpDbContract.TpDbKinder.Vorname, vorname);
        kindvalues.put(TpDbContract.TpDbKinder.Geburtstag, geburtstag);
        kindvalues.put(TpDbContract.TpDbKinder.Allergien, allergien);
        kindvalues.put(TpDbContract.TpDbKinder.Active, active);

        // Instantiierung des helpers für die Kindertabelle
        TpDbTableHelper kindhelper = new TpDbTableHelper(getApplicationContext());
        this.KindID = kindhelper.neuesKind(kindvalues);

        // ContentValues zur Übergabe erstellen
        ContentValues vertragslaufzeitvalues = new ContentValues();
        vertragslaufzeitvalues.put(TpDbContract.TpDbVertragslaufzeit.KindID,this.KindID);
        vertragslaufzeitvalues.put(TpDbContract.TpDbVertragslaufzeit.Datumvon,laufzeitanfang);
        vertragslaufzeitvalues.put(TpDbContract.TpDbVertragslaufzeit.Datumbis,laufzeitende);

        // Instantiierung des helpers für die Vertragslaufzeittabelle
        TpDbTableHelper vertragsLZHelper = new TpDbTableHelper(getApplicationContext());
        this.VertrLZID = vertragsLZHelper.neueVertrLZ(vertragslaufzeitvalues);

        // ContentValues zur Übergabe erstellen
        ContentValues stundenplanvalues = new ContentValues();
        stundenplanvalues.put(TpDbContract.TpDbStundenplan.KindID,this.KindID);
        stundenplanvalues.put(TpDbContract.TpDbStundenplan.Uhrzeitvon,uhrzeitvon);
        stundenplanvalues.put(TpDbContract.TpDbStundenplan.Uhrzeitbis,uhrzeitbis);
        stundenplanvalues.put(TpDbContract.TpDbStundenplan.Tag,tage);

        // Instantiierung des helpers für die Stundenplantabelle
        TpDbTableHelper stundenplanHelper = new TpDbTableHelper(getApplicationContext());
        this.StdPlanID = stundenplanHelper.neuerStdPlan(stundenplanvalues);

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

    public void onTimeSet(TimePicker view,int hourOfDay,int minutes) {
        if (getFragmentManager().findFragmentByTag("UZStartPicker") != null) {
            getFragmentManager().findFragmentByTag("UZStartPicker");
            String stunden = String.valueOf(hourOfDay);
            String minuten = String.valueOf((minutes));
            if (stunden.length()==1){
                stunden="0"+stunden;
            }
            if (minuten.length()==1){
                minuten="0"+minuten;
            }
            String uhrzeit = stunden+":"+minuten;
            ((EditText) findViewById(R.id.uhrzeitstart)).setText(uhrzeit);
        }
        if (getFragmentManager().findFragmentByTag("UZEndePicker") != null) {
            getFragmentManager().findFragmentByTag("UZEndePicker");
            String stunden = String.valueOf(hourOfDay);
            String minuten = String.valueOf((minutes));
            if (stunden.length()==1){
                stunden="0"+stunden;
            }
            if (minuten.length()==1){
                minuten="0"+minuten;
            }
            String uhrzeit = stunden+":"+minuten;
            ((EditText) findViewById(R.id.uhrzeitende)).setText(uhrzeit);
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

    public void setUZStart(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "UZStartPicker");
    }

    public void setUZEnde(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "UZEndePicker");
    }

}

