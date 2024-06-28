package com.example.newco;

import static android.content.Context.WINDOW_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

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

public class OverlayTrina extends Service {

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

        overlayView = LayoutInflater.from(this).inflate(R.layout.overlay_trina, null);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.START;

        ImageView filterView = overlayView.findViewById(R.id.filterTrina);
        applyBlueEnhancementFilter(filterView);

        windowManager.addView(overlayView, params);
    }

    private void applyBlueEnhancementFilter(ImageView filterView) {
        // Esta matriz de color realza los tonos azules
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{
                1.0f, 0.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 2.0f, 0.0f, 0.0f, // Duplica la intensidad del azul
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f
        });
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        filterView.setColorFilter(filter);
        filterView.setBackgroundColor(0x800000FF);  // Semi-transparent blue overlay to enhance visibility
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
