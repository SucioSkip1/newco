package com.example.newco;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class configurarPerfil extends AppCompatActivity {
    private static final int REQUEST_OVERLAY_PERMISSION = 1;
    private boolean isFilterApplied = false;
    private Button btn_uno, btn_dos, btn_tres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_perfil);




        btn_uno = findViewById(R.id.btn_Deuteranopia);
        btn_dos = findViewById(R.id.btn_Protanopia);
        btn_tres = findViewById(R.id.btn_Tritanopia);
        //Inicializar los botones
        btn_uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFilterApplied) {
                    stopOverlayService();
                } else {
                    if (Settings.canDrawOverlays(configurarPerfil.this)) {
                        startOverlayService();
                    } else {
                        requestOverlayPermission();
                    }
                }
                isFilterApplied = !isFilterApplied;
            }
        });


    }

    private void checkOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            // Si no se puede dibujar sobre otras aplicaciones, solicitar el permiso nuevamente
            requestOverlayPermission();
        } else {
            // Si el permiso está concedido, iniciar el servicio de superposición
            startOverlayService();
        }
    }
    private void requestOverlayPermission() {
       /* Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION);*/
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION);
    }


/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_OVERLAY_PERMISSION) {
            if (Settings.canDrawOverlays(this)) {
                startOverlayService();
            }
        }
    }
    */
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_OVERLAY_PERMISSION) {

            if (Settings.canDrawOverlays(this)) {
                // Si el permiso se concedió, iniciar el servicio de superposición
                startOverlayService();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }

    }
}
    private void startOverlayService() {
        Intent intent = new Intent(configurarPerfil.this, OverlayService.class);
        startService(intent);
        btn_uno.setText("Eliminar Filtro");
    }

    private void stopOverlayService() {
        Intent intent = new Intent(configurarPerfil.this, OverlayService.class);
        stopService(intent);
        btn_uno.setText("Deuteranopia");
    }

}