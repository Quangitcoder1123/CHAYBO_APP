package in.project.Activelife.Diet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import in.project.Activelife.apis.APIClient;
import in.project.Activelife.R;
import in.project.Activelife.adapter.RecipeAdater;
import in.project.Activelife.mainpage.MainActivity;
import in.project.Activelife.models_1.RootObjectModel;
import in.project.Activelife.response.SearchRecipes;
import in.project.Activelife.utils.APICredentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class proteins_page extends AppCompatActivity {
    private static final String TAG = "\t" + MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private ArrayList<RootObjectModel> recipes;
    private RecipeAdater adapter;
    private SearchView searchView;
    private  ProgressBar simpleProgressBar;
    TextView back;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proteins_page);
         simpleProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        recyclerView = findViewById(R.id.recyclerview);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchView = findViewById(R.id.searchView);
        searchView.setQueryHint("Type here to search");
        searchView.onActionViewCollapsed();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchRecipes(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });


    }

    private void searchRecipes(String query) {
        simpleProgressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APICredentials.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        APIClient apiClient = retrofit.create(APIClient.class);
        Call<SearchRecipes> searchRecipesCall = apiClient.searchRecipes(APICredentials.TYPE,query,APICredentials.APP_ID,APICredentials.API_KEY);

        searchRecipesCall.enqueue(new Callback<SearchRecipes>() {
            @Override
            public void onResponse(Call<SearchRecipes> call, Response<SearchRecipes> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    recipes = new ArrayList<>(Arrays.asList(response.body().getFoodRecipes()));
                    for (int i=0; i<recipes.size();i++){
                        RootObjectModel rootObjectModel = recipes.get(i);
                        Log.v("Tag"+TAG,"onResponse"+rootObjectModel.getRecipeModel().getLabel());
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(proteins_page.this));
                    recyclerView.setHasFixedSize(true);
                    adapter = new RecipeAdater(proteins_page.this,recipes);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<SearchRecipes> call, Throwable t) {
                Log.v("TAG"+TAG,"onFailure"+t.getMessage());
            }
        });
    }
}