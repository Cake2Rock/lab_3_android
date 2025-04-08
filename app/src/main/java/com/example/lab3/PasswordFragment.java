package com.example.lab3;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PasswordFragment extends Fragment {

    private EditText editTextPassword;
    private RadioGroup radioGroupVisibility;
    private RadioButton radioShowSymbols;
    private RadioButton radioShowAsterisks;
    private Button buttonOk;
    private TextView textViewResult;

    private Button buttonOpenStorage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextPassword = view.findViewById(R.id.editTextPassword);
        radioGroupVisibility = view.findViewById(R.id.radioGroupVisibility);
        radioShowSymbols = view.findViewById(R.id.radioShowSymbols);
        radioShowAsterisks = view.findViewById(R.id.radioShowAsterisks);
        buttonOk = view.findViewById(R.id.buttonOk);
        textViewResult = view.findViewById(R.id.textViewResult);
        buttonOpenStorage = view.findViewById(R.id.buttonOpenStorage);

        radioShowAsterisks.setChecked(true);
        editTextPassword.setInputType(
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
        );

        radioGroupVisibility.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.radioShowSymbols) {
                            editTextPassword.setInputType(
                                    InputType.TYPE_CLASS_TEXT |
                                            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                            );
                        } else if (checkedId == R.id.radioShowAsterisks) {
                            editTextPassword.setInputType(
                                    InputType.TYPE_CLASS_TEXT |
                                            InputType.TYPE_TEXT_VARIATION_PASSWORD
                            );
                        }
                        editTextPassword.setSelection(
                                editTextPassword.getText().length()
                        );
                    }
                }
        );

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = editTextPassword.getText().toString().trim();
                if (password.isEmpty()) {
                    // Показати діалог, якщо поле порожнє
                    AlertDialogFragment dialogFragment = new AlertDialogFragment();
                    dialogFragment.show(
                            requireActivity().getSupportFragmentManager(),
                            "AlertDialog"
                    );
                } else {
                    String resultText = getString(R.string.label_result) + " " + password;
                    textViewResult.setText(resultText);

                    boolean success = FileHelper.appendToFile(
                            requireContext(),
                            "data.txt",
                            password
                    );
                    if (success) {
                        Toast.makeText(
                                requireContext(),
                                "Дані успішно збережено у файл",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else {
                        Toast.makeText(
                                requireContext(),
                                "Помилка запису у файл",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
            }
        });

        buttonOpenStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), DataViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
