package com.example.toptracks.View;

import static com.example.toptracks.Model.Constants.KEY_CURRENT_USER;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.toptracks.Fragment.freetracks.FragmentFree;
import com.example.toptracks.Fragment.purchasestracks.FragmentPurchases;
import com.example.toptracks.Fragment.setting.FragmentSetting;
import com.example.toptracks.Fragment.setting.ImageToMainActivity;
import com.example.toptracks.Fragment.toptracks.FragmentTopTracks;
import com.example.toptracks.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MainActivityIterator.MainView, ImageToMainActivity {
    private EditText searchMusic;
    private TextView currentUser;
    private ImageView profileImage;
    private BottomNavigationView bottomNavigationView;

    private MainActivityPresenter presenter;

    private SharedPreferences sharedPreferences;
    private int REQUEST_CODE = 1;
    private Uri uri;
    private String imagePath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchMusic = findViewById(R.id.searchMusic);
        currentUser = findViewById(R.id.currentUser);
        profileImage = findViewById(R.id.profileImage);
        bottomNavigationView = findViewById(R.id.navigation_bar);

        ActivityCompat.requestPermissions(MainActivity.this
                , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        ActivityCompat.requestPermissions(MainActivity.this
                , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);

        presenter = new MainActivityPresenter();
        presenter.attachView(this);
        presenter.fetchMain();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_toptracks:
                        fragment = new FragmentTopTracks();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_purchases:
                        fragment = new FragmentPurchases();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_free:
                        fragment = new FragmentFree();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_setting:
                        fragment = new FragmentSetting(MainActivity.this);
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onFetchSuccess() {
        sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);
        String userName = sharedPreferences.getString(KEY_CURRENT_USER, null);
        if (userName != null) {
            currentUser.setText(userName);
        }
    }

    @Override
    public void onFailed(String msg) {
    }

    @Override
    public void onError(String msg) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                sharedPreferences.edit().remove(KEY_CURRENT_USER).commit();
                startActivity(new Intent(MainActivity.this, StartActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;
        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void getImage(Bitmap imageBitmap) {
        if (imageBitmap != null){
            ImageUtils.loadImage(profileImage, imageBitmap);
        }
    }
}
