package fr.wilda.fullstack.dto;

import java.io.Serializable;
import java.util.List;

public class Conference implements Serializable {
  private String title;
  private String description;
  private Track track;
  private List<Speaker> speakers;
  private List<Keyword> keywords;
  private List<String> timeSlots;

  public Conference(String title, String description, Track track, List<Speaker> speakers, List<Keyword> keywords,
      List<String> timeSlots) {
    this.title = title;
    this.description = description;
    this.track = track;
    this.speakers = speakers;
    this.keywords = keywords;
    this.timeSlots = timeSlots;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Track getTrack() {
    return track;
  }

  public void setTrack(Track track) {
    this.track = track;
  }

  public List<Speaker> getSpeakers() {
    return speakers;
  }

  public void setSpeakers(List<Speaker> speakers) {
    this.speakers = speakers;
  }

  public List<Keyword> getKeywords() {
    return keywords;
  }

  public void setKeywords(List<Keyword> keywords) {
    this.keywords = keywords;
  }

  public List<String> getTimeSlots() {
    return timeSlots;
  }

  public void setTimeSlots(List<String> timeSlots) {
    this.timeSlots = timeSlots;
  }

  @Override
  public String toString() {
    return """
        Ci-dessous un talk du prochain Devoxx France 2025:
        Titre du talk : %s
        Description du talk :
        %s
        Le speaker est : %s
        Type de session :
        %s
        Mots clefs :
        %s
        """.formatted(title, description, speakers, track.getName(), keywords);
  }
}
