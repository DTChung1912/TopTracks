package com.example.toptracks.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.toptracks.Fragment.setting.FragmentSetting;
import com.example.toptracks.R;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraOptions;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;

public class CameraAcitivity extends AppCompatActivity {
    private CameraView cameraView;
    private ImageButton capturePicture, switchCamera, imageGallery;
    private int SELECT_PICTURE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        cameraView = findViewById(R.id.cameraView);
        capturePicture = findViewById(R.id.capturePicture);
        switchCamera = findViewById(R.id.switchCamera);
        imageGallery = findViewById(R.id.imageGallery);

        cameraView.setLifecycleOwner(this);
        cameraView.addCameraListener(new CameraListener() {
            @Override
            public void onCameraOpened(@NonNull CameraOptions options) {
                super.onCameraOpened(options);
            }

            @Override
            public void onPictureTaken(@NonNull PictureResult result) {
                super.onPictureTaken(result);
                Intent intent = new Intent(CameraAcitivity.this, MainActivity.class);
                byte[] dataPicture = result.getData();
                intent.putExtra("cameraPicture", dataPicture);
                startActivity(intent);
            }
        });

        capturePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.takePicture();
            }
        });

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

        imageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    Intent intent = new Intent(CameraAcitivity.this, MainActivity.class);
                    intent.putExtra("chooserPicture", selectedImageUri.toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
                }
            }
        }
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