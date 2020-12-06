package com.example.ec327;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.TreeMap;

public class changeBill extends AppCompatActivity {
    TextView displayvalue;
    Button submitBill, addreturnhomebill;
    EditText typeinput, amountinput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bill);
        submitBill=findViewById(R.id.submitBill);
        typeinput = findViewById(R.id.billInput);
        amountinput = findViewById(R.id.billAmountInput);
        displayvalue = findViewById(R.id.displayvalue);
        addreturnhomebill=findViewById(R.id.addreturnhomebill);
        Intent i = getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");
        amountinput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    amountinput.setText("$");
                    amountinput.setSelection(1);
                }
            }
        });
        addreturnhomebill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(changeBill.this,profile.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }

        });
        submitBill.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if (typeinput.getText().toString().trim().equalsIgnoreCase("")) {
                    typeinput.setError("Please enter a type");
                }
                //checking user input: Amount
                else if (amountinput.getText().toString().substring(1).trim().equalsIgnoreCase("")) {
                    amountinput.setError("Remember to enter a number!");
                } else {
                    calculateTotalBills(orginaluser);                 //Calculate % spending relative to income
                    orginaluser.setBills(typeinput.getText().toString().toLowerCase(),Float.parseFloat(amountinput.getText().toString().substring(1)));
                }

            }

        });

    }
    private void calculateTotalBills(Financials financials) {
        //get entered texts from the amountinput
        Double addedBill = Double.parseDouble(amountinput.getText().toString().substring(1));
        Double totalBills =(Double) 0.0; // use financials function

        for (TreeMap.Entry<String, Float> entry: financials.bills.entrySet()) {
            Float value = (Float) entry.getValue();
            totalBills+= Double.valueOf(value);

        }
        Double total = totalBills + addedBill;

        displayvalue.setText(String.valueOf("Your new total amount of monthly Bill is $" + total));
    }
}