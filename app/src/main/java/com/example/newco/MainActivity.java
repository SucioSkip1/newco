package com.example.newco;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        Intent i = new Intent(getApplicationContext(), crear_cuenta.class);
       startActivity(i);
    }
    public void ir(View view){
        Intent i = new Intent(getApplicationContext(),configurarPerfil.class);
        startActivity(i);
    }
}