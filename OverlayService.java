// OverlayService.java package com.example.upscaleoverlay;

import android.app.Service; import android.content.Intent; import android.graphics.PixelFormat; import android.media.projection.MediaProjection; import android.media.projection.MediaProjectionManager; import android.os.IBinder; import android.view.Gravity; import android.view.SurfaceView; import android.view.WindowManager;

public class OverlayService extends Service { private WindowManager windowManager; private SurfaceView surfaceView;

@Override
public int onStartCommand(Intent intent, int flags, int startId) {
    int resultCode = intent.getIntExtra("resultCode", -1);
    Intent data = intent.getParcelableExtra("data");
    MediaProjectionManager mpm = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
    MediaProjection projection = mpm.getMediaProjection(resultCode, data);

    surfaceView = new SurfaceView(this);
    windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

    WindowManager.LayoutParams params = new WindowManager.LayoutParams(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
        PixelFormat.TRANSLUCENT);
    params.gravity = Gravity.TOP | Gravity.START;

    windowManager.addView(surfaceView, params);

    // TODO: projection → Surface로 연결, OpenGL 렌더링

    return START_NOT_STICKY;
}

@Override
public IBinder onBind(Intent intent) {
    return null;
}

}

