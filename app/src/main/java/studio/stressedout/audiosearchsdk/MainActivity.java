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
import studio.stressedout.audiosearch.AudioSearch;
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

      AudioSearch audioSearch = AudioSearch.Builder.create().applicationID(app_id).secret(app_secret).build();


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


    } catch (PackageManager.NameNotFoundException e) {
      Log.e(TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
    } catch (NullPointerException e) {
      Log.e(TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
    } catch (IOException e){
      Log.e(TAG, "Credentials");
    }


  }

}
