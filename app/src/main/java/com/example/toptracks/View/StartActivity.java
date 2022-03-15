package com.example.toptracks.View;

import static com.example.toptracks.Model.Constants.KEY_CURRENT_USER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.toptracks.Model.Constants.*;
import com.example.toptracks.R;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);
        String userName = sharedPreferences.getString(KEY_CURRENT_USER, null);
        if (userName != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
}