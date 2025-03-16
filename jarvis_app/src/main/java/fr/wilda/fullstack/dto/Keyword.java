package fr.wilda.fullstack.dto;

import java.io.Serializable;

public class Keyword implements Serializable {
  private String name;

  public Keyword() {
}

  public Keyword(String name) {
      this.name = name;
  }

  public String getName() {
      return name;
  }

  public void setName(String name) {
      this.name = name;
  }
  @Override
  public String toString() {
    return name;
  }
}
