package studio.stressedout.audiosearch.service;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import studio.stressedout.audiosearch.model.AuthResult;

/**
 * Created by martin on 8/4/17.
 */

public interface AudioSearchAuthService {

  @Headers("Content-Type: application/x-www-form-urlencoded")
  @FormUrlEncoded
  @POST("oauth/token")
  Observable<AuthResult> getAccessToken(@Field("grant_type") String grantType,
                                        @Header("Authorization") String authorizationSignature);
}
