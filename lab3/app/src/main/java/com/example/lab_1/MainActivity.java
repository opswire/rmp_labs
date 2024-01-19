package com.example.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView email, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email  = findViewById(R.id.editTextTextEmailAddress);
        pass  = findViewById(R.id.editTextTextPassword);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(view -> onClick());
    }
    private void onClick(){
        String emailStr = email.getText().toString();
        String passStr = pass.getText().toString();
    boolean ok = false;
        String[] emails = getResources().getStringArray(R.array.emails);
        String[] passwords = getResources().getStringArray(R.array.passwords);

        for(int i = 0; i < emails.length; i++)
            if(emails[i].equals(emailStr) && passwords[i].equals(passStr))
            {
                ok = true;
            }
        if(ok){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }
        else {
            email.setTextColor(getColor(R.color.red));
            pass.setTextColor(getColor(R.color.red));

        }
    }

}