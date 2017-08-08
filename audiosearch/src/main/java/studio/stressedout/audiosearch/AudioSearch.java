package studio.stressedout.audiosearch;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.stressedout.audiosearch.core.AudioSearchAPI;
import studio.stressedout.audiosearch.service.AudioSearchAPIService;
import studio.stressedout.audiosearch.service.AudioSearchAuthService;
import studio.stressedout.audiosearch.util.AuthUtils;

/**
 * Created by martin on 8/6/17.
 */

public class AudioSearch extends  AudioSearchAPI{

  private final static String AUDIOSEARCH_BASE_AUTH_URL = "https://www.audiosear.ch/";
  private final static String AUDIOSEARCH_BASE_API_URL = "https://www.audiosear.ch/api/";

  private String mApplicationID;
  private String mSecret;

  private AudioSearch(){}

  public static final class Builder{
    private  AudioSearch instance;

    private Builder(){
      this.instance = new AudioSearch();
    };

    public static Builder create(){
      return new Builder();
    }

    public Builder applicationID(String ID){
      this.instance.mApplicationID = ID;
      return this;
    }

    public Builder secret(String secret){
      this.instance.mSecret = secret;
      return this;
    }

    public AudioSearch build() throws IOException {
      this.instance.prepare();
      return this.instance;
    }
  }

  private String AUTH_SIGNATURE;

  private AudioSearchAuthService audioSearchAuthService;
  private AudioSearchAPIService audioSearchAPIService;

  private void prepare() throws IOException {
    if ( ( this.mApplicationID == null ) || ( this.mSecret == null) ){
      throw new IOException("credentials not provided");
    }

    this.AUTH_SIGNATURE = "Basic " + AuthUtils.getSignature(this.mApplicationID, this.mSecret);

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client = new OkHttpClient.Builder()
      .addInterceptor(interceptor).build();
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

    //AUTH PART
    Retrofit auth = new Retrofit.Builder()
      .baseUrl(AUDIOSEARCH_BASE_AUTH_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .client(client)
      .build();

    audioSearchAuthService = auth.create(AudioSearchAuthService.class);


    //API PART
    Retrofit api = new Retrofit.Builder()
      .baseUrl(AUDIOSEARCH_BASE_API_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .client(client)
      .build();


    audioSearchAPIService = api.create(AudioSearchAPIService.class);
  }

  @Override
  protected AudioSearchAuthService getAuthService() {
    return  audioSearchAuthService;
  }

  @Override
  protected String getAuthSignature() {
    return this.AUTH_SIGNATURE;
  }

  @NonNull
  @Override
  protected AudioSearchAPIService audioSearchAPIService() {
    return audioSearchAPIService;
  }
}
