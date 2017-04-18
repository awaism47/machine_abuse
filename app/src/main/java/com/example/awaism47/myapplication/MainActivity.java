package com.example.awaism47.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void calculate(View view) {
        EditText spindleSpeed = (EditText) findViewById(R.id.spindle_speed);
        String speedString=spindleSpeed.getText().toString();       //this will get a spindle speed string
        int speed=Integer.parseInt(speedString);                   //this will convert string to number
        EditText diameter = (EditText) findViewById(R.id.tool_diameter);
        String diaString=diameter.getText().toString();       //this will get a spindle speed string
        double dia = Double.parseDouble(diaString.getText().toString());                  //this will convert string to number
        EditText feedRate = (EditText) findViewById(R.id.feed_rate);
        String feedString=feedRate.getText().toString();       //this will get a spindle speed string
        int feed=Integer.parseInt(feedString);                 //this will convert string to number
        EditText numberOfTeeth = (EditText) findViewById(R.id.number_of_teeth);
        String teethString=numberOfTeeth.getText().toString();       //this will get a spindle speed string
        int teeth=Integer.parseInt(teethString);                 //this will convert string to number
        double feedPerTooth = ((feed*1.0)/(speed*teeth));
        double cuttingSpeed = (dia*3.14*speed)/(1000);
        String   FPT = new DecimalFormat("0.00").format(feedPerTooth);
        String surfaceSpeed = new DecimalFormat("0.00").format(cuttingSpeed);


        String feedPerToothResults = "Feed per tooth ="  + FPT;

        String cuttingSpeedResults = "Cutting speed = " + surfaceSpeed;
        String results =  feedPerToothResults +  "\n" + cuttingSpeedResults;

        displayMessage(results);
    }

    /* This method displays the given text on the screen.
   */
    private void displayMessage(String message) {
        TextView resultsTextView = (TextView) findViewById(R.id.results);
        EditText spindleSpeed = (EditText) findViewById(R.id.spindle_speed);
        String speedString=spindleSpeed.getText().toString();       //this will get a spindle speed string
        int speed=Integer.parseInt(speedString);                   //this will convert string to number
        EditText diameter = (EditText) findViewById(R.id.tool_diameter);
        String diaString=diameter.getText().toString();       //this will get a spindle speed string
        float dia=Integer.parseInt(diaString);                   //this will convert string to number
        EditText feedRate = (EditText) findViewById(R.id.feed_rate);
        String feedString=feedRate.getText().toString();       //this will get a spindle speed string
        int feed=Integer.parseInt(feedString);                 //this will convert string to number
        EditText numberOfTeeth = (EditText) findViewById(R.id.number_of_teeth);
        String teethString=numberOfTeeth.getText().toString();       //this will get a spindle speed string
        int teeth=Integer.parseInt(teethString);                 //this will convert string to number
        double feedPerTooth = ((feed*1.0)/(speed*teeth));
        double cuttingSpeed = (dia*3.14*speed)/(1000);
        String errors = "";


        if (feedPerTooth >0.26){
            resultsTextView.setTextColor(Color.parseColor("#FF0000"));
            errors = "Feed rate is too high";

        } else if (feedPerTooth<0.07){
            resultsTextView.setTextColor(Color.parseColor("#FF0000"));
            errors = "Feed rate is too low";
        }else if (cuttingSpeed<451){
            resultsTextView.setTextColor(Color.parseColor("#FF0000"));
            errors = "Cutting speed is too low";
        }else if (cuttingSpeed>5429){
            resultsTextView.setTextColor(Color.parseColor("#FF0000"));
            errors = "Cutting speed is too High";






        } else { resultsTextView.setTextColor(Color.parseColor("green"));
            errors="OK to use Feed Rate and cutting Speed" ;}

        resultsTextView.setText(message + "\n" + errors);
    }
}