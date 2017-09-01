package studio.stressedout.audiosearch.model;

import java.util.ArrayList;

/**
 * Created by martin on 8/8/17.
 */

public class AudioSearchEpisode {

  public int id;
  public String title;

  public String date_created;
  public String date_added;

  public int show_id;
  public String show_title;

  public int duration;

  public ArrayList<AudioSearchCategory> categories;

  public ArrayList<EpisodeAudioFile> audio_files;

  public EpisodeImageUrls image_urls;

  public class EpisodeAudioFile{
    public int id;
    public String mp3;
    public String audiosearch_mp3;
    public String duration;
  }

  public class EpisodeImageUrls{
    public String full;
    public String thumb;
  }
}
