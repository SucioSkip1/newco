package com.example.newco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class crear_cuenta extends AppCompatActivity {

        EditText nombre, contraseña, confirmarContra, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        //Barra
        Objects.requireNonNull(getSupportActionBar()).hide();

        nombre = findViewById(R.id.txt_usuario);
        contraseña = findViewById(R.id.txt_contraseña);
        confirmarContra = findViewById(R.id.txt_confirma_contraseña);
        correo = findViewById(R.id.correo);

    }




}