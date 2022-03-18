package org.phloxes.launcher;

import android.os.Bundle;
import android.view.TextureView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.phloxes.R;
import org.phloxes.utility.assets.vector.Parser;

import org.phloxes.utility.assets.vector.Vector;

import static org.phloxes.utility.activity.ActivityShifter.shift_activity;
import static org.phloxes.utility.notification.Notification.show_notification;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      try{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_activity);

        show_notification(getApplicationContext(),
                         "preparing App",
                         0);


          Vector vector = Parser.FromXmlToJava(this,1);
          TextView view = findViewById(R.id.tv);
//          view.setText(vector.getPath_list().toString());

        //shift_activity(this, Prelims.class);
      }
      catch (Exception e){
          show_notification(getApplicationContext(),
                           "Failed to Launch or Maintain App ->" +
                              " at Launcher : "+ "\n "+e.getMessage(),
                           1);
      }
    }
}
