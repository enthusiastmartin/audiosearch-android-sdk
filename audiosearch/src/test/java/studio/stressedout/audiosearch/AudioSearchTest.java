package studio.stressedout.audiosearch;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import io.reactivex.observers.TestObserver;
import studio.stressedout.audiosearch.model.SearchResult;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by martin on 8/6/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class AudioSearchTest {

  //TODO: remove this API KEY
  private static final String MY_AUDIOSEARCH_APP_ID = "";
  private static final String MY_AUDIOSEARCH_SECRET = "";

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void setup(){
  }

  @Test
  public void testBuilderWithNoCredentials() throws IOException {
    expectedException.expect(IOException.class);
    AudioSearch audioSearch = AudioSearch.Builder.create().build();
  }

  @Test
  public void testeBuilderWithCredentials()throws IOException{
    AudioSearch audioSearch = AudioSearch.Builder.create().applicationID(MY_AUDIOSEARCH_APP_ID).secret(MY_AUDIOSEARCH_SECRET).build();
    assertThat(audioSearch, is(notNullValue()));
  }

  @Test
  public void testSearch() throws IOException {

    AudioSearch audioSearch = AudioSearch.Builder.create().applicationID(MY_AUDIOSEARCH_APP_ID).secret(MY_AUDIOSEARCH_SECRET).build();

    assertThat(audioSearch, is(notNullValue()));

    TestObserver<List<SearchResult>> testObserver = audioSearch.search("security").test();

    testObserver.awaitTerminalEvent();

  }
}
