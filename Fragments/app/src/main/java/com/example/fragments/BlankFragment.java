package com.example.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BlankFragment extends Fragment {
    private EditText editText_login;
    private EditText editText_password;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View my_view = inflater.inflate(R.layout.fragment_blank, container, false);

        editText_login = my_view.findViewById(R.id.editText_login);
        editText_password = my_view.findViewById(R.id.editText_password);
        button = my_view.findViewById(R.id.button_fragment);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(my_view.getContext(), "btn cliquer", Toast.LENGTH_SHORT).show();
            }
        });

        return my_view;
    }

}