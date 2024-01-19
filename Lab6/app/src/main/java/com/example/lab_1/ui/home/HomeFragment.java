package com.example.lab_1.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.InputEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab_1.AppDataBase;
import com.example.lab_1.DBApp;
import com.example.lab_1.R;
import com.example.lab_1.Recipe;
import com.example.lab_1.RecipeDAO;
import com.example.lab_1.databinding.FragmentHomeBinding;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Recipe[] recipes = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        AppDataBase db = DBApp.getInstance().getDataBase();
        RecipeDAO recipeDAO = db.recipeDAO();

        Recipe[] recipes = recipeDAO.getAll().toArray(new Recipe[0]);

        View root = binding.getRoot();
        if (getArguments() != null) {
            Log.d("home", "" + getArguments().getInt("pos", 0));
            int pos = getArguments().getInt("pos", 0);

            binding.recipeName.setText(recipes[pos].name);
            binding.recipeMainText.setText(
                    "Калорийность: " + recipes[pos].calorie + "\n\n"
                            + "Время приготовления: " + recipes[pos].time +"\n\n"
                            + "Ингредиенты:\n" + recipes[pos].ingredients +"\n\n"
                            + "Уровень сложности: " + recipes[pos].difficulty);
        } else {
            binding.recipeName.setText("None");
            binding.recipeMainText.setText("None");
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}