package in.project.Activelife.Exercise.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import in.project.Activelife.Exercise.model.Exercise;
import in.project.Activelife.Exercise.view.DetailExActivity;
import in.project.Activelife.Exercise.view.ExerciseMoreActive;
import in.project.Activelife.R;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    private final ArrayList<Exercise> exerciseList;
    Context context;

    public ExerciseAdapter(ArrayList<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView categoryTextView;
        public TextView difficultyTextView;
        public TextView detailsTextView;
        public LinearLayout boad;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text1);
            categoryTextView = itemView.findViewById(R.id.text2);
            difficultyTextView  = itemView.findViewById(R.id.text3);
            boad=itemView.findViewById(R.id.boat_pose0);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exer, parent, false);
        context=parent.getContext();
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);
        holder.nameTextView.setText(("Name: " + exercise.getExercise_name()).toString());
        holder.categoryTextView.setText(("Category: " + exercise.getCategory()).toString());
        holder.difficultyTextView.setText(("Difficulty: "+exercise.getDifficulty()).toString());
        String difficulty = exercise.getDifficulty();
        if (difficulty.equals("Beginner")) {
            holder.difficultyTextView.setTextColor(context.getResources().getColor(R.color.Beginner));
        } else if (difficulty.equals("Intermediate")) {
            holder.difficultyTextView.setTextColor(context.getResources().getColor(R.color.Intermediate));
        } else if (difficulty.equals("Advanced")) {
            holder.difficultyTextView.setTextColor(context.getResources().getColor(R.color.Advanced));
        }
        holder.boad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailExActivity.class);
                intent.putExtra("id",exercise.getId());
                view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
