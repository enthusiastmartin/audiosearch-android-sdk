package studio.stressedout.audiosearch.model;

import java.util.ArrayList;

/**
 * Created by martin on 8/8/17.
 */

public class AudioSearchShow {

  public int id;

  public String title;

  public String description;

  public Network network;

  public ArrayList<AudioSearchCategory> categories;

  private ImageUrls  image_urls;

  public String getShowThumb(){

    if ( image_urls != null ){
      return image_urls.thumb;
    }

    return null;
  }

  public String getShowFullPicture(){
    if ( image_urls != null ){
      return image_urls.full;
    }

    return null;
  }

  private static class ImageUrls{
    String full;
    String thumb;
  }

  public static class Network {
    public int id;
    public String name;
  }
}
