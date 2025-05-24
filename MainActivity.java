// MainActivity.java package com.example.upscaleoverlay;

import android.app.Activity; import android.content.Intent; import android.media.projection.MediaProjectionManager; import android.os.Bundle;

public class MainActivity extends Activity { private static final int REQUEST_CODE = 1000; private MediaProjectionManager mProjectionManager;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mProjectionManager = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
    Intent captureIntent = mProjectionManager.createScreenCaptureIntent();
    startActivityForResult(captureIntent, REQUEST_CODE);
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
        Intent service = new Intent(this, OverlayService.class);
        service.putExtra("resultCode", resultCode);
        service.putExtra("data", data);
        startService(service);
        finish();
    }
}

}

