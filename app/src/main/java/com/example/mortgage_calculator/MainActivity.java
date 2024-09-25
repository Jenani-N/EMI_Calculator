package com.example.mortgage_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Declaring variables for calculation
    double principleAmount;
    double interestRate;
    double years;
    double answer;

    //Initializing the components
    EditText principle;
    EditText rate;
    EditText time;
    TextView result;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Initializing input fields
        principle = (EditText) findViewById(R.id.principle);
        rate = (EditText) findViewById(R.id.rate);
        time = (EditText) findViewById(R.id.time);
        calculate = (Button) findViewById(R.id.calculate);
        result = (TextView) findViewById(R.id.result);

        //Running the calculation when clicking the calculate button
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //Getting and storing user input
                    principleAmount = Double.valueOf(principle.getText().toString());
                    interestRate = Double.valueOf(rate.getText().toString());
                    years = Double.valueOf(time.getText().toString());

                    //Doing calculation
                    answer = calculation(principleAmount, interestRate, years);

                    //Intent to pass user input data to Result Activity
                    Intent intent = new Intent(MainActivity.this, Result.class);
                    intent.putExtra("principleAmount", principleAmount);
                    intent.putExtra("interestRate",interestRate);
                    intent.putExtra("years",years);
                    intent.putExtra("answer",answer);

                    //Open the result activity
                    startActivity(intent);

                }
                // Ensuring that user is entering valid input
                catch (NumberFormatException error){
                    result.setText(String.format("Please Enter Valid Input for Each Box"));
                }
                }
        });
    }

    //Method for doing mortgage calculation
    private double calculation(double p, double i, double t){
        double res, temp1;

        //Converting the values to the right calculation format
        i = ((i/100)/12);
        t = t*12;

        //Mortgage calculation formula
        temp1 = Math.pow(1+i,t);
        res = (p)*((i*temp1)/(temp1-1));
        res = Math.round(res * 100.0) / 100.0;

        return res;
    }
}
