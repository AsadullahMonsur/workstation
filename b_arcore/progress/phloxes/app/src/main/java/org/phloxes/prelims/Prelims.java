package org.phloxes.prelims;

import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.google.android.material.imageview.ShapeableImageView;
import org.phloxes.R;
import org.phloxes.viewerox.PhloxViewer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

import static org.phloxes.utility.activity.ActivityShifter.shift_activity;
import static org.phloxes.utility.notification.Notification.show_notification;

public class Prelims extends AppCompatActivity {
    private ShapeableImageView img_view;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.prelims_activity);

            show_notification(getApplicationContext(),
                             "loading resources",
                             0);

        img_view = findViewById(R.id.color_img);
        Drawable dbl = img_view.getDrawable();

        img_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

         //   shift_activity(this, PhloxViewer.class);
        }
        catch (Exception e){
            show_notification(getApplicationContext(),
                    "Failed to load resources ->" +
                            " at Prelims : "+ "\n "+e.getMessage(),
                    1);
        }
    }
}
