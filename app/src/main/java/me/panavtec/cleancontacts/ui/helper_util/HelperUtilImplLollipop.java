package me.panavtec.cleancontacts.ui.helper_util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;

import me.panavtec.cleancontacts.R;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class HelperUtilImplLollipop extends HelperUtilImplBase {

    public HelperUtilImplLollipop(Context context){
        super(context);
    }

    @Override
    public void setElevation(View view, float elevation){
        view.setElevation(elevation);
    }

    @Override
    public void setElevation(View view) {
        view.setElevation(view.getResources().getDimensionPixelSize(R.dimen.default_elevation));
    }

}
