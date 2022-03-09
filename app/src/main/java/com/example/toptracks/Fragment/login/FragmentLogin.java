package com.example.toptracks.Fragment.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.toptracks.R;
import com.example.toptracks.View.MainActivity;

public class FragmentLogin extends Fragment implements LoginIterator.LoginView{
    EditText userName,passWord;
    Button login;
    FragmentLoginPresenter presenter;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        userName = view.findViewById(R.id.userName);
        passWord = view.findViewById(R.id.passWord);
        login  = view.findViewById(R.id.login);

        presenter = new FragmentLoginPresenter();
        presenter.attachView(this);

        intent = new Intent(getContext(), MainActivity.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.fetchLogin();
            }
        });
        return view;
    }

    @Override
    public void onFetchSuccess() {
        startActivity(intent);
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String msg) {

    }
}