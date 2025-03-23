package fr.wilda.fullstack.dto;

import java.io.Serializable;

public class Track implements Serializable {

  private int id;
  private String name;
  private String description;
  private String imageURL;

  public Track(int id, String name, String description, String imageURL) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.imageURL = imageURL;
  }

  public int getId() {
      return id;
  }

  public void setId(int id) {
      this.id = id;
  }

  public String getName() {
      return name;
  }

  public void setName(String name) {
      this.name = name;
  }

  public String getDescription() {
      return description;
  }

  public void setDescription(String description) {
      this.description = description;
  }

  public String getImageURL() {
      return imageURL;
  }

  public void setImageURL(String imageURL) {
      this.imageURL = imageURL;
  }
}
