package me.panavtec.cleancontacts.data;

import android.content.Context;
import com.google.gson.reflect.TypeToken;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.ContactsApiService;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.responses.ApiContactsResponse;
import retrofit.Call;
import retrofit.http.Query;

public class MockContactsApiService implements ContactsApiService {

  private final Context applicationContext;

  public MockContactsApiService(Context applicationContext) {
    this.applicationContext = applicationContext.getApplicationContext();
  }

  @Override public Call<ApiContactsResponse> obtainUsers(@Query("results") int users) {
    return new LocalFileCall<>(new TypeToken<ApiContactsResponse>() {
    }.getType(), applicationContext.getAssets(), "list_of_3_contacts.json");
  }
}
