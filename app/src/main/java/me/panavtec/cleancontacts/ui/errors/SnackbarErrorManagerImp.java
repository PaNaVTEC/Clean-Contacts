package me.panavtec.cleancontacts.ui.errors;

import android.app.Activity;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

public class SnackbarErrorManagerImp implements ErrorManager {

    private Activity activity;

    public SnackbarErrorManagerImp(Activity activity) {
        this.activity = activity;
    }

    @Override public void showError(String error) {
        SnackbarManager.show(Snackbar.with(activity).text(error));
    }

}
