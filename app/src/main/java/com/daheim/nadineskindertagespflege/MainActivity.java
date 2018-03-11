package com.daheim.nadineskindertagespflege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startKinder(View view) {
        startActivity(new Intent(this,actKinder.class));
    }

    public void startDriveFileManagement(View view) {
        startActivity(new Intent(this,DriveFileManagement.class));
    }

    public void startAnwesenheit(View view) {
        startActivity(new Intent(this,actAnwesenheit.class));
    }
}
