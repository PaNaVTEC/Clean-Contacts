package me.panavtec.cleancontacts.di;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import dagger.Module;
import dagger.Provides;
import java.util.List;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.data.Endpoint;
import me.panavtec.cleancontacts.data.HeadersInterceptor;
import me.panavtec.cleancontacts.data.RetrofitLog;
import me.panavtec.cleancontacts.data.UserAgent;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.ContactsApiService;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.ContactsNetworkDataSourceImp;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.interceptors.HeadersInterceptorImpl;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsNetworkDataSource;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@Module(
    complete = false,
    library = true)
public class ApiModule {

  @Provides @Singleton ContactsNetworkDataSource provideContactsNetworkDataSource(
      ContactsApiService apiService) {
    return new ContactsNetworkDataSourceImp(apiService);
  }

  @Provides @Singleton ContactsApiService provideApiService(
      @Endpoint String enpoint,
      GsonConverterFactory gsonConverterFactory,
      OkHttpClient okClient) {

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(enpoint)
        .client(okClient)
        .addConverterFactory(gsonConverterFactory).build();

    ContactsApiService service = retrofit.create(ContactsApiService.class);
    return service;

  }

  @Provides @Singleton HttpLoggingInterceptor provideLoggingInterceptor() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return interceptor;
  }

  @Provides @Singleton @HeadersInterceptor Interceptor provideHeadersInterceptor(
      @UserAgent String userAgent) {
    return new HeadersInterceptorImpl(userAgent);
  }

  @Provides @Singleton OkHttpClient provideOkClient(@RetrofitLog boolean retrofitLog,
      @HeadersInterceptor Interceptor headersInterceptor,
      HttpLoggingInterceptor loggingInterceptor) {
    OkHttpClient okHttpClient = new OkHttpClient();
    List<Interceptor> interceptors = okHttpClient.interceptors();
    interceptors.add(headersInterceptor);

    if (retrofitLog){
      interceptors.add(loggingInterceptor);
    }

    return okHttpClient;
  }

  @Provides @Singleton GsonConverterFactory provideGsonConverterFactory() {
    return GsonConverterFactory.create();
  }

}
