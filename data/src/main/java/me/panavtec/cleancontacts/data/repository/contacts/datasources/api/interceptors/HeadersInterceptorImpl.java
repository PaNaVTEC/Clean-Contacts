package me.panavtec.cleancontacts.data.repository.contacts.datasources.api.interceptors;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

public class HeadersInterceptorImpl implements Interceptor {

  private final String userAgent;

  public HeadersInterceptorImpl(String userAgent) {
    this.userAgent = userAgent;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request original = chain.request();

    HttpUrl url = chain.request().httpUrl()
        .newBuilder()
        .addQueryParameter("seed", "panavtec")
        .build();

    // Customize the request
    Request request = original.newBuilder()
        .url(url)
        .header("User-Agent", userAgent)
        .method(original.method(), original.body())
        .build();

    Response response = chain.proceed(request);

    // Customize or return the response
    return response;
  }
}
