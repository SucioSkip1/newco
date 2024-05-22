package com.example.newco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class iniciarSesion extends AppCompatActivity {
            String Msj;
            WebServiceInicioSesion obj = new WebServiceInicioSesion();
            Button btnEntrar;
            EditText usu,contra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
       // Objects.requireNonNull(getSupportActionBar()).hide();
        btnEntrar = findViewById(R.id.btn_iniciarSesion);
        usu=findViewById(R.id.usuario_activity);
        contra=findViewById(R.id.contra_activity);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String Usuario = usu.getText().toString().trim();
                    String Contraseña = contra.getText().toString().trim();
                    login(Usuario, Contraseña);
            }
        });
    }
    //Metodo login
    private void login (String usuario, String contraseña){
        if (usuario.isEmpty()||contraseña.isEmpty()){
        }
        else{
            new LoginTask().execute(usuario,contraseña);
        }

    }
    public void irCrearCuenta(View view) {
        //Intent i = new Intent(getApplicationContext(),activity_crear_cuenta.class);
        //startActivity(i);
    }
    private class LoginTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String usuario = params[0];
            String password = params[1];
            return WebServiceInicioSesion.login(usuario, password);
        }

        @Override
        protected void onPostExecute(String response) {
            Log.d("LoginResponse", response); // Imprimir la respuesta en el Logcat
            // Aquí puedes procesar la respuesta del servidor
            if (response.equals("Inicio de sesión exitoso")) {
                Toast.makeText(iniciarSesion.this, response, Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getApplicationContext(), seleccionaUsuario.class);
                //startActivity(intent);
            } else if (response.equals("Usuario no registrado")) {
                Toast.makeText(iniciarSesion.this, response, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(iniciarSesion.this, "Error al iniciar sesión"+response, Toast.LENGTH_SHORT).show();
            }
        }
    }





}