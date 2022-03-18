package com.example.toptracks.Fragment.setting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.toptracks.R;
import com.example.toptracks.View.CameraAcitivity;
import com.example.toptracks.View.MainActivity;

public class FragmentSetting extends Fragment implements SettingIterator.SettingView {
    private ImageView profileImage;
    private Button upload;
    private FragmentSettingPresenter presenter;
    private String imageUri = null;
    private Uri uri;
    private byte[] dataPicture = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        profileImage = view.findViewById(R.id.profileImage);
        upload = view.findViewById(R.id.upload);

        presenter = new FragmentSettingPresenter();
        presenter.attachView(this);
        presenter.fetchSetting();

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CameraAcitivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            dataPicture = bundle.getByteArray("pictureData");
            if (dataPicture != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(dataPicture, 0, dataPicture.length);
                profileImage.setImageBitmap(bitmap);
            } else {
                imageUri = bundle.getString("pictureData");
                uri = Uri.parse(imageUri);
                profileImage.setImageURI(uri);
            }
        }

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                if (dataPicture != null) {
                    intent.putExtra("imageData", dataPicture);
                }
                if (imageUri != null) {
                    intent.putExtra("imageData", imageUri);
                }
                startActivity(intent);
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