package com.daheim.nadineskindertagespflege;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class actNeueVLZBotNav extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener{

    private TextView mTextMessage;
    long VertrLZID;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_neuevlz_bot_nav);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_neueVLZ);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
