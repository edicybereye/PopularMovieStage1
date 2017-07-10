package com.cybereye_community.udacitymovie;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Sample Constant
    private static final int COLUMN_COUNT = 2;

    private static final String POPULAR_ID = "popular";
    private static final String TOP_RATED_ID = "top-rated";



    @BindView(R.id.recyid)
    RecyclerView mrecyid;

    private List<Result> movieList = new ArrayList<>();
    private GridViewAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        movieAdapter = new GridViewAdapter(movieList);
        mrecyid.setAdapter(movieAdapter);
        mrecyid.setLayoutManager(new GridLayoutManager(MainActivity.this, getResources().getInteger(R.integer.COLUMN_COUNT)));

        new RequestMovie().execute("popular");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_item, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.popularid:
                new RequestMovie().execute(POPULAR_ID);
                break;
            case R.id.topratedid:
                new RequestMovie().execute(TOP_RATED_ID);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class RequestMovie extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... params) {
            String kategori  = params[0];
            if (kategori.equals("popular")){
                MyApiInterface apiService =
                        network.getRetrofit().create(MyApiInterface.class);
                Call<Movie> call = apiService.getPopularMovie();
                call.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        int statusCode = response.code();
                        Movie movie = response.body();
                        movieAdapter.setData(movie.getResults());
                        Log.d("","");
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        // Log error here since request failed
                        Log.d("","");
                    }
                });
                return  null;
            }else if(kategori.equals("top-rated")){
                MyApiInterface apiService =
                        network.getRetrofit().create(MyApiInterface.class);
                Call<Movie> call = apiService.getTopeRated();
                call.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        int statusCode = response.code();
                        Movie movie = response.body();
                        movieAdapter.setData(movie.getResults());
                        Log.d("","");
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        // Log error here since request failed
                        Log.d("","");
                    }
                });
                return  null;
            }
            return  null;
        }

    }
}
