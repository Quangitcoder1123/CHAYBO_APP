package in.project.Activelife.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.project.Activelife.Exercise.view.ExerciseMoreActive;
import in.project.Activelife.exer.SecondActivity;
import in.project.Activelife.exer.SecondActivity2;
import in.project.Activelife.R;


public class workoutFragment extends Fragment {
    Context context;
    TextView bt1, bt2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_workout, container, false);
        context = getContext();

        bt1 = view.findViewById(R.id.start1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SecondActivity.class);
                startActivity(intent);
            }
        });
        bt2 = view.findViewById(R.id.start2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SecondActivity2.class);
                startActivity(intent);
            }
        });


        view.findViewById(R.id.btmore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ExerciseMoreActive.class);
                startActivity(intent);
            }
        });


        return view;
    }



}