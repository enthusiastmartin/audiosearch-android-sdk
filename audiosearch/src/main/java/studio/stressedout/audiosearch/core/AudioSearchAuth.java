package studio.stressedout.audiosearch.core;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import studio.stressedout.audiosearch.model.AuthResult;
import studio.stressedout.audiosearch.service.AudioSearchAuthService;

/**
 * Created by martin on 8/6/17.
 */

abstract class AudioSearchAuth {
  private static final String GRANT_TYPE =  "client_credentials";

  private String  mAccessToken = null;

  protected abstract AudioSearchAuthService getAuthService();
  protected abstract String getAuthSignature();

  Observable<String> accessToken(){
    if ( mAccessToken != null ){
      return Observable.just(mAccessToken);
    }else {
      return getAuthService().getAccessToken(GRANT_TYPE, getAuthSignature()).map(new Function<AuthResult, String>() {
        @Override
        public String apply(AuthResult authResult) throws Exception {
          mAccessToken = authResult.access_token;
          return authResult.access_token;
        }
      });
    }
  }

  protected String getAuthHeader(String token){
    return "Bearer " + token;
  }
}
