package me.panavtec.cleancontacts.data;

import android.content.res.AssetManager;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import okio.BufferedSource;
import okio.Okio;

class AssetResponseBody extends ResponseBody {

  private AssetManager assets;
  private String fileName;

  public AssetResponseBody(AssetManager assets, String fileName) {
    this.assets = assets;
    this.fileName = fileName;
  }

  @Override public MediaType contentType() {
    return MediaType.parse("application/json");
  }

  @Override public long contentLength() throws IOException {
    return assets.openFd(fileName).getLength();
  }

  @Override public BufferedSource source() throws IOException {
    return Okio.buffer(Okio.source(assets.open(fileName)));
  }
}
