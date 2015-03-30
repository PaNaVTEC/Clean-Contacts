package me.panavtec.cleancontacts.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.data.RetrofitLog;
import me.panavtec.cleancontacts.data.UserAgent;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.ApiNetworkError;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.ContactsApiService;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.ContactsNetworkDataSourceImp;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsNetworkDataSource;
import retrofit.Endpoint;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

@Module(
    complete = false,
    library = true)
public class ApiModule {

  @Provides @Singleton ContactsNetworkDataSource provideContactsNetworkDataSource(
      ContactsApiService apiService) {
    return new ContactsNetworkDataSourceImp(apiService);
  }

  @Provides @Singleton ContactsApiService provideApiService(GsonConverter gsonConverter,
      ErrorHandler errorHandler, RequestInterceptor requestInterceptor, Endpoint endPoint,
      RestAdapter.LogLevel logLevel, OkClient okClient) {
    return new RestAdapter.Builder().setEndpoint(endPoint)
        .setLogLevel(logLevel)
        .setConverter(gsonConverter)
        .setClient(okClient)
        .setRequestInterceptor(requestInterceptor)
        .setErrorHandler(errorHandler)
        .build()
        .create(ContactsApiService.class);
  }

  @Provides @Singleton RequestInterceptor provideRequestInterceptor(
      @UserAgent final String userAgent) {
    return new RequestInterceptor() {
      @Override public void intercept(RequestFacade requestFacade) {
        requestFacade.addQueryParam("seed", "panavtec");
        requestFacade.addHeader("User-Agent", userAgent);
      }
    };
  }

  @Provides @Singleton ErrorHandler provideContactsApiErrorHandler() {
    return new ErrorHandler() {
      @Override public Throwable handleError(RetrofitError cause) {
        ApiNetworkError networkError = new ApiNetworkError();
        networkError.setStackTrace(cause.getStackTrace());
        return networkError;
      }
    };
  }

  @Provides @Singleton RestAdapter.LogLevel provideLogLevel(@RetrofitLog boolean logTraces) {
    return logTraces ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE;
  }

  @Provides @Singleton OkClient provideOkClient() {
    return new OkClient(new OkHttpClient());
  }

  @Provides @Singleton GsonConverter provideGsonConverter(Gson gson) {
    return new GsonConverter(gson);
  }

  @Provides @Singleton Gson provideGsonParser() {
    return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
  }
}
