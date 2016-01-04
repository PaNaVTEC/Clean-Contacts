package me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities;

import com.google.gson.annotations.Expose;

public class ApiPicture {

  @Expose public String large;
  @Expose public String medium;
  @Expose public String thumbnail;

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
