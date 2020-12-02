package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;

public class weektransport extends AppCompatActivity {

    Button buttontran,backtran;
    EditText edittran;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weektransport);

        buttontran = findViewById(R.id.buttontran);
        backtran=findViewById(R.id.backtran);
        edittran=findViewById(R.id.edittran);

        Intent i=getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");

        if(orginaluser.getGas()>0){
            edittran.setText(Float.toString(orginaluser.getGas()));
        }
        buttontran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edittran.getText().toString().trim().equalsIgnoreCase("")) {
                    edittran.setError("Input");
                }
                else if (Float.parseFloat(edittran.getText().toString())<0){
                    edittran.setError("No negative!");
                }

                    else{


                    Intent a = new Intent(weektransport.this, loadingsurvey.class);
                    orginaluser.setGas(Float.parseFloat(edittran.getText().toString()));
                    a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    a.putExtra("userObject", orginaluser);
                    startActivity(a);
                }
            }
        });
        backtran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(weektransport.this, weeklygroceries.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }
        });
    }
}