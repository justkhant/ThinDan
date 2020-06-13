package com.example.thindan_android.ui.categories;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thindan_android.R;
import com.example.thindan_android.ui.categories.CenterZoomLayoutManager;
import com.example.thindan_android.ui.categories.CategoriesAdapter;
import com.example.thindan_android.ui.categories.CategoryCardModel;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment {

//    private RecyclerView recyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView categoriesRecyclerView;
    private CategoriesAdapter categoryCardAdapter;
    List<CategoryCardModel> categoryCardModels;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_categories);
//        setContentView(R.layout.fragment_categories);
//        recyclerView = (RecyclerView) findViewById(R.id.categories_recycler_view);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        recyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        // specify an adapter (see also next example)
//        mAdapter = new CategoriesAdapter(myDataset);
//        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_categories, container, false);

        addCategoryCards();
        categoriesRecyclerView = root.findViewById(R.id.categories_recycler);
        categoryCardAdapter = new CategoriesAdapter(categoryCardModels);
        RecyclerView.LayoutManager layoutManager = new CenterZoomLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoriesRecyclerView.setLayoutManager(layoutManager);
        categoriesRecyclerView.setAdapter(categoryCardAdapter);
        SnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(categoriesRecyclerView);
        // scroll to middle item
        categoriesRecyclerView.getLayoutManager().scrollToPosition(Integer.MAX_VALUE / 2);

        return root;
    }

    public void addCategoryCards() {
        categoryCardModels = new ArrayList<>();
        categoryCardModels.add(new CategoryCardModel(R.mipmap.temp_picture,
                "English",
                "SAT Eng, AP Eng, IGCSE Eng, Vocabulary, Reading, ...",
                "12345 Thindans"));
        categoryCardModels.add(new CategoryCardModel(R.mipmap.temp_picture,
                "Math",
                "SAT Math I, SAT Math II, Calculus, AP Stats, IB HL, ...",
                "12345 Thindans"));
        categoryCardModels.add(new CategoryCardModel(R.mipmap.temp_picture,
                "Physics",
                "SAT Physics II, AP Physics, IB Physics, ...",
                "12345 Thindans"));
        categoryCardModels.add(new CategoryCardModel(R.mipmap.temp_picture,
                "Chemistry",
                "SAT Chemistry II, AP Chemistry, IB Chemistry, ...",
                "12345 Thindans"));
        categoryCardModels.add(new CategoryCardModel(R.mipmap.temp_picture,
                "Mandarin",
                "SAT Mandarin II, AP Mandarin, IB Mandarin, ...",
                "12345 Thindans"));
    }

}
