package com.example.thindanAndroid.components.main.categories;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thindanAndroid.R;


import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment {

    private RecyclerView categoriesRecyclerView;
    private CategoriesAdapter categoryCardAdapter;
    List<CategoryCardModel> categoryCardModels;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_categories, container, false);

        addCategoryCards();
        categoriesRecyclerView = root.findViewById(R.id.categories_recycler);
        categoryCardAdapter = new CategoriesAdapter(categoryCardModels);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(), 2, GridLayoutManager.VERTICAL, false);
        categoriesRecyclerView.setLayoutManager(gridLayoutManager);
        categoriesRecyclerView.setAdapter(categoryCardAdapter);

        return root;
    }

    public void addCategoryCards() {
        categoryCardModels = new ArrayList<>();
        categoryCardModels.add(new CategoryCardModel(R.drawable.abc,
                "English",
                "SAT Eng, AP Eng, IGCSE Eng, Vocabulary, Reading, ...",
                "12345 Thindans"));
        categoryCardModels.add(new CategoryCardModel(R.drawable.math,
                "Math",
                "SAT Math I, SAT Math II, Calculus, AP Stats, IB HL, ...",
                "12345 Thindans"));
        categoryCardModels.add(new CategoryCardModel(R.drawable.physics,
                "Physics",
                "SAT Physics II, AP Physics, IB Physics, ...",
                "12345 Thindans"));
        categoryCardModels.add(new CategoryCardModel(R.drawable.chemistry,
                "Chemistry",
                "SAT Chemistry II, AP Chemistry, IB Chemistry, ...",
                "12345 Thindans"));
        categoryCardModels.add(new CategoryCardModel(R.drawable.mandarin,
                "Mandarin",
                "SAT Mandarin II, AP Mandarin, IB Mandarin, ...",
                "12345 Thindans"));
        categoryCardModels.add(new CategoryCardModel(R.drawable.nezuko,
                "Burmese",
                "SAT Physics II, AP Physics, IB Physics, ...",
                "12345 Thindans"));
        categoryCardModels.add(new CategoryCardModel(R.drawable.nezuko,
                "History",
                "SAT Chemistry II, AP Chemistry, IB Chemistry, ...",
                "12345 Thindans"));
        categoryCardModels.add(new CategoryCardModel(R.drawable.nezuko,
                "Japanese",
                "SAT Mandarin II, AP Mandarin, IB Mandarin, ...",
                "12345 Thindans"));
        categoryCardModels.add(new CategoryCardModel(R.drawable.nezuko,
                "Computer Science",
                "SAT Physics II, AP Physics, IB Physics, ...",
                "12345 Thindans"));
        categoryCardModels.add(new CategoryCardModel(R.drawable.nezuko,
                "Piano",
                "SAT Chemistry II, AP Chemistry, IB Chemistry, ...",
                "12345 Thindans"));
        categoryCardModels.add(new CategoryCardModel(R.drawable.nezuko,
                "Korean",
                "SAT Mandarin II, AP Mandarin, IB Mandarin, ...",
                "12345 Thindans"));
    }


}
