package com.example.thindanAndroid.components.main.saved;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thindanAndroid.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SavedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SavedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView savedRecyclerView;
    private SavedProfileCardAdapter savedProfileCardAdapter;
    List<SavedProfileCardModel> savedProfileCardModels;

    public SavedFragment() {
        // Required empty public constructor
    }

    public static SavedFragment newInstance(String param1, String param2) {
        SavedFragment fragment = new SavedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_saved, container, false);

        addProfileCards();
        savedRecyclerView = root.findViewById(R.id.saved_recycler);
        savedProfileCardAdapter = new SavedProfileCardAdapter(savedProfileCardModels);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false);
        savedRecyclerView.setLayoutManager(layoutManager);
        savedRecyclerView.setAdapter(savedProfileCardAdapter);

        return root;
    }

    public void addProfileCards() {
        //set up profile cards view pager
        savedProfileCardModels = new ArrayList<>();
        savedProfileCardModels.add(new com.example.thindanAndroid.components.main.saved.SavedProfileCardModel(R.drawable.com_facebook_profile_picture_blank_portrait,
                "English", "Sayar Kyaw Swar",
                "Greyhound disively hello coldly wonderfully marginally far..",
                Arrays.asList("SAT English", "IGCSE Eng", "AP English")));

        savedProfileCardModels.add(new com.example.thindanAndroid.components.main.saved.SavedProfileCardModel(R.drawable.com_facebook_profile_picture_blank_portrait,
                "Math", "Kaung Khant",
                "Greyhound disively hello coldly wonderfully marginally far..",
                Arrays.asList("SAT Math", "A level Math", "Olympian Math", "Calculus II")));

        savedProfileCardModels.add(new SavedProfileCardModel(R.drawable.com_facebook_profile_picture_blank_portrait,
                "Chemistry", "Timmy Tseng",
                "Greyhound disively hello coldly wonderfully marginally far..",
                Arrays.asList("AP Chemistry", "IB Chemistry", "SAT Chemistry", "Orgo Chemistry")));

    }
}
