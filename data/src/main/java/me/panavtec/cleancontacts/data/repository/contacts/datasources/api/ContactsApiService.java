package me.panavtec.cleancontacts.data.repository.contacts.datasources.api;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.responses.ApiContactsResponse;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ContactsApiService {

  static final String API_VERSION = "/0.4.1";

  @GET(API_VERSION)
  public ApiContactsResponse obtainUser();

  @GET(API_VERSION)
  public ApiContactsResponse obtainUsers(@Query("results") int users);
}
