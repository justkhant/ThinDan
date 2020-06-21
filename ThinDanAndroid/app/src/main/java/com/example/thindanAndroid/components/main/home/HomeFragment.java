package com.example.thindanAndroid.components.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.thindanAndroid.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private ProfileCardAdapter profileCardAdapter;
    List<ProfileCardModel> profileCardModels;

    private RecyclerView popularSubjectsRecyclerView;
    private SubjectCardAdapter subjectCardAdapter;
    List<SubjectCardModel> subjectCardModels;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //set up profile cards
        addProfileCards();
        profileCardAdapter = new ProfileCardAdapter(profileCardModels, root.getContext());
        viewPager = root.findViewById(R.id.viewPager);
        viewPager.setAdapter(profileCardAdapter);
        viewPager.setPadding(60, 0, 60, 0);

        //set up subject recycler view
        addSubjectCards();
        popularSubjectsRecyclerView = root.findViewById(R.id.popular_subjects_recycler);
        subjectCardAdapter = new SubjectCardAdapter(subjectCardModels);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
        popularSubjectsRecyclerView.setLayoutManager(layoutManager);
        popularSubjectsRecyclerView.setAdapter(subjectCardAdapter);


        return root;
    }

    public void addProfileCards() {
        //set up profile cards view pager
        profileCardModels = new ArrayList<>();
        profileCardModels.add(new ProfileCardModel(R.drawable.com_facebook_profile_picture_blank_portrait,
                "English", "Sayar Kyaw Swar",
                "Greyhound disively hello coldly wonderfully marginally far..",
                Arrays.asList("SAT English", "IGCSE Eng", "AP English")));

        profileCardModels.add(new ProfileCardModel(R.drawable.com_facebook_profile_picture_blank_portrait,
                "Math", "Kaung Khant",
                "Greyhound disively hello coldly wonderfully marginally far..",
                Arrays.asList("SAT Math", "A level Math", "Olympian Math", "Calculus II")));

        profileCardModels.add(new ProfileCardModel(R.drawable.com_facebook_profile_picture_blank_portrait,
                "Chemistry", "Timmy Tseng",
                "Greyhound disively hello coldly wonderfully marginally far..",
                Arrays.asList("AP Chemistry", "IB Chemistry", "SAT Chemistry", "Orgo Chemistry")));

    }

    public void addSubjectCards() {
        subjectCardModels = new ArrayList<>();
        subjectCardModels.add(new SubjectCardModel(R.drawable.abc,
                "English",
                "SAT Eng, AP Eng, IGCSE Eng, Vocabulary, Reading, ..."));
        subjectCardModels.add(new SubjectCardModel(R.drawable.math,
                "Math",
                "SAT Math I, SAT Math II, Calculus, AP Stats, IB HL, ..."));
        subjectCardModels.add(new SubjectCardModel(R.drawable.physics,
                "Physics",
                "SAT Physics II, AP Physics, IB Physics, ..."));
        subjectCardModels.add(new SubjectCardModel(R.drawable.chemistry,
                "Chemistry",
                "SAT Chemistry II, AP Chemistry, IB Chemistry, ..."));
        subjectCardModels.add(new SubjectCardModel(R.drawable.mandarin,
                "Mandarin",
                "SAT Mandarin II, AP Mandarin, IB Mandarin, ..."));
    }

}
