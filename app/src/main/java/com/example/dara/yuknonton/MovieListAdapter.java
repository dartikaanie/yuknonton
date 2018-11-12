package com.example.dara.yuknonton;

import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dara.yuknonton.model.MovieItem;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieHolder> {

    ArrayList<MovieItem> daftarFilm = new ArrayList<>();
    OnMovieItemClicked clickHandler;

    public void setDataFilm(ArrayList<MovieItem> films){
//        dataFilm.clear();
        daftarFilm = films;
        notifyDataSetChanged(); //memberitahu data nya berubah -> update
    }

    public void setClickHandler(OnMovieItemClicked clickHandler) {
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.daftar_film, viewGroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
        MovieItem movieItem = daftarFilm.get(i);
        movieHolder.title.setText(movieItem.getTitle());
        movieHolder.rating.setText(
                String.valueOf(movieItem.getVote_average())
        );

//        movieHolder.poster.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clickHandler.movieItemClicked();
//            }
//        });

        String url = "http://image.tmdb.org/t/p/w300" + movieItem.getPoster_path();
        Glide.with(movieHolder.itemView)
                .load(url)
                .into(movieHolder.poster);
    }


    @Override
    public int getItemCount() {
        if(daftarFilm != null){
            return daftarFilm.size();
        }
        return 0;
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView rating;
        ImageView poster;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            title=  itemView.findViewById(R.id.judul);
            rating = itemView.findViewById(R.id.rate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MovieItem movieItem= daftarFilm.get(getAdapterPosition());
                    clickHandler.movieItemClicked(movieItem);
                }
            });
        }
    }

    public interface OnMovieItemClicked{
        void movieItemClicked(MovieItem movieItem);

    }
}
