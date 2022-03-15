package com.example.toptracks.Fragment.register;

import android.content.Intent;
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

public class RegisterBottomSheetDialogFragment extends BottomSheetDialogFragment implements RegisterIterator.RegisterView {
    private EditText edtFullName, edtPassword, edtUserName;
    private CheckBox checkBox;
    private Button register;

    private FragmentRegisterPresenter presenter;
    private OnRegister onRegister;

    public RegisterBottomSheetDialogFragment(OnRegister onRegister) {
        this.onRegister = onRegister;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        edtFullName = view.findViewById(R.id.edtFullName);
        edtPassword = view.findViewById(R.id.edtPassword);
        edtUserName = view.findViewById(R.id.edtUserName);
        checkBox = view.findViewById(R.id.checkbox);
        register = view.findViewById(R.id.register);

        presenter = new FragmentRegisterPresenter();
        presenter.attachView(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strFullName = edtFullName.getText().toString();
                if (edtFullName.getText().toString().isEmpty() ||
                        edtPassword.getText().toString().isEmpty() ||
                        edtUserName.getText().toString().isEmpty()) {

                    Toast.makeText(getContext(), "Please fill in information", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkBox.isChecked()) {
                        onRegister.onRegistered(strFullName);
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
        Intent intent = new Intent(getContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onFailed(String msg) {

    }

    @Override
    public void onError(String msg) {

    }
}
