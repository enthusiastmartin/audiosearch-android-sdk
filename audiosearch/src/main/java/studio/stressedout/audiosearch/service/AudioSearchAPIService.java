package studio.stressedout.audiosearch.service;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import studio.stressedout.audiosearch.core.response.SearchResponse;
import studio.stressedout.audiosearch.core.response.TrendingResponse;
import studio.stressedout.audiosearch.model.AudioSearchCategory;
import studio.stressedout.audiosearch.model.AudioSearchEpisode;
import studio.stressedout.audiosearch.model.AudioSearchNetwork;
import studio.stressedout.audiosearch.model.AudioSearchShow;

/**
 * Created by martin on 8/4/17.
 */

public interface AudioSearchAPIService {

  @GET("search/shows/{query}")
  Observable<SearchResponse> search(@Path("query") String query, @Query("page") int page, @Header("Authorization") String accessToken);

  @GET("categories")
  Observable<List<AudioSearchCategory>> categories(@Header("Authorization") String accessToken);

  @GET("topics")
  Observable<List<String>> topics(@Header("Authorization") String accessToken);

  @GET("networks")
  Observable<List<AudioSearchNetwork>> networks(@Header("Authorization") String accessToken);

  @GET("shows/{sid}")
  Observable<AudioSearchShow> showById(@Path("sid") int id, @Header("Authorization") String accessToken);

  @GET("shows/{sid}/episodes")
  Observable<List<AudioSearchEpisode>> showEpisodes(@Path("sid") int showId, @Header("Authorization") String accessToken);

  @GET("trending")
  Observable<List<TrendingResponse>> trending(@Header("Authorization") String accessToken);
}
