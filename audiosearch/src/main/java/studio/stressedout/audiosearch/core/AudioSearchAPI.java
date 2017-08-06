package studio.stressedout.audiosearch.core;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
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
        return audioSearchAPIService().search(query, s);
      }
    });
  }
}
