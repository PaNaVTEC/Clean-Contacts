package me.panavtec.cleancontacts.data.repository.contacts.datasources.api.interceptors;

import android.util.Log;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 18/11/15.
 */
public class LoggingInterceptorImpl implements Interceptor {
  private static final String TAG = "Retrofit Logging";

  @Override public Response intercept(Interceptor.Chain chain) throws IOException {
    Request request = chain.request();

    long t1 = System.nanoTime();
    Log.i(TAG, String.format("Sending request %s on %s %n%s", request.url(), chain.connection(),
        request.headers()));

    if (request.body() != null) {
      Log.i(TAG, "Request body: " + request.body());
    }

    Response response = chain.proceed(request);

    long t2 = System.nanoTime();
    Log.i(TAG, String.format("Received response for %s in %.1fms with Http response <%s %s> and headers: %n%s ",
        response.request().url(), (t2 - t1) / 1e6d, response.code(), response.message(), response.headers()));

    String responseBody = response.body().string();
    if (responseBody != null) {
      Log.i(TAG, "Received response body: " + responseBody);
    }

    // Is needed to rebuild response body because response.body().string() consumes the data

    return response.newBuilder()
        .body(ResponseBody.create(response.body().contentType(), responseBody))
        .build();
  }
}
