package com.example.buco;

import android.app.DatePickerDialog;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import static androidx.core.content.ContextCompat.getSystemService;

public class NewReceipt extends Fragment implements AdapterView.OnItemSelectedListener {
import com.example.buco.database.DatabaseHelper;
import com.example.buco.model.Receipt;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewReceipt extends Fragment implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    String[] categories = { "Groceries", "Bills", "Drugs", "Fashion", "Beauty", "Night Out" };
    EditText valueEditText;
    EditText shopEditText;
    EditText commentEditText;
    String[] categories = {"Groceries", "Bills", "Drugs", "Fashion", "Beauty", "Night Out"};
    EditText shop;
    EditText comment;
    EditText date;
    ImageView selectDate;
    EditText value;
    EditText category;
    Spinner spin;

    private int day, month, year;

    DatabaseHelper databaseHelper;

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
        databaseHelper =  new DatabaseHelper(getView().getContext());

        HideKeyboardOnEditTextFocusLost(view);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
//                        @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(NewReceipt.this)
//                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
//            }
            @Override
            public void onClick(View view) {
                databaseHelper.addReceipt(Integer.valueOf(value.getText().toString().trim()), shop.getText().toString().trim(),
                        spin.getSelectedItem().toString(), date.getText().toString().trim(), comment.getText().toString().trim());
            }


        });

        view.findViewById(R.id.select_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth+"-" +month+"-"+year);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        shop = view.findViewById(R.id.shop);
//        category = view.findViewById(R.id.category);
        value = view.findViewById(R.id.value);
        date = view.findViewById(R.id.show_date);
        selectDate = view.findViewById(R.id.select_date);
        comment = view.findViewById(R.id.comment);

        spin = getView().findViewById(R.id.category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);
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
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getContext(), "Selected category: " + categories[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView textView = (TextView) view.findViewById(R.id.show_date);
        textView.setText(currentDateString);
    }
}