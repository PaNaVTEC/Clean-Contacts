package me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities;

import com.google.gson.annotations.Expose;
import com.mobandme.android.transformer.compiler.Mappable;
import com.mobandme.android.transformer.compiler.Mapped;
import me.panavtec.cleancontacts.domain.entities.Name;

@Mappable(with = Name.class)
public class ApiName {

  @Expose @Mapped public String title;
  @Expose @Mapped public String first;
  @Expose @Mapped public String last;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFirst() {
    return first;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public String getLast() {
    return last;
  }

  public void setLast(String last) {
    this.last = last;
  }
}
