package me.panavtec.cleancontacts.ui.helper_util;

import android.content.Context;
import android.os.Build;
import android.view.View;

public class HelperUtilImpl implements HelperUtil {

    private final HelperUtil helperUtilInterface;

    public HelperUtilImpl(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            helperUtilInterface =  new HelperUtilImplLollipop(context);
        } else {
            helperUtilInterface = new HelperUtilImplBase(context);
        }
    }

    @Override
    public void setElevation(View view, float elevation){
        helperUtilInterface.setElevation(view, elevation);
    }

    @Override
    public void setElevation(View view){
        helperUtilInterface.setElevation(view);
    }


}
