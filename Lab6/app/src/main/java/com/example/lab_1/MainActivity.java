package com.example.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView email, pass;
    User[] users;
    Recipe[] recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email  = findViewById(R.id.editTextTextEmailAddress);
        pass  = findViewById(R.id.editTextTextPassword);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(view -> onClick());

        AppDataBase db = DBApp.getInstance().getDataBase();

        UserDAO userDAO = db.userDAO();
        if(userDAO.getAll().size() < 2){
            String[] emails = getResources().getStringArray(R.array.emails);
            String[] passwords = getResources().getStringArray(R.array.passwords);

            for(int i = 0; i < emails.length; i++) {
                User user = new User();
                user.email = emails[i];
                user.password = passwords[i];

                userDAO.insertAll(user);
            }
        }
        userDAO = db.userDAO();
        users = userDAO.getAll().toArray(new User[0]);

        RecipeDAO recipeDAO = db.recipeDAO();
        if(recipeDAO.getAll().size() < 2){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    URL url = null;
                    HttpURLConnection urlConnection = null;
                    try {
                        url = new URL("https://raw.githubusercontent.com/Lpirskaya/JsonLab/master/recipes2022.json");
                        urlConnection = (HttpURLConnection) url.openConnection();

                        BufferedReader input = new BufferedReader(new InputStreamReader(
                                urlConnection.getInputStream()), 8192);

                        StringBuilder buf = new StringBuilder();
                        String line;
                        while ((line = input.readLine()) != null) {
                            buf.append(line).append("\n");
                        }

                        line = buf.toString();
                        recipes = new Gson().fromJson(line, Recipe[].class);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } finally {
                        if (urlConnection != null)
                            urlConnection.disconnect();
                    }
                }
            });
            thread.start();

            while (recipes == null) ;

            recipeDAO.insertAll(recipes);
        }
    }
    private void onClick(){
        String emailStr = email.getText().toString();
        String passStr = pass.getText().toString();

        boolean ok = false;
        for(int i = 0; i < users.length; i++)
            if(users[i].email.equals(emailStr) && users[i].password.equals(passStr))
            {
                ok = true;
                break;
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