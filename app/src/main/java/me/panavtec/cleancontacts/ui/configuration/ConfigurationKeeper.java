package me.panavtec.cleancontacts.ui.configuration;

import android.os.Handler;
import android.os.Message;

public class ConfigurationKeeper extends Handler {

  private static final int MSG_REALLY_DESTROYED = 1;
  
  private ConfigurationKeeperListener configurationKeeperListener;
  private boolean mStopped;
  private boolean mReallyStopped;
  private final Runnable sendEmptyMessage = new Runnable() {
    @Override public void run() {
      sendEmptyMessage(MSG_REALLY_DESTROYED);
    }
  };

  public ConfigurationKeeper(ConfigurationKeeperListener configurationKeeperListener) {
    this.configurationKeeperListener = configurationKeeperListener;
  }

  @Override public void handleMessage(Message msg) {
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
    post(sendEmptyMessage);
  }

  void doReallyDestroy() {
    if (!mReallyStopped) {
      mReallyStopped = true;
      removeMessages(MSG_REALLY_DESTROYED);
      configurationKeeperListener.onDestroyConfigurationKeeper();
    }
  }

}
