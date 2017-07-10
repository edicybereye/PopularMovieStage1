package com.cybereye_community.udacitymovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class detail_movie extends AppCompatActivity {


    @BindView(R.id.poster)
    ImageView mposter;

    @BindView(R.id.title)
    TextView mtitle;

    @BindView(R.id.date)
    TextView mdate;

    @BindView(R.id.rating)
    TextView mrating;

    @BindView(R.id.synop)
    TextView msynop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movie);
        ButterKnife.bind(this);
        Result data = new GsonBuilder().create().fromJson(this.getIntent().getStringExtra("movie"), Result.class);

        Glide.with(this)
                .load(GlideImg.BASE_IMG+ data.getPosterPath())
                //.diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(mposter);
        mtitle.setText(data.getTitle());
        mdate.setText("Release Date: \n"+data.getReleaseDate());
       msynop.setText("Overview:\n"+data.getOverview());
        mrating.setText("Vote Average: \n"+data.getVoteAverage());

    }
}
