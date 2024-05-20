package com.example.newco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class iniciarSesion1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion1);
    }
    public void irConfi(View view){
        Intent i = new Intent(getApplicationContext(), configurarPerfil.class);
        startActivity(i);
    }
}