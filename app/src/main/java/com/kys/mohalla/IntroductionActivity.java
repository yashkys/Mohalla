package com.kys.mohalla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntroductionActivity extends AppCompatActivity {

    Button login, register;
//    TextView skip;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        login = findViewById(R.id.btnlogin);
        register = findViewById(R.id.btnregister);
//        skip = findViewById(R.id.tvskip);

        login.setOnClickListener(view -> sendToLogin());

        register.setOnClickListener(view -> sendToRegister());
    }

    private void sendToRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void sendToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}