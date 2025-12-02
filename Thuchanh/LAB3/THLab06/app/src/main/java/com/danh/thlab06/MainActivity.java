package com.danh.thlab06;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String[] units = {
            "Hải lý", "Dặm", "Km", "Lý", "Met", "Yard", "Foot", "Inch"
    };

    private final double[][] ratio = {
            // Hải lý, Dặm,    Km,      Lý,      Met,     Yard,    Foot,    Inch
            {1.0000000, 1.15077945, 1.8520000, 20.2537183, 1852.0000, 2025.37183, 6076.11549, 72913.3858},
            {0.86897624, 1.0000000, 1.6093440, 17.6000000, 1609.3440, 1760.00000, 5280.00000, 63360.0000},
            {0.53995680, 0.62137119, 1.0000000, 10.9361330, 1000.0000, 1093.61330, 3280.83990, 39370.0787},
            {0.04937365, 0.05681818, 0.0914400, 1.0000000, 91.44000, 100.00000, 300.00000, 3600.0000},
            {0.00053996, 0.00062137, 0.0010000, 0.0109361, 1.00000, 1.09361, 3.28084, 39.37008},
            {0.00049374, 0.00056818, 0.0009144, 0.0100000, 0.9144, 1.00000, 3.00000, 36.00000},
            {0.00016458, 0.00018939, 0.0003048, 0.0033333, 0.3048, 0.33333, 1.00000, 12.00000},
            {0.00001371, 0.00001578, 0.0000254, 0.0002778, 0.0254, 0.02778, 0.08333, 1.00000}
    };

    private EditText editTextNumber;
    private Spinner spinnerFrom, spinnerTo;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber = findViewById(R.id.editTextNumber);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        tvResult = findViewById(R.id.tvResult);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        spinnerFrom.setSelection(1);
        spinnerTo.setSelection(2);

        // Sự kiện khi thay đổi text hoặc spinner
        TextWatcher watcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                convertLength();
            }
        };
        editTextNumber.addTextChangedListener(watcher);

        spinnerFrom.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                convertLength();
            }
            @Override public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        spinnerTo.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                convertLength();
            }
            @Override public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });
    }

    private void convertLength() {
        String input = editTextNumber.getText().toString().trim();
        if (input.isEmpty()) {
            tvResult.setText("0.00");
            return;
        }

        double value;
        try {
            value = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            tvResult.setText("Error");
            return;
        }

        int from = spinnerFrom.getSelectedItemPosition();
        int to = spinnerTo.getSelectedItemPosition();

        double result = value * ratio[from][to];

        tvResult.setText(String.format("%.6f", result));
    }
}