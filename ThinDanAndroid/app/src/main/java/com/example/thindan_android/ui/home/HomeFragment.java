package com.example.thindan_android.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.thindan_android.R;
import com.example.thindan_android.utils.ProfileCardAdapter;
import com.example.thindan_android.utils.ProfileCardModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ViewPager viewPager;
    private ProfileCardAdapter adapter;
    List<ProfileCardModel> models;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        models = new ArrayList<>();
        models.add(new ProfileCardModel(R.drawable.com_facebook_profile_picture_blank_portrait,
                "English", "Sayar Kyaw Swar",
                "Greyhound disively hello coldly wonderfully marginally far.." ));
        models.add(new ProfileCardModel(R.drawable.com_facebook_profile_picture_blank_portrait,
                "Math", "Kaung Khant",
                "Greyhound disively hello coldly wonderfully marginally far.." ));
        models.add(new ProfileCardModel(R.drawable.com_facebook_profile_picture_blank_portrait,
                "Chemistry", "Timmy Tseng",
                "Greyhound disively hello coldly wonderfully marginally far.." ));

        adapter = new ProfileCardAdapter(models, root.getContext());

        viewPager = root.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(60, 0, 60, 0);

        return root;
    }

}
