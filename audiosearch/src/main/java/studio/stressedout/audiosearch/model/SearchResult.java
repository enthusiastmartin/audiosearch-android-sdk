package studio.stressedout.audiosearch.model;

import java.util.ArrayList;

/**
 * Created by martin on 5/24/17.
 */

public class SearchResult {
  public int id;

  public String title;

  public String description;

  public ArrayList<ImageFileObject> image_files;

  public String getShowThumb(){
    return image_files.get(0).file.thumb.url;
  }

  private class ImageFileObject {
    public int id;
    public FileDetail file;
  }

  private class FileDetail {
    public String url;
    public ThumbImageFile thumb;
  }

  private class ThumbImageFile{
    public String url;
  }
}
