package com.murtaza.infosecuritychatapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText field1, field2, field3, field4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_register);

        field1 = findViewById(R.id.name);
        field2 = findViewById(R.id.email);
        field3 = findViewById(R.id.password);
        field4 = findViewById(R.id.confirmpassword);


        DbHelper helper = new DbHelper(Register.this);
        SQLiteDatabase database = helper.getWritableDatabase();

        TextView login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        Button button = findViewById(R.id.signupBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = field1.getText().toString();
                String email = field2.getText().toString();
                String password = field3.getText().toString();
                String confirmPassword = field4.getText().toString();

                if (!email.equals("") && !password.equals("") && !confirmPassword.equals("")) {
                    if (password.equals(confirmPassword)) {
                        Boolean inserted = helper.insertData(name, email, password, database);
                        if (inserted) {
                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Register.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(Register.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Register.this, "Please fill out all fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}