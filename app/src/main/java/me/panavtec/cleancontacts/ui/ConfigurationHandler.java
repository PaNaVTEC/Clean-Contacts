package me.panavtec.cleancontacts.ui;

import android.os.Handler;
import android.os.Message;
import hugo.weaving.DebugLog;

public class ConfigurationHandler {

  private static final int MSG_REALLY_DESTROYED = 1;
  private ConfigurationHandlerListener configurationHandlerListener;

  static interface ConfigurationHandlerListener {
    void destroyThemAll();
  }

  public ConfigurationHandler(ConfigurationHandlerListener configurationHandlerListener) {
    this.configurationHandlerListener = configurationHandlerListener;
  }

  boolean mStopped;
  boolean mReallyStopped;

  Handler mHandler = new Handler() {
    @DebugLog @Override public void handleMessage(Message msg) {
      switch (msg.what) {
        case MSG_REALLY_DESTROYED:
          if (mStopped) {
            doReallyDestroy();
            mHandler = null;
          }
          break;
      }
    }
  };

  void doReallyDestroy() {
    if (!mReallyStopped) {
      mReallyStopped = true;
      mHandler.removeMessages(MSG_REALLY_DESTROYED);
      configurationHandlerListener.destroyThemAll();
    }
  }

  public void destroy() {
    mStopped = true;
    mHandler.sendEmptyMessage(MSG_REALLY_DESTROYED);
  }

  public void create() {
    mStopped = false;
    mReallyStopped = false;
    mHandler.removeMessages(MSG_REALLY_DESTROYED);
  }

}
