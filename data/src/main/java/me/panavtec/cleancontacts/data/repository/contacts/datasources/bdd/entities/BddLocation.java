package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities;

import com.j256.ormlite.field.DatabaseField;
import com.mobandme.android.transformer.compiler.Mappable;
import com.mobandme.android.transformer.compiler.Mapped;
import me.panavtec.cleancontacts.domain.model.Location;

@Mappable(with = Location.class)
public class BddLocation {

  @DatabaseField(generatedId = true, columnName = "id") private int id;
  @DatabaseField @Mapped public String street;
  @DatabaseField @Mapped public String city;
  @DatabaseField @Mapped public String state;
  @DatabaseField @Mapped public String zip;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }
}
