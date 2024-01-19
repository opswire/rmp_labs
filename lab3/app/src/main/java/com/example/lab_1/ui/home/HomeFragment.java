package com.example.lab_1.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab_1.R;
import com.example.lab_1.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        if(getArguments() != null) {
            Log.d("home", "" + getArguments().getInt("pos", 0));
            int pos = getArguments().getInt("pos", 0);

            String[] names = getResources().getStringArray(R.array.recipes_name);
            String[] texts = getResources().getStringArray(R.array.recipes_text);

            binding.recipeName.setText(names[pos]);
            binding.recipeMainText.setText(texts[pos]);
        }
        else{
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