package com.daheim.nadineskindertagespflege;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class anzeigeKind extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //holen der parameter vom intent bei der uebergabe (Name und eindeutige id in der DB)
        Intent iduebergabe = getIntent();
        setContentView(R.layout.activity_anzeige_kind);

        //Titel auf Name setzen (geholt aus "Bundle")
        setTitle(iduebergabe.getExtras().keySet().toArray()[0].toString());

        //Holen des Cursors und Mitgabe der eindeutigen ID in der DB (Value aus Bundle vom einzigen Key)
        TpDbTableKinderHelper einzelkindview = new TpDbTableKinderHelper(getApplicationContext());
        Cursor anzeigekind = einzelkindview.anzeigeGewaeltesKind(iduebergabe.getStringExtra(iduebergabe.getExtras().keySet().toArray()[0].toString()));

        // Bilden der Map zur Anzeige durch Iteration durch Cursor
        final Map<String, String> einzelanzeigekind = new HashMap<String, String>();
        while (anzeigekind.moveToNext()) {
            einzelanzeigekind.put("Name", anzeigekind.getString(
                    anzeigekind.getColumnIndexOrThrow(TpDbContract.TpDbKinder.Name)));
            einzelanzeigekind.put("Vorname", anzeigekind.getString(
                    anzeigekind.getColumnIndexOrThrow(TpDbContract.TpDbKinder.Vorname)));
            einzelanzeigekind.put("Geburtstag", anzeigekind.getString(
                    anzeigekind.getColumnIndexOrThrow(TpDbContract.TpDbKinder.Geburtstag)));
            einzelanzeigekind.put("Allergien", anzeigekind.getString(
                    anzeigekind.getColumnIndexOrThrow(TpDbContract.TpDbKinder.Allergien)));
            einzelanzeigekind.put("Active", anzeigekind.getString(
                    anzeigekind.getColumnIndexOrThrow(TpDbContract.TpDbKinder.Active)));
            einzelanzeigekind.put("_ID", anzeigekind.getString(
                    anzeigekind.getColumnIndexOrThrow(TpDbContract.TpDbKinder._ID)));
        }
        anzeigekind.close();

        List<String> keylist = new ArrayList();
        keylist.addAll((Collection) einzelanzeigekind.keySet());
        final TextView[] einzelkind = new TextView[keylist.size()];
        for (int i = 0; i < keylist.size(); i++) {
            TextView einzelner = new TextView(this);
            einzelner.setId(i + 1);
            einzelner.setText(keylist.get(i) + ": ");
            einzelner.setTextSize(22);
            einzelkind[i] = einzelner;
        }

        RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.activity_anzeige_kind);
        for (int i = 0; i < einzelkind.length; i++) {
            if (i == 0) {
//                Log.d("if;i=",String.valueOf(i));
                relativelayout.addView(einzelkind[i]);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) einzelkind[i].getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//                kindbuttons[i].setLayoutParams(params); //causes layout
            } else {
//                Log.d("else;i=",String.valueOf(i));
                relativelayout.addView(einzelkind[i]);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) einzelkind[i].getLayoutParams();
                params.addRule(RelativeLayout.BELOW, einzelkind[i - 1].getId());
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            }
        }
    }
}