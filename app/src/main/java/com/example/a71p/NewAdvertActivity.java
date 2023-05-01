package com.example.a71p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a71p.data.DatabaseHelper;
import com.example.a71p.model.Advert;

import java.util.ArrayList;
import java.util.List;

public class NewAdvertActivity extends AppCompatActivity {

    DatabaseHelper db;

    CheckBox lostCheck, foundCheck;
    EditText nameText, phoneText, descriptionText, dateText, locationText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_advert);

        db = new DatabaseHelper(this);

        lostCheck = findViewById(R.id.lostCheckBox);
        foundCheck = findViewById(R.id.foundCheckBox);
        nameText = findViewById(R.id.nameEditText);
        phoneText = findViewById(R.id.phoneEditText);
        descriptionText = findViewById(R.id.descriptionEditText);
        dateText = findViewById(R.id.dateEditText);
        locationText = findViewById(R.id.locationEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check for identical post
                boolean duplicateCheck = db.advertDupCheck(nameText.getText().toString(), phoneText.getText().toString(), descriptionText.getText().toString(), dateText.getText().toString(), locationText.getText().toString());

                //catch invalid responses
                if (lostCheck.isChecked() == foundCheck.isChecked()) {
                    Toast.makeText(NewAdvertActivity.this, "Invalid post type", Toast.LENGTH_LONG).show();
                } else if ((TextUtils.isEmpty(nameText.getText())) | (TextUtils.isEmpty(phoneText.getText())) | (TextUtils.isEmpty(descriptionText.getText())) | (TextUtils.isEmpty(dateText.getText())) | (TextUtils.isEmpty(locationText.getText()))) {
                    Toast.makeText(NewAdvertActivity.this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
                } else if (duplicateCheck) {
                    Toast.makeText(NewAdvertActivity.this, "Duplicate post", Toast.LENGTH_LONG).show();
                } else {
                    //define advert elements
                    String name, postType, phone, description, date, location;

                    //get advert element values
                    name = nameText.getText().toString();
                    if (lostCheck.isChecked()) {
                        postType = "Lost";
                    }
                    else {
                        postType = "Found";
                    }
                    phone = phoneText.getText().toString();
                    description = descriptionText.getText().toString();
                    date = dateText.getText().toString();
                    location = locationText.getText().toString();

                    //combine to advert and add to database
                    long result = db.insertAdvert(new Advert(name, postType, phone, description, date, location));

                    //confirm result
                    if (result > 0) {
                        Toast.makeText(NewAdvertActivity.this, "Post successful", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(NewAdvertActivity.this, "Post unsuccessful", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}