package com.example.thindan_android.ui.saved;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.thindan_android.R;
import com.example.thindan_android.ui.home.SubjectTagAdapter;

import java.util.List;

public class SavedProfileCardAdapter extends PagerAdapter {
    private List<SavedProfileCardModel> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public SavedProfileCardAdapter(List<SavedProfileCardModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.saved_profile_card_layout, container, false);
        ImageView profilePic = view.findViewById(R.id.saved_profile_card_profilePic);
        TextView subject = view.findViewById(R.id.saved_profile_card_subject);
        TextView name = view.findViewById(R.id.saved_profile_card_name);
        TextView description = view.findViewById(R.id.saved_profile_card_description);

        RecyclerView tags = view.findViewById(R.id.saved_profile_card_tags);
        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(context);

        // Set LayoutManager on Recycler View
        tags.setLayoutManager(recyclerViewLayoutManager);

        // calling constructor of adapter
        // with tag list as a parameter
        SubjectTagAdapter adapter = new SubjectTagAdapter(models.get(position).getTags());

        // Set Horizontal Layout Manager
        // for Recycler view
        LinearLayoutManager horizontalLayout = new LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false);

        tags.setLayoutManager(horizontalLayout);

        // Set adapter on recycler view
        tags.setAdapter(adapter);

        profilePic.setImageResource(models.get(position).getImage());
        subject.setText(models.get(position).getSubject());
        name.setText(models.get(position).getName());
        description.setText(models.get(position).getDescription());

        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
