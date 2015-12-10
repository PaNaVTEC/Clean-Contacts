package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities;

import com.j256.ormlite.field.DatabaseField;
import com.mobandme.android.transformer.compiler.Mappable;
import com.mobandme.android.transformer.compiler.Mapped;
import me.panavtec.cleancontacts.domain.model.Picture;

@Mappable(with = Picture.class)
public class BddPicture {

  @DatabaseField(generatedId = true, columnName = "id") private int id;
  @DatabaseField @Mapped public String large;
  @DatabaseField @Mapped public String medium;
  @DatabaseField @Mapped public String thumbnail;

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
