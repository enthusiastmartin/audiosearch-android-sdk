package studio.stressedout.audiosearch.model;

import java.util.ArrayList;

/**
 * Created by martin on 8/8/17.
 */

public class AudioSearchShow {

  public int id;

  public String title;

  public String network;

  public String status;

  public ArrayList<String> categories;

  public String description;

  public int number_of_episodes;

  public ArrayList<Integer> episode_ids;

  private ArrayList<ImageFileObject> image_files;

  public String getShowThumb(){
    if ( image_files != null && image_files.size() > 0 && image_files.get(0) != null) {
      return image_files.get(0).url.thumb;
    }
    return null;
  }

  private class ImageFileObject {
    public FileDetail url;
  }

  private class FileDetail{
    String full;
    String thumb;
  }
}
