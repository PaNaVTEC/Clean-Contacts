package me.panavtec.cleancontacts.data.api.responses;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class ApiContactsResponse {

  @Expose private List<ApiContactResult> results = new ArrayList<ApiContactResult>();

  public List<ApiContactResult> getResults() {
    return results;
  }

  public void setResults(List<ApiContactResult> results) {
    this.results = results;
  }
}