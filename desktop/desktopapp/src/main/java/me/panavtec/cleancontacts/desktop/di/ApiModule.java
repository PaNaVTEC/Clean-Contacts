package me.panavtec.cleancontacts.desktop.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.data.api.ApiNetworkError;
import me.panavtec.cleancontacts.data.api.ContactsApiService;
import me.panavtec.cleancontacts.data.api.ContactsNetworkDataSourceImp;
import me.panavtec.cleancontacts.desktop.data.RetrofitLog;
import me.panavtec.cleancontacts.desktop.data.UserAgent;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsNetworkDataSource;
import retrofit.Endpoint;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.converter.GsonConverter;

@Module(
    complete = false,
    library = true)
public class ApiModule {

  @Provides @Singleton ContactsNetworkDataSource provideContactsNetworkDataSource(
      ContactsApiService apiService) {
    return new ContactsNetworkDataSourceImp(apiService);
  }

  @Provides @Singleton ContactsApiService provideApiService(@UserAgent final String userAgent,
      Endpoint endPoint, @RetrofitLog boolean logTraces) {
    return new RestAdapter.Builder().setEndpoint(endPoint)
        .setLogLevel(logTraces ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
        .setConverter(new GsonConverter(newGson()))
        .setRequestInterceptor(new RequestInterceptor() {
          @Override public void intercept(RequestFacade requestFacade) {
            requestFacade.addQueryParam("seed", "panavtec");
            requestFacade.addHeader("User-Agent", userAgent);
          }
        })
        .setErrorHandler(new ErrorHandler() {
          @Override public Throwable handleError(RetrofitError cause) {
            ApiNetworkError networkError = new ApiNetworkError();
            networkError.setStackTrace(cause.getStackTrace());
            return networkError;
          }
        })
        .build()
        .create(ContactsApiService.class);
  }

  public static Gson newGson() {
    return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
  }
}
