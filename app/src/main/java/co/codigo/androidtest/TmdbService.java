package co.codigo.androidtest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbService {

    @GET("configuration")
    Call<ConfigurationResponse> getConfiguration(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<TopRatedMoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}
