package studio.stressedout.audiosearch.model;

/**
 * Created by martin on 5/24/17.
 */

public class SearchResult {
  public int id;

  public String title;

  public String description;

  private ImageUrls image_urls;

  private static class ImageUrls {
    String thumb;
    String full;
  }

  public String getShowThumb(){
    if ( image_urls != null ){
      return image_urls.thumb;
    }
    return null;
  }

  public String getShowFullImage(){
    if ( image_urls != null ){
      return image_urls.full;
    }
    return null;
  }
}
