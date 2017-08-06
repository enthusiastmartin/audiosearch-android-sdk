package studio.stressedout.audiosearch;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by martin on 8/6/17.
 */

//@RunWith(MockitoJUnitRunner.class)
public class AudioSearchTest {

  private static final String MY_AUDIOSEARCH_APP_ID = "3c2ac4d6cf93e4dcfc0426e8bf2b5d2c01c0b2b6e9c0741dea45b06b5fd81eca";
  private static final String MY_AUDIOSEARCH_SECRET = "363467bb489e509f735c6bb51ec74ae0b73cc9b1ad64a5d9a793e2f94415324c";

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

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
}
