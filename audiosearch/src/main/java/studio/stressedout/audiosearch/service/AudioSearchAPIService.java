package studio.stressedout.audiosearch.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import studio.stressedout.audiosearch.core.response.SearchResponse;

/**
 * Created by martin on 8/4/17.
 */

public interface AudioSearchAPIService {

  @GET("search/shows/{query}")
  Observable<SearchResponse> search(@Path("query") String query, @Header("Authorization") String accessToken);
}
