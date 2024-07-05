package in.project.Activelife.Exercise.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.project.Activelife.Exercise.Adapter.ExerciseAdapter;
import in.project.Activelife.Exercise.RetrofitClient;
import in.project.Activelife.Exercise.model.Exercise;
import in.project.Activelife.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciseMoreActive extends AppCompatActivity {
    private RecyclerView exerciseRecyclerView;
    private CheckBox begi,inte,adva ;
    private SearchView searchView;
    public  ArrayList<Exercise> list;
    public  String s="";
    TextView back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_more_active);
        exerciseRecyclerView =(RecyclerView) findViewById(R.id.rcc_exercise);
        exerciseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        begi=(CheckBox)findViewById(R.id.cb_beginner);
        inte=(CheckBox)findViewById(R.id.cb_intermediate);
        adva=(CheckBox)findViewById(R.id.cb_advanced);
        searchView=findViewById(R.id.searchView);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences("myinfPrefs01", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("exercise",true);
        edit.apply();
        list=new ArrayList<>();
        getSuperHeroes();


        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchby(s);

            }
        });


        searchView.setQueryHint("Type here to search");
        searchView.onActionViewCollapsed();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                s=query;
                searchby(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                s=newText;
                searchby(newText);

                return true;
            }
        });


    }
    private void searchby(String s){
        ArrayList<Exercise> listsearch=new ArrayList<Exercise>();
        Log.d(TAG, "searchby: "+list.size());
        listsearch.clear();
        for (int i=0;i<list.size();i++)
        {
            Exercise e=list.get(i);
            if(e.getDifficulty()!=null){
                if(s.trim().length()==0)
                {

                    if (begi.isChecked()) {
                        if(e.getDifficulty().equals("Beginner"))
                        {
                            listsearch.add(e);
                        }
                        Log.d(TAG, "searchby: Beginner");


                    }
                    if (inte.isChecked()) {
                        Log.d(TAG, "searchby: Intermediate");
                        if(e.getDifficulty().equals("Intermediate"))
                        {
                            listsearch.add(e);
                        }
                    }
                    if (adva.isChecked()) {

                        if(e.getDifficulty().equals("Advanced"))
                        {
                            Log.d(TAG, String.valueOf("searchby: Advanced "+ e.getDifficulty()));
                            listsearch.add(e);
                        }
                    }
                }
                else
                if((e.getExercise_name().toLowerCase().contains(s.toLowerCase()))||(e.getCategory().toLowerCase().contains(s.toLowerCase())))
                {
                    if (begi.isChecked()) {
                        if(e.getDifficulty().equals("Beginner"))
                        {
                            listsearch.add(e);
                        }
                    }
                    if (inte.isChecked()) {
                        if(e.getDifficulty().equals("Intermediate"))
                        {
                            listsearch.add(e);
                        }
                    }
                    if (adva.isChecked()) {
                        if(e.getDifficulty().equals("Advanced"))
                        {
                            listsearch.add(e);
                        }
                    }

                    if(!adva.isChecked() && !inte.isChecked() && !inte.isChecked()){
                        listsearch.add(e);
                    }
                }

            }


        }
        Log.d(TAG, "searchby: "+listsearch.size());
        exerciseRecyclerView.setAdapter(new ExerciseAdapter(listsearch));

    }

    private void getSuperHeroes() {
        Call<ArrayList<Exercise>> call =
                RetrofitClient.getInstance().getMyApi().getsuperHeroes();
        call.enqueue(new Callback<ArrayList<Exercise>>() {
            @Override
            public void onResponse(Call<ArrayList<Exercise>> call,
                                   Response<ArrayList<Exercise>> response) {
                ArrayList<Exercise> myheroList = response.body();
                list=myheroList;
                exerciseRecyclerView.setAdapter(new ExerciseAdapter(list));
            }
            @Override
            public void onFailure(Call<ArrayList<Exercise>> call,
                                  Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured",
                        Toast.LENGTH_LONG).show();
            }
        });

    }

}