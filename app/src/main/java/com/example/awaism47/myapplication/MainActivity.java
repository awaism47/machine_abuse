package com.example.awaism47.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //Defined objects & variables
    Spinner spinner_spindle;
    Spinner spinner_feedrate;
    float Changed_Spindle_Speed;
    float Changed_Feed_Rate;
    float speed;
    float dia;
    float teeth;
    float feed;
    double feedPerTooth;
    double cuttingSpeed;

    EditText Current_SpindleSpeed;
    EditText Current_Feed_Rate;
    EditText Current_Tool_Diameter;
    EditText Current_Number_Of_Teeth;


    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Current_SpindleSpeed = (EditText) findViewById(R.id.spindle_speed);
        Current_Feed_Rate = (EditText) findViewById(R.id.feed_rate);
        Current_Tool_Diameter = (EditText) findViewById(R.id.tool_diameter);
        Current_Number_Of_Teeth = (EditText) findViewById(R.id.number_of_teeth);
        spinner_spindle = (Spinner) findViewById(R.id.new_spindle_speed);
        adapter = ArrayAdapter.createFromResource(this,R.array.new_spindle_speed,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_spindle.setAdapter(adapter);
        spinner_spindle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When spinner is selected this converts String to float


                String SpindleSpeed = spinner_spindle.getSelectedItem().toString();
                SpindleSpeed = SpindleSpeed.replaceAll("%", "");
                Changed_Spindle_Speed =  Float.parseFloat(SpindleSpeed);
                Changed_Spindle_Speed=Changed_Spindle_Speed/100;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_feedrate = (Spinner) findViewById(R.id.new_feed_rate);
        adapter = ArrayAdapter.createFromResource(this,R.array.new_feed_rate,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_feedrate.setAdapter(adapter);
        spinner_feedrate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String FeedRate = spinner_feedrate.getSelectedItem().toString();
                FeedRate = FeedRate.replaceAll("%", "");
                Changed_Feed_Rate=  Float.parseFloat(FeedRate);
                Changed_Feed_Rate=Changed_Feed_Rate/100;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Current_SpindleSpeed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //This throws up an error if user does not enter a value in this field
                //Found this code online and not sure if its the most efficient way

                if(Current_SpindleSpeed.getText().toString().trim().equalsIgnoreCase("")){
                    Current_SpindleSpeed.setError("Enter Programmed Spindle Speed");
                }

            }
        });
        Current_SpindleSpeed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Current_SpindleSpeed.setError(null);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
                public void afterTextChanged(Editable s) {
                    Current_SpindleSpeed.setError(null);

            }
        });

        Current_Feed_Rate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if(Current_Feed_Rate.getText().toString().trim().equalsIgnoreCase("")){
                    Current_Feed_Rate.setError("Enter Programmed Feed Rate");
                }

            }
        });
        Current_Feed_Rate.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Current_Feed_Rate.setError(null);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                Current_Feed_Rate.setError(null);

            }
        });
        Current_Tool_Diameter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if(Current_Tool_Diameter.getText().toString().trim().equalsIgnoreCase("")){
                    Current_Tool_Diameter.setError("Enter Tool Diameter in mm");
                }

            }
        });
        Current_Tool_Diameter.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Current_Tool_Diameter.setError(null);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                Current_Tool_Diameter.setError(null);

            }
        });

        Current_Number_Of_Teeth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if(Current_Number_Of_Teeth.getText().toString().trim().equalsIgnoreCase("")){
                    Current_Number_Of_Teeth.setError("Enter number of cutting teeth");
                }

            }
        });
        Current_Number_Of_Teeth.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Current_Number_Of_Teeth.setError(null);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                Current_Number_Of_Teeth.setError(null);

            }
        });
    }



    public void calculate(View view) {

        EditText spindleSpeed = (EditText) findViewById(R.id.spindle_speed);
        EditText diameter = (EditText) findViewById(R.id.tool_diameter);
        EditText feedRate = (EditText) findViewById(R.id.feed_rate);
        EditText numberOfTeeth = (EditText) findViewById(R.id.number_of_teeth);
        String SS= spindleSpeed.getText().toString();
        String Dia= diameter.getText().toString();
        String FR= feedRate.getText().toString();
        String NOT=numberOfTeeth.getText().toString();

        if (SS.equals("") || Dia.equals("") || Dia.equals(".") || FR.equals("") || NOT.equals("") ){
            speed=0;
            dia=0;
            feed=0;
            teeth=0;
            feedPerTooth=0;
            cuttingSpeed=0;
        }
        else {
        speed = Float.valueOf(SS);
        dia = Float.valueOf(Dia);
        feed = Float.valueOf(FR);
        teeth = Float.valueOf(NOT);

        feedPerTooth = ((feed*Changed_Feed_Rate*1.0)/(speed*Changed_Spindle_Speed*teeth));
        cuttingSpeed = (dia*3.14*speed*Changed_Spindle_Speed)/(1000);}
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

       // feedPerTooth = ((feed*Changed_Feed_Rate*1.0)/(speed*Changed_Spindle_Speed*teeth));
       //cuttingSpeed = (dia*3.14*speed*Changed_Spindle_Speed)/(1000);
        String errors = "";


        if (feedPerTooth >0.26){
            resultsTextView.setTextColor(Color.parseColor("#FF0000"));
            errors = "Parameters outside the limits";

        }
        else if (feedPerTooth==0.00){
            resultsTextView.setTextColor(Color.parseColor("#1a237e"));
            errors = "Check if you entered the data correctly";
        }else if (cuttingSpeed==0.00){
            resultsTextView.setTextColor(Color.parseColor("#1a237e"));
            errors = "Check if you entered the data correctly";}
        else if (feedPerTooth<0.07){
            resultsTextView.setTextColor(Color.parseColor("#FF0000"));
            errors = "Parameters outside the limits";
        }else if (cuttingSpeed<451){
            resultsTextView.setTextColor(Color.parseColor("#FF0000"));
            errors = "Parameters outside the limits";
        }else if (cuttingSpeed>5429){
            resultsTextView.setTextColor(Color.parseColor("#FF0000"));
            errors = "Parameters outside the limits";






        } else { resultsTextView.setTextColor(Color.parseColor("#8bc34a"));
            errors="Parameters within the limits" ;}

        resultsTextView.setText(message + "\n" + errors);
    }
}