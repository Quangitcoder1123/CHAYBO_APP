package in.project.Activelife.Exercise;

import in.project.Activelife.Exercise.Api.ApiEx;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private ApiEx myApi;
    private RetrofitClient() {
        Retrofit retrofit = new
                Retrofit.Builder().baseUrl(ApiEx.BASE_URL)

                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(ApiEx.class);
    }
    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }
    public ApiEx getMyApi() {
        return myApi;
    }

}
