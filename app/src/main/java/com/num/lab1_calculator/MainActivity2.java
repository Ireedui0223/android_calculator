package com.num.lab1_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    MaterialButton calculatorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        assignId(calculatorButton, R.id.calculator_navigation);
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        if (buttonText.equals("Calculator")) {
            openActivity1();
        }
    }

    public void openActivity1() {
        Intent intent = new Intent(this, Activity.class);
        startActivity(intent);
    }
}