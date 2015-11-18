package me.panavtec.cleancontacts.data.repository.contacts.datasources.api;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.responses.ApiContactsResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ContactsApiService {

  static final String API_VERSION = "/0.4.1/";

  @GET(API_VERSION)
  Call<ApiContactsResponse> obtainUser();

  @GET(API_VERSION)
  Call<ApiContactsResponse> obtainUsers(@Query("results") int users);
}
