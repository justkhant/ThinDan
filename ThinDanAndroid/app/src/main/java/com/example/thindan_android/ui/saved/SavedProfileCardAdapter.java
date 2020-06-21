package com.example.thindan_android.ui.saved;

        import android.animation.AnimatorSet;
        import android.animation.ObjectAnimator;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.thindan_android.R;
        import com.squareup.picasso.Picasso;

        import java.util.List;

        import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class SavedProfileCardAdapter extends RecyclerView.Adapter<com.example.thindan_android.ui.saved.SavedProfileCardAdapter.SavedView> {
    private List<SavedProfileCardModel> models;
    private static int CORNER_RADIUS = 8;

    long DURATION = 500;
    private boolean onAttach = true;

    public class SavedView extends RecyclerView.ViewHolder {
        ImageView picture;
        TextView subject;
        TextView name;


        // parameterised constructor for View Holder class
        // which takes the view as a parameter
        public SavedView(@NonNull View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.saved_profile_card_profilePic);
            subject = itemView.findViewById(R.id.saved_profile_card_subject);
            name = itemView.findViewById(R.id.saved_profile_card_name);

        }
    }

    //custom adapter class takes in a list of tags
    public SavedProfileCardAdapter(List<SavedProfileCardModel> models) {
        this.models = models;
    }

    // Override onCreateViewHolder which deals
    // with the inflation of the card layout
    // as an item for the RecyclerView.
    @NonNull
    @Override
    public com.example.thindan_android.ui.saved.SavedProfileCardAdapter.SavedView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate subject_tag_layout.xml
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_profile_card_layout, parent,false);

        return new com.example.thindan_android.ui.saved.SavedProfileCardAdapter.SavedView(itemView);
    }

    // Override onBindViewHolder which deals
    // with the setting of different data
    // and methods related to clicks on
    // particular items of the RecyclerView.
    @Override
    public void onBindViewHolder(@NonNull com.example.thindan_android.ui.saved.SavedProfileCardAdapter.SavedView holder, int position) {
        position = position % models.size();
        Picasso.get()
                .load(models.get(position).getImage())
                .fit()        // to centerCrop, you have to do either resize() or fit()
                .centerCrop() // to remove any possible white areas
                .transform(new RoundedCornersTransformation(CORNER_RADIUS, 0,
                        RoundedCornersTransformation.CornerType.TOP))
                .into(holder.picture);
        holder.subject.setText(models.get(position).getSubject());
        holder.name.setText(models.get(position).getName());
        //setAnimation(holder.itemView, position);

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

//    private void setAnimation(View itemView, int i) {
//        if(!onAttach){
//            i = -1;
//        }
//        boolean isNotFirstItem = i == -1;
//        i++;
//        itemView.setAlpha(0.f);
//        AnimatorSet animatorSet = new AnimatorSet();
//        ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "alpha", 0.f, 0.5f, 1.0f);
//        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
//        animator.setStartDelay(isNotFirstItem ? DURATION / 2 : (i * DURATION / 3));
//        animator.setDuration(500);
//        animatorSet.play(animator);
//        animator.start();
//    }
}