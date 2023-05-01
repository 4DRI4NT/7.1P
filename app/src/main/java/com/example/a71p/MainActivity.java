package com.example.a71p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a71p.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    Button createAdvert, showAdverts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createAdvert = findViewById(R.id.newAdvertButton);
        showAdverts = findViewById(R.id.showAdvertsButton);

        createAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create and start newAdvertActivity
                Intent advertIntent = new Intent(MainActivity.this, NewAdvertActivity.class);
                startActivity(advertIntent);
            }
        });

        showAdverts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create and start lostFoundActivity
                Intent advertIntent = new Intent(MainActivity.this, LostFoundActivity.class);
                startActivity(advertIntent);
            }
        });
    }
}