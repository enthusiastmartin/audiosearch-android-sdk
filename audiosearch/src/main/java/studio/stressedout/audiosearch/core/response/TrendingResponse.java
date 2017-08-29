package studio.stressedout.audiosearch.core.response;

import java.util.ArrayList;

import studio.stressedout.audiosearch.model.AudioSearchEpisode;

/**
 * Created by martin on 8/29/17.
 */

public class TrendingResponse {

  public String trend;

  public ArrayList<AudioSearchEpisode> related_episodes;
}
