package com.example.dara.yuknonton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dara.yuknonton.model.MovieItem;
import com.example.dara.yuknonton.model.MovieList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.OnMovieItemClicked{

//    private TextView textView;

    private static final String TAG = "MainActivity";
    ArrayList<MovieItem> daftarFilm = new ArrayList<>();
    RecyclerView rvMovieList;
    MovieListAdapter movieListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        textView = (TextView) findViewById(R.id.text_result);

        setContentView(R.layout.activity_main);

//        loadDummyData();

        Log.d(TAG, String.valueOf(daftarFilm.size()));

        movieListAdapter = new MovieListAdapter();
        movieListAdapter.setDataFilm(daftarFilm);
        movieListAdapter.setClickHandler(this);

        rvMovieList = findViewById(R.id.rv_daftar);
        rvMovieList.setAdapter(movieListAdapter);
        rvMovieList.setLayoutManager(new LinearLayoutManager(this));


        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        int mode = preferences.getInt("status_movies",1);
        if( mode == 1){
            getNowPlayingMovies();
        }else{
            getUpcomingMovies();;
        }

   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Jika tombol refresh diklik
        switch (item.getItemId()){
         case   R.id.menu_refresh :
                Toast.makeText(this, "Refreshing data", Toast.LENGTH_SHORT).show();
             SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
             int mode = preferences.getInt("status_movies",1);
             if( mode == 1){
                 getNowPlayingMovies();
             }else{
                 getUpcomingMovies();;
             }

                break;


         case  R.id.now_playing :
             Toast.makeText(this, "Now Playing data", Toast.LENGTH_SHORT).show();
             getNowPlayingMovies();
             break;

         case R.id.upcoming :
             Toast.makeText(this, "Up Coming Movies", Toast.LENGTH_SHORT).show();
             getUpcomingMovies();
             break;
        }
        if (item.getItemId() == R.id.menu_refresh) {


        }
        return super.onOptionsItemSelected(item);
    }


    //method mengambil data
    private void getNowPlayingMovies()  {
//        GetNowPlayingTask task = new GetNowPlayingTask();
//        task.execute();ata

//        loadDummyData();
        String API_BASE_URL = "https://api.themoviedb.org";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TmdbClient client = retrofit.create(TmdbClient.class);

        Call<MovieList> call = client.getNowPlayingMovies("4c180b85240811f5521423090f06d6cc");
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                Toast.makeText(MainActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                MovieList movieList =response.body();
                List<MovieItem> listMovieItem = movieList.results;
                movieListAdapter.setDataFilm(new ArrayList<MovieItem>(listMovieItem));

                getSupportActionBar().setTitle("Now Paying");
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });

        SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("status_movies",1);
        editor.commit();

    }

    private void getUpcomingMovies()  {

        String API_BASE_URL = "https://api.themoviedb.org";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TmdbClient client = retrofit.create(TmdbClient.class);

        Call<MovieList> call = client.getUpcomingMovies("4c180b85240811f5521423090f06d6cc");
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                Toast.makeText(MainActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                MovieList movieList =response.body();
                List<MovieItem> listMovieItem = movieList.results;
                movieListAdapter.setDataFilm(new ArrayList<MovieItem>(listMovieItem));
                getSupportActionBar().setTitle("Up Coming");

            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });

        SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("status_movies",2);
        editor.commit();

    }




    @Override
    public void movieItemClicked(MovieItem movieItem) {
        Toast.makeText(
                this,
                "Item yang diklik adalah : " + movieItem.getTitle(),
                Toast.LENGTH_SHORT).show();
        Intent detailMovieIntent = new Intent(this, DetailActivity.class);
        detailMovieIntent.putExtra("key_movie_parcelable", movieItem);
        startActivity(detailMovieIntent);
    }


}


//    private void loadDummyData(){
//        Toast.makeText(this, "get data", Toast.LENGTH_LONG).show();
//        daftarFilm.add(new MovieItem(
//                335983,
//                "Venom",
//                "When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego Venom to save his life.",
//                6.6,
//                "2018-10-03",
//                "/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"
//                ));
//        daftarFilm.add(new MovieItem(
//                424139,
//                "Halloween",
//                "Laurie Strode comes to her final confrontation with Michael Myers, the masked figure who has haunted her since she narrowly escaped his killing spree on Halloween night four decades ago.",
//                6.7,
//                "2018-10-18",
//                "/bXs0zkv2iGVViZEy78teg2ycDBm.jpg"
//                ));
//        daftarFilm.add(new MovieItem(
//                260513,
//                "Incredibles 2",
//                "Elastigirl springs into action to save the day, while Mr. Incredible faces his greatest challenge yet – taking care of the problems of his three children.",
//                7.6,
//                "2018-06-14",
//                "/x1txcDXkcM65gl7w20PwYSxAYah.jpg"
//                ));
//        daftarFilm.add(new MovieItem(
//                347375,
//                "Mile 22",
//                "A CIA field officer and an Indonesian police officer are forced to work together in confronting political corruption. An informant must be moved twenty-two miles to safety.",
//                5.8,
//                "2018-08-16",
//                "/ptSrT1JwZFWGhjSpYUtJaasQrh.jpg"
//                ));
//        daftarFilm.add(new MovieItem(
//                439079,
//                "The Nun",
//                "When a young nun at a cloistered abbey in Romania takes her own life, a priest with a haunted past and a novitiate on the threshold of her final vows are sent by the Vatican to investigate. Together they uncover the order’s unholy secret. Risking not only their lives but their faith and their very souls, they confront a malevolent force in the form of the same demonic nun that first terrorized audiences in “The Conjuring 2,” as the abbey becomes a horrific battleground between the living and the damned",
//                5.8,
//                "2018-09-05",
//                "/sFC1ElvoKGdHJIWRpNB3xWJ9lJA.jpg"
//                ));
////        return daftarfilm;
//    }



//    class GetNowPlayingTask extends AsyncTask<Void, Void, String>{
//        TextView text = findViewById(R.id.text_result);
//        //UI thread
//
//        //bg threads
//        @Override
//        protected String doInBackground(Void... voids) {
//            String webUrl ="https://api.themoviedb.org/3/movie/now_playing?api_key=4c180b85240811f5521423090f06d6cc";
//            HttpURLConnection urlConnection; //objek utk koneksi ke internet
//            try {
//                URL url = new URL(webUrl);
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("GET");
//                urlConnection.connect();
//
//                //ambil data dari server
//                InputStream inputStream = urlConnection.getInputStream();
//                if(inputStream==null){
//                    return null; //keluar
//                }
//
//                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//
//                //konvert data dalam bentuk string
//                StringBuffer buffer = new StringBuffer();
//                String line;
//                while((line= reader.readLine())!= null){
//                    buffer.append(line+"\n");
//                }
//
//                if (buffer.length() == 0) {
//                    return null;
//                }
//
//                String result = buffer.toString();
//                Log.d("Tes di doIn", result);
//                return result;
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//
//            } catch (ProtocolException e) {
//                e.printStackTrace();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            s = (s == null) ? "THERE WAS AN ERROR" : s;
//
//            text.setText(s);
//            Log.d("tes di onpost ex", s);
//            super.onPostExecute(s);
//        }
//    }

