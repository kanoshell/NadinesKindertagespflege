package com.daheim.nadineskindertagespflege;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Kinder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinder);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.activity_kinder);
        //setSupportActionBar(toolbar);

        // holen des cursors für die anzeige der kinder
        TpDbTableKinderHelper kindviewer = new TpDbTableKinderHelper(getApplicationContext());
        Cursor kinder = kindviewer.anzeigeKinder();

        // Bilden der Map zur Anzeige durch Iteration durch Cursor
        final Map<String,String> aktivekindermap = new TreeMap<String,String>();
        while(kinder.moveToNext()) {
            String kindname = kinder.getString(
                    kinder.getColumnIndexOrThrow(TpDbContract.TpDbKinder.Vorname));
            kindname += " ";
            kindname += kinder.getString(
                    kinder.getColumnIndexOrThrow(TpDbContract.TpDbKinder.Name));
            String einid = kinder.getString(
                    kinder.getColumnIndex(TpDbContract.TpDbKinder._ID));
            aktivekindermap.put(kindname,einid);
        }
        kinder.close();

//        Log.d("aktive kinder",aktivekinder.toString());
        //Bilden einer liste über die Keys aus der Map zur Benamung der Buttons
        List<String> aktivekinder = new ArrayList<String>();
        aktivekinder.addAll((Collection<String>)aktivekindermap.keySet());
        // Anzeige der Liste aktivekinder in Activity - Button pro Kind
        final Button[] kindbuttons = new Button[aktivekinder.size()];
        for (int i = 0; i < aktivekinder.size(); i++) {
            Button kindbutton = new Button(this);
            kindbutton.setId(i+1);
            kindbutton.setText(aktivekinder.get(i));
            kindbuttons[i] = kindbutton;
        }
//        Log.d("groesse kindbt-array: ",String.valueOf(kindbuttons.length));
        RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.activity_kinder);
        for (int i = 0; i < kindbuttons.length; i++) {
            if (i==0) {
//                Log.d("if;i=",String.valueOf(i));
                relativelayout.addView(kindbuttons[i]);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)kindbuttons[i].getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//                kindbuttons[i].setLayoutParams(params); //causes layout
                final int finalI1 = i;
                kindbuttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent iduebergabe = new Intent(Kinder.this,anzeigeKind.class);
                        iduebergabe.putExtra(kindbuttons[finalI1].getText().toString(),aktivekindermap.get(kindbuttons[finalI1].getText().toString()));
                        startActivity(iduebergabe);
                    }
                });
            } else {
//                Log.d("else;i=",String.valueOf(i));
                relativelayout.addView(kindbuttons[i]);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)kindbuttons[i].getLayoutParams();
                params.addRule(RelativeLayout.BELOW,kindbuttons[i-1].getId());
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                final int finalI = i;
                kindbuttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent iduebergabe = new Intent(Kinder.this,anzeigeKind.class);
                        iduebergabe.putExtra(kindbuttons[finalI].getText().toString(),aktivekindermap.get(kindbuttons[finalI].getText().toString()));
                        startActivity(iduebergabe);
                    }
                });
//                kindbuttons[i].setLayoutParams(params); //causes layout update
            }
        }

    }

    protected void onStart() {
        super.onStart();

        FloatingActionButton neuesKindButton = (FloatingActionButton) findViewById(R.id.neuesKindButton);
        neuesKindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Kinder.this,neuesKind.class));
            }
        });
    }

}
