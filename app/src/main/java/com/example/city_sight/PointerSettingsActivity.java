package com.example.city_sight;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PointerSettingsActivity extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    SharedPreferences sets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointer_settings);
        sets = getSharedPreferences("storage", Context.MODE_PRIVATE);

        ImageButton but1 = (ImageButton) findViewById(R.id.imageButtonChangePointerForm1);
        but1.setOnClickListener(v -> writePointerSettingsToSets(R.drawable.locationpointer_8377411));

        ImageButton but2 = (ImageButton) findViewById(R.id.imageButtonChangePointerForm2);
        but2.setOnClickListener(v -> writePointerSettingsToSets(R.drawable.pointer2));


        ImageButton but3 = (ImageButton) findViewById(R.id.imageButtonChangePointerForm3);
        but3.setOnClickListener(v -> writePointerSettingsToSets(R.drawable.pointer3));

    }

    private void writePointerSettingsToSets(int pointerName) {
        SharedPreferences.Editor editor = sets.edit();
        editor.remove("form");
        editor.apply();
        editor.putInt("form", pointerName);
        editor.apply();
    }

}
