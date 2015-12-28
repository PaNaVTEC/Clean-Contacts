package me.panavtec.cleancontacts.data;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import java.lang.reflect.Type;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Response;

public class LocalFileCall<T> implements Call<T> {
  private Type classType;
  private AssetManager assets;
  private String fileName;

  public LocalFileCall(Type classType, AssetManager assets, String fileName) {
    this.classType = classType;
    this.assets = assets;
    this.fileName = fileName;
  }

  @Override public Response<T> execute() throws IOException {
    Converter<ResponseBody, T> converter = makeConverter();
    return Response.success(converter.convert(new AssetResponseBody(assets, fileName)));
  }

  @NonNull private Converter<ResponseBody, T> makeConverter() {
    return (Converter<ResponseBody, T>) GsonConverterFactory.create()
        .fromResponseBody(classType, null);
  }

  @Override public void enqueue(Callback<T> callback) {
  }

  @Override public void cancel() {
  }

  @Override public Call<T> clone() {
    return null;
  }
}
