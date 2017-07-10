package com.cybereye_community.udacitymovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by edikurniawan on 7/10/17.
 */

public interface MyApiInterface {

    final String DB_API_KEY = "2c4a98f399f0a24f8e92247dacbbe32b";
    @GET("popular?api_key="+DB_API_KEY)
    Call<Movie> getPopularMovie();

    @GET("top_rated?api_key="+DB_API_KEY)
    Call<Movie> getTopeRated();


}
