package com.example.toptracks.View;

import static com.example.toptracks.Model.Constants.KEY_CAMERA_GALLERY;
import static com.example.toptracks.Model.Constants.KEY_CAMERA_TAKEN;
import static com.example.toptracks.Model.Constants.REQUEST_CODE_CAMERA_GALLEY;
import static com.example.toptracks.Model.Constants.REQUEST_CODE_CAMERA_TAKEN;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.toptracks.R;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraOptions;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;

public class CameraAcitivity extends AppCompatActivity {
    private CameraView cameraView;
    private ImageButton capturePicture, switchCamera, imageGallery;

    private ActivityResultLauncher<String> openGallery;
    private byte[] dataPicture = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        cameraView = findViewById(R.id.cameraView);
        capturePicture = findViewById(R.id.capturePicture);
        switchCamera = findViewById(R.id.switchCamera);
        imageGallery = findViewById(R.id.imageGallery);

        cameraView.setLifecycleOwner(this);

        switchCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (cameraView.toggleFacing()) {
                    case BACK:
                        Toast.makeText(CameraAcitivity.this, "Switched to back camera!", Toast.LENGTH_SHORT).show();
                        break;
                    case FRONT:
                        Toast.makeText(CameraAcitivity.this, "Switched to front camera!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        cameraView.addCameraListener(new CameraListener() {
            @Override
            public void onCameraOpened(@NonNull CameraOptions options) {
                super.onCameraOpened(options);
            }

            @Override
            public void onPictureTaken(@NonNull PictureResult result) {
                super.onPictureTaken(result);
                Intent intentTaken = new Intent();
                dataPicture = result.getData();
                Toast.makeText(CameraAcitivity.this, dataPicture.toString(), Toast.LENGTH_SHORT).show();
                if (dataPicture != null) {
                    intentTaken.putExtra(KEY_CAMERA_TAKEN, dataPicture);
                    setResult(REQUEST_CODE_CAMERA_TAKEN, intentTaken);
                    finish();
                }
            }
        });

        capturePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.takePicture();
            }
        });

        openGallery = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Toast.makeText(CameraAcitivity.this, result.toString(), Toast.LENGTH_SHORT).show();
                Intent intentGallery = new Intent();
                intentGallery.putExtra(KEY_CAMERA_GALLERY, result.toString());
                setResult(REQUEST_CODE_CAMERA_GALLEY, intentGallery);
                finish();
            }
        });

        imageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery.launch("image/*");
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        cameraView.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraView.destroy();
    }
}