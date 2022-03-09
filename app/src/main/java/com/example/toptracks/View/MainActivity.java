package com.example.toptracks.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import com.example.toptracks.R;
import com.example.toptracks.View.viewpager.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity implements MainActivityIterator.MainView{
    EditText searchMusic;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchMusic = findViewById(R.id.searchMusic);
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
        Log.d(msg,"Succsess");
    }

    @Override
    public void onFailed(String msg) {}

    @Override
    public void onError(String msg) {}
}

