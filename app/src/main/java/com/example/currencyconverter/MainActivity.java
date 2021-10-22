package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner inputSpinner;
    Spinner outputSpinner;
    double inMultiplier;
    double outMultiplier;
    double money;
    String moneyToString;
    RelativeLayout convRL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputSpinner = findViewById(R.id.outputSpinner);
        inputSpinner = findViewById(R.id.inputSpinner);
        convRL = findViewById(R.id.convRL);

        ArrayList<String> country = new ArrayList<>();
        country.add("GBP");
        country.add("USD");
        country.add("EUR");
        country.add("AUD");
        country.add("JPY");

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, country);

        inputSpinner.setAdapter(countryAdapter);
        outputSpinner.setAdapter(countryAdapter);

        outputSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (country.get(position)) {
                    case "GBP":
                        outMultiplier = 1;
                        break;
                    case "USD":
                        outMultiplier = 1.38;
                        break;
                    case "EUR":
                        outMultiplier = 1.19;
                        break;
                    case "AUD":
                        outMultiplier = 1.85;
                        break;
                    case "JPY":
                        outMultiplier = 157.18;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        inputSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (country.get(position)) {
                    case "GBP":
                        inMultiplier = 1;
                        break;
                    case "USD":
                        inMultiplier = 0.72;
                        break;
                    case "EUR":
                        inMultiplier = 0.84;
                        break;
                    case "AUD":
                        inMultiplier = 0.54;
                        break;
                    case "JPY":
                        inMultiplier = 0.0064;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void onClick(View view) {
        DecimalFormat df = new DecimalFormat();
        EditText currencyInput = findViewById(R.id.currencyInput);
        TextView currentOutput = findViewById(R.id.currencyOutput);
        String value = currencyInput.getText().toString();
        if (currencyInput.getText().toString().isEmpty()) {
            showSnackbar();
        } else {
            try {
                money = Double.parseDouble(value);
                money = (money * inMultiplier); // makes it into GPD
                money = (money * outMultiplier); //make it into desired output
                df.setMaximumFractionDigits(2);
                moneyToString = df.format(money);
                currentOutput.setText(moneyToString);
            } catch (Exception e) {
                showSnackbar();
            }
        }
    }

    private void showSnackbar() {
        Snackbar.make(convRL, "Warning: Do not include any symbols expect \".\"", Snackbar.LENGTH_LONG).show();
    }
}

