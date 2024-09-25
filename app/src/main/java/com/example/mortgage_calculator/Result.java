package com.example.mortgage_calculator;

import static com.example.mortgage_calculator.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //Getting user input from the main activity
        Intent intent = getIntent();
        double principleAmount = intent.getDoubleExtra("principleAmount",0);
        double interestRate = intent.getDoubleExtra("interestRate",0);
        double years = intent.getDoubleExtra("years",0);
        double answer = intent.getDoubleExtra("answer",0);

        //Initializing/defining input fields
        TextView result = findViewById(R.id.resultView);
        TextView principle = findViewById(R.id.principleView);
        TextView rate = findViewById(R.id.rateView);
        TextView time = findViewById(R.id.yearView);

        //Displaying user input and calculation result
        principle.setText(String.format("Principal Payment: $%.2f", principleAmount));
        rate.setText(String.format("Interest Rate: %.2f%%", interestRate));
        time.setText(String.format("Amortization Period: %.2f Year(s)", years));
        result.setText(String.format("$%.2f", answer));

        Button backButton = findViewById(id.back);

        //Creating intent to set an onclick function to go back to  main activity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Result.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}