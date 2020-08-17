package com.example.thindanAndroid.components.main.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thindanAndroid.R;

import java.util.List;

public class SubjectTagAdapter extends RecyclerView.Adapter<SubjectTagAdapter.MyView> {

    private List<String> tags;

    public class MyView extends RecyclerView.ViewHolder {
        TextView tagContent;

        // parameterised constructor for View Holder class
        // which takes the view as a parameter
        public MyView(@NonNull View itemView) {
            super(itemView);
            tagContent = itemView.findViewById(R.id.tag_content);
        }
    }

    //custom adapter class takes in a list of tags
    public SubjectTagAdapter(List<String> tags) {
        this.tags = tags;
    }

    // Override onCreateViewHolder which deals
    // with the inflation of the card layout
    // as an item for the RecyclerView.
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate subject_tag_layout.xml
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_tag_layout, parent,false);

        return new MyView(itemView);
    }

    // Override onBindViewHolder which deals
    // with the setting of different data
    // and methods related to clicks on
    // particular items of the RecyclerView.
    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.tagContent.setText(tags.get(position));
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }
}
