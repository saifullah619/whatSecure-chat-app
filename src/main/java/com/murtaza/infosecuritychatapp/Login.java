package com.murtaza.infosecuritychatapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText field1, field2;
    Boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_login);

        DbHelper helper = new DbHelper(Login.this);
        SQLiteDatabase database = helper.getReadableDatabase();

        TextView regNow = findViewById(R.id.register);
        regNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        Button button = findViewById(R.id.loginBtn);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                field1 = findViewById(R.id.email);
                field2 = findViewById(R.id.password);
                String email = field1.getText().toString();
                String password = field2.getText().toString();

                if (!email.equals("") && !password.equals("")) {
                    Cursor cursor = database.rawQuery("SELECT name, email, password FROM USERS", new String[]{});

                    if (cursor != null)
                        cursor.moveToFirst();

                    do {
                        String value1 = null;
                        if (cursor != null) {
                            value1 = cursor.getString(0);
                        }
                        String value2 = null;
                        if (cursor != null) {
                            value2 = cursor.getString(1);
                        }
                        String value3 = null;
                        if (cursor != null) {
                            value3 = cursor.getString(2);
                        }

                        if (email.equals(value2) && password.equals(value3)) {
                            loggedIn = true;
                            CurrentUser currentUser = new CurrentUser();
                            currentUser.setUser(value1);
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        }
                    } while (cursor.moveToNext());

                    if (!loggedIn) {
                        Toast.makeText(Login.this, "Incorrect Email or Password! Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Login.this, "Please fill out all fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}