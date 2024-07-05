package in.project.Activelife.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.project.Activelife.Diet.proteins_page;
import in.project.Activelife.R;


public class mealFragment extends Fragment {
    Context context;
TextView proteins;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_meal, container, false);
        context = view.getContext();

        proteins = view.findViewById(R.id.proteins);
        proteins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), proteins_page.class);
                startActivity(intent);
            }
        });


        return view;
    }



    private void Click(View view){

    }


}