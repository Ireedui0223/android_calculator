package com.num.lab1_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_text, result_solution;
    MaterialButton button_seven, button_eight, button_nine, button_four, button_five, button_six, button_one, button_two, button_three, button_0;
    MaterialButton button_ac, button_clear, button_perc, button_div, button_equi, button_multi, button_neg, button_sum, button_dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_text = findViewById(R.id.result_text);
        result_solution = findViewById(R.id.result_solution);

        assignId(button_0, R.id.button_0);
        assignId(button_one, R.id.button_one);
        assignId(button_two, R.id.button_two);
        assignId(button_three, R.id.button_three);
        assignId(button_four, R.id.button_four);
        assignId(button_five, R.id.button_five);
        assignId(button_six, R.id.button_six);
        assignId(button_seven, R.id.button_seven);
        assignId(button_eight, R.id.button_eight);
        assignId(button_nine, R.id.button_nine);
        assignId(button_ac, R.id.button_ac);
        assignId(button_clear, R.id.button_clear);
        assignId(button_perc, R.id.button_perc);
        assignId(button_div, R.id.button_div);
        assignId(button_equi, R.id.button_equi);
        assignId(button_multi, R.id.button_multi);
        assignId(button_neg, R.id.button_neg);
        assignId(button_sum, R.id.button_sum);
        assignId(button_dot, R.id.button_dot);
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = result_solution.getText().toString();

        if (buttonText.equals("AC")) {
            result_solution.setText("");
            result_text.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            result_solution.setText(result_text.getText());
            return;
        }
        if (buttonText.equals("C")) {
            if (dataToCalculate.length() > 0)
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }

        if (buttonText.equals("%")) {
            Log.d("state", result_solution.getText() + "/100");
            String perc = getResult(result_solution.getText() + "/100");
            result_text.setText(perc);
            return;
        }
        result_solution.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Err")) {
            result_text.setText(finalResult);
        }

    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }
}