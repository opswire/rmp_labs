package com.example.lab_1.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_1.AppDataBase;
import com.example.lab_1.DBApp;
import com.example.lab_1.R;
import com.example.lab_1.Recipe;
import com.example.lab_1.RecipeDAO;
import com.example.lab_1.databinding.FragmentDashboardBinding;
import com.example.lab_1.ui.Adapter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView mRecyclerView = (RecyclerView) root.findViewById(R.id.recycle);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        AppDataBase db = DBApp.getInstance().getDataBase();
        RecipeDAO recipeDAO = db.recipeDAO();

        Recipe[] recipes = recipeDAO.getAll().toArray(new Recipe[0]);

        Adapter mAdapter = new Adapter(recipes);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setClickListener(new Adapter.ClickListener(){
            public void onItemClick(int position, View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("pos", position);

                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main2);
                navController.navigate(R.id.navigation_home, bundle);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}