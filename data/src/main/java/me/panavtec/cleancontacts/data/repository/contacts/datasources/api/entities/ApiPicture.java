package me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities;

import com.google.gson.annotations.Expose;
import com.mobandme.android.transformer.compiler.Mappable;
import com.mobandme.android.transformer.compiler.Mapped;
import me.panavtec.cleancontacts.domain.model.Picture;

@Mappable(with = Picture.class)
public class ApiPicture {

  @Expose @Mapped public String large;
  @Expose @Mapped public String medium;
  @Expose @Mapped public String thumbnail;

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
