package com.openclassrooms.realestatemanager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import java.text.NumberFormat;
import java.util.Locale;

public class LoanSimulatorActivity extends AppCompatActivity {

    private TextInputLayout textBorrowedLayout;
    private TextInputLayout textDurationLayout;
    private TextInputLayout textRateLayout;
    private TextView estimatedPrice;
    private Button btn;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_simulator);

        this.setupTextInputs();
        this.calculate();

    }

    private void setupTextInputs() {
        textBorrowedLayout = findViewById(R.id.loan_borrowed);
        textDurationLayout = findViewById(R.id.loan_duration);
        textRateLayout = findViewById(R.id.loan_rate);

        estimatedPrice = findViewById(R.id.loan_estimated);
    }

    private void calculate() {
        btn = findViewById(R.id.loan_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                double borrowedValue = Double.parseDouble(textBorrowedLayout.getEditText().getText().toString());
                int durationValue = Integer.parseInt(textDurationLayout.getEditText().getText().toString());
                double rate = Double.parseDouble(textRateLayout.getEditText().getText().toString());

                int months = durationValue * 12;
                double monthlyRate = rate / 12 / 100;
                double monthlyDue = (borrowedValue * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));

                NumberFormat nf = NumberFormat.getNumberInstance(Locale.getDefault());
                estimatedPrice.setText("The monthly due is " + nf.format(monthlyDue) + " $.");
            }
        });

    }


}
