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
    return chain.proceed(composeRequest(chain));
  }

  private Request composeRequest(Chain chain) {
    HttpUrl url = composeUrl(chain);
    Request original = chain.request();
    return original.newBuilder()
        .url(url)
        .header("User-Agent", userAgent)
        .method(original.method(), original.body())
        .build();
  }

  private HttpUrl composeUrl(Chain chain) {
    return chain.request().httpUrl()
          .newBuilder()
          .addQueryParameter("seed", "panavtec")
          .build();
  }
}
