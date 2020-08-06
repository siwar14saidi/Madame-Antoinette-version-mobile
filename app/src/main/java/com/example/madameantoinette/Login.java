package com.example.madameantoinette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.JsonObject;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Login extends AppCompatActivity {

    Button btnlogin;
    EditText email, password;
    TextView createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin = findViewById(R.id.btnLogin);
        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwoedLogin);
        createAccount = findViewById(R.id.createAccount);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailF = email.getText().toString();
                final String passwd = password.getText().toString();

                JsonObject json = new JsonObject();
                json.addProperty("email", emailF);
                json.addProperty("password", passwd);
                Ion.with(Login.this)
                        .load("http://10.0.2.2:3000/login")
                        .setJsonObjectBody(json)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                               // if ((e == null)) {
                                    if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                                        Toast.makeText(getApplicationContext(), "enter email address / enter password", Toast.LENGTH_SHORT).show();

                                    } else
                                        {

                                        if (result.get("success").getAsBoolean()) {
                                            Toast.makeText(getApplicationContext(), "valid email address / valid password    ", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Login.this, Accueil.class);
                                            startActivity(intent);

                                        } else {

                                            Toast.makeText(getApplicationContext(), "Invalid email address / Invalid password", Toast.LENGTH_SHORT).show();

                                        }


                                    }
                                }
                            //}

                        });
            }
        });


    }
}