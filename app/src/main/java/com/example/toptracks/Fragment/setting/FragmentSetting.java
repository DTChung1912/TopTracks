package com.example.toptracks.Fragment.setting;

import static com.example.toptracks.Model.Constants.KEY_CAMERA_GALLERY;
import static com.example.toptracks.Model.Constants.KEY_CAMERA_TAKEN;
import static com.example.toptracks.Model.Constants.REQUEST_CODE_CAMERA_GALLEY;
import static com.example.toptracks.Model.Constants.REQUEST_CODE_CAMERA_TAKEN;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.example.toptracks.R;
import com.example.toptracks.View.CameraAcitivity;
import com.example.toptracks.View.ImageUtils;

import java.io.IOException;

public class FragmentSetting extends Fragment implements SettingIterator.SettingView {
    private ImageView profileImage;
    private Button upload;
    private FragmentSettingPresenter presenter;
    private String imageUri = null;
    private Uri uri = null;
    private byte[] dataPicture = null;
    private Bitmap imageBitmap = null;

    private ActivityResultLauncher<Intent> launcherCamera;
    private ImageToMainActivity imageToMainActivity;

    public FragmentSetting(ImageToMainActivity imageToMainActivity) {
        this.imageToMainActivity = imageToMainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        profileImage = view.findViewById(R.id.profileImage);
        upload = view.findViewById(R.id.upload);

        presenter = new FragmentSettingPresenter();
        presenter.attachView(this);

        launcherCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result != null) {
                    if (result.getResultCode() == REQUEST_CODE_CAMERA_GALLEY) {
                        Intent intentGallery = result.getData();
                        if (intentGallery != null) {
                            imageUri = intentGallery.getStringExtra(KEY_CAMERA_GALLERY);
                            if (imageUri != null) {
                                uri = Uri.parse(imageUri);
                                try {
                                    imageBitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                                    ImageUtils.loadImage(profileImage, imageBitmap);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else if (result.getResultCode() == REQUEST_CODE_CAMERA_TAKEN) {
                        Intent intentTaken = result.getData();
                        if (intentTaken != null) {
                            dataPicture = intentTaken.getByteArrayExtra(KEY_CAMERA_TAKEN);
                            if (dataPicture != null) {
                                imageBitmap = BitmapFactory.decodeByteArray(dataPicture, 0, dataPicture.length);
                                ImageUtils.loadImage(profileImage, imageBitmap);
                            }
                        }
                    } else {
                        Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        presenter.fetchSetting();

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CameraAcitivity.class);
                launcherCamera.launch(intent);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageBitmap != null) {
                    imageToMainActivity.getImage(imageBitmap);
                }
            }
        });
        return view;
    }

    @Override
    public void onFetchSuccess() {

    }

    @Override
    public void onFailed(String msg) {

    }

    @Override
    public void onError(String msg) {

    }
}