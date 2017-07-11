package net.shreygupta.doctoronthego;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by archit on 12/7/17.
 */

public class CustomAdaptorDoctor extends ArrayAdapter {

    public CustomAdaptorDoctor(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }
}
