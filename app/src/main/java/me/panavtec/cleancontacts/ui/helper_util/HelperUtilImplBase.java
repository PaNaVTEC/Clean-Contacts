package me.panavtec.cleancontacts.ui.helper_util;

import android.content.Context;
import android.view.View;

public class HelperUtilImplBase implements HelperUtil {

    protected Context context;

    public HelperUtilImplBase(Context context){
        this.context = context;
    }

    @Override
    public void setElevation(View view, float elevation) {
        // Do nothing pre-L
    }

    @Override
    public void setElevation(View view) {
        // Do nothing pre-L
    }

}
