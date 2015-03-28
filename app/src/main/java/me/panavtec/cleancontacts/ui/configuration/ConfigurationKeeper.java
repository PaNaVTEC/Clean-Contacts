package me.panavtec.cleancontacts.ui.configuration;

import android.os.Handler;
import android.os.Message;
import hugo.weaving.DebugLog;

public class ConfigurationKeeper extends Handler {

  private static final int MSG_REALLY_DESTROYED = 1;
  
  private DestroyListener destroyListener;
  private boolean mStopped;
  private boolean mReallyStopped;

  public ConfigurationKeeper(DestroyListener destroyListener) {
    this.destroyListener = destroyListener;
  }

  @DebugLog @Override public void handleMessage(Message msg) {
    switch (msg.what) {
      case MSG_REALLY_DESTROYED:
        if (mStopped) {
          doReallyDestroy();
        }
        break;
    }
  }

  public void create() {
    mStopped = false;
    mReallyStopped = false;
    removeMessages(MSG_REALLY_DESTROYED);
  }

  public void destroy() {
    mStopped = true;
    sendEmptyMessage(MSG_REALLY_DESTROYED);
  }

  void doReallyDestroy() {
    if (!mReallyStopped) {
      mReallyStopped = true;
      removeMessages(MSG_REALLY_DESTROYED);
      destroyListener.destroyThemAll();
    }
  }

}
