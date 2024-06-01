package com.mirea.kt.practical_2_10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button buttonStart = findViewById(R.id.buttonStart);
        Log.d("MainActivity", "Created");

        buttonStart.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AddActivity.class));
        });
    }
}