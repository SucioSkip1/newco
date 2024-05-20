package com.example.newco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class iniciarSesion extends AppCompatActivity {
        EditText nombre, contraseña, confirmarContra, correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        nombre = findViewById(R.id.txt_usuario);
        contraseña = findViewById(R.id.txt_contraseña);
        confirmarContra = findViewById(R.id.txt_confirma_contraseña);
        correo = findViewById(R.id.correo);
    }



    public void irConfig(View view){
        if (nombre.getText().toString().isEmpty()||contraseña.getText().toString().isEmpty()||confirmarContra.getText().toString().isEmpty()
            ||correo.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Aun faltan datos por llenar", Toast.LENGTH_SHORT).show();
            nombre.setError("Ingrese un nombre");
            contraseña.setError("Ingrese una contraseña");
            confirmarContra.setError("Ingrese su contraseña");
            correo.setError("Ingrese su correo");
        }else{
            Bundle envioDatos = new Bundle();
            Intent i = new Intent(getApplicationContext(), iniciarSesion1.class);
            startActivity(i);
        }
        
    }
}