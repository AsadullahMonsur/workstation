package org.phloxes.viewerox;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import org.phloxes.R;
import org.phloxes.prelims.Prelims;

import static org.phloxes.utility.activity.ActivityShifter.shift_activity;
import static org.phloxes.utility.notification.Notification.show_notification;

public class PhloxViewer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.viewerox_activity);

            show_notification(getApplicationContext(),
                             "Phlox 3D Viewer Started",
                             0);


        }
        catch (Exception e){
            show_notification(getApplicationContext(),
                    "Phlox 3D Viewer Error ->" +
                            " at PhloxViewer: "+ "\n "+e.getMessage(),
                    1);
        }
    }
}
