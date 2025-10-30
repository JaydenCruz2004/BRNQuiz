package com.exam.brnquiz;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class IntroActivity extends AppCompatActivity {

    Button button;
    Switch themeSwitch;
    Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO}, 67);
        }

        random = new Random();


        //Initialize the button
        button = findViewById(R.id.button);
        themeSwitch = findViewById(R.id.themeSwitch);

        int n = random.nextInt(4);//0-3
        if(n == 0){
            button.setBackgroundColor(ContextCompat.getColor(this,R.color.red));
        }
        if(n == 1){
            button.setBackgroundColor(ContextCompat.getColor(this,R.color.blue));
        }
        if(n == 2){
            button.setBackgroundColor(ContextCompat.getColor(this,R.color.green));
        }
        if(n == 3){
            button.setBackgroundColor(ContextCompat.getColor(this,R.color.purple));
        }

        // Toggle dark/light mode
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        View.OnClickListener buttonListenr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        };

        button.setOnClickListener(buttonListenr);




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}