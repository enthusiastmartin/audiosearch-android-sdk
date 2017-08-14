package studio.stressedout.audiosearchsdk;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import studio.stressedout.audiosearch.AudioSearch;
import studio.stressedout.audiosearch.model.AudioSearchEpisode;
import studio.stressedout.audiosearch.model.AudioSearchShow;
import studio.stressedout.audiosearch.model.SearchResult;

/**
 * Created by martin on 8/6/17.
 */

public class MainActivity extends AppCompatActivity {

  private final static String TAG = MainActivity.class.getName();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    try {
      ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
      Bundle bundle = ai.metaData;
      String app_id = bundle.getString("studio.stressedout.test.APP_ID");
      String app_secret = bundle.getString("studio.stressedout.test.APP_SECRET");

      AudioSearch audioSearch = AudioSearch.Builder.create()
        .applicationID(app_id)
        .secret(app_secret)
        .httpClient(this.createCustomClient())
        .build();

      audioSearch.search("security")
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DefaultObserver<List<SearchResult>>() {
        @Override
        public void onNext(List<SearchResult> value) {
          Log.d(TAG, "Received " + Integer.toString(value.size()));
        }

        @Override
        public void onError(Throwable e) {
          Log.e(TAG, "error " + e.getLocalizedMessage());
        }

        @Override
        public void onComplete() {

        }
      });

      audioSearch.showById(2380)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DefaultObserver<AudioSearchShow>() {
          @Override
          public void onNext(AudioSearchShow value) {
            Log.d(TAG, "Got show " + value.title);
            Log.d(TAG, "Got show " + value.getShowThumb());
          }

          @Override
          public void onError(Throwable e) {
            Log.d(TAG, "Error retrieving show " + e.getLocalizedMessage());
          }

          @Override
          public void onComplete() {

          }
        });

      audioSearch.showEpisodes(3280)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DefaultObserver<List<AudioSearchEpisode>>() {
          @Override
          public void onNext(List<AudioSearchEpisode> value) {
            Log.d(TAG, "Received episodes " + Integer.toString(value.size()));

            for (AudioSearchEpisode episode : value){
              Log.d(TAG, episode.title);
              Log.d(TAG, episode.audio_files.get(0).mp3);
            }
          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onComplete() {

          }
        });
    } catch (PackageManager.NameNotFoundException e) {
      Log.e(TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
    } catch (NullPointerException e) {
      Log.e(TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
    } catch (IOException e){
      Log.e(TAG, "Credentials");
    }


  }


  private OkHttpClient createCustomClient(){
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client = new OkHttpClient.Builder()
      .addInterceptor(interceptor).build();
    return client;
    /*
    OkHttpClient.Builder client = new OkHttpClient.Builder();

    client.addInterceptor(new Interceptor() {
      @Override
      public Response intercept(final Chain chain) throws IOException {
        Request r = chain.request();
        Log.d("LOG", "INTER " + r.url().toString());
        Request request = chain.request().newBuilder()
          .addHeader("Authorization", "Bearer " + mAccessToken)
          .build();
        return chain.proceed(chain.request());
      }
    });
  */

  }

}
