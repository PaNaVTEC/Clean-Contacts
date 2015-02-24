package me.panavtec.cleancontacts.data.api;

import me.panavtec.cleancontacts.data.api.responses.ApiContactsResponse;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ContactsApiService {

    static final String API_VERSION = "/0.4.1";

    @GET(API_VERSION)
    public ApiContactsResponse obtainUser();

    @GET(API_VERSION)
    public ApiContactsResponse obtainUsers(@Query("results") int users);

}
