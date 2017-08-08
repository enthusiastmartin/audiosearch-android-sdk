package studio.stressedout.audiosearch.core;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import studio.stressedout.audiosearch.core.response.SearchResponse;
import studio.stressedout.audiosearch.model.AudioSearchCategory;
import studio.stressedout.audiosearch.model.AudioSearchEpisode;
import studio.stressedout.audiosearch.model.AudioSearchNetwork;
import studio.stressedout.audiosearch.model.AudioSearchShow;
import studio.stressedout.audiosearch.model.SearchResult;
import studio.stressedout.audiosearch.service.AudioSearchAPIService;

/**
 * Created by martin on 8/6/17.
 */

public abstract class AudioSearchAPI extends AudioSearchAuth{

  protected abstract @NonNull
  AudioSearchAPIService audioSearchAPIService();

  public Observable<List<SearchResult>> search(final String query){
    return accessToken().flatMap(new Function<String, ObservableSource<List<SearchResult>>>() {
      @Override
      public ObservableSource<List<SearchResult>> apply(String s) throws Exception {
        return audioSearchAPIService().search(query, AudioSearchAPI.this.getAuthHeader(s)).map(new Function<SearchResponse, List<SearchResult>>() {
          @Override
          public List<SearchResult> apply(SearchResponse searchResponse) throws Exception {
            return searchResponse.results;
          }
        });
      }
    });
  }

  public Observable<List<AudioSearchCategory>> categories(){
    return accessToken().flatMap(new Function<String, ObservableSource<List<AudioSearchCategory>>>() {
      @Override
      public ObservableSource<List<AudioSearchCategory>> apply(String s) throws Exception {
        return audioSearchAPIService().categories(AudioSearchAPI.this.getAuthHeader(s));
      }
    });
  }

  public Observable<List<String>> topics(){
    return accessToken().flatMap(new Function<String, ObservableSource<List<String>>>() {
      @Override
      public ObservableSource<List<String>> apply(String s) throws Exception {
        return audioSearchAPIService().topics(AudioSearchAPI.this.getAuthHeader(s));
      }
    });
  }

  public Observable<List<AudioSearchNetwork>> networks(){
    return accessToken().flatMap(new Function<String, ObservableSource<List<AudioSearchNetwork>>>() {
      @Override
      public ObservableSource<List<AudioSearchNetwork>> apply(String s) throws Exception {
        return audioSearchAPIService().networks(AudioSearchAPI.this.getAuthHeader(s));
      }
    });
  }

  public Observable<AudioSearchShow> showById(final int id){
    return accessToken().flatMap(new Function<String, ObservableSource<AudioSearchShow>>() {
      @Override
      public ObservableSource<AudioSearchShow> apply(String s) throws Exception {
        return audioSearchAPIService().showById(id, AudioSearchAPI.this.getAuthHeader(s));
      }
    });
  }

  public Observable<List<AudioSearchEpisode>> showEpisodes(final int showId){
    return accessToken().flatMap(new Function<String, ObservableSource<List<AudioSearchEpisode>>>() {
      @Override
      public ObservableSource<List<AudioSearchEpisode>> apply(String s) throws Exception {
        return audioSearchAPIService().showEpisodes(showId, AudioSearchAPI.this.getAuthHeader(s));
      }
    });
  }
}
