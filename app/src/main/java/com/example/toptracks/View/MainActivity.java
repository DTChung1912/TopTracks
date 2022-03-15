package com.example.toptracks.View;

import static com.example.toptracks.Model.Constants.KEY_CURRENT_USER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.toptracks.R;
import com.example.toptracks.View.viewpager.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements MainActivityIterator.MainView {
    private EditText searchMusic;
    private TextView currentUser;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ViewPagerAdapter viewPagerAdapter;
    private MainActivityPresenter presenter;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchMusic = findViewById(R.id.searchMusic);
        currentUser = findViewById(R.id.currentUser);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        presenter = new MainActivityPresenter();
        presenter.attachView(this);
        presenter.fetchMain();

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onFetchSuccess(String msg) {
        Log.d(msg, "Succsess");
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
}

