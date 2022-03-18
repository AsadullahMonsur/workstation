package org.phloxes.utility.assets.vector;

import android.content.Context;


import android.util.Log;
import org.phloxes.R;
import org.phloxes.utility.notification.Notification;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URL;
import java.util.logging.Logger;

public class Parser {
    public static Vector FromXmlToJava(Context context, int resource_id){
        try {
          InputStream stream = context.getResources().openRawResource(R.raw.color_square);

          JAXBContext jaxbContext = JAXBContext.newInstance(Vector.class);
          Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

          Vector vector = (Vector) unmarshaller.unmarshal(stream);

         // Notification.show_notification(context,vector.getName(),1);
          return vector;
        }
        catch (Exception e) {
            Notification.show_notification(context,
                    "Vector Parsing Error: To Java :-> \n"+ e.getMessage(),
                    1);
            Log.e("Error",e.getMessage(),e.getCause());
        }
        return null;
    }

    public static void FromJavaToXml(){

    }
}
