package co.codigo.androidtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TmdbService service;
    private String imageBaseUrl;
    private String imageFileSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(TmdbService.class);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Call<ConfigurationResponse> call = service.getConfiguration(Constants.TMDB_API_KEY);

        call.enqueue(new Callback<ConfigurationResponse>() {
            @Override
            public void onResponse(Call<ConfigurationResponse> call,
                                   Response<ConfigurationResponse> response) {
                if (response.isSuccessful()) {
                    imageBaseUrl = response.body().getImages().getBase_url();
                    imageFileSize = response.body().getImages().getLogo_sizes().get(0);

                    setupRecyclerView();
                } else {
                    Log.d("MainActivity", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ConfigurationResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupRecyclerView() {
        Call<TopRatedMoviesResponse> topRatedMoviesCall =
                service.getTopRatedMovies(Constants.TMDB_API_KEY);

        topRatedMoviesCall.enqueue(new Callback<TopRatedMoviesResponse>() {
            @Override
            public void onResponse(Call<TopRatedMoviesResponse> call,
                                   Response<TopRatedMoviesResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("MainActivity",
                            "Total number of results: " + response.body().getTotal_results());

                    TopRatedMoviesAdapter adapter = new TopRatedMoviesAdapter(
                            Glide.with(MainActivity.this),
                            response.body().getResults(),
                            imageBaseUrl,
                            imageFileSize,
                            R.color.titleRed,
                            R.color.overviewBlue);

                    recyclerView.setAdapter(adapter);

                } else {
                    Log.d("MainActivity", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TopRatedMoviesResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
