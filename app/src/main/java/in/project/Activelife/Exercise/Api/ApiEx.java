package in.project.Activelife.Exercise.Api;



import java.util.ArrayList;
import java.util.List;

import in.project.Activelife.Exercise.model.Exercise;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiEx {
    String BASE_URL = "https://musclewiki.p.rapidapi.com/";
    @Headers({"X-RapidAPI-Host: musclewiki.p.rapidapi.com",
            "X-RapidAPI-Key: 65f611b658msh772ba27afe3e92dp1b7647jsn4ba3e89240df",
            "content-type: application/octet-stream"})
    @GET("exercises")
    Call<ArrayList<Exercise>> getsuperHeroes();


    @Headers({"X-RapidAPI-Host: musclewiki.p.rapidapi.com",
            "X-RapidAPI-Key: 65f611b658msh772ba27afe3e92dp1b7647jsn4ba3e89240df",
            "content-type: application/octet-stream"})
    @GET("exercises/{id}")
    Call<Exercise> getbyId(@Path("id") int id);






}
