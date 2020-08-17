package com.example.thindanAndroid.components.main.categories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thindanAndroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryView> {
    private List<CategoryCardModel> models;
    private static int CORNER_RADIUS = 8;

    long DURATION = 500;
    private boolean onAttach = true;

    public class CategoryView extends RecyclerView.ViewHolder {
        ImageView picture;
        TextView subject;
        TextView subjectCategories;
        TextView count;

        // parameterised constructor for View Holder class
        // which takes the view as a parameter
        public CategoryView(@NonNull View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.subject_picture);
            subject = itemView.findViewById(R.id.category_title);
            subjectCategories = itemView.findViewById(R.id.sub_categories);
            count = itemView.findViewById(R.id.thindan_count);
        }
    }

    //custom adapter class takes in a list of tags
    public CategoriesAdapter(List<CategoryCardModel> models) {
        this.models = models;
    }

    // Override onCreateViewHolder which deals
    // with the inflation of the card layout
    // as an item for the RecyclerView.
    @NonNull
    @Override
    public CategoryView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_row, parent,false);

        return new CategoryView(itemView);
    }

    // Override onBindViewHolder which deals
    // with the setting of different data
    // and methods related to clicks on
    // particular items of the RecyclerView.
    @Override
    public void onBindViewHolder(@NonNull CategoryView holder, int position) {
        position = position % models.size();
        Picasso.get()
                .load(models.get(position).getImage())
                .fit()        // to centerCrop, you have to do either resize() or fit()
                .centerCrop() // to remove any possible white areas
                .transform(new RoundedCornersTransformation(CORNER_RADIUS, 0,
                        RoundedCornersTransformation.CornerType.TOP))
                .into(holder.picture);
        holder.subject.setText(models.get(position).getSubjectTitle());
        holder.subjectCategories.setText(models.get(position).getSubjectCategories());


    }

    @Override
    public int getItemCount(){
        return models.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                onAttach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }

        });

        super.onAttachedToRecyclerView(recyclerView);
    }
}
