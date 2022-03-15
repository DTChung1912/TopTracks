package com.example.toptracks.Fragment.register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.toptracks.R;
import com.example.toptracks.View.MainActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FragmentRegister extends BottomSheetDialogFragment implements RegisterIterator.RegisterView {
    private EditText fullName, password, userName;
    private CheckBox checkBox;
    private Button register;

    private FragmentRegisterPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        fullName = view.findViewById(R.id.fullName);
        password = view.findViewById(R.id.password);
        userName = view.findViewById(R.id.userName);
        checkBox = view.findViewById(R.id.checkbox);
        register = view.findViewById(R.id.register);

        presenter = new FragmentRegisterPresenter();
        presenter.attachView(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullName.getText().toString().isEmpty() ||
                        password.getText().toString().isEmpty() ||
                        userName.getText().toString().isEmpty()) {

                    Toast.makeText(getContext(), "Please fill in information", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkBox.isChecked()) {
                        presenter.fetchRegister();
                    } else {
                        Toast.makeText(getContext(), "Please check 'I agree'", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onFetchSuccess() {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit();
        editor.putString("currentuser", fullName.getText().toString());
        editor.apply();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailed(String msg) {

    }

    @Override
    public void onError(String msg) {

    }
}
