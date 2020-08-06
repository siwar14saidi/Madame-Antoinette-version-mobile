package com.example.madameantoinette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Register extends AppCompatActivity {
    EditText  email,  password, repeatpass;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.emailRegister);
        password = findViewById(R.id.passwordRegister);
        repeatpass = findViewById(R.id.repeatPass);
        btnRegister = findViewById(R.id.registerBtn);
btnRegister.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        final String emailF = email.getText().toString();
        final String passwd = password.getText().toString();
        JsonObject json = new JsonObject();
        json.addProperty("email", emailF);
        json.addProperty("password", passwd);


        Ion.with(Register.this)
                .load("http://192.168.1.5:3000/register")
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "enter les données", Toast.LENGTH_SHORT).show();
                        } else {
                            if (email.getText().toString().trim().matches(emailF) || password.getText().toString().trim().matches(passwd)) {
                                Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
    }
});
    }
}
