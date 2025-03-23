package fr.wilda.fullstack.dto;

import java.io.Serializable;

public class Speaker implements Serializable {

  private int id;
  private String firstName;
  private String lastName;
  private String fullName;
  private String bio;
  private String anonymizedBio;
  private String company;
  private String imageUrl;

  public Speaker(int id, String firstName, String lastName, String fullName, String bio, String anonymizedBio,
      String company, String imageUrl) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.fullName = fullName;
    this.bio = bio;
    this.anonymizedBio = anonymizedBio;
    this.company = company;
    this.imageUrl = imageUrl;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getAnonymizedBio() {
    return anonymizedBio;
  }

  public void setAnonymizedBio(String anonymizedBio) {
    this.anonymizedBio = anonymizedBio;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @Override
  public String toString() {
    return fullName;
  }

}
