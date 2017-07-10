package com.cybereye_community.udacitymovie;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by edikurniawan on 7/10/17.
 */

public class network {
    public static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    public static Retrofit getRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit;
    }
}
