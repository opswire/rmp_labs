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

import com.example.lab_1.R;
import com.example.lab_1.Recipe;
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
    private  RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Recipe[] recipes = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycle);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

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
        while (recipes == null);

        mAdapter = new Adapter(recipes);
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