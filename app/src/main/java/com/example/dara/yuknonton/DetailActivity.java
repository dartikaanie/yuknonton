package com.example.dara.yuknonton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dara.yuknonton.model.MovieItem;

public class DetailActivity extends AppCompatActivity {

    TextView textReleaseDate;
    TextView textTitle;
    TextView textRating;
    TextView textOverview;
    ImageView poster ;
    MovieItem movieItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textReleaseDate = findViewById(R.id.text_release_date);
        textOverview = findViewById(R.id.text_overview);
        textRating = findViewById(R.id.text_rating);
        textTitle = findViewById(R.id.title);
        poster  = findViewById(R.id.image_poster);

        Intent detailIntent = getIntent();
        if(null != detailIntent) {
            movieItem = detailIntent.getParcelableExtra("key_movie_parcelable");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(movieItem != null) {
            getSupportActionBar().setTitle(movieItem.getTitle());
            textRating.setText(String.valueOf(movieItem.getVote_average()));
            textReleaseDate.setText(movieItem.getRelease_date());
            textOverview.setText(movieItem.getOverview());
            textTitle.setText(movieItem.getTitle());
            String url = "http://image.tmdb.org/t/p/w300" + movieItem.getPoster_path();
//            Toast.makeText(
//                    this,
//                    url,
//                    Toast.LENGTH_SHORT).show();
            Glide.with(this).load(url).into(poster);
        }
    }
}
