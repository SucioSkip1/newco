package com.example.newco;


import android.app.Service;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.graphics.PixelFormat;

public class OverlayService extends Service {

    private WindowManager windowManager;
    private View overlayView;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        overlayView = LayoutInflater.from(this).inflate(R.layout.overlay_layout, null);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.START;

        ImageView filterView = overlayView.findViewById(R.id.filterProna);
        applyBlueEnhancementFilter(filterView);

        windowManager.addView(overlayView, params);
    }

    private void applyBlueEnhancementFilter(ImageView filterView) {
        // Esta matriz de color realza los tonos azules
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        colorMatrix.set(new float[]{
                0.625f, 0.375f, 0.0f, 0.0f, 0.0f,  // Ajuste del rojo
                0.7f, 0.3f, 0.0f, 0.0f, 0.0f,  // Ajuste del verde
                0.0f, 0.3f, 0.7f, 0.0f, 0.0f,  // Ajuste del azul
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f
        });
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        filterView.setColorFilter(filter);
        filterView.setBackgroundColor(0x8000FF00);  // Semi-transparent blue overlay to enhance visibility
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (overlayView != null) {
            windowManager.removeView(overlayView);
            overlayView = null;
        }
    }
}
