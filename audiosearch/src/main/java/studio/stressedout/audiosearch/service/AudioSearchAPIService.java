package studio.stressedout.audiosearch.service;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import studio.stressedout.audiosearch.model.SearchResult;

/**
 * Created by martin on 8/4/17.
 */

public interface AudioSearchAPIService {

  @GET("search/shows/{query}")
  Observable<List<SearchResult>> search(@Path("query") String query, @Header("Authorization") String accessToken);
}
