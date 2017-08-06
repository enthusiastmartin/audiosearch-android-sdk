package studio.stressedout.audiosearchsdk;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
      String app_id = bundle.getString("test_app_id");
    } catch (PackageManager.NameNotFoundException e) {
      Log.e(TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
    } catch (NullPointerException e) {
      Log.e(TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
    }
  }

}
