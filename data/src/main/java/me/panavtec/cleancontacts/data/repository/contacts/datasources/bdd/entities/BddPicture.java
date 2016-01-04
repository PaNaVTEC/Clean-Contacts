package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities;

import com.j256.ormlite.field.DatabaseField;

public class BddPicture {

  @DatabaseField(generatedId = true, columnName = "id") private int id;
  @DatabaseField public String large;
  @DatabaseField public String medium;
  @DatabaseField public String thumbnail;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLarge() {
    return large;
  }

  public void setLarge(String large) {
    this.large = large;
  }

  public String getMedium() {
    return medium;
  }

  public void setMedium(String medium) {
    this.medium = medium;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }
}
