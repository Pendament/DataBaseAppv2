package com.example.databasev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    LinearLayout layout;
    TextView settingsTitle;
    Button buttonReturnFromSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        layout = findViewById(R.id.layout);
        settingsTitle = findViewById(R.id.settingsTitle);
        buttonReturnFromSettings = findViewById(R.id.buttonReturnFromSettings);

        initSettings();
        initRadioGroupColorChoice();


        initMainActivity(); // Takes User back to Home Page
    }

    private void initSettings() {
        SharedPreferences sharedPref = getSharedPreferences("MySettingPreferences", Context.MODE_PRIVATE);
        String changeColor = sharedPref.getString("color", "name");

        //Change Color
        RadioButton rbDefault = findViewById(R.id.rbuttonDefault);
        RadioButton rbWhite = findViewById(R.id.rbuttonWhite);
        RadioButton rbCreme = findViewById(R.id.rbuttonCreme);
        if (changeColor.equals("default")) {
            rbDefault.setChecked(true);
        } else if (changeColor.equals("white")) {
            rbWhite.setChecked(true);
        } else {
            rbCreme.setChecked(true);
        }


    }

    private void initRadioGroupColorChoice() {
        RadioGroup rg = findViewById(R.id.radioGroupsColorChoice);
        RadioButton rbDefault = findViewById(R.id.rbuttonDefault);
        RadioButton rbWhite = findViewById(R.id.rbuttonWhite);
        RadioButton rbCreme = findViewById(R.id.rbuttonCreme);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rbDefault.isChecked()) {
                    SharedPreferences sp = getSharedPreferences("MySettingPreferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("color","default");
                    editor.apply();

                } else if (rbWhite.isChecked()) {
                    SharedPreferences sp = getSharedPreferences("MySettingPreferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("color","white");
                    editor.apply();

                } else if (rbCreme.isChecked()) {
                    SharedPreferences sp = getSharedPreferences("MySettingPreferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("color","creme");
                    editor.apply();

                }
            }
        });
    }

    private void initMainActivity(){
        Button backToMain = findViewById(R.id.buttonReturnFromSettings);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));

            }
        });
    }

    public void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences("MySettingPreferences", Context.MODE_PRIVATE);
        String changeColor = sharedPref.getString("color", "name");

        if (changeColor.equals("default")) {
            layout.setBackgroundColor(0xFF2B2A2A);
            settingsTitle.setTextColor(0xFFFFFFFF);
            buttonReturnFromSettings.setBackgroundColor(0xFFFFFFFF);
            buttonReturnFromSettings.setTextColor(0x802B2A2A);
            radioButtonColors();
        } else if (changeColor.equals("white")) {
            layout.setBackgroundColor(0xFFFFFFFF);
            settingsTitle.setTextColor(0x802B2A2A);
            buttonReturnFromSettings.setBackgroundColor(0x802B2A2A);
            buttonReturnFromSettings.setTextColor(0xFFFFFFFF);
            radioButtonColors();
        } else {
            layout.setBackgroundColor(0xFFFFEACB);
            settingsTitle.setTextColor(0xFF4e350e);
            buttonReturnFromSettings.setBackgroundColor(0x804e350e);
            buttonReturnFromSettings.setTextColor(0x80FFEACB);
            radioButtonColors();
        }

    }

    private void radioButtonColors() {

        TextView textViewColorChoice;

        RadioButton rbuttonDefault;
        RadioButton rbuttonWhite;
        RadioButton rbuttonCreme;


        textViewColorChoice = findViewById(R.id.textViewColorChoice);


        rbuttonDefault = findViewById(R.id.rbuttonDefault);
        rbuttonWhite = findViewById(R.id.rbuttonWhite);
        rbuttonCreme = findViewById(R.id.rbuttonCreme);

        SharedPreferences sharedPref = getSharedPreferences("MySettingPreferences", Context.MODE_PRIVATE);
        String changeColor = sharedPref.getString("color", "name");

        if (changeColor.equals("default")) {

            textViewColorChoice.setTextColor(0xFFFFFFFF);
            rbuttonDefault.setTextColor(0xFFFFFFFF);
            rbuttonWhite.setTextColor(0xFFFFFFFF);
            rbuttonCreme.setTextColor(0xFFFFFFFF);
        } else if (changeColor.equals("white")) {
            textViewColorChoice.setTextColor(0x802B2A2A);
            rbuttonDefault.setTextColor(0x802B2A2A);
            rbuttonWhite.setTextColor(0x802B2A2A);
            rbuttonCreme.setTextColor(0x802B2A2A);
        } else {
            textViewColorChoice.setTextColor(0xFF4e350e);
            rbuttonDefault.setTextColor(0xFF4e350e);
            rbuttonWhite.setTextColor(0xFF4e350e);
            rbuttonCreme.setTextColor(0xFF4e350e);
        }


    }
}