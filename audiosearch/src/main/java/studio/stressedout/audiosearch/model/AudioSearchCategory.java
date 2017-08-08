package studio.stressedout.audiosearch.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by martin on 8/8/17.
 */

public class AudioSearchCategory {

  public int id;
  public String name;

  @SerializedName("parent_id")
  public int parentId;
  @SerializedName("parent_name")
  public String parentName;
}
