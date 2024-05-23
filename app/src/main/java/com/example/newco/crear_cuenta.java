package com.example.newco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class crear_cuenta extends AppCompatActivity {

        EditText nombre, contraseña, confirmarContra, correo;
        Button Registrar;
    String Msj;

    WebServiceInicioSesion obj = new WebServiceInicioSesion();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        //Barra
        //Objects.requireNonNull(getSupportActionBar()).hide();

        nombre = findViewById(R.id.txt_usuario);
        contraseña = findViewById(R.id.txt_contraseña);
        confirmarContra = findViewById(R.id.txt_confirma_contraseña);
        correo = findViewById(R.id.correo);
        Registrar = findViewById(R.id.btn_registrar);
        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nombre.getText().toString().isEmpty()
                        || contraseña.getText().toString().isEmpty()
                        || confirmarContra.getText().toString().isEmpty()
                        || correo.getText().toString().isEmpty()){
                    nombre.setError("Ingrese su nombre");
                    contraseña.setError("Ingrese su contraseña");
                    confirmarContra.setError("Confirme su contraseña");
                    correo.setError("Ingrese su correo");
                    //Animacion
                    nombre.startAnimation(shake);
                    contraseña.startAnimation(shake);
                    confirmarContra.startAnimation(shake);
                    correo.startAnimation(shake);
                    Toast.makeText(crear_cuenta.this, "Alerta, le faltan datos por llenar!", Toast.LENGTH_SHORT).show();



                }



                else{


                }
            }
        });
    }




}