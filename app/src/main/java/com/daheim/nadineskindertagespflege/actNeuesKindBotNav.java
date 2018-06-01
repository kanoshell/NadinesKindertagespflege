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

public class actNeuesKindBotNav extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener{

    private TextView mTextMessage;
    long KindID;

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
        setContentView(R.layout.activity_act_neueskind_bot_nav);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_neuesKind);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        if(getFragmentManager().findFragmentByTag("GebTagPicker")!=null){
            getFragmentManager().findFragmentByTag("GebTagPicker");
            GregorianCalendar datum = new GregorianCalendar(year,month,day);
            ((EditText)findViewById(R.id.Geburtstag)).setText(formatDate(datum));
        }

    }
    public void setGebTagDatum(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "GebTagPicker");
    }

    private String formatDate(GregorianCalendar datum) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String datumstring=formatter.format(datum.getTime());
        return datumstring;
    }

}
