package com.example.newco;


import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void irIniciarSesion(View view){
        Intent i = new Intent(getApplicationContext(),iniciarSesion.class);
        startActivity(i);
    }
    public void irCrearCuenta(View view){
        Intent i = new Intent(getApplicationContext(), iniciarSesion1.class);
       startActivity(i);
    }
}