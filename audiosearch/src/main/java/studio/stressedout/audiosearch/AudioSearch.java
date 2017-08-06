package studio.stressedout.audiosearch;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.stressedout.audiosearch.service.AudioSearchAPIService;
import studio.stressedout.audiosearch.service.AudioSearchAuthService;
import studio.stressedout.audiosearch.util.AuthUtils;

/**
 * Created by martin on 8/6/17.
 */

public class AudioSearch {

  private String mApplicationID;
  private String mAccessToken;
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

    public Builder accessToken(String token){
      this.instance.mAccessToken = token;
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
    if ( ( this.mApplicationID == null ) || ( this.mSecret == null) || ( this.mAccessToken == null )){
      throw new IOException("credentials not provided");
    }

    this.AUTH_SIGNATURE = "Basic " + AuthUtils.getSignature(this.mApplicationID, this.mSecret);

    //AUTH PART
    Retrofit auth = new Retrofit.Builder()
      .baseUrl("")
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build();

    audioSearchAuthService = auth.create(AudioSearchAuthService.class);

    //API PART
    Retrofit api = new Retrofit.Builder()
      .baseUrl("")
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build();

    audioSearchAPIService = api.create(AudioSearchAPIService.class);
  }
}
