package me.panavtec.cleancontacts.data.repository.contacts.datasources.api.responses;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class ApiContactsResponse {

  @Expose private List<ApiContactResult> results = new ArrayList<>();

  public List<ApiContactResult> getResults() {
    return results;
  }

  public void setResults(List<ApiContactResult> results) {
    this.results = results;
  }
}