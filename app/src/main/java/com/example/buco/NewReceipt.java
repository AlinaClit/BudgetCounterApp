package com.example.buco;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import static androidx.core.content.ContextCompat.getSystemService;

public class NewReceipt extends Fragment implements AdapterView.OnItemSelectedListener {

    String[] categories = { "Groceries", "Bills", "Drugs", "Fashion", "Beauty", "Night Out" };
    EditText valueEditText;
    EditText shopEditText;
    EditText commentEditText;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.new_receipt, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HideKeyboardOnEditTextFocusLost(view);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(NewReceipt.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        Spinner spin = getView().findViewById(R.id.category);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
    }

    private void HideKeyboardOnEditTextFocusLost(@NonNull View view) {
        valueEditText = (EditText) view.findViewById(R.id.value);
        shopEditText = (EditText) view.findViewById(R.id.shop);
        commentEditText = (EditText) view.findViewById(R.id.comment);

        View.OnFocusChangeListener hideKeyboardOnFocusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        };

        valueEditText.setOnFocusChangeListener(hideKeyboardOnFocusChangeListener);
        shopEditText.setOnFocusChangeListener(hideKeyboardOnFocusChangeListener);
        commentEditText.setOnFocusChangeListener(hideKeyboardOnFocusChangeListener);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        Toast.makeText(getContext(), "Selected User: "+ categories[position] ,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }
}