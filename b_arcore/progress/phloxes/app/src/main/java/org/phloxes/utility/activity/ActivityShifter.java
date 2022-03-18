package org.phloxes.utility.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ActivityShifter {
    public static void shift_activity(Activity activity, Class destination ){
        Intent in = new Intent(activity, destination);
        activity.startActivity(in);
        activity.finish();
    }
}
